import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdministrador {
    Scanner scanner = new Scanner(System.in);
    private final Administrador superAdmin;
    private final ClientController clientController;
    private final AuthorController authorController;
    private final BookController bookController;
    private final AdministradorController administradorController;

    public MenuAdministrador(Administrador superAdmin, ClientController clientController, AuthorController authorController, BookController bookController, AdministradorController administradorController) {
        this.superAdmin = superAdmin;
        this.clientController = clientController;
        this.authorController = authorController;
        this.bookController = bookController;
        this.administradorController = administradorController;
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

            int next1 = scanner.nextInt();
            scanner.nextLine();
            switch (next1) {
                case 1:
                    System.out.println("¿Qué acción quieres realizar?");
                    System.out.println("1. Crear entidad");
                    System.out.println("2. Actualizar entidad");
                    int action1 = scanner.nextInt();
                    switch (action1) {
                        case 1:
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
                            break;
                        case 2:
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
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                    break;
                case 2:
                    System.out.println("¿Qué entidad quieres leer?");
                    System.out.println("1. Cliente");
                    System.out.println("2. Libro");
                    System.out.println("3. Administrador");
                    System.out.println("4. Autor");

                    int type43 = scanner.nextInt();
                    scanner.nextLine();
                    switch (type43) {
                        case 1:
                            clientController.readClient();
                            break;
                        case 2:
                            System.out.println("Listado de libros: ");
                            bookController.showAllBooks();
                            System.out.println("Libros disponibles");
                            bookController.showAvailableBooks();
                            System.out.println("Libros prestados: ");
                            bookController.showBorrowedBooks();
                            break;
                        case 3:
                            administradorController.readAdmin();
                            break;
                        case 4:
                            authorController.readAuthor();
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                    break;
                case 3:
                    System.out.println("¿Qué entidad quieres borrar?");
                    System.out.println("1. Cliente");
                    System.out.println("2. Libro");
                    System.out.println("3. Administrador");
                    System.out.println("4. Autor");

                    int type36 = scanner.nextInt();
                    scanner.nextLine();
                    switch (type36) {
                        case 1:
                            clientController.deleteClient();
                            break;
                        case 2:
                            bookController.deleteBook();
                            break;
                        case 3:
                            administradorController.deleteAdmin();
                            break;
                        case 4:
                            authorController.deleteAuthor();
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
