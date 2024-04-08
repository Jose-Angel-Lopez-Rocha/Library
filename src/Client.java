import java.util.ArrayList;

public class Client extends User {
    Profile profile;
    ArrayList<Book>borrowedBooks;

    public Client(Profile profile, String username, String password,ArrayList<Book> borrowedBooks) {
        super(profile, username, password);
        this.profile=profile;
        this.borrowedBooks = borrowedBooks;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

}
