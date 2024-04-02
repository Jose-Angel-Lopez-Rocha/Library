import java.util.ArrayList;
import java.util.Scanner;

public class BookController extends Repository {
    Scanner scanner=new Scanner(System.in);

    public void createBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese el año de publicación del libro: ");
        int publishDate = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Lista de autores:");
        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            System.out.println((i + 1) + ". " + author.getProfile().getName());
        }

        System.out.print("Selecciona el número del autor: ");
        int newAuthorIndex = scanner.nextInt();
        scanner.nextLine();

        if (newAuthorIndex >= 1 && newAuthorIndex <= authors.size()) {
            Author newAuthor = authors.get(newAuthorIndex - 1);
            System.out.println("Ingresa si el libro está disponible: ");
            boolean isAvailable = scanner.nextBoolean();

            Book newBook = new Book(title, isbn, publishDate, newAuthor, isAvailable);
            books.add(newBook);
            System.out.println("Libro creado correctamente.");
        } else {
            System.out.println("Error: Selección de autor no válida.");
        }
    }

    public void showAllBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " (" + book.getIsbn() + ")");
        }
    }

    public void showBorrowedBooks() {
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book.getTitle() + " (" + book.getIsbn() + ")");
            }
        }
    }

    public void showAvailableBooks() {
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " (" + book.getIsbn() + ")");
            }
        }
    }

    public void updateBook(String isbn){
        for (Book book: books){
            if (book.getIsbn().equals(isbn)){

                System.out.println("Ingresa el nuvo titulo del libro: ");
                String newTitle=scanner.nextLine();
                book.setTitle(newTitle);

                System.out.println("Ingresa el nuevo año de publicacion de libro: ");
                int newPublishYear=scanner.nextInt();
                scanner.nextLine();
                book.setPublishdate(newPublishYear);

                System.out.println("Ingresa el nuevo isbn del libro: ");
                String newISBN=scanner.nextLine();
                book.setIsbn(newISBN);

                System.out.println("Lista de autores:");
                for (int i = 0; i < authors.size(); i++) {
                    System.out.println((i + 1) + ". " + authors.get(i).getProfile().getName());
                }
                System.out.print("Selecciona el número del autor: ");
                int newAuthorIndex = scanner.nextInt();
                scanner.nextLine();

                if (newAuthorIndex < 1 || newAuthorIndex > authors.size()) {
                    System.out.println("Error: Selección de autor no válida.");
                    return;
                }
                Author newAuthor = authors.get(newAuthorIndex - 1);
                book.setAuthor(newAuthor);


                System.out.println("Ingresa si el libro esta disponible: ");
                boolean newAvailability=scanner.nextBoolean();
                book.setAvailable(newAvailability);
            }else{
                System.out.println("Lo siento humano ese libro no existe!");
            }
        }
    }

    public void deleteBook(String isbn) {
        Book bookToRemove = null;

        // Buscar el libro por ISBN
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    System.out.println("No se puede eliminar: el libro está en poder de un cliente.");
                    return;
                }
                bookToRemove = book;
                break; // Salir del bucle una vez que se encuentra el libro
            }
        }

        if (bookToRemove != null) {
            // Obtener el autor del libro
            Author author = bookToRemove.getAuthor();

            // Eliminar el libro de la lista de libros del autor
            author.removeBook(bookToRemove);

            // Eliminar el libro de la lista de libros
            books.remove(bookToRemove);

            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("Lo siento, ese libro no existe.");
        }
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---- Menú de Libros ----");
            System.out.println("1. Crear libro");
            System.out.println("2. Mostrar todos los libros");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (option) {
                case 1:
                    createBook();
                    break;
                case 2:
                    System.out.println("Opciones 1) Mostrar todos los libros \n 2) Mostrar libros prestados \n 3) Mostrat los libros por estado ");
                    int optionread=scanner.nextInt();
                    switch (optionread){
                        case 1:
                            showAllBooks();
                            break;
                        case 2:
                            showBorrowedBooks();
                            break;
                        case 3:showAvailableBooks();
                        break;
                        default:
                            System.out.println("Lo siento humano opcion incorrecta");
                    }
                    break;
                case 3:
                    System.out.println("Ingresal el isbn del libro a actualizar: ");
                    String isbn3=scanner.nextLine();
                    updateBook(isbn3);
                    break;
                case 4:
                    System.out.println("Ingresal el isbn del libro a eliminar: ");
                    String isbn4=scanner.nextLine();
                    deleteBook(isbn4);
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