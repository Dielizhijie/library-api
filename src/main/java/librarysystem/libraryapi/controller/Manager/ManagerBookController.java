package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.controller.tool.DBManager;
import librarysystem.libraryapi.Bean.Book;
import librarysystem.libraryapi.controller.tool.ErrorAlert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                book.id = Integer.parseInt(result.getString("id"));
                book.name = result.getString("name");
                book.class_id = Integer.parseInt(result.getString("class_id"));
                book.author = result.getString("author");
                book.cover = result.getString("cover");
                book.details = result.getString("details");
                book.publication_date = result.getString("publication_date");
                book.location = result.getString("location");
                book.count = Integer.parseInt(result.getString("count"));
                book.borrowing_count = Integer.parseInt(result.getString("borrowing_count"));
                book.borrowed_user_count = Integer.parseInt(result.getString("borrowed_user_count"));
                book.predetermine_count = Integer.parseInt(result.getString("predetermine_count"));
                bookList.add(book);
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
        model.addAttribute("bookList", bookList);
        return "manager/book/index";
    }

    @RequestMapping(value = "/book/detail/redirect/edit" ,method = RequestMethod.POST)
    public String ManageDetailEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("editButton"));
        return "redirect:/manager/book/detail/"+String.valueOf(id);
    }
    @RequestMapping(value = "/book/detail/redirect/delete" ,method = RequestMethod.POST)
    public String ManageDetailDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("editButton"));
        return "redirect:/manager/book/detail/"+String.valueOf(id);
    }
}
