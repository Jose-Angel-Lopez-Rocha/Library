//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Seeder seeder = new Seeder(repository);
        seeder.initialize();
        ConsoleReader consoleReader=new ConsoleReader();
        consoleReader.displayMenu();
        }
}