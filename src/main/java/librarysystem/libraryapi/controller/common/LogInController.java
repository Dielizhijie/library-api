package librarysystem.libraryapi.controller.common;

import com.sun.net.httpserver.spi.HttpServerProvider;
import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;

@Controller
@RequestMapping()
public class LogInController {
    private int type;
    private String userName;
    private String password;

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest, Model model, HttpServletResponse response) {
        userName = httpServletRequest.getParameter("userName");
        password = httpServletRequest.getParameter("password");
        type = Integer.parseInt(httpServletRequest.getParameter("user_type"));
        if (type == 0) {
            //学生
            String sql = "SELECT password FROM user where user_id = " + userName + ";";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;

            try {
                String DBpassword = null;
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {
                    DBpassword = result.getString("password");
                }
                if (DBpassword.equals(password)) {
                    result.close();
                    dbManager.close();
                    getUserDetail(userName, 0);
                    return "redirect:/student/detail";
                } else {//这还缺少一个提醒账号密码错误
                    System.out.println("错了");
                }
            } catch (Exception e) {
                System.out.println("e错了");
            }
        } else {
            String sql = "SELECT password FROM manager where user_id = " + userName + ";";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            try {
                String DBpassword = null;
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {
                    DBpassword = result.getString("password");
                }
                if (DBpassword.equals(password)) {
                    result.close();
                    dbManager.close();
                    getUserDetail(userName, 1);
                    return "redirect:/manager/detail";
                } else {
                    System.out.println("e1错了");
                }

            } catch (Exception e) {
                System.out.println("e2错了");
            }
        }
        return "/login";
    }

    private void getUserDetail(String userName, int type) {
        if (type == 0) {
            String sql = "SELECT * FROM user where user_id = " + userName + ";";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            try {
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {
                    User.instance.name = result.getString("name");
                    User.instance.user_id = result.getString("user_id");
                    User.instance.password = result.getString("password");
                    User.instance.phone = result.getString("phone");
                    User.instance.academy = result.getString("academy");
                    User.instance.major = result.getString("major");
                    User.instance.sex = Integer.parseInt(result.getString("sex"));
                    User.instance.grade = Integer.parseInt(result.getString("grade"));
                    User.instance.card = Integer.parseInt(result.getString("card"));
                    User.instance.credit = Integer.parseInt(result.getString("credit"));
                }
                result.close();
                dbManager.close();
            } catch (Exception e) {

            }
        } else {
            String sql = "SELECT * FROM manager where user_id = " + userName + ";";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            try {
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {
                    Manager.instance.name = result.getString("name");
                    Manager.instance.user_id = result.getString("user_id");
                    Manager.instance.password = result.getString("password");
                    Manager.instance.work_id = result.getString("work_id");
                }
                result.close();
                dbManager.close();
            } catch (Exception e) {

            }
        }

    }
}
