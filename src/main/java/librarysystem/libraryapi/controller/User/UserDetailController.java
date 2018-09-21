package librarysystem.libraryapi.controller.User;

import librarysystem.libraryapi.Bean.Book;
import librarysystem.libraryapi.Bean.BorrowBook;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.DBManager;
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
@RequestMapping("/student")
public class UserDetailController {
    String sql = "SELECT * FROM book";
//    DBManager dbManager = new DBManager(sql);
//    ResultSet result = null;


    @RequestMapping("/detail")
    public String ManageDetail(Model model) {
        model.addAttribute("name", User.getInstance().name);
        model.addAttribute("user_id", User.getInstance().user_id);
        model.addAttribute("password", User.getInstance().password);
        model.addAttribute("phone", User.getInstance().phone);
        model.addAttribute("academy", User.getInstance().academy);
        model.addAttribute("major", User.getInstance().major);
        model.addAttribute("sex", User.getInstance().sex);
        model.addAttribute("grade", User.getInstance().grade);
        model.addAttribute("card", User.getInstance().card);
        model.addAttribute("credit", User.getInstance().credit);

        String sql = "SELECT id,bid FROM borrow where user_id = " + User.getInstance().id + ";";
        int borrowID = 0;
        List<Integer> bidList = new ArrayList<>();
        List<BorrowBook> borrowBookList = new ArrayList<>();
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                borrowID = Integer.parseInt(result.getString("id"));
                bidList.add(Integer.parseInt(result.getString("bid")));
            }
            result.close();
            dbManager.close();

            for (int i = 0; i < bidList.size(); i++) {
                String sql1 = "SELECT name,author FROM book where id = " + bidList.get(i) + ";";
                DBManager dbManager1 = new DBManager(sql1);
                ResultSet result1 = null;
                result1 = dbManager1.preparedStatement.executeQuery();

                while (result1.next()) {
                    BorrowBook book = new BorrowBook();
                    book.id = borrowID;
                    book.name = result1.getString("name");
                    book.author = result1.getString("author");
                    borrowBookList.add(book);
                }
                result1.close();
                dbManager1.close();
            }
        } catch (Exception e) {

        }
        model.addAttribute("borrowBookList", borrowBookList);

        String predetermineSql = "SELECT id,bid FROM predetermine where user_id = " + User.getInstance().id + ";";
        int borrowID1 = 0;
        List<Integer> bidList1 = new ArrayList<>();
        List<BorrowBook> predetermineBookList = new ArrayList<>();
        try {
            DBManager dbManager = new DBManager(predetermineSql);
            ResultSet result = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                borrowID1 = Integer.parseInt(result.getString("id"));
                bidList1.add(Integer.parseInt(result.getString("bid")));
            }
            result.close();
            dbManager.close();

            for (int i = 0; i < bidList1.size(); i++) {
                String sql1 = "SELECT name,author FROM book where id = " + bidList1.get(i) + ";";
                DBManager dbManager1 = new DBManager(sql1);
                ResultSet result1 = null;
                result1 = dbManager1.preparedStatement.executeQuery();

                while (result1.next()) {
                    BorrowBook book = new BorrowBook();
                    book.id = borrowID1;
                    book.name = result1.getString("name");
                    book.author = result1.getString("author");
                    predetermineBookList.add(book);
                }
                result1.close();
                dbManager1.close();
            }
        } catch (Exception e) {

        }
        model.addAttribute("predetermineBookList", predetermineBookList);
        return "student/detail";
    }

    @RequestMapping(value = "/book/detail/borrow", method = RequestMethod.POST)
    public String studentDetailBorrow(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("borrowButton"));
        String sql = "insert into borrow (user_id,bid) values (" + User.getInstance().id + "," + id + ");";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            ErrorAlert.popAlert(response, "数据库访问出错");
        }
        return "redirect:/student/detail";
    }

    @RequestMapping(value = "/book/detail/giveback", method = RequestMethod.POST)
    public String studentDetailGiveback(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("givebackButton"));
        String sql = "delete from borrow where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            ErrorAlert.popAlert(response, "数据库访问出错");
        }
        return "redirect:/student/detail";
    }

    @RequestMapping(value = "/book/detail/cancel", method = RequestMethod.POST)
    public String studentDetailCancel(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("cancelButton"));
        String sql = "delete from predetermine where id = " + id + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            ErrorAlert.popAlert(response, "数据库访问出错");
        }
        return "redirect:/student/detail";
    }
}
