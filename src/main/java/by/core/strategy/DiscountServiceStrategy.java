package by.core.strategy;

import java.util.Date;

public interface DiscountServiceStrategy {

    int getDiscount(String userName, String eventName, Date dateTime);

}
