package librarysystem.libraryapi.Bean;

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
}
