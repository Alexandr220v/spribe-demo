package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties config = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("FATAL: Unable to find config.properties on the classpath.");
            }
            config.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("FATAL: Error loading config.properties", ex);
        }
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}
