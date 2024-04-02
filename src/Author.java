import java.util.ArrayList;

public class Author {
    private Profile profile;
    private ArrayList<Book> books;

    public Author(Profile profile) {
        this.profile = profile;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    // Getters y setters
    public Profile getProfile() {
        return profile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}