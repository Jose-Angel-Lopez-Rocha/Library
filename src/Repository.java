import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Repository{
    ArrayList<Author> authors = new ArrayList<>();
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Transaction> loan = new ArrayList<>();
    ArrayList<Transaction> devolution = new ArrayList<>();
    ArrayList<Transaction>transactions=new ArrayList<>();
    ArrayList<Administrador> administradores = new ArrayList<>();
    ArrayList<User>users=new ArrayList<>();
    ArrayList<Administrador.Permissions>permissions=new ArrayList<>();


    public void initialize(){

            Author author1 = new Author(new Profile("Jorge","Ramirez",1997));
            Author author2 = new Author(new Profile("Susana","Reyes",1998));
            authors.add(author1);
            authors.add(author2);


            Book book1 = new Book("Pollos hermanos", "32324", 2000, authors.get(0), true);
            Book book2 = new Book("Majula", "4234", 2014, authors.get(1),true);
            books.add(book1);
            books.add(book2);
            authors.get(0).addBook(book1);
            authors.get(1).addBook(book2);


            ArrayList<Book> borrowedBooks1 = new ArrayList<>();
            ArrayList<Book> borrowedBooks2 = new ArrayList<>();
            Client client1= new Client(new Profile("Jose Manuel","Gutirrez", 2001),"Cliente1","12345",borrowedBooks1);
            Client client2=new Client(new Profile("Saul","Ramirez",2003),"Cliente2","0987",borrowedBooks2);
            clients.add(client1);
            clients.add(client2);
            users.add(client1);
            users.add(client2);



            ArrayList<Administrador.Permissions> permissions1 = new ArrayList<>();
            permissions1.add(Administrador.Permissions.READ);
            permissions1.add(Administrador.Permissions.WRITE);

            permissions.addAll(permissions1);


            ArrayList<Administrador.Permissions> permissions2 = new ArrayList<>();
            permissions2.add(Administrador.Permissions.READ);
            permissions2.add(Administrador.Permissions.WRITE);
            permissions2.add(Administrador.Permissions.DELETE);

            permissions.addAll(permissions2);


            Administrador admin1 = new Administrador(new Profile("Carlos", "Gomez", 1980), "Admin1", "password1", permissions1, false);
            Administrador admin2 = new Administrador(new Profile("Ana", "Lopez", 1985), "Admin2", "password2", permissions2, true);

            administradores.add(admin1);
            administradores.add(admin2);

            users.add(admin1);
            users.add(admin2);


        for (Client client : clients) {
            for (Book book : client.getBorrowedBooks()) {
                Transaction transaction = new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(), client, book, LocalDate.now());
                loan.add(transaction);
            }
        }
    }
}