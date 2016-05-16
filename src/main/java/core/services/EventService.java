package core.services;

import core.models.EventModel;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface EventService {
    void createEvent(String eventName, String eventPlace, Date eventDate, Time eventStartTime, Time eventEndTime, Double priceForTicket, int rating);
    void deleteEvent(String eventName);
    void deleteEvent(int eventId);
    EventModel getEventByName(String eventName);
    List<EventModel> getAllEvents();
    boolean isAssignAuditorium(EventModel event, String auditorium, Date date);
}
