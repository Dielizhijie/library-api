package librarysystem.libraryapi.controller;

import librarysystem.libraryapi.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("manager")
public class BookController {
    @RequestMapping("/book/index")
    public String index(Model model) {
        Book book;

        model.addAttribute("book", "发表");
        return "manager/book/index";
    }

}
