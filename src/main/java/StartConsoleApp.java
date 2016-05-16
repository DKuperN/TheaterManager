import ui.consoleui.AppMenu;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Dzianis_Kupryianchyk on 02-May-16.
 */
public class StartConsoleApp {

    private AppMenu appMenu;

    public StartConsoleApp(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springContext.xml");
        StartConsoleApp app = (StartConsoleApp) context.getBean("theaterManagerApp");

        try {
            app.appMenu.showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
