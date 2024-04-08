import java.util.Scanner;

public class AuthorController{

    private final Repository repository;
    Scanner scanner=new Scanner(System.in);
    public AuthorController(Repository repository) {
        this.repository = repository;
    }



    public void createAuthor(){
        System.out.println("Ingresa el nombre del autor: ");
        String name= scanner.nextLine();

        System.out.println("Ingresa los apellidos del autor: ");
        String lastName=scanner.nextLine();

        System.out.println("Ingresa el año de nacimiento del autor: ");
        int birthDate=scanner.nextInt();

        Author newAuthor=new Author(new Profile(name,lastName,birthDate));
        repository.authors.add(newAuthor);
    }

        public void readAuthor(){
            System.out.println("Lista de autores: \n");
            for(Author author: repository.authors){
                System.out.print("Nombre: " + author.getProfile().getName() + "\n");
                System.out.print("Apellido: " + author.getProfile().getLastName() + "\n");
                System.out.print("Fecha de nacimiento: " + author.getProfile().getBirthDate() +"\n");
                System.out.print("Libros escritos: \n");
                for (Book book : author.getBooks()) {
                    System.out.println(" - " + book.getTitle() + "\n");
                }
            }
        }

    public void updateAuthor(){
        if(repository.authors.isEmpty()){
            System.out.println("No hay autores para actualizar.");
            return;
        }

        System.out.println("Seleccione el cliente que desea actualizar:");
        for(int i = 0; i < repository.authors.size(); i++){
            System.out.println((i+1) + ". " + repository.authors.get(i).getProfile().getName());
        }

        int authorIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(authorIndex < 0 || authorIndex >= repository.authors.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Author author = repository.authors.get(authorIndex);

        System.out.println("Ingrese el nuevo nombre del autor:");
        String newName = scanner.nextLine();
        author.getProfile().setName(newName);

        System.out.println("Ingrese el nuevo apellido del autor:");
        String newLastName = scanner.nextLine();
        author.getProfile().setLastName(newLastName);

        System.out.println("Ingrese el nuevo año de nacimiento del autor:");
        int newBirthDate = scanner.nextInt();
        scanner.nextLine(); // consume newline
        author.getProfile().setBirthDate(newBirthDate);
        System.out.println("Información del cliente actualizada con éxito.");
    }
    public void deleteAuthor() {
        if(repository.authors.isEmpty()){
            System.out.println("No hay autores para eliminar.");
            return;
        }

        System.out.println("Seleccione el autor que desea eliminar:");
        for(int i = 0; i < repository.authors.size(); i++){
            System.out.println((i+1) + ". " + repository.authors.get(i).getProfile().getName());
        }

        int authorIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(authorIndex < 0 || authorIndex >= repository.authors.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Author authorToRemove = repository.authors.get(authorIndex);

        // Verificar si el autor tiene libros escritos
        if (authorToRemove.getBooks().isEmpty()) {
            repository.authors.remove(authorToRemove);
            System.out.println("Autor " + authorToRemove.getProfile().getName() + " eliminado correctamente.");
        } else {
            System.out.println("No se puede eliminar el autor " + authorToRemove.getProfile().getName() + " porque tiene libros escritos.");
        }
    }
}