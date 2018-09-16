package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
@Controller
@RequestMapping("/manager")
public class ManagerStudentController {
    String sql = "SELECT * FROM book";
    DBManager dbManager = new DBManager(sql);
    //        return dbManager;
    ResultSet result = null;

    @RequestMapping("/student/index")
    public String ManageDetail(Model model){

        return null;
    }
}
