import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner;
    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String prompt, IntegerValidator validator){
        int input;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()){
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
            }
            input = scanner.nextInt();
            if(input < 0){
                System.out.println("Por favor, ingresa un número positivo.");
            }
        } while (validator.validate(input));
        scanner.nextLine();
        return input;
    }

    public String readString(String prompt, StringValidator validator){
        String input;
        do{
            System.out.println(prompt);
            input=scanner.nextLine();
            if(input.isEmpty()){
                System.out.println("Por favor, ingresa una cadena válida.");
            }
        }while(validator.validate(input));
        return input;
    }

    public boolean readYesNo() {
        System.out.println("Ingresa 's' para sí o 'n' para no:");
        String input = scanner.nextLine().toLowerCase();
        return input.equals("s");
    }

    public int readIndex(int index) {
        System.out.println("Ingresa el indice: ");
        return scanner.nextInt();
    }
    public boolean readBoolean(String prompt, BooleanValidator booleanValidator) {
        System.out.println(prompt);
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (booleanValidator.validate(input)) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Entrada no válida. Intente nuevamente.");
            }
        }
    }
    public int readInteger(int min, int max) {
        int input;
        do {
            System.out.println("Ingresa un número entre " + min + " y " + max + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("¡Eso no es un número! Ingresa un número entre " + min + " y " + max + ":");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        scanner.nextLine(); // consume newline
        return input;
    }
}
