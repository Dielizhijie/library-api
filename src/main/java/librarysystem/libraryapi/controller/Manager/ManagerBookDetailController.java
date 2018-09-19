package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.controller.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;

@Controller
@RequestMapping("/manager")
public class ManagerBookDetailController {


    @RequestMapping("/book/detail")
    public String ManageDetail(Model model) {
        String sql = "SELECT * FROM book";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
        } catch (Exception e) {

        }
        return "manager/book/edit";
    }
}
