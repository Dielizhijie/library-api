package librarysystem.libraryapi.controller.common;

import librarysystem.libraryapi.controller.tool.ErrorAlert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
@Controller
public class LogoutController {
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOut( HttpServletResponse response){
        ErrorAlert.popAlert(response,"退出成功");
        return "/loginPage";
    }
}
