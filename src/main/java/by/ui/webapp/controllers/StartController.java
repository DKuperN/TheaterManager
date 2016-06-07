package by.ui.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {

    @RequestMapping("/test")
    public ModelAndView showStartPage(){
        return new ModelAndView("index");
    }
}
