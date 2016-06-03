package by.ui.webapp.controllers;

import by.core.models.TestPOJO;
import by.core.models.UserModel;
import by.core.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserProcessingController {
    final String FUNCTION_NOT_READY = "Function not ready";

//    @Autowired
    private UserService userService;

    @RequestMapping("/userprocessing")
    public ModelAndView userProcessingPage(){
        return new ModelAndView("user/userprocessing");
    }

@Deprecated
//use POST method
    @RequestMapping(value = "/userprocessing/registeruser", method = RequestMethod.GET)
    public @ResponseBody String registerUser1(@RequestParam(value = "userName", required = false) String name,
                           @RequestParam(value = "newUserEmail", required = false) String email){
        UserModel userModel = new UserModel();
        userModel.setUserName(name);
        userModel.setUserEmail(email);
        return userModel.toString();
    }

    @RequestMapping(value = "/userprocessing/registeruser", method = RequestMethod.POST
//            , consumes = "application/json", produces = "application/json"
            )
    public @ResponseBody TestPOJO testPOJO (@RequestParam(value = "userName", required = false) String userName,
                              @RequestParam(value = "userEmail", required = false) String userEmail){

        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setUserName(userName);
        testPOJO.setUserEmail(userEmail);
        return testPOJO;
    }

    @RequestMapping(value = "/userprocessing/ajaxtest", method = RequestMethod.GET)
    @ResponseBody
    public String ajaxTest() {
        String records = "TEST COMPLEETE!";
         return records;
    }


    @RequestMapping("/userprocessing/userInfo")
    public ModelAndView showUserInfo(@RequestParam(value = "name", required = false) String name){
        ModelAndView view = new ModelAndView("helloworld");

        return view;
    }
    @RequestMapping("/userprocessing/allUsers")
    public ModelAndView showAllUsers(){
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
