import java.util.Scanner;

public class Login{

    Repository repository;

    public Login(Repository repository) {
        this.repository=repository;
    }

    public void start() {
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            System.out.println("Clientes: ");
            for(Client client: repository.clients){
                System.out.print(" - " + client.getUsername() + "\n");
            }
            System.out.println("Administradores: ");
            for (Administrador administrador: repository.administradores){
                System.out.print(" - " + administrador.getUsername() + "\n");
            }

            System.out.println("Por favor, introduce tu nombre de usuario:");
            String username = scanner.nextLine();

            System.out.println("Por favor, introduce tu contraseña:");
            String password = scanner.nextLine();
            User user=findUserByUsername(username);
            if (user != null && user.checkPassword(password)) {
                System.out.println("Inicio de sesión exitoso.");
                displayMenu(user);
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
            }

            System.out.println("¿Desea regresar al login? (Si/No)");
            String opcion = scanner.nextLine();
            if (!opcion.equalsIgnoreCase("Si")) {
                continuar = false;
            }
        }
        System.out.println("Programa terminado.");
    }

    public User findUserByUsername(String username) {
        for (User user : repository.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    private void displayMenu(User user) {
        if (user instanceof Administrador) {
            ClientController clientController = new ClientController(repository);
            AuthorController authorController = new AuthorController(repository);
            BookController bookController = new BookController(repository);
            AdministradorController administradorController = new AdministradorController(repository);
            MenuAdministrador menuAdministrador = new MenuAdministrador((Administrador) user, clientController, authorController, bookController, administradorController);
            menuAdministrador.displayMenu();
        } else if (user instanceof Client) {
            TransactionController transactionController = new TransactionController(repository);
            MovementReport movementReport = new MovementReport(repository);
            MenuClient menuClient= new MenuClient(transactionController,movementReport);
            menuClient.displayMenu();
        }
    }
}

