import java.util.ArrayList;
import java.util.Scanner;

public class ClientController{
    Scanner scanner=new Scanner(System.in);

    private final Repository repository;
    public ClientController(Repository repository) {
        this.repository = repository;
    }

    public void createClient(){
        System.out.println("Ingresa el nombre del cliente: ");
        String name=scanner.nextLine();
        System.out.println("Ingresa el apellido del cliente: ");
        String lastName=scanner.nextLine();
        System.out.println("Ingresa el año de nacimiento: ");
        int birthDate=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingresa el nombre de usuario del cliente: ");
        String username=scanner.nextLine();
        System.out.println("Ingresa la contraseña del cliente: ");
        String password=scanner.nextLine();

        Client newClient=new Client(new Profile(name,lastName,birthDate),username,password,new ArrayList<>());
        repository.clients.add(newClient);
        repository.users.add(newClient);
    }
    public void readClient(){
        for (Client client : repository.clients) {
            System.out.println("Perfil del Cliente: \n");
            System.out.println("Nombre: " + client.getProfile().getName() + "\n");
            System.out.println("Apellido: " + client.getProfile().getLastName() + "\n");
            System.out.println("Fecha de nacimiento: " + client.getProfile().getBirthDate() + "\n");
            System.out.println("Contraseña hasheada: " + client.getPassword() + "\n");
            System.out.println("Libros prestados:" + "\n");
            ArrayList<Book> borrowedBooks = client.getBorrowedBooks();
            if (borrowedBooks.isEmpty()) {
                System.out.println("El cliente no tiene libros prestados.");
            } else {
                for (Book book:client.getBorrowedBooks()) {
                    System.out.println("- " + book.getTitle());
                }
            }
            System.out.println(); // Agregar una línea en blanco para separar los datos de cada cliente
        }
    }
    public void updateClient(){
        if(repository.clients.isEmpty()){
            System.out.println("No hay clientes para actualizar.");
            return;
        }

        System.out.println("Seleccione el cliente que desea actualizar:");
        for(int i = 0; i < repository.clients.size(); i++){
            System.out.println((i+1) + ". " + repository.clients.get(i).getProfile().getName());
        }

        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(clientIndex < 0 || clientIndex >= repository.clients.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Client client = repository.clients.get(clientIndex);

        System.out.println("Ingrese el nuevo nombre del cliente:");
        String newName = scanner.nextLine();
        client.getProfile().setName(newName);

        System.out.println("Ingrese el nuevo apellido del cliente:");
        String newLastName = scanner.nextLine();
        client.getProfile().setLastName(newLastName);

        System.out.println("Ingrese el nuevo año de nacimiento del cliente:");
        int newBirthDate = scanner.nextInt();
        scanner.nextLine(); // consume newline
        client.getProfile().setBirthDate(newBirthDate);

        System.out.println("Ingresa el nuevo nombre de usuario del cliente: ");
        String newUserName = scanner.nextLine();
        client.setUsername(newUserName);

        System.out.println("Información del cliente actualizada con éxito.");
    }
    public void deleteClient() {
        if(repository.clients.isEmpty()){
            System.out.println("No hay clientes para eliminar.");
            return;
        }

        System.out.println("Seleccione el cliente que desea eliminar:");
        for(int i = 0; i < repository.clients.size(); i++){
            System.out.println((i+1) + ". " + repository.clients.get(i).getProfile().getName());
        }

        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(clientIndex < 0 || clientIndex >= repository.clients.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Client clientToRemove = repository.clients.get(clientIndex);

        // Verificar si el cliente tiene libros prestados
        if (clientToRemove.getBorrowedBooks().isEmpty()) {
            repository.clients.remove(clientToRemove);
            System.out.println("Cliente " + clientToRemove.getProfile().getName() + " eliminado correctamente.");
        } else {
            System.out.println("No se puede eliminar el cliente " + clientToRemove.getProfile().getName() + " porque tiene libros en su poder.");
        }
    }

}