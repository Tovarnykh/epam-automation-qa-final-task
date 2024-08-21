package com.epam.tovarnykh.ta.saucedemo.model.config;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * The UserAdapter class provides a utility method for loading a User configuration
 * from a JSON file located in the classpath. It uses the Jackson library to map
 * the JSON content to a User object.
 */
public class UserAdapter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Loads a User configuration from a specified JSON file path.
     * The JSON file should be located in the classpath. This method reads
     * the file and maps its content to a User object using Jackson's ObjectMapper.
     *
     * @param filePath The path to the JSON file relative to the classpath.
     * @return A User object populated with data from the JSON file.
     * @throws IOException If the file is not found or an error occurs during reading.
     */
    public static User loadUserProperty(String filePath) throws IOException {
        try (InputStream inputStream = UserAdapter.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            return objectMapper.readValue(inputStream, User.class);
        }
    }

}
