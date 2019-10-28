import booking.Booking;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookingTest2 {
    @Test
    void testAddBooking() throws ParseException {
        String user = "Barry";
        String room = "rooBm4";
        String description = " eat chicken";
        String dateTimeStart = "21/12/2021 1100";
        String timeEnd = "1500";
        //User temp = new User("dummy");
        Booking newBooking = new Booking(user, room, description, dateTimeStart, timeEnd);
        assertEquals(newBooking.toString(), "Barry room4 Tue Dec 21 11:00:00 SGT 2021 to 1500 P");
    }
}
