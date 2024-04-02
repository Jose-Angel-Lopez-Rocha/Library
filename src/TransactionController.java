import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController extends Repository {

    Scanner scanner=new Scanner(System.in);

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;  // Devuelve null si no se encuentra el libro
    }
    public void showAvailableBooksAndClients() {
        System.out.println("Libros disponibles: ");
        for (Book book : books) {
            System.out.println(" - " + book.getTitle());
        }

        System.out.println("Clientes disponibles: ");
        for (Client client : clients) {
            System.out.println(" - " + client.getProfile().getName());
        }
    }

    public Client findClientByName(String name) {
        for (Client client : clients) {
            if (client.getProfile().getName().equals(name)) {
                return client;
            }
        }
        return null;  // Devuelve null si no se encuentra el cliente
    }

    public void showClientsWithBorrowedBooks() {
        System.out.println("Clientes con libros prestados: ");
        for (Client client : clients) {
            if (!client.getBorrowedBooks().isEmpty()) {
                System.out.println(" - " + client.getProfile().getName());
            }
        }
    }
    public void borrowBook(String bookTitle, String clientName) {
        Book book = findBookByTitle(bookTitle);
        Client client = findClientByName(clientName);
        if (book != null && client != null) {
            if (client.getBorrowedBooks().size() < 3) {
                client.borrowedBook(book);
                loan.add(new Transaction(Transaction.TransactionType.LOAN,UUID.randomUUID().toString(),client,book,LocalDate.now()));
                System.out.println("Libro prestado correctamente.");
            } else {
                System.out.println("El cliente ya tiene 3 libros prestados.");
            }
        } else {
            System.out.println("Libro o cliente no encontrado.");
        }
    }
    public void returnBook() {
        showClientsWithBorrowedBooks();
        System.out.println("Ingrese el nombre del cliente:");
        String clientName = scanner.nextLine();
        System.out.println("Ingrese el título del libro:");
        String bookTitle = scanner.nextLine();
        Client client = findClientByName(clientName);
        Book book = findBookByTitle(bookTitle);
        if (client != null && book != null && client.getBorrowedBooks().contains(book)) {
            client.getBorrowedBooks().remove(book);
            devolution.add(new Transaction(Transaction.TransactionType.DEVOLUTION, UUID.randomUUID().toString(), client, book, LocalDate.now()));
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("Cliente o libro no encontrado.");
        }
    }
    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de Transacciones ----");
            System.out.println("1  Mostrar clientes con libros prestados");
            System.out.println("2. Mostrar libros y clientes disponibles");
            System.out.println("3. Pedir prestamo un de libro");
            System.out.println("4. Devolver un libro");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    showClientsWithBorrowedBooks();
                    break;
                case 2:
                    showAvailableBooksAndClients();
                    break;
                case 3:
                    System.out.println("Ingrese el título del libro:");
                    String bookTitle = scanner.nextLine();
                    System.out.println("Ingrese el nombre del cliente:");
                    String clientName = scanner.nextLine();
                    borrowBook(bookTitle, clientName);
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    exit=true;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}