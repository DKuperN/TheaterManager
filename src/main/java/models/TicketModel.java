package models;

import java.util.List;

public class TicketModel {

    private int TicketId;

    private List Tickets;

    private boolean isFreeTicket;

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public List getTickets() {
        return Tickets;
    }

    public void setTickets(List tickets) {
        Tickets = tickets;
    }

    public boolean isFreeTicket() {
        return isFreeTicket;
    }

    public void setFreeTicket(boolean freeTicket) {
        isFreeTicket = freeTicket;
    }
}
