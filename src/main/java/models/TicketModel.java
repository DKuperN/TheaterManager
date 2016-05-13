package models;

public class TicketModel {

    private int ticketId;
    private EventModel eventModel;
    private Double resultPrice;

    public boolean isSeatVip() {
        return isSeatVip;
    }

    public void setSeatVip(boolean seatVip) {
        isSeatVip = seatVip;
    }

    private boolean isSeatVip;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public Double getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(Double resultPrice) {
        this.resultPrice = resultPrice;
    }


    public TicketModel(int ticketId, EventModel eventModel, Double resultPrice, boolean isSeatVip) {
        this.ticketId = ticketId;
        this.eventModel = eventModel;
        this.resultPrice = resultPrice;
        this.isSeatVip = isSeatVip;
    }
}
