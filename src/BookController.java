import java.util.Scanner;

public class BookController{

    private final Repository repository;
    public BookController(Repository repository) {
        this.repository = repository;
    }
    Scanner scanner=new Scanner(System.in);

    public void createBook() {
        System.out.print("Ingrese el título del libro: " + "\n");
        String title = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: " + "\n");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese el año de publicación del libro: " + "\n");
        int publishDate= scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Selecciona un autor de la lista:");
        for (int i = 0; i < repository.authors.size(); i++) {
            System.out.println((i + 1) + ". " + repository.authors.get(i).getProfile().getName());
        }

        int indiceAutor = scanner.nextInt() - 1;

        if (indiceAutor >= 0 && indiceAutor < repository.authors.size()) {
            Author author = repository.authors.get(indiceAutor);

            System.out.println("Ingresa si el libro está disponible: (true/false) ");
            boolean isAvailable = scanner.nextBoolean();
            Book newBook = new Book(title, isbn, publishDate, author, isAvailable);
            repository.books.add(newBook);
            author.addBook(newBook);
            System.out.println("Libro creado correctamente.");
        } else {
            System.out.println("Error: Selección de autor no válida.");
        }
    }


    public void showAllBooks() {
        for (Book book : repository.books) {
            System.out.println(" Titulo del libro: " + book.getTitle() +
                    ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                    ", ISBN del libro: " + book.getIsbn());
        }
    }

    public void showBorrowedBooks() {
        for (Book book : repository.books) {
            if (!book.isAvailable()) {
                System.out.println(" Titulo del libro: " + book.getTitle() +
                        ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                        ", ISBN del libro: " + book.getIsbn());
            }
        }
    }

    public void showAvailableBooks() {
        for (Book book : repository.books) {
            if (book.isAvailable()) {
                System.out.println(" Titulo del libro: " + book.getTitle() +
                        ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                        ", ISBN del libro: " + book.getIsbn());
            }
        }
    }

    public void updateBook( ){
        if(repository.books.isEmpty()){
            System.out.println("No hay libros para actualizar.");
            return;
        }

        System.out.println("Seleccione el librO que desea actualizar:");
        for(int i = 0; i < repository.books.size(); i++){
            System.out.println((i+1) + ". " + repository.books.get(i).getTitle());
        }

        int bookIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(bookIndex < 0 || bookIndex >= repository.books.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Book book = repository.books.get(bookIndex);

        System.out.println("Ingrese el nuevo titulo del libro:");
        String newTitle = scanner.nextLine();
        book.setTitle(newTitle);

        System.out.println("Ingrese el nuevo año de publicacion del libro:");
        int newPublishYear=scanner.nextInt();
        scanner.nextLine();
        book.setPublishdate(newPublishYear);

        System.out.println("Ingrese el nuevo isbn del libro:");
        String newISBN = scanner.nextLine();
        book.setIsbn(newISBN);

        System.out.println("Selecciona el nuevo autor del libro: ");
        for(int i = 0; i < repository.authors.size(); i++){
            System.out.println((i+1) + ". " + repository.authors.get(i).getProfile().getName());
        }
        int newAuthorIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (newAuthorIndex < 1 || newAuthorIndex > repository.authors.size()) {
            System.out.println("Error: Selección de autor no válida.");
            return;
        }
        Author newAuthor = repository.authors.get(newAuthorIndex - 1);
        book.setAuthor(newAuthor);

        }
    public void deleteBook() {
        if(repository.books.isEmpty()){
            System.out.println("No hay libros para eliminar.");
            return;
        }

        System.out.println("Seleccione el libro que desea eliminar:");
        for(int i = 0; i < repository.books.size(); i++){
            System.out.println((i+1) + ". " + repository.books.get(i).getTitle());
        }

        int bookIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if(bookIndex < 0 || bookIndex >= repository.books.size()){
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Book bookToRemove = repository.books.get(bookIndex);

        // Verificar si el libro está en poder de algún cliente
        for(Client client : repository.clients){
            if(client.getBorrowedBooks().contains(bookToRemove)){
                System.out.println("No se puede eliminar el libro " + bookToRemove.getTitle() + " porque está en poder de un cliente.");
                return;
            }
        }

        // Remover el libro de la lista de libros del autor
        bookToRemove.getAuthor().getBooks().remove(bookToRemove);

        repository.books.remove(bookToRemove);
        System.out.println("Libro " + bookToRemove.getTitle() + " eliminado correctamente.");
    }

}