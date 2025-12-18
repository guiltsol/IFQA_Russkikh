package hw_5.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
