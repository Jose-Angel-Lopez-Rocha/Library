
public class AuthorController{

    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public AuthorController(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }

    public void createAuthor(){
        String name=consoleReader.readString("Ingresa el nombre del autor: ",input -> stringValidator.validate(input));
        String lastName=consoleReader.readString("Ingresa el apellido del autor: ",input ->stringValidator.validate(input));
        int birthDate=consoleReader.readInt("Ingresa la fecha de nacimiento del autor: ",input -> integerValidator.validate(input));
        Author newAuthor=new Author(new Profile(name,lastName,birthDate));
        seeder.authorRepository.authors.add(newAuthor);
    }

        public void readAuthor(){
            System.out.println("Lista de autores: \n");
            for(Author author: seeder.authorRepository.authors){
                System.out.print("Nombre: " + author.getProfile().getName() + "\n");
                System.out.print("Apellido: " + author.getProfile().getLastName() + "\n");
                System.out.print("Fecha de nacimiento: " + author.getProfile().getBirthDate() +"\n");
                System.out.print("Libros escritos: \n");
                for (Book book : author.getBooks()) {
                    System.out.println(" - " + book.getTitle() + "\n");
                }
            }
        }

    public void updateAuthor() {
        if (seeder.authorRepository.authors.isEmpty()) {
            System.out.println("No hay autores para actualizar.");
            return;
        }

        System.out.println("Seleccione el autor que desea actualizar:");
        for (int i = 0; i < seeder.authorRepository.authors.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.authorRepository.authors.get(i).getProfile().getName());
        }

        int authorIndex = consoleReader.readIndex(seeder.authorRepository.authors.size()) - 1;

        if (authorIndex < 0 || authorIndex >= seeder.authorRepository.authors.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Author author = seeder.authorRepository.authors.get(authorIndex);

        String newName = consoleReader.readString("Ingrese el nuevo nombre del autor: ", input -> stringValidator.validate(input));
        author.getProfile().setName(newName);
        String newLastName = consoleReader.readString("Ingrese el nuevo apellido del autor: ",input -> stringValidator.validate(input));
        author.getProfile().setLastName(newLastName);
        int newBirthDate = consoleReader.readInt("Ingrese el nuevo año de nacimiento del autor: ", input -> integerValidator.validate(input));
        author.getProfile().setBirthDate(newBirthDate);
        System.out.println("Información del autor actualizada con éxito.");
    }

    public void deleteAuthor() {
        if (seeder.authorRepository.authors.isEmpty()) {
            System.out.println("No hay autores para eliminar.");
            return;
        }

        System.out.println("Seleccione el autor que desea eliminar:");
        for (int i = 0; i < seeder.authorRepository.authors.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.authorRepository.authors.get(i).getProfile().getName());
        }

        int authorIndex = consoleReader.readIndex(seeder.authorRepository.authors.size()) - 1;

        if (authorIndex < 0 || authorIndex >= seeder.authorRepository.authors.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Author authorToRemove = seeder.authorRepository.authors.get(authorIndex);

        if (authorToRemove.getBooks().isEmpty()) {
            seeder.authorRepository.authors.remove(authorToRemove);
            System.out.println("Autor " + authorToRemove.getProfile().getName() + " eliminado correctamente.");
        } else {
            System.out.println("No se puede eliminar el autor " + authorToRemove.getProfile().getName() + " porque tiene libros escritos.");
        }
    }

}