package genericUtility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * this class consist of method related to property file
 * this method is used to read data from property file
 * @param key
 * @return
 * @throws IOException
 */

public class PropertyFileUtility {
	
	public String toReadDataFromPropertyFile(String key)throws IOException{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
		}
	}