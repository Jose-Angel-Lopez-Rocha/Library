//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main   {
    public static void main(String[] args) {
        Repository repository=new Repository();
        repository.initialize();
        Login login=new Login(repository);
        login.start();
    }
}