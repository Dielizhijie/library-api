package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
@Controller
@RequestMapping("/student")
public class UserBookController {
    String sql = "SELECT * FROM book";
    DBManager dbManager = new DBManager(sql);
    //        return dbManager;
    ResultSet result = null;

    @RequestMapping("/index")
    public String UserBook(Model model){
        return "student/index";
    }
}
