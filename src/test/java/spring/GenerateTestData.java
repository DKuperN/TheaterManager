package spring;

import by.annotationbeans.AnnotationBeans;
import by.core.services.impl.EventServiceImpl;
import by.core.services.impl.UserServiceImpl;
import by.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate TestData
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AnnotationBeans.class})
public class GenerateTestData {

    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private Utils utils;
    @Autowired
    Environment environment;

    @Test
    public void generateTestData() throws Exception {
        String userName = environment.getProperty("test.userName");
        String rowsQuantity = environment.getProperty("test.mainQuantityDBRows");
        String events = environment.getProperty("test.base.events");
        String eventsPlaces = environment.getProperty("test.base.eventsPlaces");
        String eventsDates = environment.getProperty("test.base.eventsDates");
        String eventStartTimes = environment.getProperty("test.base.eventStartTimes");
        String eventEndTimes = environment.getProperty("test.base.eventEndTimes");
        String eventPrices = environment.getProperty("test.base.eventPrices");
        String eventRating = environment.getProperty("test.base.eventRating");

       /**
         * Добавление пользователей
         */
        for (int i = 0; i < Integer.parseInt(rowsQuantity); i++){
            userService.registerUser(userName+i, userName+i+"@test.tt");
        }

        /**
         * Добавление эвент-рейтингов
         */
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "HIGH");
        map.put(2, "MID");
        map.put(3, "LOW");
        for (Map.Entry<Integer, String> hashMap : map.entrySet()) {
            utils.executeQuery("INSERT INTO EVENT_RATING(RATING) VALUES (?)", hashMap.getValue());
        }

        /**
         * Добавление эвентов
         */
        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat timeformatPattern = new SimpleDateFormat("HH.mm");
        String[] _events = events.split("\\,");
        String[] _eventsPlaces = eventsPlaces.split("\\,");
        String[] _eventsDates = eventsDates.split("\\,");
        String[] _eventStartTimes = eventStartTimes.split("\\,");
        String[] _eventEndTimes = eventEndTimes.split("\\,");
        String[] _eventPrices = eventPrices.split("\\,");
        String[] _eventRating = eventRating.split("\\,");

        for (int i = 0; i < 6; i++){
            eventService.createEvent(
                    _events[i],
                    _eventsPlaces[i],
                    formatPattern.parse(_eventsDates[i]),
                    new Time(timeformatPattern.parse(_eventStartTimes[i]).getTime()),
                    new Time(timeformatPattern.parse(_eventEndTimes[i]).getTime()),
                    Double.parseDouble(_eventPrices[i]),
                    Integer.parseInt(_eventRating[i])
            );
        }
        System.out.println("Test data are created successfully!!!!!!!!");
        System.out.println("*************");
        System.out.println("Run other tests for test application core.services");
    }
}
