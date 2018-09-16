package librarysystem.libraryapi.Bean;

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
    public String studentID;

    private final static User instance = new User();

    private User() {

    }

    public static User getInstance() {
        return instance;
    }
}
