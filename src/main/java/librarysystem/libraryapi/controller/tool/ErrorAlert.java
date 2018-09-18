package librarysystem.libraryapi.controller.tool;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ErrorAlert {
    public static void popAlert(HttpServletResponse response, String string) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('" + string + "'); window.location='loginPage' </script>");
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }
}
