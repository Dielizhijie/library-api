package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Book;
import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.DBManager;
import librarysystem.libraryapi.controller.tool.ErrorAlert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@Controller
@RequestMapping("/manager")
public class ManagerBookDetailController {
    int ID;

    @RequestMapping("/book/detail/{id}")
    public String ManageDetail(Model model, @PathVariable String id, HttpServletResponse response) {
        String sql = "SELECT * FROM book where id = " + id + ";";
        ID = Integer.parseInt(id);
        ResultSet result = null;
        try {
            DBManager dbManager = new DBManager(sql);
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                model.addAttribute("name", result.getString("name"));
                model.addAttribute("class_id", result.getString("class_id"));
                model.addAttribute("author", result.getString("author"));
                model.addAttribute("cover", result.getString("cover"));
                model.addAttribute("details", result.getString("details"));
                model.addAttribute("publication_date", result.getString("publication_date"));
                model.addAttribute("location", result.getString("location"));
                model.addAttribute("count", result.getString("count"));
                model.addAttribute("borrowing_count", result.getString("borrowing_count"));
                model.addAttribute("borrowed_user_count", result.getString("borrowed_user_count"));
                model.addAttribute("predetermine_count", result.getString("predetermine"));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
        return "manager/book/edit";
    }

    @RequestMapping(value = "/book/detail/edit", method = RequestMethod.POST)
    public String editDetail(HttpServletResponse response, HttpServletRequest request) {
        String newName = request.getParameter("name");
        String newClassID = request.getParameter("class_id");
        String newAuthor = request.getParameter("author");
        String newDetails = request.getParameter("details");
        String newCover = request.getParameter("cover");
        String newPublicationDate = request.getParameter("publication_date");
        String newLocation = request.getParameter("location");
        String newCount = request.getParameter("count");
        String newBorrowingCount = request.getParameter("borrowing_count");
        String newBorrowedUserCount = request.getParameter("borrowed_user_count");
        String newPredetermineCount = request.getParameter("predetermine_count");
        String sql = "Update book set name = '" + newName
//                + "', class_id = '" + newClassID//
                + "', author = '" + newAuthor
                + "', details = '" + newDetails
//                + "', cover = '" + newCover//
                + "', publication_date = '" + newPublicationDate
                + "', location = '" + newLocation
                + "', count = '" + newCount
//                + "', borrowing_count = '" + newBorrowingCount//
//                + "', borrowed_user_count = '" + newBorrowedUserCount//
//                + "', predetermine_count = '" + newPredetermineCount//
                + "' where id = " + ID + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
//            ErrorAlert.popAlert(response,"修改成功！",null);
        } catch (Exception e) {
            ErrorAlert.popAlert(response, "数据库访问出错，修改失败");
        }
        return "redirect:/manager/book/index";
    }
}
