package by.ui.webapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloSpringBootController {

    private static final Logger logger = Logger.getLogger(HelloSpringBootController.class);

    @RequestMapping("/boot")
    public String index(){
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/hello")
    public ModelAndView firstPage(ModelMap model) {
        logger.info("Log4j info is working");
        logger.warn("Log4j warn is working");
        logger.debug("Log4j debug is working");
        logger.error("Log4j error is working");
        System.out.println("System out is working");
        model.addAttribute("message", "Spring Boot Hello World!");
        return new ModelAndView("index");
    }
}
