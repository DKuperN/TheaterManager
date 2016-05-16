package by.core.services.impl;

import by.core.daos.impl.BookingDAOImpl;
import by.core.models.TicketModel;
import by.core.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.utils.Utils;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingDAOImpl booking;
    private Utils utils;

    public BookingServiceImpl() {}

    public BookingServiceImpl(BookingDAOImpl booking, Utils utils) {
        this.booking = booking;
        this.utils = utils;
    }

    public Double getTicketPrice(String eventName, String userName, int seatNumber) {
        return null;
    }

    public TicketModel bookTicket(String eventName, String userName, int seatNumber) {
        TicketModel ticket = null;
        try {
            ticket = booking.bookTicket(eventName, userName, seatNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public TicketModel bookTicket(String eventName, String userName, int seatNumber, boolean enableDiscountStrategy) {
        TicketModel ticket = null;
        try {
            ticket = booking.bookTicket(eventName, userName, seatNumber, enableDiscountStrategy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate) {
        return booking.getPurchasedTicketsForEvent(eventName, eventDate);
    }
}
