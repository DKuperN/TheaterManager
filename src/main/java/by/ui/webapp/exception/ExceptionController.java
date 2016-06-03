package by.ui.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController {
 final String ERROR_PAGE = "errorpage";
    @RequestMapping("/error")
    public ModelAndView showMessage(
            @RequestParam(value = "msg", required = false) String message,
            @RequestParam(value = "sttrace", defaultValue = "") String sttrace){
        ModelAndView view = new ModelAndView(ERROR_PAGE);
        view.addObject("errmessage", message);
        view.addObject("sttrace", sttrace);
        return view;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView pageNotFound(){
        ModelAndView mav = new ModelAndView(ERROR_PAGE);
        mav.addObject("errmessage", "Error");
        mav.addObject("sttrace", "33333333333");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception exception) {
        ModelAndView mav = new ModelAndView(ERROR_PAGE);
        mav.addObject("errmessage", "Error");
        mav.addObject("sttrace", exception);
        return mav;
    }
}
