package models;

import java.util.List;

public class TicketModel {

    private int ticketId;
    private EventModel eventModel;
    private Double resultPrice;

    public TicketModel(int ticketId, EventModel eventModel, Double resultPrice) {
        this.ticketId = ticketId;
        this.eventModel = eventModel;
        this.resultPrice = resultPrice;
    }
}
