package spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.EventServiceImpl;
import utils.Utils;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
@Component
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
    private Date eDate;
    @Value("${test.eventStartTime}")
    private String seStartTime;
    private Time eStartTime;
    @Value("${test.eventEndTime}")
    private String seEndTime;
    private Time eEndTime;
    @Value("${test.eventPrice}")
    private String sePrice;
    private Double ePrice;
    @Value("${test.eventRating}")
    private String seRating;
    private int eRating;

    //TODO clean code

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
