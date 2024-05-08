import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MenuAdministrador {
    Scanner scanner = new Scanner(System.in);
    private final Administrador superAdmin;
    private final ClientController clientController;
    private final AuthorController authorController;
    private final BookController bookController;
    private final AdministradorController administradorController;
    private final Menu menu;

    public MenuAdministrador(Administrador superAdmin, ClientController clientController, AuthorController authorController, BookController bookController, AdministradorController administradorController,Menu menu) {
        this.superAdmin = superAdmin;
        this.clientController = clientController;
        this.authorController = authorController;
        this.bookController = bookController;
        this.administradorController = administradorController;
        this.menu=menu;
        setupMenu();
    }
    private void setupMenu() {
        menu.addOption("1", () -> {
            System.out.println("¿Qué acción quieres realizar?");
            System.out.println("1. Crear entidad");
            System.out.println("2. Actualizar entidad");
            int action1 = scanner.nextInt();
            scanner.nextLine();
            if (action1 == 1) {
                System.out.println("¿Qué entidad quieres crear?");
                System.out.println("1. Cliente");
                System.out.println("2. Libro");
                System.out.println("3. Administrador");
                System.out.println("4. Autor");

                int type1 = scanner.nextInt();
                scanner.nextLine();
                switch (type1) {
                    case 1:
                        clientController.createClient();
                        break;
                    case 2:
                        bookController.createBook();
                        break;
                    case 3:
                        administradorController.createAdmin();
                        break;
                    case 4:
                        authorController.createAuthor();
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } else if (action1 == 2) {
                System.out.println("¿Qué entidad quieres actualizar?");
                System.out.println("1. Cliente");
                System.out.println("2. Libro");
                System.out.println("3. Administrador");
                System.out.println("4. Autor");

                int type2 = scanner.nextInt();
                scanner.nextLine();
                switch (type2) {
                    case 1:
                        clientController.updateClient();
                        break;
                    case 2:
                        bookController.updateBook();
                        break;
                    case 3:
                        administradorController.updateAdmin();
                        break;
                    case 4:
                        authorController.updateAuthor();
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } else {
                System.out.println("Opción inválida");
            }
        });
        menu.addOption("2", () -> {
            System.out.println("¿Qué entidad quieres leer?");
            System.out.println("1. Cliente");
            System.out.println("2. Libro");
            System.out.println("3. Administrador");
            System.out.println("4. Autor");
            HashMap<Integer, Runnable> readEntityOptions = new HashMap<>();
            readEntityOptions.put(1, clientController::readClient);
            readEntityOptions.put(2, () -> {
                System.out.println("Listado de libros: ");
                bookController.showAllBooks();
                System.out.println("Libros disponibles");
                bookController.showAvailableBooks();
                System.out.println("Libros prestados: ");
                bookController.showBorrowedBooks();
            });
            readEntityOptions.put(3, administradorController::readAdmin);
            readEntityOptions.put(4, authorController::readAuthor);

            int type43 = scanner.nextInt();
            scanner.nextLine();
            Runnable action = readEntityOptions.get(type43);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Opción inválida");
            }
        });
        menu.addOption("3", () -> {
            System.out.println("¿Qué entidad quieres borrar?");
            System.out.println("1. Cliente");
            System.out.println("2. Libro");
            System.out.println("3. Administrador");
            System.out.println("4. Autor");
            int type36 = scanner.nextInt();
            HashMap<Integer, Runnable> deleteEntityOptions = new HashMap<>();
            deleteEntityOptions.put(1, clientController::deleteClient);
            deleteEntityOptions.put(2, bookController::deleteBook);
            deleteEntityOptions.put(3, administradorController::deleteAdmin);
            deleteEntityOptions.put(4, authorController::deleteAuthor);
            scanner.nextLine();
            Runnable action = deleteEntityOptions.get(type36);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Opción inválida");
            }
        });
        menu.addOption("4", () -> System.out.println("Adios humano"));
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Menú de Administrador:");
            ArrayList<Administrador.Permissions> permissions = superAdmin.getPermissions();
            for (Administrador.Permissions permission : permissions) {
                switch (permission) {
                    case WRITE:
                        System.out.println("1. Opcion de escribir: ");
                        break;
                    case READ:
                        System.out.println("2. Opcion de leer: ");
                        break;
                    case DELETE:
                        System.out.println("3. Opcion de borrar: ");
                }
            }
            System.out.println("4. Salir del menu");

            String choice = scanner.next();

            if ("4".equals(choice)) {
                exit = true;
            }

            menu.executeOption(choice);
        }
    }
}
