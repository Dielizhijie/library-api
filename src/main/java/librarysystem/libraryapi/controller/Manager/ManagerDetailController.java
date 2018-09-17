package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
@Controller
@RequestMapping("/manager")
public class ManagerDetailController {

    @RequestMapping("/detail")
    public String ManageDetail(Model model){
        model.addAttribute("name", Manager.instance.name);
        model.addAttribute("user_id",Manager.instance.user_id);
        model.addAttribute("work_id",Manager.instance.work_id);
        return "manager/detail";
    }
}
