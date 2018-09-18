package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerDetailController {

    @RequestMapping("/detail")
    public String ManageDetail(Model model){
        model.addAttribute("name", Manager.getInstance().name);
        model.addAttribute("user_id",Manager.getInstance().user_id);
        model.addAttribute("work_id",Manager.getInstance().work_id);
        return "manager/detail";
    }
}
