import java.util.ArrayList;

public class AuthorRepository {
    ArrayList<Author> authors = new ArrayList<>();
    public void initializeAuthors() {
        authors.add(new Author(new Profile("Jorge", "Ramirez", 1997)));
        authors.add(new Author(new Profile("Susana", "Reyes", 1998)));
    }
}
