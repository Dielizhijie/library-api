package librarysystem.libraryapi.controller.common;

import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.Bean.User;
import librarysystem.libraryapi.controller.tool.DBManager;
import librarysystem.libraryapi.controller.tool.ErrorAlert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//登录的跳转链接
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        userName = httpServletRequest.getParameter("userName");
        password = httpServletRequest.getParameter("password");
        type = Integer.parseInt(httpServletRequest.getParameter("user_type"));
        if (type == 0) {
            //学生
            String sql = "SELECT password FROM user where user_id = " + userName + ";";
            try {
                DBManager dbManager = new DBManager(sql);
                ResultSet result;
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
                    ErrorAlert.popAlert(response,"密码错误");
                }
            } catch (Exception e) {
                ErrorAlert.popAlert(response,"账号或账号类型错误");
            }
        } else if (type == 1){//管理员登录界面
            String sql = "SELECT password FROM manager where user_id = " + userName + ";";
            try {
                DBManager dbManager = new DBManager(sql);
                ResultSet result = null;
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
                    ErrorAlert.popAlert(response,"密码错误");
                }
            } catch (Exception e) {
                ErrorAlert.popAlert(response,"账号或账号类型错误");
            }
        }else {
            ErrorAlert.popAlert(response,"未输入用户类型");
        }
        return "/login";
    }

    //当验证用户账号密码成功的时候把用户的个人信息存储下来
    private void getUserDetail(String userName, int type) {
        if (type == 0) {
            Manager.getInstance().updateData(userName);
        } else {
            User.getInstance().updateData(userName);
        }
    }
}
