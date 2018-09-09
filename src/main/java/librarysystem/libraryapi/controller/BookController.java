package librarysystem.libraryapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("manager")
public class BookController {
    @RequestMapping("/book/index")
    public String greeting() {
//        model.addAttribute("name", name);
        return "manager/book/index";
    }

}
