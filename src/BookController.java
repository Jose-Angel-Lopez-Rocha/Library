public class BookController{
    Seeder seeder;
    ConsoleReader consoleReader;
    StringValidator stringValidator;
    IntegerValidator integerValidator;
    BooleanValidator booleanValidator;

    public BookController(Seeder seeder,StringValidator stringValidator,IntegerValidator integerValidator,ConsoleReader consoleReader, BooleanValidator booleanValidator) {
        this.seeder = seeder;
        this.stringValidator=stringValidator;
        this.integerValidator=integerValidator;
        this.consoleReader=consoleReader;
        this.booleanValidator=booleanValidator;
    }
    public void createBook() {
        System.out.println("Ingresa el nombre del libro: ");
        String title = consoleReader.readString("Ingresa el nombre del libro: ",input -> stringValidator.validate(input) );
        int isbn = consoleReader.readInt("Ingresa el isbn del libro: ",input -> integerValidator.validate(input));
        int publishDate = consoleReader.readInt("Ingresa la fecha de publicacion del libro: ",input -> integerValidator.validate(input));
        System.out.println("Ingresa si el libro esta disponible: ");
        boolean isAvailable = consoleReader.readBoolean("Ingresa si el libro esta disponible: ",input -> booleanValidator.validate(input));

        System.out.println("Selecciona un autor de la lista:");
        for (int i = 0; i < seeder.authorRepository.authors.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.authorRepository.authors.get(i).getProfile().getName());
        }

        int indiceAutor = consoleReader.readIndex(seeder.authorRepository.authors.size()) - 1;

        if (indiceAutor >= 0 && indiceAutor < seeder.authorRepository.authors.size()) {
            Author author = seeder.authorRepository.authors.get(indiceAutor);

            Book newBook = new Book(title, isbn, publishDate, author, isAvailable);
            seeder.bookRepository.books.add(newBook);
            author.addBook(newBook);
            System.out.println("Libro creado correctamente.");
        } else {
            System.out.println("Error: Selección de autor no válida.");
        }
    }

    public void showAllBooks() {
        for (Book book : seeder.bookRepository.books) {
            System.out.println(" Titulo del libro: " + book.getTitle() +
                    ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                    ", ISBN del libro: " + book.getIsbn());
        }
    }

    public void showBorrowedBooks() {
        for (Book book : seeder.bookRepository.books) {
            if (!book.isAvailable()) {
                System.out.println(" Titulo del libro: " + book.getTitle() +
                        ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                        ", ISBN del libro: " + book.getIsbn());
            }
        }
    }

    public void showAvailableBooks() {
        for (Book book : seeder.bookRepository.books) {
            if (book.isAvailable()) {
                System.out.println(" Titulo del libro: " + book.getTitle() +
                        ", Autor del libro: " + book.getAuthor().getProfile().getName() +
                        ", ISBN del libro: " + book.getIsbn());
            }
        }
    }
    public void updateBook() {
        if (seeder.bookRepository.books.isEmpty()) {
            System.out.println("No hay libros para actualizar.");
            return;
        }

        System.out.println("Seleccione el libro que desea actualizar:");
        for (int i = 0; i < seeder.bookRepository.books.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.bookRepository.books.get(i).getTitle());
        }

        int bookIndex = consoleReader.readIndex(seeder.bookRepository.books.size()) - 1;

        if (bookIndex < 0 || bookIndex >= seeder.bookRepository.books.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }

        Book book = seeder.bookRepository.books.get(bookIndex);

        System.out.println("Ingrese el nuevo título del libro: ");
        String newTitle = consoleReader.readString("Ingrese el nuevo título del libro: ",input -> stringValidator.validate(input));
        book.setTitle(newTitle);
        int newPublishYear = consoleReader.readInt("Ingrese el nuevo año de publicación del libro: ",input -> integerValidator.validate(input));
        book.setPublishdate(newPublishYear);
        System.out.println("Ingrese el nuevo ISBN del libro: ");
        int newISBN = consoleReader.readInt("Ingrese el nuevo ISBN del libro: ",input -> integerValidator.validate(input));
        book.setIsbn(newISBN);

        System.out.println("Seleccione el nuevo autor del libro: ");
        for (int i = 0; i < seeder.authorRepository.authors.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.authorRepository.authors.get(i).getProfile().getName());
        }
        int newAuthorIndex = consoleReader.readIndex(seeder.authorRepository.authors.size());
        Author newAuthor = seeder.authorRepository.authors.get(newAuthorIndex - 1);
        book.setAuthor(newAuthor);
    }

    public void deleteBook() {
        if (seeder.bookRepository.books.isEmpty()) {
            System.out.println("No hay libros para eliminar.");
            return;
        }

        System.out.println("Seleccione el libro que desea eliminar:");
        for (int i = 0; i < seeder.bookRepository.books.size(); i++) {
            System.out.println((i + 1) + ". " + seeder.bookRepository.books.get(i).getTitle());
        }
        int bookIndex = consoleReader.readIndex(seeder.bookRepository.books.size()) - 1;

        if (bookIndex < 0 || bookIndex >= seeder.bookRepository.books.size()) {
            System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return;
        }
        Book bookToRemove = seeder.bookRepository.books.get(bookIndex);
        for (Client client : seeder.userRepository.clients) {
            if (client.getBorrowedBooks().contains(bookToRemove)) {
                System.out.println("No se puede eliminar el libro " + bookToRemove.getTitle() + " porque está en poder de un cliente.");
                return;
            }
        }
        bookToRemove.getAuthor().getBooks().remove(bookToRemove);
        seeder.bookRepository.books.remove(bookToRemove);
        System.out.println("Libro " + bookToRemove.getTitle() + " eliminado correctamente.");
    }
}