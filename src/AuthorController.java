import java.util.ArrayList;
import java.util.Scanner;

public class AuthorController extends  Repository{


    Scanner scanner=new Scanner(System.in);

    public void createAuthor(){
        System.out.println("Ingresa el nombre del autor: ");
        String name= scanner.nextLine();

        System.out.println("Ingresa los apellidos del autor: ");
        String lastName=scanner.nextLine();

        System.out.println("Ingresa el año de nacimiento del autor: ");
        int birthDate=scanner.nextInt();

        Author newAuthor=new Author(new Profile(name,lastName,birthDate));
        authors.add(newAuthor);
    }

        public void readAuthor(){
            System.out.println("Lista de autores: \n");
            for(Author author: authors){
                System.out.println("Nombre: " + author.getProfile().getName() + "\n");
                System.out.println("Apellido: " + author.getProfile().getLastName() + "\n");
                System.out.println("Fecha de nacimiento: " + author.getProfile().getBirthDate() +"\n");
                System.out.println("Libros escritos: \n");
                for (Book book : author.getBooks()) {
                    System.out.println(" - " + book.getTitle() + "\n");
                }
            }
        }

    public void updateAuthor(String name){
        for(Author author: authors){
            if (author.getProfile().getName().equals(name)){
                System.out.println("Ingrese el nuevo nombre del autor:");
                String newName = scanner.nextLine();
                author.getProfile().setName(newName);
                System.out.println("Ingrese el nuevo apellido del autor:");
                String newLastName = scanner.nextLine();
                author.getProfile().setLastName(newLastName);
                System.out.println("Ingrese el nuevo año de nacimiento del autor:");
                int newBirthDate = scanner.nextInt();
                author.getProfile().setBirthDate(newBirthDate);
                System.out.println("Autor actualizado correctamente.");
            }else{
                System.out.println("Lo siento humano el autor no existe!");
            }
        }
    }
    public void deleteAuthor(String name) {
        Author authorToRemove = null;

        // Buscar al autor por nombre
        for (Author author : authors) {
            if (author.getProfile().getName().equals(name)) {
                authorToRemove = author;
                break; // Salir del bucle una vez que se encuentra el autor
            }
        }

        if (authorToRemove != null) {
            // Verificar si el autor tiene libros asociados
            if (authorToRemove.getBooks().isEmpty()) {
                authors.remove(authorToRemove);
                System.out.println("Autor " + authorToRemove.getProfile().getName() + " eliminado correctamente.");
            } else {
                System.out.println("No se puede eliminar el autor " + authorToRemove.getProfile().getName() + " porque tiene libros asociados.");
            }
        } else {
            System.out.println("Lo siento, el autor con nombre " + name + " no existe.");
        }
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de autores ----");
            System.out.println("1. Crear autor");
            System.out.println("2. Mostrar todos los autores");
            System.out.println("3. Actualizar autor");
            System.out.println("4. Eliminar autor");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    createAuthor();
                    break;
                case 2:
                    readAuthor();
                    break;
                case 3:
                    System.out.println("Ingresa el nombre del autor a actualizar: ");
                    String name3=scanner.nextLine();
                    updateAuthor(name3);
                    break;
                case 4:
                    System.out.println("Ingresal el nombre del autor a eliminar: ");
                    String name4=scanner.nextLine();
                    deleteAuthor(name4);
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