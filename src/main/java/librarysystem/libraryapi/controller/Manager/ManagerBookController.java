package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.controller.tool.DBManager;
import librarysystem.libraryapi.Bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerBookController {

    @RequestMapping("/book/index")
    public String index(Model model) {
        String sql = "SELECT * FROM book";
        List<Book> bookList = new ArrayList<>();
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
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
//        model.addAttribute("book","我是一本好书");
        model.addAttribute("bookList", bookList);
        return "manager/book/index";
    }
}
