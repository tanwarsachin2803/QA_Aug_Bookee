package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    ReadFile rf=new ReadFile();
    WebDriver driver;
    Properties prop;
    public BaseClass() throws IOException {
        prop=rf.readConfig("config");
    }

    public void setup()
    {
        String browserName=prop.getProperty("browser");
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
    }
}
