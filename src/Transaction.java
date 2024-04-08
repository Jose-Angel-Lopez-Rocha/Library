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


    public  TransactionType getType(){
        return type;
    }
    public  Client getClient(){
        return  client;
    }
    public  Book getBook(){
        return  book;
    }


}
