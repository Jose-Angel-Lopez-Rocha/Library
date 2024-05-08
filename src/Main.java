//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main   {
    public static void main(String[] args) {
        Seeder seeder =new Seeder();
        ConsoleReader consoleReader=new ConsoleReader();
        IntegerValidator integerValidator= input -> false;
        StringValidator stringValidator= input -> false;
        BooleanValidator booleanValidator=input -> false;
        seeder.initialize();
        Login login=new Login(seeder,stringValidator,integerValidator,consoleReader,booleanValidator);
        login.start();
    }
}