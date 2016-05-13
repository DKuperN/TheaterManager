package services;

import java.util.Date;

public interface DiscountService {
    int getDiscount(String userName, String eventName, Date dateTime, int ticketId);
}
