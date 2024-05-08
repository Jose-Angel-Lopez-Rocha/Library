import java.util.ArrayList;

public class UserRepository {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Administrador> administradores = new ArrayList<>();
    ArrayList<Administrador.Permissions> permissions = new ArrayList<>();
    public void initializeUsers() {
        // Inicialización de clientes
        ArrayList<Book> borrowedBooks1 = new ArrayList<>();
        ArrayList<Book> borrowedBooks2 = new ArrayList<>();
        Client client1 = new Client(new Profile("Jose", "Gutirrez", 2001), "Cliente1", "1234", borrowedBooks1);
        Client client2 = new Client(new Profile("Saul", "Ramirez", 2003), "Cliente2", "0987", borrowedBooks2);
        clients.add(client1);
        clients.add(client2);
        users.add(client1);
        users.add(client2);


        // Inicialización de administradores
        ArrayList<Administrador.Permissions> permissions1 = new ArrayList<>();
        permissions1.add(Administrador.Permissions.READ);
        permissions1.add(Administrador.Permissions.WRITE);

        permissions.addAll(permissions1);

        ArrayList<Administrador.Permissions> permissions2 = new ArrayList<>();
        permissions2.add(Administrador.Permissions.READ);
        permissions2.add(Administrador.Permissions.WRITE);
        permissions2.add(Administrador.Permissions.DELETE);

        Administrador admin1 = new Administrador(new Profile("Carlos", "Gomez", 1980), "Admin1", "password1", permissions1, false);
        Administrador admin2 = new Administrador(new Profile("Ana", "Lopez", 1985), "Admin2", "password2", permissions2, true);

        permissions.addAll(permissions2);

        administradores.add(admin1);
        administradores.add(admin2);
        users.add(admin1);
        users.add(admin2);
    }
}
