package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private Properties prop = new Properties();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.properties");

    public String getPropertyByName(String propertyName) throws IOException {
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file 'menu.properties' not found in the classpath");
        }

        return prop.getProperty(propertyName);
    }

}
