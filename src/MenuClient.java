import java.util.Scanner;

public class MenuClient{

    private final TransactionController transactionController;
    private final MovementReport movementReport;
    Scanner scanner = new Scanner(System.in);


    public MenuClient(TransactionController transactionController, MovementReport movementReport) {
        this.transactionController = transactionController;
        this.movementReport = movementReport;
    }

    public void displayMenu() {
        boolean exit=false;
        while (!exit){
        System.out.println("Menú de Cliente:");
        System.out.println("1.- Ver libros");
        System.out.println("2.- Ver transacciones");
        System.out.println("3.- Pedir Prestado un libro");
        System.out.println("4.- Devolver un libro");
        System.out.println("5.- Salir");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                transactionController.listOfBooks();
                break;
            case 2:
                movementReport.generateReportByClient();
                break;
            case 3:
                transactionController.borrowBook();
                break;
            case 4:
                transactionController.returnBook();
                break;
            case 5:
                exit=true;
                System.out.println("Adios humano");
                break;
            default:
                System.out.println("Opcion invalida");
            }
        }
    }
}