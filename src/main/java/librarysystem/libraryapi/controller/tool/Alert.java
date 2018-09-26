package librarysystem.libraryapi.controller.tool;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//有url但是传回的字符串是空的时候不跳转，有url并且传回字符串不为空的时候跳转到写的url里
public class Alert {
    //跳转的部分
    public static void popAlert(HttpServletResponse response, String string,String url) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (url == null){
                out.print("<script>alert('" + string + "') ;</script>");
            }else{
                out.print("<script>alert('" + string + "'); window.location='"+url+"' </script>");
            }
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }

    //跳转到登录界面，用于报错的时候
    public static void popErrorAlert(HttpServletResponse response, String string) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('" + string + "'); window.location='/loginPage' </script>");
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }
}
