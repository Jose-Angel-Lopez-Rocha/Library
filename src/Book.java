
public class Book{
    private int isbn;
    private String title;
    private Author author;
    private int publishdate;
    private boolean isAvailable;


    public Book(String title, int isbn, int publishdate, Author author, boolean isAvailable) {
        this.isbn=isbn;
        this.title=title;
        this.publishdate=publishdate;
        this.isAvailable=isAvailable;
        this.author=author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(int publishdate) {
        this.publishdate = publishdate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable=isAvailable;
    }
}
