
public class Book{
    private String isbn;
    private String title;
    private Author author;
    private int publishdate;
    private boolean isAvailable;
    private Author authorForBook;

    public Book(String title, String isbn, int publishdate, Author authorForBook, boolean isAvailable) {
    this.isbn=isbn;
    this.title=title;
    this.publishdate=publishdate;
    this.isAvailable=isAvailable;
    this.author=authorForBook;
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishdate() {
        return publishdate;
    }

    public boolean isAvailable(){return  isAvailable;}

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublishdate(int publishdate) {
        this.publishdate = publishdate;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Author getAuthorForBook() {
        return authorForBook;
    }

    public void setAuthorForBook(Author authorForBook) {
        this.authorForBook = authorForBook;
    }
}
