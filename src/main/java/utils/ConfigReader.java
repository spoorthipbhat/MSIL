package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public void loadProperties(String env) {
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/" + env + ".properties");
            if (inputStream == null) {
                throw new RuntimeException("Config file not found: " + env + ".properties");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config for environment: " + env, e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
