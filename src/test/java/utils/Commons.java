package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Commons {

	public static String getGlobalPropertiesValue(String key) throws IOException {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("src/test/java/config.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(key);
	}
}