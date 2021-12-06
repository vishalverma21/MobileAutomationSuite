package common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
	static Properties properties;
	
	//Method created to read data from config file
	public static void loadConfig()
	{
		try {
			File file = new File("Config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			ConfigManager.properties = properties;
			
		} catch (Exception e) {
			System.out.println("Exception occurred while loading config.properties "+ e.getMessage());
		}
		
	}

	//Method created to get the data from property
	public static String getProperty(String key)
	{
		try
		{
			loadConfig();
		return properties.getProperty(key);
		}catch (Exception e) {
			return null;
		}
	}
	
}
