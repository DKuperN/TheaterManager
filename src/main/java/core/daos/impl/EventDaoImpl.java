package core.daos.impl;

import core.daos.EventsDAO;
import core.models.EventModel;
import utils.Utils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EventDaoImpl implements EventsDAO {

    private DataSource dataSource;
    private Utils utils;


    public EventDaoImpl(DataSource dataSource, Utils utils) {
        this.dataSource = dataSource;
        this.utils = utils;
    }

    public void createEvent(String eventName, String eventPlace, Date eventDate, Time eventStartTime, Time eventEndTime, Double priceForTicket, int rating) {
        final String SQL_SAVE_NEW_EVENT = "INSERT INTO EVENT (EVENTNAME, EVENTPLACE, EVENTDATE, EVENTSTARTTIME, EVENTENDTIME, PRICEFORTICKET, RATING) VALUES (?,?,?,?,?,?,?)";
        HashMap<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, eventName);
            map.put(2, eventPlace);
            map.put(3, eventDate);
            map.put(4, eventStartTime);
            map.put(5, eventEndTime);
            map.put(6, priceForTicket);
            map.put(7, rating);
        utils.executeQuery(SQL_SAVE_NEW_EVENT, map);
    }

    public void deleteEvent(String eventName) {
        deleteEvent(getEventIdByName(eventName));
    }

    public void deleteEvent(int eventId) {
        final String SQL_DELETE_EVENT = "DELETE FROM EVENT WHERE EVENTID = ?";
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, eventId);
        utils.executeQuery(SQL_DELETE_EVENT, map);
    }

    public EventModel getEventByName(String eventName) {
        int eventId = getEventIdByName(eventName);

        final String SQL_SEARCH_EVENT_BY_ID = "SELECT * FROM EVENT WHERE EVENTID = ?";

        //TODO need refactoring - move this code to Utils.java
        Connection connection = null;
        EventModel eventModel = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_EVENT_BY_ID);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                eventModel = new EventModel(
                        rs.getInt("EVENTID"),
                        rs.getString("EVENTNAME"),
                        rs.getString("EVENTPLACE"),
                        rs.getDate("EVENTDATE"),
                        rs.getTime("EVENTSTARTTIME"),
                        rs.getTime("EVENTENDTIME"),
                        rs.getDouble("PRICEFORTICKET"),
                        rs.getInt("RATING"));
            }
            ps.close();
            if (eventModel != null){
                return eventModel;
            } else {
                System.out.println("Object with that parameters is not founded");
            }
        } catch (SQLException e) {
            System.out.println("FAIL: something wrong with connection or query");
            e.printStackTrace();
        } finally {
            utils.closeConnection(connection);
        }
        return eventModel;
    }

    public List<EventModel> allEvents() {
        List<EventModel> eventModelList = new ArrayList<EventModel>();
        final String SQL_SEARCH_EVENTS = "SELECT * FROM EVENT";
        Connection connection = null;
        EventModel eventModel = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SEARCH_EVENTS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                eventModel = new EventModel(
                        rs.getInt("EVENTID"),
                        rs.getString("EVENTNAME"),
                        rs.getString("EVENTPLACE"),
                        rs.getDate("EVENTDATE"),
                        rs.getTime("EVENTSTARTTIME"),
                        rs.getTime("EVENTENDTIME"),
                        rs.getDouble("PRICEFORTICKET"),
                        rs.getInt("RATING"));
                eventModelList.add(eventModel);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventModelList;
    }

    public boolean isAssignAuditorium(EventModel event, String auditorium, Date date) {
        return false;
    }

    private int getEventIdByName(String eventName){
        final String SQL_SEARCH_EVENT_BY_NAME = "SELECT * FROM EVENT WHERE EVENTNAME = ?";
        return utils.executeWithReturnInt(SQL_SEARCH_EVENT_BY_NAME, eventName, "EVENTID");
    }
}
