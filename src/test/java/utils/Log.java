package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
    private static Logger logger = Logger.getLogger(Log.class);


    static {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log4j.properties");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}
