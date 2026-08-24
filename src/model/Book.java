package model;

public class Book {
    
    private int bookID;
    private String bookTitle;
    private String bookAuthor;
    private String bookCategory;
    private BookStatus bookStatus;
    

    public Book(int bookID, String bookTitle, String bookAuthor, String bookCategory, String bookStatus) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookStatus = BookStatus.valueOf(bookStatus);
    }

    //Setters
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
    public void setBookStatus(String bookStatus) {
        this.bookStatus = BookStatus.valueOf(bookStatus);
    }

    //Getters
    public int getBookID() {
        return bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public String getBookCategory() {
        return bookCategory;
    }
    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
