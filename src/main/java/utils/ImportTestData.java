package utils;

import org.springframework.beans.factory.annotation.Autowired;
import services.impl.AuditoriumServiceImpl;
import services.impl.EventServiceImpl;
import services.impl.UserServiceImpl;

import javax.sql.DataSource;

/**
 * Class for import minimum important test data
 */
public class ImportTestData {
//TODO вызвать стартовые методы и сгенерить данные для пустой схемы
    private DataSource dataSource;
    private UserServiceImpl userService;
    private EventServiceImpl eventService;
    private AuditoriumServiceImpl auditoriumService;
    private Utils utils;

    public ImportTestData(DataSource dataSource, UserServiceImpl userService, EventServiceImpl eventService, AuditoriumServiceImpl auditoriumService, Utils utils) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.utils = utils;
    }

    public void generateData(){

    }
}
