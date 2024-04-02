import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;

public class Seeder {
    Repository repository;
    public Seeder(Repository repository) {
        this.repository = repository;
    }

    public void initialize() {
        // Crear autores
        Author author1 = new Author(new Profile("Jorge","Ramirez",1997));
        Author author2 = new Author(new Profile("Susana","Reyes",1998));
        repository.authors.add(author1);
        repository.authors.add(author2);

        // Crear libros
        Book book1 = new Book("Pollos hermanos", "32324", 2000, author1, true);
        Book book2 = new Book("Majula", "4234", 2014, author2, true);
        repository.books.add(book1);
        repository.books.add(book2);
        author1.addBook(book1);
        author2.addBook(book2);

        // Crear clientes
        ArrayList<Book> borrowedBooks1 = new ArrayList<>();
        ArrayList<Book> borrowedBooks2 = new ArrayList<>();
        Client client1= new Client(new Profile("Jose Manuel","Gutirrez", 2001), borrowedBooks1);
        Client client2=new Client(new Profile("Saul","Ramirez",2003), borrowedBooks2);
        repository.clients.add(client1);
        repository.clients.add(client2);
        repository.borrowedBooks.add(book1);
        repository.borrowedBooks.add(book2);

        // Crear transacciones
        Transaction loan1=new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(),client1,book1,LocalDate.now());
        repository.loan.add(loan1);
        Transaction loan2=new Transaction(Transaction.TransactionType.LOAN,UUID.randomUUID().toString(),client2,book2, LocalDate.now().plusDays(1));
        repository.loan.add(loan2);
        Transaction devolution1=new Transaction(Transaction.TransactionType.DEVOLUTION,UUID.randomUUID().toString(),client1,book1,LocalDate.now().plusDays(2));
        repository.devolution.add(devolution1);
        Transaction devolution2=new Transaction(Transaction.TransactionType.DEVOLUTION,UUID.randomUUID().toString(),client2,book2,LocalDate.now().plusDays(3));
        repository.devolution.add(devolution2);
    }
}

