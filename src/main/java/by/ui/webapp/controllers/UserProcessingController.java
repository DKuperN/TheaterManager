package by.ui.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserProcessingController {
    final String FUNCTION_NOT_READY = "Function not ready";

    @RequestMapping("/userprocessing")
    public ModelAndView userProcessingPage(){
        ModelAndView view = new ModelAndView("user/userprocessing");
        return view;
    }
    @RequestMapping("/userprocessing/registeruser")
    public ModelAndView registerUser(@RequestParam(value = "name", required = false, defaultValue = "World") String name){
        ModelAndView view = new ModelAndView("helloworld");

        return view;
    }
    @RequestMapping("/userprocessing/deleteuser")
    public ModelAndView deleteUser(@RequestParam(value = "userName", required = false) String userName){
        ModelAndView view = new ModelAndView("errorpage");
        view.addObject("msg", FUNCTION_NOT_READY);
        return view;
    }
}
