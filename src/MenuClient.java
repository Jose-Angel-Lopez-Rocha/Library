import java.util.Scanner;

public class MenuClient{

    private final TransactionController transactionController;
    private final MovementReport movementReport;
    private final Menu menu;


    Scanner scanner = new Scanner(System.in);


    public MenuClient(TransactionController transactionController, MovementReport movementReport,Menu menu) {
        this.transactionController = transactionController;
        this.movementReport = movementReport;
        this.menu=menu;
    }

    private void setupMenu(Client loggedClient){
        menu.addOption("1", transactionController::listOfBooks);
        menu.addOption("2", () ->movementReport.generateReportByClient(loggedClient));
        menu.addOption("3", ()->transactionController.borrowBook(loggedClient));
        menu.addOption("4", ()->transactionController.returnBook(loggedClient));
        menu.addOption("5", () -> System.out.println("Adios humano"));
    }
    public void displayMenu(Client loggedClient) {
        setupMenu(loggedClient);
        boolean exit = false;
        while (!exit) {
            System.out.println("Menú de Cliente:");
            System.out.println("1.- Ver libros");
            System.out.println("2.- Ver transacciones");
            System.out.println("3.- Pedir prestado libro");
            System.out.println("4.-  Devolver un libro");
            System.out.println("5.- Salir");
            String choice = scanner.next();

            if ("5".equals(choice)) {
                exit = true;
            }
            menu.executeOption(choice);
        }
    }
}