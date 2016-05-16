package by.core.services.impl;

import by.core.daos.impl.BookingDAOImpl;
import by.core.models.BookingModel;
import by.core.models.EventModel;
import by.core.models.TicketModel;
import by.core.models.UserModel;
import by.core.services.AuditoriumService;
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
    @Autowired
    AuditoriumServiceImpl auditoriumService;
    @Autowired
    DiscountServiceImpl discountService;

    public BookingServiceImpl() {}

    public BookingServiceImpl(BookingDAOImpl booking, Utils utils) {
        this.booking = booking;
        this.utils = utils;
    }

    public Double getTicketPrice(String eventName, String userName, int seatNumber) {
        return null;
    }

    public TicketModel bookTicket(BookingModel bookingModel, int seatNumber) {
        TicketModel ticket = null;
        try {
            populateBookingModel(bookingModel, seatNumber);
            ticket = booking.bookTicket(bookingModel, seatNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public TicketModel bookTicket(BookingModel bookingModel, int seatNumber, boolean enableDiscountStrategy) {
        TicketModel ticket = null;
        try {
            populateBookingModel(bookingModel, seatNumber);
            ticket = booking.bookTicket(bookingModel, seatNumber, enableDiscountStrategy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate) {
        return booking.getPurchasedTicketsForEvent(eventName, eventDate);
    }

    @Override
    public BookingModel getBookingModel(UserModel userModel, EventModel eventModel, boolean isSeatVip, int discount) {
        return new BookingModel(userModel, eventModel, isSeatVip, discount);
    }

    private void populateBookingModel(BookingModel bookingModel, int seatNumber) throws IOException {
        //TODO  прикрутить куда-нить дату, пока хз куда
        Date date = new Date(System.currentTimeMillis());
        bookingModel.setSeatVip(auditoriumService.isPlaceVip(bookingModel.getEventModel().getEventName(), seatNumber));
        bookingModel.setDiscount(discountService.getDiscount(bookingModel.getUserModel().getUserName(), bookingModel.getEventModel().getEventName(), date));
    }

}
