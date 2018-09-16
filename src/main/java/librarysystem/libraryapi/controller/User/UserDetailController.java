package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.DBManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;

public class UserDetailController {
    String sql = "SELECT * FROM book";
    DBManager dbManager = new DBManager(sql);
    //        return dbManager;
    ResultSet result = null;

    @RequestMapping("/detail/index")
    public String ManageDetail(Model model){

        return null;
    }
}
