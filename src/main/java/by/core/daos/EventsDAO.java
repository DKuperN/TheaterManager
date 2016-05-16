package by.core.daos;

import by.core.models.EventModel;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface EventsDAO {
    void createEvent(String eventName, String eventPlace, Date eventDate, Time eventStartTime, Time eventEndTime, Double priceForTicket, int rating);
    void deleteEvent(String eventName);
    void deleteEvent(int eventId);
    EventModel getEventByName(String eventName);
    List<EventModel> allEvents();
    boolean isAssignAuditorium(EventModel event, String auditorium, Date date);

}
