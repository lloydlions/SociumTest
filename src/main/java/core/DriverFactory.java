package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.PropertyHelper;

public class DriverFactory {
    Class<? extends WebDriver> driverClass = null;

    // initialize driver; set headless browser
    public static WebDriver initialize(){
        final Logger LOGGER = LogManager.getLogger(DriverFactory.class);

        String headless = null;
        String browser = null;
        WebDriver driver = null;
        try {
            browser = PropertyHelper.getPropValue("BROWSER").toUpperCase().toString();
            headless = PropertyHelper.getPropValue("HEADLESS");
            LOGGER.info("BROWSER: " + browser);;

            if(browser == null){
                browser = "CHROME";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            switch (browser){
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    if(headless.equals("yes")){
                        LOGGER.info("ENABLE HEADLESS WEB DRIVER");
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--log-level=3");
                        chromeOptions.addArguments("--silent");
                        driver = new ChromeDriver(chromeOptions);
                    }else{
                        driver = new ChromeDriver();
                    }
                    break;
                case "FIREFOX":
//                    WebDriverManager.firefoxdriver().setup();
                    break;
                case "EDGE" :
//                driverClass = EdgeDriver.class;
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return driver;
    }
}
