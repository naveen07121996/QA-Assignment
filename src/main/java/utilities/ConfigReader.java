package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;

	// Constructor to load the properties file
	public ConfigReader() {
		try {
			FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to get property values
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
