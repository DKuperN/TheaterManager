package spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import core.services.impl.EventServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class CreateTestEvent {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private Utils utils;

    @Value("${test.eventName}")
    private String eName;
    @Value("${test.eventPlace}")
    private String ePlace;
    @Value("${test.eventDate}")
    private String seDate;
    @Value("${test.eventStartTime}")
    private String seStartTime;
    @Value("${test.eventEndTime}")
    private String seEndTime;
    @Value("${test.eventPrice}")
    private String sePrice;
    @Value("${test.eventRating}")
    private String seRating;

    @Test
    public void createTestEvent() throws ParseException, IOException {
        //TestData
        DateFormat formatPattern = new SimpleDateFormat("yyyy-mm-dd");
        Date eDate = formatPattern.parse(seDate);
        Time eStartTime = utils.timeFormatter(seStartTime);
        Time eEndTime = utils.timeFormatter(seEndTime);
        Double ePrice = Double.parseDouble(sePrice);
        int eRating = Integer.parseInt(seRating);

        eventService.createEvent(eName, ePlace, eDate, eStartTime, eEndTime, ePrice, eRating);
        System.out.println("Created test Event!");
    }

}
