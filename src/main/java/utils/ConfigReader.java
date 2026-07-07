package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    public static String get(String key) throws Exception {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("src/main/resources/config.properties");
        properties.load(file);
        return properties.getProperty(key);
    }
}
