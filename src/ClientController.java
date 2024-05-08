import java.util.ArrayList;

public class ClientController{
    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public ClientController(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }
    public void createClient(){
        String name=consoleReader.readString("Ingresa el nombre del cliente: ",input -> stringValidator.validate(input));
        String lastName=consoleReader.readString("Ingresa el apellido del cliente: ",input -> stringValidator.validate(input));
        int birthDate=consoleReader.readInt("Ingresa la fecha de nacimiento del cliente: ",input -> integerValidator.validate(input));
        String username=consoleReader.readString("Ingresa el nombre de usuario del cliente: ",input -> stringValidator.validate(input));
        String password=consoleReader.readString("Ingresa la contraseña del cliente: ", input -> stringValidator.validate(input));
        Client newClient=new Client(new Profile(name,lastName,birthDate),username,password,new ArrayList<>());
        seeder.userRepository.clients.add(newClient);
        seeder.userRepository.users.add(newClient);
    }
    public void readClient(){
        for (Client client : seeder.userRepository.clients) {
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
    public void updateClient() {
        if (seeder.userRepository.clients.isEmpty()) {
            System.out.println("No hay clientes para actualizar.");
            return;
        }

        System.out.println("Seleccione el cliente que desea actualizar:");
        for (int i = 0; i < seeder.userRepository.clients.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.userRepository.clients.get(i).getProfile().getName());
        }

        int clientIndex = consoleReader.readIndex(seeder.userRepository.clients.size()) - 1;

        if (clientIndex < 0 || clientIndex >= seeder.userRepository.clients.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Client client = seeder.userRepository.clients.get(clientIndex);

        String newName = consoleReader.readString("Ingrese el nuevo nombre del cliente: ",input -> stringValidator.validate(input));
        client.getProfile().setName(newName);
        String newLastName = consoleReader.readString("Ingrese el nuevo apellido del cliente: ", input -> stringValidator.validate(input));
        client.getProfile().setLastName(newLastName);
        int newBirthDate = consoleReader.readInt("Ingrese el nuevo año de nacimiento del cliente: ", input -> integerValidator.validate(input));
        client.getProfile().setBirthDate(newBirthDate);
        String newUserName = consoleReader.readString("Ingresa el nuevo nombre de usuario del cliente: ", input -> stringValidator.validate(input));
        client.setUsername(newUserName);

        System.out.println("Información del cliente actualizada con éxito.");
    }

    public void deleteClient() {
        if (seeder.userRepository.clients.isEmpty()) {
            System.out.println("No hay clientes para eliminar.");
            return;
        }

        System.out.println("Seleccione el cliente que desea eliminar:");
        for (int i = 0; i < seeder.userRepository.clients.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.userRepository.clients.get(i).getProfile().getName());
        }

        int clientIndex = consoleReader.readIndex(seeder.userRepository.clients.size()) - 1;

        if (clientIndex < 0 || clientIndex >= seeder.userRepository.clients.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Client clientToRemove = seeder.userRepository.clients.get(clientIndex);

        if (clientToRemove.getBorrowedBooks().isEmpty()) {
            seeder.userRepository.clients.remove(clientToRemove);
            System.out.println("Cliente " + clientToRemove.getProfile().getName() + " eliminado correctamente.");
        } else {
            System.out.println("No se puede eliminar el cliente " + clientToRemove.getProfile().getName() + " porque tiene libros en su poder.");
        }
    }


}