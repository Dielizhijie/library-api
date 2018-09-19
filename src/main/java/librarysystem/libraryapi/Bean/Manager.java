package librarysystem.libraryapi.Bean;

import librarysystem.libraryapi.controller.tool.DBManager;

import java.sql.ResultSet;

public class Manager {
    public int id;//id
    public String name;//管理员名称
    public String user_id;//管理员账号
    public String password;//管理员密码
    public String work_id;//职工号

    private final static Manager instance = new Manager();

    private Manager() {

    }

    public static Manager getInstance() {
        return instance;
    }

    public Manager setID (int id){
        this.id = id;
        return this;
    }
    public Manager setName (String name){
        this.name = name;
        return instance;
    }
    public Manager setUserID (String user_id){
        this.user_id = user_id;
        return instance;
    }
    public Manager setPassword (String password){
        this.password = password;
        return instance;
    }
    public Manager setWorkID (String work_id){
        this.work_id = work_id;
        return instance;
    }

    public void onLogout (){
        Manager.getInstance().setWorkID(null)
                .setPassword(null)
                .setUserID(null)
                .setName(null);
    }
    public void updateData (String userName){
        String sql = "SELECT * FROM user where user_id = " + userName + ";";
        ResultSet result = null;
        try {
            DBManager dbManager = new DBManager(sql);
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                User.getInstance().setName(result.getString("name"))
                        .setID(Integer.valueOf(result.getString("id")))
                        .setUserID(result.getString("user_id"))
                        .setPassword(result.getString("password"))
                        .setPhone(result.getString("phone"))
                        .setAcademy(result.getString("academy"))
                        .setMajor(result.getString("major"))
                        .setGrade(Integer.parseInt(result.getString("grade")))
                        .setSex(Integer.parseInt(result.getString("sex")))
                        .setCard(Integer.parseInt(result.getString("card")))
                        .setCredit(Integer.parseInt(result.getString("credit")));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
    }
}
