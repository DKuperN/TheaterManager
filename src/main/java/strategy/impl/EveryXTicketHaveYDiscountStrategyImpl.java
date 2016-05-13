package strategy.impl;

import strategy.DiscountServiceStrategy;

import java.util.Date;

public class EveryXTicketHaveYDiscountStrategyImpl implements DiscountServiceStrategy {
    @Override
    public int getDiscount(String userName, String eventName, Date dateTime, int ticketId) {
        //TODO
        return 32;
    }
}
