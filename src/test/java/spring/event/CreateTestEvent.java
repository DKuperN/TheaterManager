package spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class CreateTestEvent {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private Utils utils;

    @Test
    public void createTestEvent() throws ParseException, IOException {
        //TestData
        String eName = utils.getPropertyByName("test.eventName");
        String ePlace = utils.getPropertyByName("test.eventName");
        Date eDate = utils.dateFormatter(utils.getPropertyByName("test.eventDate"));
        Time eStartTime = utils.timeFormatter(utils.getPropertyByName("test.eventStartTime"));
        Time eEndTime = utils.timeFormatter(utils.getPropertyByName("test.eventEndTime"));
        Double ePrice = Double.parseDouble(utils.getPropertyByName("test.eventPrice"));
        int eRating = Integer.parseInt(utils.getPropertyByName("test.eventRating"));

        eventService.createEvent(eName, ePlace, eDate, eStartTime, eEndTime, ePrice, eRating);
        System.out.println("Created test Event!");
    }

}
