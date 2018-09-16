package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.DBManager;
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

        return null;
    }
}
