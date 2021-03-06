package by.core.services.impl;

import by.core.daos.impl.EventDaoImpl;
import by.core.models.EventModel;
import by.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    EventDaoImpl eventDao;
    private Date date;
    private DateFormat df;

    public EventServiceImpl() {}

    public EventServiceImpl(EventDaoImpl eventDao) {
        this.eventDao = eventDao;
    }

    public EventServiceImpl(EventDaoImpl eventDao, Date date, DateFormat df) {
        this.eventDao = eventDao;
        this.date = date;
        this.df = df;
    }

    public void createEvent(String eventName, String eventPlace, Date eventDate, Time eventStartTime, Time eventEndTime, Double priceForTicket, int rating) {
        Serializable indate = eventDate != null ? eventDate : df.format(date);
        java.sql.Date sqlDate = new java.sql.Date(eventDate.getTime());
        eventDao.createEvent(eventName, eventPlace, sqlDate, eventStartTime, eventEndTime, priceForTicket, rating);
    }

    public void deleteEvent(String eventName) {
        eventDao.deleteEvent(eventName);
    }

    public void deleteEvent(int eventId) {
        eventDao.deleteEvent(eventId);
    }

    public EventModel getEventByName(String eventName) {
        return eventDao.getEventByName(eventName);
    }

    public List<EventModel> getAllEvents() {
        return eventDao.allEvents();
    }

    public boolean isAssignAuditorium(EventModel event, String auditorium, Date date) {
        return false;
    }
}
