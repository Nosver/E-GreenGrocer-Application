package application;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        
        try (FileInputStream fileInputStream = new FileInputStream("../OOP-Project3/src/application/application.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJdbcUrl() {
        return properties.getProperty("JDBC.URL");
    }

    public String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public String getPassword() {
        return properties.getProperty("PASSWORD");
    }
}
