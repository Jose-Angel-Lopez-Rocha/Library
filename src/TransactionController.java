import java.time.LocalDate;
import java.util.UUID;

public class TransactionController {
    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public TransactionController(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }
    public Book findBookByIndex(int index) {
        if (index>=0 && index< seeder.bookRepository.books.size()){
            return seeder.bookRepository.books.get(index);
            }
        return null;
    }

    public void listOfBooks(){
        System.out.println("Libros: ");
        int i=1;
        for(Book book: seeder.bookRepository.books){
            System.out.println(i + " - " + book.getTitle() + " Estado: " + book.isAvailable());
            i++;
        }
    }

    public Client findClientByIndex(int index) {
        if(index>=0 && index< seeder.userRepository.clients.size()){
            return seeder.userRepository.clients.get(index);
                }
                return null;
            }
    public void showClientsWithoutBooks() {
        System.out.println("Clientes sin libros prestados: ");
        int i=1;
        for (Client client : seeder.userRepository.clients) {
            if (client.getBorrowedBooks().isEmpty()) {
                System.out.println(i + " - " + client.getUsername());
                i++;
            }
        }
    }

    public void showBorrowedBooks(){
        System.out.println("Libros prestados: ");
        int i=1;
        int j=1;
        for(Transaction transaction: seeder.transactionRepository.transactions){
            if (transaction.getType() == Transaction.TransactionType.LOAN) {
                System.out.println(i + " - " + transaction.getBook().getTitle() + " \n  Prestado a: " + j + " - " + transaction.getClient().getUsername());
                i++;
                j++;
            }
        }
    }

    public void showAvailableBooks() {
        System.out.println("Libros disponibles:");
        int i=1;
        for (Book book : seeder.bookRepository.books) {
            if (book.isAvailable()) {
                System.out.println(i + " - " + book.getTitle());
                i++;
            }
        }
    }

    public void borrowBook(Client loggedClient) {
        showAvailableBooks();
        int bookind = consoleReader.readInt("Ingrese el numero del libro: ", input -> integerValidator.validate(input)) - 1;
        Book book = findBookByIndex(bookind);
        if (book != null && loggedClient != null) {
            if (loggedClient.getBorrowedBooks().size() < 3) {
                loggedClient.borrowBook(book);
                book.setAvailable(false);
                Transaction transaction = new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(), loggedClient, book, LocalDate.now());
                seeder.loanRepository.transactions.add(transaction);
                seeder.transactionRepository.transactions.add(transaction);
                System.out.println("Libro prestado correctamente.");
            } else {
                System.out.println("El cliente ya tiene 3 libros prestados.");
            }
        } else {
            System.out.println("Libro o cliente no encontrado.");
        }
    }
    public void returnBook(Client loggedClient) {
        showBorrowedBooks();
        int bookind = consoleReader.readInt("Ingrese el numero del libro : ", input -> integerValidator.validate(input))-1;
        Book book = findBookByIndex(bookind);
        if (loggedClient != null && book != null && loggedClient.getBorrowedBooks().contains(book)) {
            loggedClient.getBorrowedBooks().remove(book);
            book.setAvailable(true);
            Transaction transaction = new Transaction(Transaction.TransactionType.DEVOLUTION, UUID.randomUUID().toString(), loggedClient, book, LocalDate.now());
            seeder.devolutionRepository.transactions.add(transaction);
            seeder.transactionRepository.transactions.add(transaction);
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("Cliente o libro no encontrado.");
        }
    }

}