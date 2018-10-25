package librarysystem.libraryapi.controller.tool;

import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
    //用于连接数据库
    private static final String url = "jdbc:mysql://45.76.65.63:3306/library_manager?useSSL=true&characterEncoding=utf-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "library_mysql_manager";
    private static final String password = "library_123456";
    public Connection connection = null;
    public PreparedStatement preparedStatement = null;
    public SQLConnecter sqlConnecter = null;

    public DBManager(String sql) throws Exception {
        Class.forName(name);
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(sql);
    }

    public void close() {
        try {
            this.connection.close();
            this.preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //这里使用了回调机制减少代码量
//    public static abstract class SQLConnecter {
//        String sql;
//        ResultSet resultSet = null;
//        HttpServletResponse response;
//        String alertString;
//
//        SQLConnecter(String sql, HttpServletResponse response, String alertString) {
//            this.sql = sql;
//            this.response = response;
//            getResult();
//        }
//
//        SQLConnecter(String sql, HttpServletResponse response) {
//            this.sql = sql;
//            this.response = response;
//            getResult();
//        }
//
//        private void getResult() {
//            try {
//                DBManager dbManager = new DBManager(sql);
//                dbManager.preparedStatement.executeUpdate();
//                dbManager.close();
//            } catch (Exception e) {
//                Alert.popErrorAlert(response, "数据库访问出错");
//            }
//        }
//
//        public abstract void onResult();

//        public Builder getNewBuilder (){
//            return new Builder(this);
//        }

    //本来想用builder模式去创建这个匿名内部类，但是发现无法返回
//        public static class Builder {
//            String sql;
//            ResultSet resultSet = null;
//            HttpServletResponse response;
//            String alertString;
//
//            Builder (SQLConnecter connecter){
//                this.sql = connecter.sql;
//                this.response = connecter.response;
//                this.alertString = connecter.alertString;
//            }
//
//            public Builder setSQL (String sql){
//                this.sql = sql;
//                return this;
//            }
//
//            public Builder setResponse (HttpServletResponse response){
//                this.response = response;
//                return this;
//            }
//
//            public Builder setAlertString (String alertString){
//                this.alertString = alertString;
//                return this;
//            }
//
//            public SQLConnecter build(){
//                return new SQLConnecter();
//            }
//        }
//    }

}
