import java.util.ArrayList;

public class Client {
    Profile profile;
    ArrayList<Book>borrowedBooks;

    public Client(Profile profile, ArrayList<Book> borrowedBooks) {
        this.profile = profile;
        this.borrowedBooks=borrowedBooks;
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

    public void borrowedBook(Book book){
        borrowedBooks.add(book);
    }
}
