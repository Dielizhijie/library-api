package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.Bean.Book;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.Alert;
import librarysystem.libraryapi.controller.tool.DBManager;
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
@RequestMapping("/student")
public class UserBookController {

    @RequestMapping("/book")
    public String userBook(Model model) {
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
        return "student/book";
    }

    @RequestMapping(value = "/book/borrow", method = RequestMethod.POST)
    public String studentBorrow(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("borrowButton"));
        int borrowingCount = 0, predetermineCount = 0;
        String sql = "insert into borrow (user_id,bid) values (" + User.getInstance().id + "," + id + ");";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql2 = "select borrowing_count,predetermine_count from book where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql2);
            ResultSet result = result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                borrowingCount = Integer.parseInt(result.getString("borrowing_count"));
                predetermineCount = Integer.parseInt(result.getString("predetermine_count"));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql3 = "update book set borrowing_count = " + (borrowingCount + 1) + " ,predetermine_count =" + (predetermineCount) + " where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql3);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        Alert.popAlert(response,"借书成功","/student/book");
        return "redirect:/student/book";
    }

    @RequestMapping(value = "/book/preordain", method = RequestMethod.POST)
    public String studentPreordain(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("preordainButton"));
        int borrowingCount = 0, predetermineCount = 0;
        String sql = "insert into predetermine (user_id,bid) values (" + User.getInstance().id + "," + id + ");";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql2 = "select borrowing_count,predetermine_count from book where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql2);
            ResultSet result = result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                borrowingCount = Integer.parseInt(result.getString("borrowing_count"));
                predetermineCount = Integer.parseInt(result.getString("predetermine_count"));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql3 = "update book set borrowing_count = " + (borrowingCount) + " ,predetermine_count =" + (predetermineCount + 1) + " where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql3);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        Alert.popAlert(response,"预定成功","/student/book");
        return "redirect:/student/book";
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.POST)
    public String studentSearch(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("borrowButton"));
        int borrowingCount = 0, predetermineCount = 0;
        String sql = "insert into borrow (user_id,bid) values (" + User.getInstance().id + "," + id + ");";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql2 = "select borrowing_count,predetermine_count from book where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql2);
            ResultSet result = result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                borrowingCount = Integer.parseInt(result.getString("borrowing_count"));
                predetermineCount = Integer.parseInt(result.getString("predetermine_count"));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        String sql3 = "update book set borrowing_count = " + (borrowingCount + 1) + " ,predetermine_count =" + (predetermineCount) + " where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql3);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错");
        }
        Alert.popAlert(response,"借书成功","/student/book");
        return "redirect:/student/book";
    }
}
