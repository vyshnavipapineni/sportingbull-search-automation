package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                    + "/src/test/resources/config.properties");
            properties.load(fis);
        }
    }

    public static String getProperty(String key) throws IOException {
        loadProperties();
        return properties.getProperty(key);
    }
}
