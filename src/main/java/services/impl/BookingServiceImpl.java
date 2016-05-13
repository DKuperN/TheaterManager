package services.impl;

import daos.impl.BookingDAOImpl;
import models.TicketModel;
import services.BookingService;
import utils.Utils;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

public class BookingServiceImpl implements BookingService {

    private BookingDAOImpl booking;
    private Utils utils;

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

    public Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate) {
        return booking.getPurchasedTicketsForEvent(eventName, eventDate);
    }
}
