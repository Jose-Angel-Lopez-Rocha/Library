import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
public class TransactionRepository {
    ArrayList<Transaction> transactions = new ArrayList<>();

    public void InitializeLoan(ArrayList<Client> clients, ArrayList<Book> books) {
        for (Client client : clients) {
            for (Book book : client.getBorrowedBooks()) {
                Transaction loanTransaction = new Transaction(Transaction.TransactionType.LOAN, UUID.randomUUID().toString(), client, book, LocalDate.now());
                transactions.add(loanTransaction);
            }
        }
    }
    public void InitializeDevolution(ArrayList<Client>clients, ArrayList<Book>books){
        for (Client client : clients) {
            for (Book book : client.getBorrowedBooks()) {
                Transaction devolutionTransaction = new Transaction(Transaction.TransactionType.DEVOLUTION, UUID.randomUUID().toString(), client, book, LocalDate.now());
                transactions.add(devolutionTransaction);
                }
            }
        }
    }
