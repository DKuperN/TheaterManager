package consoleui;

import models.UserModel;
import services.UserService;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;

public class AppMenu {

    private UserService userService;
    private Utils utils;

    private Scanner scanner = new Scanner(System.in);

    public AppMenu(UserService userService, Utils utils) {
        this.userService = userService;
        this.utils = utils;
    }

    public void showMainMenu() throws IOException {

        System.out.print(utils.getPropertyByName("menu.choseAction")+"\n");
        System.out.print(utils.getPropertyByName("menu.userService")+"\n");
        System.out.print(utils.getPropertyByName("menu.eventService")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.exit")+"\n");

        while (scanner.hasNext()) {
            actionSelector(Integer.parseInt(scanner.nextLine()), scanner);
        }

    }

    private void actionSelector(int position, Scanner scanner) throws IOException {
        switch (position){
            case 1:
                showUserActionsMenu(scanner);
                return;
            case 2:
                showEventActionMenu(scanner);
                return;
            case 3:
//                System.out.println(utils.getPropertyByName("menu.title.userInfo")+"\n*********************");
//                showUserInfo(scanner);
                return;


            case 4:
                this.scanner.close();
                System.out.println(utils.getPropertyByName("menu.exit"));
            default:
                showMainMenu();

        }
    }

    private void showEventActionMenu(Scanner scanner) throws IOException {
        System.out.print(utils.getPropertyByName("menu.choseAction")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.create")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.delete")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.show.event")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.show.all.event")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.set.event.auditorium")+"\n");
        System.out.print(utils.getPropertyByName("menu.showMainMenu")+"\n");

        while (scanner.hasNext()) {
            eventActionSelector(Integer.parseInt(scanner.nextLine()), scanner);
        }

    }

    private void eventActionSelector(int position, Scanner scanner) throws IOException {
        switch (position){
            case 1: case 2: case 3: case 4: case 5:
                System.out.println("in progress");
                return;


           default:
                showMainMenu();

        }
    }

    private void showUserActionsMenu(Scanner scanner) throws IOException {

        System.out.print(utils.getPropertyByName("menu.choseAction")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.1")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.2")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.3")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.exit")+"\n");
        System.out.print(utils.getPropertyByName("menu.showMainMenu")+"\n");

        while (scanner.hasNext()) {
            userActionSelector(Integer.parseInt(scanner.nextLine()), scanner);
        }

    }


    private void userActionSelector(int subPosition, Scanner scanner) throws IOException {
        switch (subPosition){
            case 1:
                System.out.println(utils.getPropertyByName("menu.title.registerUser")+"\n*********************");
                createUserMenu(scanner);
                return;
            case 2:
                System.out.println(utils.getPropertyByName("menu.title.removeUser")+"\n*********************");
                removeUser(scanner);
                return;
            case 3:
                System.out.println(utils.getPropertyByName("menu.title.userInfo")+"\n*********************");
                showUserInfo(scanner);
                return;
            case 4:
                this.scanner.close();
                System.out.println(utils.getPropertyByName("menu.exit"));
            default:
                showMainMenu();

        }
    }

    private void showUserInfo(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.user.info"));
        UserModel userModel = userService.getUserByName(scanner.nextLine());
        if(userModel != null) {
            System.out.println(utils.getPropertyByName("menu.asterix"));
            System.out.println(utils.getPropertyByName("menu.user.info.userid") + " " + userModel.getUserId());
            System.out.println(utils.getPropertyByName("menu.user.info.Name") + " " + userModel.getUserName());
            System.out.println(utils.getPropertyByName("menu.user.info.Email") + " " + userModel.getUserEmail());
            System.out.println(utils.getPropertyByName("menu.asterix"));
        }
        showMainMenu();
    }

    private void createUserMenu(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.user.Enter.UserName"));
        String userName = scanner.nextLine();
        System.out.println(utils.getPropertyByName("menu.user.Enter.UserEmail"));
        String userEmail = scanner.nextLine();
        userService.registerUser(userName, userEmail);
        showMainMenu();
    }

    private void removeUser(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.user.Enter.UserName"));
        userService.removeUser(scanner.nextLine());
        showMainMenu();
    }

}
