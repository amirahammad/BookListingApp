package tasks.com.booklistingapp;


public class Pojo {
    private   String bookName;
    private   String authorName;
    private   String description;



    public Pojo(String bookName, String authorName, String description) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;


    }

    public String getBookName() {
        return bookName;
    }
    public String getAuthorNmae() {
        return authorName;
    }
    public String getDescription() {
        return description;
    }

}
