package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Book;
import librarysystem.libraryapi.Bean.Student;
import librarysystem.libraryapi.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerStudentController {

    @RequestMapping("/student")
    public String ManageDetail(Model model){
        String sql = "SELECT * FROM book";
        DBManager dbManager = new DBManager(sql);
        ResultSet result = null;
        List<Student> studentList = new ArrayList<>();
        try {
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()){
                Student student = new Student();
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
        }
        catch (Exception e){

        }
        model.addAttribute("studentList", studentList);
        return "manager/student";
    }
}
