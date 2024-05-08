import java.util.ArrayList;

public class AdministradorController{

    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public AdministradorController(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }

    public void createAdmin() {
        String name = consoleReader.readString("Ingresa el nombre del administrador: ",input -> stringValidator.validate(input));
        String lastName = consoleReader.readString("Ingresa el apellido del administrador: ",input -> stringValidator.validate(input));
        int birthDate = consoleReader.readInt("Ingresa la fecha de nacimiento del administrador: ",input -> integerValidator.validate(input));
        String username = consoleReader.readString("Ingresa el nombre de usuario del a dministrador: ",input -> stringValidator.validate(input));
        String password = consoleReader.readString("Ingresa la contraseña del administrador:  ",input -> stringValidator.validate(input));
        System.out.println("¿Quieres hacer del administrador un super administrador? (s/n)");
        boolean isSuperAdmin = consoleReader.readYesNo();

        if (isSuperAdmin) {
            for (Administrador administrador : seeder.userRepository.administradores) {
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

        int option = consoleReader.readInteger(1, 3);

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

        Administrador newAdmin = new Administrador(new Profile(name, lastName, birthDate), username, password, permissions, isSuperAdmin);
        seeder.userRepository.administradores.add(newAdmin);
        seeder.userRepository.users.add(newAdmin);
    }


    public void readAdmin() {
        System.out.println("Lista de administradores: \n ");
        for (Administrador administrador : seeder.userRepository.administradores) {
            System.out.print("Nombre: " + administrador.getProfile().getName() + "\n");
            System.out.print("Apellido: " + administrador.getProfile().getLastName() + "\n");
            System.out.print("Fecha de nacimiento: " + administrador.getProfile().getBirthDate() + "\n");
            System.out.println("Contraseña hasheada: " + administrador.getPassword());
            System.out.println("Es superadministrador: " + (administrador.isSuperAdmin() ? "Sí" : "No"));
            System.out.print("Permisos del administrador : \n");
            for (Administrador.Permissions permissions : administrador.getPermissions()) {
                System.out.println(" - " + permissions + "\n");
            }
        }
    }
    public void updateAdmin() {
        if (seeder.userRepository.administradores.isEmpty()) {
            System.out.println("No hay administradores para actualizar.");
            return;
        }

        System.out.println("Seleccione el administrador que desea actualizar:");
        for (int i = 0; i < seeder.userRepository.administradores.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.userRepository.administradores.get(i).getProfile().getName());
        }

        int adminIndex = consoleReader.readInteger(1, seeder.userRepository.administradores.size()) - 1;

        Administrador administrador = seeder.userRepository.administradores.get(adminIndex);

        String newName = consoleReader.readString("Ingrese el nuevo nombre del administrador: ",input -> stringValidator.validate(input));
        administrador.getProfile().setName(newName);

        String newLastName = consoleReader.readString("Ingrese el nuevo nombre del administrador: ",input -> stringValidator.validate(input));
        administrador.getProfile().setLastName(newLastName);

        System.out.println("Ingrese el nuevo año de nacimiento del administrador: ");
        int newBirthDate = consoleReader.readInt("Ingrese el nuevo año de nacimiento del administrador: ", input -> integerValidator.validate(input));
        administrador.getProfile().setBirthDate(newBirthDate);

        String newUserName = consoleReader.readString("Ingresa el nuevo nombre de usuario del administrador: ", input -> stringValidator.validate(input));
        administrador.setUsername(newUserName);

        String newPassword = consoleReader.readString("Ingresa la nueva contraseña del administrador: ",input -> stringValidator.validate(input));
        administrador.setPassword(newPassword);

        System.out.println("¿Deseas actualizar los permisos del administrador? (s/n)");
        boolean updatePermissions = consoleReader.readYesNo();
        if (updatePermissions) {
            ArrayList<Administrador.Permissions> newPermissions = new ArrayList<>();
            boolean addAnotherPermission = true;
            while (addAnotherPermission) {
                System.out.println("Selecciona el permiso para agregar al administrador:");
                System.out.println("1. Permiso de lectura");
                System.out.println("2. Permiso de escritura");
                System.out.println("3. Permiso de eliminación");
                int option = consoleReader.readInteger(1,3);
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
                addAnotherPermission = consoleReader.readYesNo();
            }
            administrador.setPermissions(newPermissions);
        }

        System.out.println("Administrador actualizado correctamente");
    }

    public void deleteAdmin() {
        if (seeder.userRepository.administradores.isEmpty()) {
            System.out.println("No hay administradores para eliminar.");
            return;
        }

        System.out.println("Seleccione el administrador que desea eliminar:");
        for (int i = 0; i < seeder.userRepository.administradores.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.userRepository.administradores.get(i).getUsername());
        }

        int adminIndex = consoleReader.readInteger(1, seeder.userRepository.administradores.size()) - 1;

        Administrador administradorToRemove = seeder.userRepository.administradores.get(adminIndex);

        if (administradorToRemove.isSuperAdmin()) {
            System.out.println("No se puede eliminar un super administrador");
            return;
        }
        seeder.userRepository.administradores.remove(administradorToRemove);
        System.out.println("Administrador " + administradorToRemove.getProfile().getName() + " eliminado correctamente.");
    }

}