package librarysystem.libraryapi.controller.common;

import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.ErrorAlert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
@Controller
public class LogoutController {//登出函数
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOut( HttpServletResponse response){
        Manager.getInstance().onLogout();
        User.getInstance().onLogout();
        ErrorAlert.popAlert(response,"退出成功");
        return "/loginPage";
    }
}
