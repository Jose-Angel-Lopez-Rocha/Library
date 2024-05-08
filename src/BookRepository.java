import java.util.ArrayList;
public class BookRepository {
    ArrayList<Book> books = new ArrayList<>();
    public void initializeBooks(ArrayList<Author> authors) {
        Book book1 = new Book("Como Triunfar en Youtube", 32324, 2002, authors.get(0), true);
        Book book2 = new Book("Luna De Pluton", 4234, 2014, authors.get(1), true);
        books.add(book1);
        books.add(book2);
        authors.get(0).addBook(book1);
        authors.get(1).addBook(book2);
    }
}
