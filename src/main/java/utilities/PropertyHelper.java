package utilities;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyHelper {

    private static final String PROPERTY_FILE = System.getProperty("user");
    private static Properties prop = new Properties();

    //load property config
    public static String getPropValue(String key) throws Exception {
        try{
            FileInputStream fi = new FileInputStream(System.getProperty("user.dir")
                    + File.separator + "src"
                    + File.separator + "test"
                    + File.separator + "resources"
                    + File.separator + "config.properties");
            prop = new Properties();
            prop.load(fi);

        }catch (Exception e){

        }
        return prop.getProperty(key);
    }

    public static void loadLog4jPropFile(){
        try{
            Properties props = new Properties();
            props.load(new FileInputStream(System.getProperty("user.dir")
                    + File.separator + "src"
                    + File.separator + "main"
                    + File.separator + "resources"
                    + File.separator + "log4j.properties"));
            PropertyConfigurator.configure(props);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
