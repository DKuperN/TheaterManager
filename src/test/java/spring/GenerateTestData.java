package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;
import services.impl.UserServiceImpl;
import utils.Utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generate TestData
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:springContext.xml"})
})
public class GenerateTestData {

    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private Utils utils;

    @Value("${test.userName}")
    private String userName;
    @Value("${test.userEmail}")
    private String userEmail;
    @Value("${test.mainQuantityDBRows}")
    private String rowsQuantity;
    @Value("${test.base.events}")
    private String events;
    @Value("${test.base.eventsPlaces}")
    private String eventsPlaces;
    @Value("${test.base.eventsDates}")
    private String eventsDates;
    @Value("${test.base.eventStartTimes}")
    private String eventStartTimes;
    @Value("${test.base.eventEndTimes}")
    private String eventEndTimes;
    @Value("${test.base.eventPrices}")
    private String eventPrices;
    @Value("${test.base.eventRating}")
    private String eventRating;


    @Test
    public void generateTestData() throws Exception {
       /**
         * Добавление пользователей
         */
        for (int i = 0; i < Integer.parseInt(rowsQuantity); i++){
            userService.registerUser(userName+i, userEmail+i+"@test.tt");
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
        System.out.println("Run other tests for test application services");
    }
}
