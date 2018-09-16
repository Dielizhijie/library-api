package librarysystem.libraryapi.Bean;

import java.sql.Date;

public class Book {
    public int id;//id
    public String name;//书名
    public int class_id;//书的种类
    public String author;//作者
    public String cover;//书的封面
    public String details;//书的详细信息
    public String publication_date;//出版日期
    public String location;//图书馆再馆位置
    public int count;//图书总数
    public int borrowing_count;//图书背借人数
    public int borrowed_user_count;//图书曾被借人数
    public int predetermine_count;//图书预定人数
}
