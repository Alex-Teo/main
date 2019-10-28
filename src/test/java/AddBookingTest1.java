import booking.Booking;
import org.junit.jupiter.api.Test;
import user.User;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookingTest1 {
    @Test
    void testAddBooking() throws ParseException {
        String user = "Bob";
        String room = "room4";
        String description = " study";
        String dateTimeStart = "22/12/2019 1100";
        String timeEnd = "1200";
        //User temp = new User("dummy");
        Booking newBooking = new Booking(user, room, description, dateTimeStart, timeEnd);
        assertEquals(newBooking.toString(), "Bob room4 Sun Dec 22 11:00:00 SGT 2019 to 1200 P");
    }
}
