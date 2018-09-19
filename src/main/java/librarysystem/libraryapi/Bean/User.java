package librarysystem.libraryapi.Bean;

import librarysystem.libraryapi.controller.tool.DBManager;

import java.sql.ResultSet;

//这里使用了单例
public class User {
    public int id;//学生id
    public String name;//学生姓名
    public String phone;//学生手机号
    public String academy;//学生学院
    public String major;//学生专业
    public int sex;//学生性别
    public int grade;//学生年级
    public int card;//学生结束资格，0为已经被取消资格
    public int credit;//学生信誉度，0为没有过期未还的书
    public String user_id;
    public String password;

    private final static User instance = new User();

    private User() {

    }

    public static User getInstance() {
        return instance;
    }

    //这里使用了建造者模式
    public User setID(int id){
        this.id = id;
        return instance;
    }
    public User setName (String name){
        this.name = name;
        return instance;
    }
    public User setPhone (String phone){
        this.phone = phone;
        return instance;
    }
    public User setUserID (String user_id){
        this.user_id = user_id;
        return instance;
    }
    public User setPassword (String password){
        this.password = password;
        return instance;
    }
    public User setAcademy (String academy){
        this.academy = academy;
        return instance;
    }
    public User setMajor (String major){
        this.major = major;
        return instance;
    }
    public User setSex (int sex){
        this.sex = sex;
        return instance;
    }
    public User setGrade (int Grade){
        this.grade = grade;
        return instance;
    }
    public User setCard (int card){
        this.phone = phone;
        return instance;
    }
    public User setCredit (int credit){
        this.credit = credit;
        return instance;
    }

    public void onLogout (){
        User.getInstance().setName(null)
                .setID(0)
                .setUserID(null)
                .setPhone(null)
                .setUserID(null)
                .setPassword(null)
                .setAcademy(null)
                .setMajor(null)
                .setSex(0)
                .setGrade(0)
                .setCard(0)
                .setCredit(0);
    }
    public void updateData(String userName){
        String sql = "SELECT * FROM manager where user_id = " + userName + ";";

        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                Manager.getInstance().setName(result.getString("name"))
                        .setID(Integer.valueOf(result.getString("id")))
                        .setUserID(result.getString("user_id"))
                        .setPassword(result.getString("password"))
                        .setWorkID(result.getString("work_id"));
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {

        }
    }
}
