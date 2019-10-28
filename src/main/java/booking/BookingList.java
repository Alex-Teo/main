package booking;

import exception.DukeException;
import storage.BookingConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class BookingList extends ArrayList<Booking> {

    /**
     * Create booking list from text file.
     * @param loader strings from text file containing booking info
     * @throws DukeException if file format incorrect
     */
    public BookingList(ArrayList<String> loader) throws DukeException {
        for (String line : loader) {
            String[] splitStr = line.split(" \\| ", 6);
            this.add(new Booking(splitStr[BookingConstants.USERNAME],
                    splitStr[BookingConstants.VENUE], splitStr[BookingConstants.DESCRIPTION],
                    splitStr[BookingConstants.TIMESTART], splitStr[BookingConstants.TIMEEND],
                    splitStr[BookingConstants.STATUS]));
        }
    }


    public BookingList() {
        super();
    }

    /**
     * To check if a request with the same place and time slot has already been made.
     * @param bookinglist the list of requests
     * @param roomcode the room in question
     * @param timeStart the starting time
     * @param timeEnd the ending time
     * @return if there is already a request with that venue and time slot
     */
    public static boolean checkBooking(BookingList bookinglist, String roomcode, String timeStart, String timeEnd) throws ParseException {
        boolean found = false;
        DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("HHmm");
        Date startTime = sdf.parse(timeStart);
        LocalTime endTime = LocalTime.parse(timeEnd, formatterEnd);
        for (int i = 0; i < bookinglist.size(); i++) {
            if (bookinglist.get(i).venue == roomcode) {
                if ((bookinglist.get(i).dateTimeStart.before(startTime)
                        || bookinglist.get(i).dateTimeStart.equals(startTime))
                        && ((bookinglist.get(i).timeEnd.isAfter(endTime))
                        && (bookinglist.get(i).timeEnd.isBefore(endTime)))) {
                    found = true;
                }
            }
        }
        return found;
    }
}