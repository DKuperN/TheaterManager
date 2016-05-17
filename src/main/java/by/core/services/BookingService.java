package by.core.services;

import by.core.models.BookingModel;
import by.core.models.EventModel;
import by.core.models.TicketModel;
import by.core.models.UserModel;

import java.sql.Date;
import java.util.Map;

public interface BookingService {

    Double getTicketPrice(String eventName, String userName, int seatNumber);

//    @Deprecated //not uses
//    TicketModel bookTicketModel(BookingModel bookingModel, int seatNumber);

    TicketModel bookTicketModel(BookingModel bookingModel, int seatNumber, boolean enableDiscountStrategy);

    Map<String, Object> getPurchasedTicketsForEvent(String eventName, Date eventDate);

    BookingModel getBookingModel(UserModel userModel, EventModel eventModel, boolean isSeatVip, int discount);

}
