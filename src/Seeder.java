public class Seeder {
    AuthorRepository authorRepository=new AuthorRepository();
    BookRepository bookRepository=new BookRepository();
    TransactionRepository transactionRepository=new TransactionRepository();
    UserRepository userRepository=new UserRepository();
    TransactionRepository loanRepository=new TransactionRepository();
    TransactionRepository devolutionRepository=new TransactionRepository();
    public void initialize(){
        authorRepository.initializeAuthors();
        bookRepository.initializeBooks(authorRepository.authors);
        transactionRepository.InitializeLoan(userRepository.clients, bookRepository.books);
        transactionRepository.InitializeDevolution(userRepository.clients,bookRepository.books);
        userRepository.initializeUsers();
    }
}
