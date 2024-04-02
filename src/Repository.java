import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Repository {
        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Transaction>loan=new ArrayList<>();
        ArrayList<Transaction>devolution=new ArrayList<>();
        ArrayList<Book>borrowedBooks =new ArrayList<>();

        public Repository() {
            AuthorRepository();
            BookRepository();
            ClientRepository();
            TransactionRepository();
        }

        private void AuthorRepository(){
            Author author1 = new Author(new Profile("Jorge","Ramirez",1997));
            Author author2 = new Author(new Profile("Susana","Reyes",1998));
            authors.add(author1);
            authors.add(author2);
        }

        private void BookRepository() {

            Book book1 = new Book("Pollos hermanos", "32324", 2000, authors.get(0), true);
            Book book2 = new Book("Majula", "4234", 2014, authors.get(1),true);
            books.add(book1);
            books.add(book2);
            authors.get(0).addBook(book1);
            authors.get(1).addBook(book2);
        }

        private void ClientRepository(){
            ArrayList<Book> borrowedBooks1 = new ArrayList<>();
            ArrayList<Book> borrowedBooks2 = new ArrayList<>();
            Client client1= new Client(new Profile("Jose Manuel","Gutirrez", 2001), borrowedBooks1);
            Client client2=new Client(new Profile("Saul","Ramirez",2003), borrowedBooks2);
            clients.add(client1);
            clients.add(client2);
            borrowedBooks.add(books.get(0));
            borrowedBooks.add(books.get(1));

        }
        private void TransactionRepository(){
            Transaction loan1=new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(),clients.get(0),books.get(0),LocalDate.now());
            loan.add(loan1);
            Transaction loan2=new Transaction(Transaction.TransactionType.LOAN,UUID.randomUUID().toString(),clients.get(1),books.get(1),LocalDate.now().plusDays(1));
            loan.add(loan2);
            Transaction devolution1=new Transaction(Transaction.TransactionType.DEVOLUTION,UUID.randomUUID().toString(),clients.get(0),books.get(0),LocalDate.now().plusDays(2));
            devolution.add(devolution1);
            Transaction devolution2=new Transaction(Transaction.TransactionType.DEVOLUTION,UUID.randomUUID().toString(),clients.get(1),books.get(1), LocalDate.now().plusDays(3));
            devolution.add(devolution2);
        }
    }