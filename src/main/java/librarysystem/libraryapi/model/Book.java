package librarysystem.libraryapi.model;

import java.sql.Date;

public class Book {
    private int id;
    private String name;
    private int class_id;
    private String author;
    private String cover;
    private String details;
    private String publication_date;
    private String location;
    private int count;
    private int borrowing_count;
    private int borrowed_user_count;
    private int predetermine_count;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}
