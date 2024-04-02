import java.util.Scanner;

public class ConsoleReader {
    AuthorController authorController = new AuthorController();
    BookController bookController=new BookController();
    ClientController clientController=new ClientController();
    MovementReport movementReport=new MovementReport();
    TransactionController transactionController=new TransactionController();
    Scanner scanner=new Scanner(System.in);
    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de General ----");
            System.out.println("1. Menu de autores");
            System.out.println("2. Menu de libros");
            System.out.println("3. Menu de clientes");
            System.out.println("4. Menu de transacciones");
            System.out.println("5. Menu de movimientos ");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    authorController.displayMenu();
                    break;
                case 2:
                    bookController.displayMenu();
                    break;
                case 3:
                    clientController.displayMenu();
                    break;
                case 4:
                    transactionController.displayMenu();
                    break;
                case 5:
                    movementReport.displayMenu();
                    break;
                case 6:
                    exit=true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
            }
        }
    }
}
