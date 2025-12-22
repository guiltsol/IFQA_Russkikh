package hw_3.utils;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CustomProperties {

    @Getter
    private static final Properties props = new Properties();

    public static void loadProperties() {
        try {
            props.load(Files.newInputStream(Paths.get("src/test/resources/config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
