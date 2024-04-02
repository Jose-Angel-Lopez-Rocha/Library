import java.time.LocalDate;


public class Transaction {
    String id;
    TransactionType type;
    Client client;
    Book book;
    LocalDate date;

    public enum TransactionType{
        LOAN,DEVOLUTION
    }

    public Transaction(TransactionType type, String id,Client client, Book book, LocalDate date) {
        this.id = id;
        this.type=type;
        this.client=client;
        this.book=book;
        this.date=date;
    }

    public String getId() {
        return id;
    }
    public  TransactionType getType(){
        return type;
    }
    public  Client getClient(){
        return  client;
    }
    public  Book getBook(){
        return  book;
    }
    public  LocalDate getDate(){
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
