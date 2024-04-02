import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class MovementReport extends TransactionController {

    public void generateReportByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredTransactions = loan.stream()
                .filter(transaction -> !transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        System.out.println("Movimientos entre " + startDate + " y " + endDate + ":");
        for (Transaction transaction : filteredTransactions) {
            System.out.println(transaction.getType() + " - " + transaction.getBook().getTitle() + " - " + transaction.getClient().getProfile().getName());
        }
    }

    public void generateReportByClient(String clientName) {
        Client client = findClientByName(clientName);
        if (client != null) {
            List<Transaction> filteredTransactions = loan.stream()
                    .filter(transaction -> transaction.getClient().equals(client))
                    .collect(Collectors.toList());

            System.out.println("Movimientos del cliente " + clientName + ":");
            for (Transaction transaction : filteredTransactions) {
                System.out.println(transaction.getType() + " - " + transaction.getBook().getTitle());
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void generateReportByBook(String bookTitle) {
        Book book = findBookByTitle(bookTitle);
        if (book != null) {
            List<Transaction> filteredTransactions = loan.stream()
                    .filter(transaction -> transaction.getBook().equals(book))
                    .collect(Collectors.toList());

            System.out.println("Movimientos del libro " + bookTitle + ":");
            for (Transaction transaction : filteredTransactions) {
                System.out.println(transaction.getType() + " - " + transaction.getClient().getProfile().getName());
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de movimientos ----");
            System.out.println("1. Movimientos por tiempo");
            System.out.println("2. Movimientos por clientes");
            System.out.println("3. Movimientos por libros");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.println("Ingrese la fecha de inicio (formato: YYYY-MM-DD):");
                    String startDateString = scanner.nextLine();
                    LocalDate startDate = LocalDate.parse(startDateString);

                    System.out.println("Ingrese la fecha de fin (formato: YYYY-MM-DD):");
                    String endDateString = scanner.nextLine();

                    LocalDate endDate = LocalDate.parse(endDateString);
                    generateReportByDateRange(startDate,endDate);
                    break;
                case 2:
                    System.out.println("Humano ingresa el nombre del cliente para generar el reporte: ");
                    String clientName=scanner.nextLine();
                    generateReportByClient(clientName);
                    break;
                case 3:
                    System.out.println("Humano ingresa el nombre del libro para generar el reporte: ");
                    String bookTitle=scanner.nextLine();
                    generateReportByBook(bookTitle);
                    break;
                case 4:
                    exit=true;
                    break;
                default:
                    System.out.println("Humano opcion invalida vuelve a ingresar una opcion del 1-4");
            }
        }
    }
}