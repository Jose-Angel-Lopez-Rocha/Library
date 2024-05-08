import java.util.ArrayList;

    public class MovementReport {
        Seeder seeder;
        ArrayList<Transaction>loan=new ArrayList<>();
        ArrayList<Transaction>devolution=new ArrayList<>();
        public MovementReport(Seeder seeder) {
            this.seeder = seeder;
        }
        public void generateReportByClient(Client loggedClient) {
            loan= seeder.loanRepository.transactions;
            devolution = seeder.devolutionRepository.transactions;

            System.out.println("Movimientos del cliente " + loggedClient.getUsername() + " :");

            // Imprimir los detalles de las transacciones de préstamo
            for(Transaction transactionL: loan){
                if(transactionL.getClient().equals(loggedClient)) {
                    System.out.println(transactionL.getType() + " - " + transactionL.getBook().getTitle());
                }
            }

            // Imprimir los detalles de las transacciones de devolución
            for(Transaction transactionD: devolution){
                if(transactionD.getClient().equals(loggedClient)) {
                    System.out.println(transactionD.getType() + " - " + transactionD.getBook().getTitle());
                }
            }
        }
    }
