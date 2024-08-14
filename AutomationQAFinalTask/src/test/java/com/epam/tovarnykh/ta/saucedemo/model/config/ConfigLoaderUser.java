package com.epam.tovarnykh.ta.saucedemo.model.config;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoaderUser {

    public static User loadConfig(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = ConfigLoaderUser.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            return objectMapper.readValue(inputStream, User.class);
        }
    }

}
