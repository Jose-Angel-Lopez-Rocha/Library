import java.util.Scanner;

public class Login{

    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public Login(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }

    public void start() {
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            System.out.println("Clientes: ");
            for(Client client: seeder.userRepository.clients){
                System.out.print(" - " + client.getUsername() + "\n");
            }
            System.out.println("Administradores: ");
            for (Administrador administrador: seeder.userRepository.administradores){
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

            System.out.println("¿Desea regresar al login? (s/n)");
            String option = scanner.nextLine();
            if (!option.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        System.out.println("Programa terminado.");
    }

    public User findUserByUsername(String username) {
        for (User user : seeder.userRepository.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    private void displayMenu(User user) {
        if (user instanceof Administrador) {
            ClientController clientController = new ClientController(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
            AuthorController authorController = new AuthorController(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
            BookController bookController = new BookController(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
            AdministradorController administradorController = new AdministradorController(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
            Menu menu=new Menu();
            MenuAdministrador menuAdministrador = new MenuAdministrador((Administrador) user, clientController, authorController, bookController, administradorController,menu);
            menuAdministrador.displayMenu();

        } else if (user instanceof Client) {
            TransactionController transactionController = new TransactionController(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
            MovementReport movementReport = new MovementReport(seeder);
            Menu menu=new Menu();
            MenuClient menuClient= new MenuClient(transactionController,movementReport,menu);
            menuClient.displayMenu((Client) user);
        }
    }
}

