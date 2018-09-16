package librarysystem.libraryapi.controller.common;

import com.sun.net.httpserver.spi.HttpServerProvider;
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
        if (type == 0) {//学生
            String sql = "SELECT password FROM user where user_id = " + userName+ ";";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            try {
                String DBpassword = null;
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {

                    DBpassword = result.getString("password");
                }
                if (DBpassword.equals(password)) {
//                    model.addAttribute("user_type", 0);
//                    RequestDispatcher rd = httpServletRequest.getRequestDispatcher("student/index.html");
//                    rd.forward(httpServletRequest,response);
                    result.close();
                    dbManager.close();
                    return "redirect:/student/index";
                } else {//这还缺少一个提醒账号密码错误
                    System.out.println("错了");
                }

            } catch (Exception e) {
                System.out.println("e错了");
            }
        } else {
            String sql = "SELECT user_id,password FROM manager";
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            try {
                result = dbManager.preparedStatement.executeQuery();
                String DBuserName = result.getString("user_id");
                String DBpassword = result.getString("password");
                if (DBuserName == userName && DBpassword == password) {
                    model.addAttribute("user_type", 1);
                } else {

                }
                result.close();
                dbManager.close();
            } catch (Exception e) {

            }
        }
        return "/login";
    }

//    public void popAlert() {
//
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//        out.print("<script>alert('您还没有登录，请登录...'); window.location='userlogin.html' </script>");
//        out.flush();
//        out.close();
//
//    }
}
