import java.util.ArrayList;
import java.util.Scanner;

public class AdministradorController{

    Scanner scanner = new Scanner(System.in);
    private final Repository repository;


    public AdministradorController(Repository repository) {
        this.repository = repository;
    }

    public void createAdmin() {

        System.out.println("Ingresa el nombre del administrador: ");
        String name = scanner.nextLine();

        System.out.println("Ingresa los apellidos del administrador: ");
        String lastName = scanner.nextLine();

        System.out.println("Ingresa el año de nacimiento del administrador: ");
        int birthDate = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Ingresa el nombre de usuario del administrador: ");
        String username = scanner.nextLine();

        System.out.println("Ingresa la contraseña del administrador: ");
        String password = scanner.nextLine();

        System.out.println("Quieres hacer del administrador un super administrador? (s/n) ");
        String SuperAdminsistrador=scanner.nextLine();
        boolean isSuperAdministrador=SuperAdminsistrador.equalsIgnoreCase("s");
        if (isSuperAdministrador) {
            for (Administrador administrador : repository.administradores) {
                if (administrador.isSuperAdmin()) {
                    System.out.println("Ya existe un super administrador. No se puede crear otro.");
                    return;
                }
            }
        }


        ArrayList<Administrador.Permissions> permissions = new ArrayList<>();

        System.out.println("Selecciona los permisos para el nuevo administrador:");
        System.out.println("1. Permiso de lectura");
        System.out.println("2. Permiso de escritura");
        System.out.println("3. Permiso de eliminación");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                permissions.add(Administrador.Permissions.READ);
                break;
            case 2:
                permissions.add(Administrador.Permissions.WRITE);
                break;
            case 3:
                permissions.add(Administrador.Permissions.DELETE);
                break;
            default:
                System.out.println("Opcion invalida");
        }

        Administrador newAdmin = new Administrador(new Profile(name, lastName, birthDate), username, password, permissions, isSuperAdministrador);
        repository.administradores.add(newAdmin);
        repository.users.add(newAdmin);


    }

    public void readAdmin() {
        System.out.println("Lista de administradores: \n ");
        for (Administrador administrador : repository.administradores) {
            System.out.print("Nombre: " + administrador.getProfile().getName() + "\n");
            System.out.print("Apellido: " + administrador.getProfile().getLastName() + "\n");
            System.out.print("Fecha de nacimiento: " + administrador.getProfile().getBirthDate() + "\n");
            System.out.println("Es superadministrador: " + (administrador.isSuperAdmin() ? "Sí" : "No"));
            System.out.print("Permisos del administrador : \n");
            for (Administrador.Permissions permissions : administrador.getPermissions()) {
                System.out.println(" - " + permissions + "\n");
            }
        }
    }

    public void updateAdmin() {
        if(repository.administradores.isEmpty()){
            System.out.println("No hay administradores para actualizar.");
            return;
        }

        System.out.println("Seleccione el administrador que desea actualizar:");
        for(int i = 0; i < repository.administradores.size(); i++){
            System.out.println((i+1) + ". " + repository.administradores.get(i).getProfile().getName());
        }

        int adminIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(adminIndex < 0 || adminIndex >= repository.administradores.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Administrador administrador = repository.administradores.get(adminIndex);

        System.out.println("Ingrese el nuevo nombre del administrador:");
        String newName = scanner.nextLine();
        administrador.getProfile().setName(newName);

        System.out.println("Ingrese el nuevo apellido del administrador:");
        String newLastName = scanner.nextLine();
        administrador.getProfile().setLastName(newLastName);

        System.out.println("Ingrese el nuevo año de nacimiento del administrador:");
        int newBirthDate = scanner.nextInt();
        scanner.nextLine(); // consume newline
        administrador.getProfile().setBirthDate(newBirthDate);

        System.out.println("Ingresa el nuevo nombre de usuario del administrador: ");
        String newUserName = scanner.nextLine();
        administrador.setUsername(newUserName);

        System.out.println("Ingresa la nueva contraseña del administrador");
        String newPassword = scanner.nextLine();
        administrador.setPassword(newPassword);

        System.out.println("¿Deseas actualizar los permisos del administrador? (s/n)");
        String updatePermissions = scanner.nextLine();
        if(updatePermissions.equalsIgnoreCase("s")){
            ArrayList<Administrador.Permissions> newPermissions = new ArrayList<>();
            String s = "s";
            while(s.equalsIgnoreCase("s")){
                System.out.println("Selecciona el permiso para agregar al administrador:");
                System.out.println("1. Permiso de lectura");
                System.out.println("2. Permiso de escritura");
                System.out.println("3. Permiso de eliminación");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (option) {
                    case 1:
                        newPermissions.add(Administrador.Permissions.READ);
                        break;
                    case 2:
                        newPermissions.add(Administrador.Permissions.WRITE);
                        break;
                    case 3:
                        newPermissions.add(Administrador.Permissions.DELETE);
                        break;
                    default:
                        System.out.println("Opción inválida");
                }

                System.out.println("¿Deseas agregar otro permiso? (s/n)");
                s = scanner.nextLine();
            }
            administrador.setPermissions(newPermissions);
        }

        System.out.println("Administrador actualizado correctamente");
    }


    public void deleteAdmin() {
        if(repository.administradores.isEmpty()){
            System.out.println("No hay administradores para eliminar.");
            return;
        }

        System.out.println("Seleccione el administrador que desea eliminar:");
        for(int i = 0; i < repository.administradores.size(); i++){
            System.out.println((i+1) + ". " + repository.administradores.get(i).getUsername());
        }

        int adminIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(adminIndex < 0 || adminIndex >= repository.administradores.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Administrador administradorToRemove = repository.administradores.get(adminIndex);

        if(administradorToRemove.isSuperAdmin()){
            System.out.println("No se puede eliminar un super administrador");
            return;
        }
        repository.administradores.remove(administradorToRemove);
        System.out.println("Administrador " + administradorToRemove.getProfile().getName() + " eliminado correctamente.");
    }
}