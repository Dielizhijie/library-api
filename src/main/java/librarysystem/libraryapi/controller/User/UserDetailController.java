package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;

@Controller
@RequestMapping("/student")
public class UserDetailController {
    String sql = "SELECT * FROM book";
    DBManager dbManager = new DBManager(sql);
    ResultSet result = null;


    @RequestMapping("/detail")
    public String ManageDetail(Model model){
        model.addAttribute("name", User.getInstance().name);
        model.addAttribute("user_id", User.getInstance().user_id);
        model.addAttribute("phone",User.getInstance().phone);
        model.addAttribute("academy",User.getInstance().academy);
        model.addAttribute("major",User.getInstance().major);
        model.addAttribute("sex",User.getInstance().sex);
        model.addAttribute("grade",User.getInstance().grade);
        model.addAttribute("card",User.getInstance().card);
        model.addAttribute("credit",User.getInstance().credit);
        return "student/detail";
    }
}
