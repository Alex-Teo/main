package command;

import booking.Booking;
import booking.BookingList;
import exception.DukeException;
import room.RoomList;
import storage.Constants;
import storage.Storage;
import ui.Ui;
import user.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApproveCommand extends Command {

    private String name;
    private String[] splitC;
    private String roomcode;
    private Date dateTimeStart;
    private String datetimeStartString;

    //@@author Alex-Teo
    /**
     * Approve a request.
     * format is approve name roomcode date time
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     * @throws IOException when entry is incorrect
     */
    public ApproveCommand(String input, String[] splitStr) throws DukeException, IOException {
        if (splitStr.length <= 1) {
            throw new DukeException("☹ OOPS!!! Please create the booking you want to edit with the following format: "
                    + "name, roomcode, start date and time");
        }
        splitC = input.split(" ", 5);
        name = splitC[1];
        roomcode = splitC[2];
    }

    @Override
    public void execute(RoomList roomList, BookingList bookingList, Ui ui, Storage bookingstorage,
                        Storage roomstorage, User user) throws DukeException, IOException, ParseException {
        if (!roomList.checkRoom(roomcode)) {
            throw new DukeException(Constants.UNHAPPY + "OOPS!!! This room doesn't exist!");
        }
        datetimeStartString = splitC[3] + " " + splitC[4];
        DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.dateTimeStart = sdf.parse(datetimeStartString);
        for (Booking i: bookingList) {
            if ((i.getVenue() == roomcode) && (i.getDateTimeStart() == dateTimeStart) && (i.getName() == name)) {
                i.setStatus("A");
                ui.addToOutput("This request has been approved!");
                bookingstorage.saveToFile(bookingList);
                break;
            }
        }
    }
}
