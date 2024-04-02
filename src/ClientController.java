import java.util.ArrayList;
import java.util.Scanner;

public class ClientController extends Repository {
    Scanner scanner=new Scanner(System.in);

    public void createClient(){
        System.out.println("Ingresa el nombre del cliente: ");
        String name=scanner.nextLine();
        System.out.println("Ingresa el apellido del cliente: ");
        String lastName=scanner.nextLine();
        System.out.println("Ingresa el año de nacimiento: ");
        int birthDate=scanner.nextInt();
        scanner.nextLine();

        Client newClient=new Client(new Profile(name,lastName,birthDate),new ArrayList<>());
        clients.add(newClient);
    }

    public void readClient(){
        for (Client client : clients) {
            System.out.println("Perfil del Cliente:\n");
            System.out.println("Nombre: " + client.getProfile().getName() + "\n");
            System.out.println("Apellido: " + client.getProfile().getLastName() + "\n");
            System.out.println("Fecha de nacimiento: " + client.getProfile().getBirthDate() + "\n");
            System.out.println("Libros prestados:" + "\n");
            ArrayList<Book> borrowedBooks = client.getBorrowedBooks();
            if (borrowedBooks.isEmpty()) {
                System.out.println("El cliente no tiene libros prestados.");
            } else {
                for (Book book : borrowedBooks) {
                    System.out.println("- " + book.getTitle() + "\n");
                }
            }
            System.out.println(); // Agregar una línea en blanco para separar los datos de cada cliente
        }
    }
    public void updateClient(String name){
        for(Client client:clients){
            if(client.getProfile().getName().equals(name)){
                System.out.println("Ingrese el nuevo nombre del cliente:");
                String newName = scanner.nextLine();
                client.getProfile().setName(newName);
                System.out.println("Ingrese el nuevo apellido del cliente:");
                String newLastName = scanner.nextLine();
                client.getProfile().setLastName(newLastName);
                System.out.println("Ingrese el neuvo año de nacimiento del cliente:");
                int newBirthDate = scanner.nextInt();
                client.getProfile().setBirthDate(newBirthDate);
                System.out.println("Cliente actualizado correctamente.");
            }else{
                System.out.println("Lo siento humano el cliente no existe!");
            }
        }
    }
    public void deleteClient(String name) {
        Client clientToRemove = null;

        // Buscar al cliente por nombre
        for (Client client : clients) {
            if (client.getProfile().getName().equals(name)) {
                clientToRemove = client;
                break; // Salir del bucle una vez que se encuentra el cliente
            }
        }

        if (clientToRemove != null) {
            // Verificar si el cliente tiene libros prestados
            if (clientToRemove.getBorrowedBooks().isEmpty()) {
                clients.remove(clientToRemove);
                System.out.println("Cliente " + clientToRemove.getProfile().getName() + " eliminado correctamente.");
            } else {
                System.out.println("No se puede eliminar el cliente " + clientToRemove.getProfile().getName() + " porque tiene libros en su poder.");
            }
        } else {
            System.out.println("Lo siento, el cliente con nombre " + name + " no existe.");
        }
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de clientes ----");
            System.out.println("1. Crear cliente");
            System.out.println("2. Mostrar todos los clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    createClient();
                    break;
                case 2:
                    readClient();
                    break;
                case 3:
                    System.out.println("Ingresa el nimbre del cliente a actualizar: ");
                    String name3 =scanner.nextLine();
                    updateClient(name3);
                    break;
                case 4:
                    System.out.println("Ingresa el nombre del cliente a eliminar: ");
                    String name4 =scanner.nextLine();
                    deleteClient(name4);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
            }
        }
    }
}