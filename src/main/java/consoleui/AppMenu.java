package consoleui;


//import java.io.Console;
import services.UserService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class AppMenu {

    private UserService userService;

    private Scanner scanner = new Scanner(System.in);

    private Properties prop = new Properties();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.properties");

    public AppMenu(UserService userService) {
        this.userService = userService;
    }

    public void ShowMenu() throws IOException {

        System.out.print(getPropertyByName("menu.choseAction")+"\n");
        System.out.print(getPropertyByName("menu.choseAction1")+"\n");
        System.out.print(getPropertyByName("menu.choseAction2")+"\n");
        System.out.print(getPropertyByName("menu.choseAction.exit")+"\n");

        while (scanner.hasNext()) {
            showSubMenu(Integer.parseInt(scanner.nextLine()), scanner);
        }

    }

    private void showSubMenu(int subPosition, Scanner scanner) throws IOException {
        switch (subPosition){
            case 1:
                System.out.println(getPropertyByName("menu.title.registerUser")+"\n*********************");
                createUserMenu(scanner);
                return;
            case 2:
                System.out.println(getPropertyByName("menu.title.removeUser")+"\n*********************");
                removeUser(scanner);
                return;
            default:
                this.scanner.close();
                System.out.println("Exit program");
        }
    }

    private String getPropertyByName(String propertyName) throws IOException {
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file 'menu.properties' not found in the classpath");
        }

        return prop.getProperty(propertyName);
    }

    private void createUserMenu(Scanner scanner) throws IOException {
        System.out.println(getPropertyByName("menu.user.Enter.UserName"));
        String userName = scanner.nextLine();
        System.out.println(getPropertyByName("menu.user.Enter.UserEmail"));
        String userEmail = scanner.nextLine();
        userService.registerUser(userName, userEmail);
        ShowMenu();
    }

    private void removeUser(Scanner scanner) throws IOException {
        System.out.println(getPropertyByName("menu.user.Enter.UserName"));
        userService.removeUser(scanner.nextLine());
        ShowMenu();
    }

}
