package by.core.models;

import java.sql.Time;
import java.util.Date;

public class EventModel {

    private int eventId;

    private String eventName;

    private String eventPlace;

    private Date eventDate;

    private Time eventStartTime;

    private Time eventEndTime;

    private double priceForTicket;

    private int rating;

    public EventModel(int eventId, String eventName, String eventPlace, Date eventDate, Time eventStartTime, Time eventEndTime, double priceForTicket, int rating) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.priceForTicket = priceForTicket;
        this.rating = rating;
    }

    public double getPriceForTicket() {
        return priceForTicket;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getBasePriceForTicket() {
        return priceForTicket;
    }

    public void setPriceForTicket(double priceForTicket) {
        this.priceForTicket = priceForTicket;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Time getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Time eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
