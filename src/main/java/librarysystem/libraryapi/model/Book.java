package librarysystem.libraryapi.model;

import java.sql.Date;

public class Book {
    public int id;
    public String name;
    public int class_id;
    public String author;
    public String cover;
    public String details;
    public String publication_date;
    public String location;
    public int count;
    public int borrowing_count;
    public int borrowed_user_count;
    public int predetermine_count;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}
