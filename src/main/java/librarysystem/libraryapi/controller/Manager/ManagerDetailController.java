package librarysystem.libraryapi.controller.Manager;

import librarysystem.libraryapi.Bean.Manager;
import librarysystem.libraryapi.controller.tool.DBManager;
import librarysystem.libraryapi.controller.tool.Alert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/manager")
public class ManagerDetailController {

    @RequestMapping("/detail")
    public String ManageDetail(Model model){
        model.addAttribute("name", Manager.getInstance().name);
        model.addAttribute("user_id",Manager.getInstance().user_id);
        model.addAttribute("password",Manager.getInstance().password);
        return "manager/detail";
    }

    @RequestMapping(value = "/detail/edit", method = RequestMethod.POST)
    public String ManageDetail(HttpServletResponse response, HttpServletRequest request){
//        Alert.popAlert(response,"您确定要修改吗",null);
        String newName = request.getParameter("name");
        String newUserID = request.getParameter("user_id");
        String newPassword = request.getParameter("password");
        String sql= "Update manager set name = '" + newName
                + "', user_id = '" + newUserID
                + "', password = '" + newPassword
                + "' where id = " + Manager.getInstance().id + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
            Manager.getInstance().updateData(newUserID);
//            Alert.popAlert(response,"修改成功！",null);
        }
        catch (Exception e){
            Alert.popErrorAlert(response,"数据库访问出错，修改失败");
        }
        return "manager/detail";
    }
}
