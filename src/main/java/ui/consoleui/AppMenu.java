package ui.consoleui;

import core.models.EventModel;
import core.models.UserModel;
import core.services.EventService;
import core.services.UserService;
import utils.Utils;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class AppMenu {

    private UserService userService;
    private EventService eventService;
    private Utils utils;

    private Scanner scanner = new Scanner(System.in);

    public AppMenu(UserService userService, EventService eventService, Utils utils) {
        this.userService = userService;
        this.eventService = eventService;
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
            case 0:
                this.scanner.close();
                System.out.println(utils.getPropertyByName("menu.exit"));
                return;
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
            default:
                showMainMenu();

        }
    }

    /* Event menu*/
    private void showEventActionMenu(Scanner scanner) throws IOException {
        System.out.print(utils.getPropertyByName("menu.choseAction")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.create")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.delete")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.show.event")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.show.all.event")+"\n");
        System.out.print(utils.getPropertyByName("menu.event.set.event.auditorium")+"\n");
        System.out.print(utils.getPropertyByName("menu.showMainMenu")+"\n");
        System.out.print(utils.getPropertyByName("menu.choseAction.exit")+"\n");

        while (scanner.hasNext()) {
            eventActionSelector(Integer.parseInt(scanner.nextLine()), scanner);
        }
    }

    private void eventActionSelector(int position, Scanner scanner) throws IOException {
        switch (position){
            case 0:
                this.scanner.close();
                System.out.println(utils.getPropertyByName("menu.exit"));
                return;
            case 1:
                System.out.println(utils.getPropertyByName("menu.event.create")+"\n*********************");
                createNewEvent(scanner);
                return;
            case 2:
                System.out.println(utils.getPropertyByName("menu.event.delete")+"\n*********************");
                deleteEvent(scanner);
                return;
            case 3:
                System.out.println(utils.getPropertyByName("menu.event.show.event")+"\n*********************");
                showEvent(scanner);
                return;
            case 4: case 5:
                System.out.println("in progress");
                return;


           default:
                showMainMenu();

        }
    }

    private void createNewEvent(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterName"));
        String eventName = scanner.nextLine();
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterPlace"));
        String eventPlace = scanner.nextLine();
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterDate"));
        Date eventDate = null;
        try {
            eventDate = utils.dateFormatter(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterStartTime"));
        Time startTime = null;
        try {
            startTime = utils.timeFormatter(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterEndTime"));
        Time endTime = null;
        try {
            endTime = utils.timeFormatter(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterPriceForTicket"));
        Double priceForTicket = Double.parseDouble(scanner.nextLine());
        System.out.println(utils.getPropertyByName("menu.title.createNewEvent.enterRating"));
        Integer rating = Integer.parseInt(scanner.nextLine());
        eventService.createEvent(eventName, eventPlace, eventDate, startTime, endTime, priceForTicket, rating);
        showMainMenu();
    }

    private void deleteEvent(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.title.askAboutEventName"));
        eventService.deleteEvent(scanner.nextLine());
        showMainMenu();
    }

    private void showEvent(Scanner scanner) throws IOException {
        System.out.println(utils.getPropertyByName("menu.title.askAboutEventName"));
        EventModel event = eventService.getEventByName(scanner.nextLine());
        System.out.println(utils.getPropertyByName("menu.asterix"));
        System.out.println(utils.getPropertyByName("menu.event.info"));
        System.out.println(utils.getPropertyByName("menu.asterix"));
        if(event != null) {
            System.out.println("Event ID:     " + event.getEventId());
            System.out.println("Event name:   " + event.getEventName());
            System.out.println("Event date:   " + event.getEventDate());
            System.out.println("Begin:        " + event.getEventStartTime());
            System.out.println("Finish:       " + event.getEventEndTime());
            System.out.println("Ticket price: " + event.getEventName());
            System.out.println("Rating:       " + event.getEventName());
            System.out.println(utils.getPropertyByName("menu.asterix"));
        }
        showMainMenu();
    }

    /* END event menu*/

    /* User menu*/
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
            case 0:
                this.scanner.close();
                System.out.println(utils.getPropertyByName("menu.exit"));
                return;
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
    /* END user menu*/

}
