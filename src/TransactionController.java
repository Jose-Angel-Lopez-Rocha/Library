import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController {

    Repository repository;

    public TransactionController(Repository repository) {
        this.repository = repository;
    }

    Scanner scanner = new Scanner(System.in);

    public Book findBookByTitle(String title) {
        for (Book book : repository.books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;  // Devuelve null si no se encuentra el libro
    }

    public void listOfBooks(){
        System.out.println("Libros: ");
        for(Book book: repository.books){
            System.out.println(" - " + book.getTitle() + " Estado: " + book.isAvailable());
        }
    }

    public Client findClientByName(String name) {
        for (Client client : repository.clients) {
            if (client.getProfile().getName().equals(name)) {
                return client;
            }
        }
        return null;  // Devuelve null si no se encuentra el cliente
    }


    public void showBorrowedBooks(){
        System.out.println("Libros prestados: ");
        for(Transaction transaction: repository.loan){
            if (transaction.getType() == Transaction.TransactionType.LOAN) {
                System.out.println("Libro: " + transaction.getBook().getTitle() + ", Prestado a: " + transaction.getClient().getProfile().getName());

            }
        }
    }

    public void showAvailableBooks() {
        System.out.println("Libros disponibles:");
        for (Book book : repository.books) {
            if (book.isAvailable()) {
                System.out.println("Libro: " + book.getTitle());
            }
        }
    }

    public void borrowBook() {
        showAvailableBooks();
        System.out.println("Ingrese el título del libro:");
        String bookTitle = scanner.nextLine();
        showClientsWithoutBooks();
        System.out.println("Ingrese el nombre del cliente:");
        String clientName = scanner.nextLine();
        Book book = findBookByTitle(bookTitle);
        Client client = findClientByName(clientName);
        if (book != null && client != null) {
            if (client.getBorrowedBooks().size() < 3) {
                client.borrowBook(book);
                book.setAvailable(false);
                Transaction transaction= new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(), client, book, LocalDate.now());
                repository.loan.add(transaction);
                System.out.println("Libro prestado correctamente.");
            } else {
                System.out.println("El cliente ya tiene 3 libros prestados.");
            }
        } else {
            System.out.println("Libro o cliente no encontrado.");
        }
    }

    public void returnBook() {
        showBorrowedBooks();
        System.out.println("Ingrese el nombre del cliente:");
        String clientName = scanner.nextLine();
        System.out.println("Ingrese el título del libro:");
        String bookTitle = scanner.nextLine();
        Client client = findClientByName(clientName);
        Book book = findBookByTitle(bookTitle);
        if (client != null && book != null && client.getBorrowedBooks().contains(book)) {
            client.getBorrowedBooks().remove(book);
            book.setAvailable(true);
            Transaction transaction=new Transaction(Transaction.TransactionType.DEVOLUTION,UUID.randomUUID().toString(),client,book, LocalDate.now());
            repository.devolution.add(transaction);
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("Cliente o libro no encontrado.");
        }
    }

    public void showClientsWithoutBooks() {
        System.out.println("Clientes sin libros prestados: ");
        for (Client client : repository.clients) {
            if (client.getBorrowedBooks().isEmpty()) {
                System.out.println(" - " + client.getProfile().getName());
            }
        }
    }
}