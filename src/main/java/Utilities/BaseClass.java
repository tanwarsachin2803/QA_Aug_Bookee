package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class BaseClass {

    ReadFile rf=new ReadFile();
    public WebDriver driver;
    MobileAppConnection mac;
    Properties prop=new Properties();

    public BaseClass() throws IOException {
        mac=new MobileAppConnection();
        prop=rf.readConfig("config");
    }

    public void platformSetup() throws IOException {
        String platformOs= prop.getProperty("Platform_OS"); //keyword value of Platform os in config file
        log.info("Platform OS set to: " + platformOs);

        switch (platformOs)
        {
            default:
                driver=browserSetup();
                break;
            case "mobile":
                driver=mac.mobileSetup();
                break;
        }
    }
    public WebDriver browserSetup()
    {
        String browserName=prop.getProperty("browser");
        log.info("Browser set to: " + browserName);
        switch (browserName)
        {
            default:
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver=new SafariDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                break;
        }
        return driver;
    }
    public void getUrl(String url)
    {
        String os=prop.getProperty("Platform_OS");
        String url2=prop.getProperty("url");
        String checkOS="website";
        if(os.contentEquals("website"))
        {
            driver.get(url2);
        }
        log.info("Navigating to URL: " + url);

    }

}
