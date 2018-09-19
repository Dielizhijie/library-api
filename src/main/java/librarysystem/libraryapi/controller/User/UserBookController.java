package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.controller.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
@Controller
@RequestMapping("/student")
public class UserBookController {
    String sql = "SELECT * FROM book";
//    DBManager dbManager = new DBManager(sql);
//    //        return dbManager;
//    ResultSet result = null;

    @RequestMapping("/book")
    public String userBook(Model model){
        return "student/book";
    }
}
