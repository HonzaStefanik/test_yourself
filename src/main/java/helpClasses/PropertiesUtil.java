package helpClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String getProperty(String property){
        Properties properties = new Properties();
        try {
            FileInputStream input;

            input = new FileInputStream("config.properties");

            properties.load(input);

            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }

    public static void setProperty(String key, String value){
        Properties properties = new Properties();
        try {
            FileOutputStream output;

            output = new FileOutputStream("config.properties");

            properties.setProperty(key, value);

            properties.store(output, null);

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}