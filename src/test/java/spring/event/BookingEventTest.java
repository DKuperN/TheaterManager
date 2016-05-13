package spring.event;

import models.TicketModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.BookingServiceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class BookingEventTest {
    @Autowired
    private BookingServiceImpl bookingService;

    //Test data
    @Value("${test.eventName}")
    private String eventName;
    @Value("${test.eventDate}")
    private String seventDate;

    private String userName = "test4";
    private int seatPlaceNumber = 28;

    @Test
    public void bookingTicketTest(){
        TicketModel ticket = bookingService.bookTicket(eventName, userName, seatPlaceNumber);
        assertNotNull(ticket);
        System.out.println("Your ticket:");
        System.out.println("ticket id:    " + ticket.getTicketId());
        System.out.println("Where:        " + ticket.getEventModel().getEventPlace());
        System.out.println("Place number: " + seatPlaceNumber + (ticket.isSeatVip() ? " - vip" : ""));
        System.out.println("Event name:   " + ticket.getEventModel().getEventName());
        System.out.println("Event date:   " + ticket.getEventModel().getEventDate());
        System.out.println("Begin:        " + ticket.getEventModel().getEventStartTime());
        System.out.println("Finish:       " + ticket.getEventModel().getEventEndTime());
        System.out.println("Ticket price: " + ticket.getResultPrice() + " popugaev!");
        System.out.println("*************");
    }

    @Test
    public void getPurchasedTicketsForEvent() throws ParseException {
        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        Date eDate = formatPattern.parse(seventDate);

        Map<String, Object> ticketInfo = bookingService.getPurchasedTicketsForEvent(eventName, new java.sql.Date(eDate.getTime()));
        if(ticketInfo != null) {
            System.out.println("On *" + eventName + "* event which will be " + seventDate + " already booked:");
            System.out.println("Purchased places: " + Arrays.toString((int[]) ticketInfo.get("purchasedTickets")));
            System.out.println("Total summ      : " + ticketInfo.get("totalSumm").toString() + " popugaev!");
        } else {
            System.out.println("FAIL:");
            System.out.println("impossible to find event " + eventName + " on " + seventDate + "!");
        }
    }

}
