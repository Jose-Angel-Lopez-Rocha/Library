import java.util.HashMap;

public class Menu {
    private final HashMap<String, Controller> menuOptions;

    public Menu() {
        menuOptions = new HashMap<>();
    }

    public void addOption(String option, Controller controller) {
        menuOptions.put(option, controller);
    }
    public void executeOption(String option) {
        Controller controller = menuOptions.get(option);
        if (controller != null) {
            controller.execute();
        } else {
            System.out.println("Opción no válida");
        }
    }
}