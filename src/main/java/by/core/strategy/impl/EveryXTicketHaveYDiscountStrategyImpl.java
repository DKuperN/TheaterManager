package by.core.strategy.impl;

import by.core.strategy.DiscountServiceStrategy;

import java.util.Date;

public class EveryXTicketHaveYDiscountStrategyImpl implements DiscountServiceStrategy {
    @Override
    public int getDiscount(String userName, String eventName, Date dateTime) {
        //TODO
        return 32;
    }
}
