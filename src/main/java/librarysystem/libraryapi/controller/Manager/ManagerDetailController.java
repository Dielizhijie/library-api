package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.DBManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
@RequestMapping("/manager")
public class ManagerDetailController {
    String sql = "SELECT * FROM book";
    DBManager dbManager = new DBManager(sql);
    //        return dbManager;
    ResultSet result = null;

    @RequestMapping("/detail/index")
    public String ManageDetail(Model model){

        return null;
    }
}
