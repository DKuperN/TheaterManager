package by.ui.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBootController {

    @RequestMapping("/boot")
    public String index(){
        return "Greetings from Spring Boot!";
    }
}
