package hw_5.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

import java.io.File;

public class JsonEditor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static ObjectNode readObjectNodeFromFile(String path) {
        return (ObjectNode) objectMapper.readTree(new File(path));
    }

    public static ObjectNode change(ObjectNode json, String fieldName, String value) {
        json.put(fieldName, value);
        return json;
    }

    public static ObjectNode createCopy(ObjectNode original) {
        return original.deepCopy();
    }
}
