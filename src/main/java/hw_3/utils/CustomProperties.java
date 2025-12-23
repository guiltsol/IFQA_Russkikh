package hw_3.utils;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CustomProperties {

    @Getter
    private static final Properties props = new Properties();

    public static void loadProperties() {
        try (InputStreamReader reader = new InputStreamReader(
                Files.newInputStream(Paths.get("src/test/resources/config.properties")),
                StandardCharsets.UTF_8)) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
