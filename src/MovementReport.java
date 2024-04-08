
import java.util.List;
import java.util.Scanner;

    public class MovementReport{

    private final Repository repository;
    Scanner scanner=new Scanner(System.in);

        public MovementReport(Repository repository) {
            this.repository = repository;
        }

    public void generateReportByClient() {
        System.out.println("Lista de clientes: ");
        for(Client client: repository.clients){
            System.out.println(client.getProfile().getName());
        }
        System.out.println("Ingresa el nombre del cliente para generar el reporte: ");
        String clientName=scanner.nextLine();
        Client client = findClientByName(clientName);
        if (client != null) {
            List<Transaction> filteredTransactionsL= repository.loan.stream()
                    .filter(transaction -> transaction.getClient().equals(client))
                    .toList();
            List<Transaction> filteredTransactionsD=repository.devolution.stream()
                            .filter(transaction -> transaction.getClient().equals(client))
                                    .toList();
            System.out.println("Movimientos del cliente " + clientName + ":");
            for (Transaction transactionD : filteredTransactionsD) {
                System.out.println(transactionD.getType() + " - " + transactionD.getBook().getTitle());
                for (Transaction transactionL : filteredTransactionsL) {
                    System.out.println(transactionL.getType() + " - " + transactionL.getBook().getTitle());
                }
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
        public Client findClientByName(String name) {
            for (Client client : repository.clients) {
                if (client.getProfile().getName().equals(name)) {
                    return client;
                }
            }
            return null;  // Devuelve null si no se encuentra el cliente
        }
}