package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Student;
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
@RequestMapping("/manager")
public class ManagerStudentController {

    @RequestMapping("/student")
    public String ManageDetail(Model model) {
        String sql = "SELECT * FROM user";
        List<Student> studentList = new ArrayList<>();
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                Student student = new Student();
                student.id = Integer.valueOf(result.getString("id"));
                student.name = result.getString("name");
                student.phone = result.getString("phone");
                student.user_id = result.getString("user_id");
                student.phone = result.getString("phone");
                student.academy = result.getString("academy");
                student.major = result.getString("major");
                student.sex = Integer.valueOf(result.getString("sex"));
                student.grade = Integer.valueOf(result.getString("grade"));
                student.card = Integer.valueOf(result.getString("card"));
                student.credit = Integer.valueOf(result.getString("credit"));
                studentList.add(student);
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
        model.addAttribute("studentList", studentList);
        return "manager/student/student";
    }

    @RequestMapping(value = "/student/restore" ,method = RequestMethod.POST)
    public String ManageDetailEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("restoreButton"));
        String sql = "update user set card = 1 where id = " + id +  ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错，删除失败");
        }
        return "redirect:/manager/student";
    }
    @RequestMapping(value = "/student/cancel" ,method = RequestMethod.POST)
    public String ManageDetailDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("cancelButton"));
        String sql = "update user set card = 0 where id = " + id +  ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
        } catch (Exception e) {
            Alert.popErrorAlert(response, "数据库访问出错，删除失败");
        }
        return "redirect:/manager/student";
    }
}
