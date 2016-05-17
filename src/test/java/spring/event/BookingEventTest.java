package spring.event;

import by.annotationbeans.AnnotationBeans;
import by.core.models.BookingModel;
import by.core.models.TicketModel;
import by.core.services.impl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:discountStrategy.xml"}),
        @ContextConfiguration(classes = {AnnotationBeans.class})
})
public class BookingEventTest {
    @Autowired
    private BookingServiceImpl bookingService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    Environment environment;
    @Autowired
    DiscountServiceImpl discountService;


    @Test
    public void bookingTicketTest() throws IOException {
        String userName = environment.getProperty("test.userName");
        String eventName = environment.getProperty("test.eventName");
        String enableDiscountStrategy = environment.getProperty("test.enableDiscountStrategy");

        BookingModel bookingModel = bookingService.getBookingModel(
                userService.getUserByName(userName),
                eventService.getEventByName(eventName),
                true,
                discountService.getDiscount(userName, eventName, new java.sql.Date(System.currentTimeMillis()))
                );

        Random r = new Random();
        int seatPlaceNumber = r.nextInt(100);

        TicketModel ticket = bookingService.bookTicket(bookingModel, seatPlaceNumber, Boolean.parseBoolean(enableDiscountStrategy));
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
        System.out.println("Discount:     " + ticket.getDiscount() + "%");
        System.out.println("*************");
    }

    @Test
    public void getPurchasedTicketsForEvent() throws ParseException {
        String eventName = environment.getProperty("test.eventName");
        String seventDate = environment.getProperty("test.eventDate");

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
