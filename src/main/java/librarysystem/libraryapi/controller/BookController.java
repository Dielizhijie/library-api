package librarysystem.libraryapi.controller;

import librarysystem.libraryapi.DBManager;
import librarysystem.libraryapi.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class BookController {

    @RequestMapping("/book/index")
    public String index(Model model) {
        String sql = "SELECT * FROM book";
        DBManager dbManager = new DBManager(sql);
        ResultSet result = null;
        List<Book> bookList = new ArrayList<>();
        try {
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.id = Integer.parseInt(result.getString(1));
                book.name = result.getString(2);
                book.class_id = Integer.parseInt(result.getString(3));
                book.author = result.getString(4);
                book.cover = result.getString(5);
                book.details = result.getString(6);
                book.publication_date = result.getString(7);
                book.location = result.getString(8);
                book.count = Integer.parseInt(result.getString(9));
                book.borrowing_count = Integer.parseInt(result.getString(10));
                book.borrowed_user_count = Integer.parseInt(result.getString(11));
                book.predetermine_count = Integer.parseInt(result.getString(12));
                bookList.add(book);
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
        model.addAttribute("book","我是一本好书");
        model.addAttribute("bookList", bookList);
        return "manager/book/index";
    }

}
