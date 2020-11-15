package MAF.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import MAF.BasePackage.ProjectBaseClass;

public class ReadConfig extends ProjectBaseClass {
	
	
	public static String readPropertiesFile() throws IOException {
		
		Properties prop = new Properties();
		
		//provide the inputfile to read data
		
		InputStream input = new FileInputStream (projectpath1 +"/src/test/resources/ConfigurationProperties/config.properties");
		prop.load(input);
		
		//getting properties form get property function
		String URL = prop.getProperty("URL");
		System.out.println(URL);
		return URL;
	}
	

}
