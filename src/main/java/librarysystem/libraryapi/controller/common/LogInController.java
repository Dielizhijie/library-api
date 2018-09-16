package librarysystem.libraryapi.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class LogInController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(){
        return "/login";
    }
}
