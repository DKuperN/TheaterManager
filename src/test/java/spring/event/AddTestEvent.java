package spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;
import utils.Utils;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class AddTestEvent {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private Utils utils;

    @Test
    public void createTestEvent() throws ParseException {
        //TestData
        String eName = "Dracula";
        String ePlace = "Kinozal Centralni";
        Date eDate = utils.dateFormatter("2016-11-26");
        Time eStartTime = utils.timeFormatter("11.15");
        Time eEndTime = utils.timeFormatter("15.25");
        Double ePrice = 23.5;
        int eRating = 1;

        eventService.createEvent(eName, ePlace, eDate, eStartTime, eEndTime, ePrice, eRating);
        System.out.println("Created test Event!");
    }

}
