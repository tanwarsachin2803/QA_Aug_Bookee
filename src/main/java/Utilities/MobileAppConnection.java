package Utilities;

import WebPages.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
public class MobileAppConnection {
    public static WebDriver driver;
    static BaseClass bs;
    static HomePage hp;
    ReadFile rf;
    Properties prop;
    private  AppiumDriverLocalService appiumDriverLocalService;


    static DesiredCapabilities dc;
    String appiumHostNumber;
    String appiumPortNumber;
    String platformName;
  public MobileAppConnection() throws IOException {
      dc=new DesiredCapabilities();
      rf=new ReadFile();
      prop=rf.readConfig("mobileConfig");
      appiumHostNumber= prop.getProperty("appiumHostNumber");
      appiumPortNumber= prop.getProperty("appiumPortNumber");
      platformName= prop.getProperty("platform_Os");
  }
    public WebDriver android_Setup() throws IOException {

      int port= Integer.parseInt(appiumPortNumber);
      //Starting appium server
        startAppiumServer(appiumHostNumber,port);


        // These are the values which we are taking from the mobileConfig properties file
        String platformName= prop.getProperty("platformName");
        String platformVersion= prop.getProperty("platformVersion");
        String deviceName= prop.getProperty("deviceName");
        String udid= prop.getProperty("udid");
        String app= prop.getProperty("app");
        URL url=new URL("http://"+appiumHostNumber+":"+appiumPortNumber+"/wd/hub");


        dc.setCapability("platformName", platformName);
        dc.setCapability("appium:platformVersion", platformVersion); // Ensure platformVersion is a string
        dc.setCapability("appium:deviceName", deviceName);
        dc.setCapability("appium:udid", udid);
        dc.setCapability("appium:app", app);

        dc.setCapability("appium:appPackage","com.facebook.katana");
        dc.setCapability("appium:appActivity","com.facebook.katana.LoginActivity");
        driver = new AndroidDriver<MobileElement>(url, dc);
        return driver;
       // driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@content-desc='Username']")).sendKeys("pragra123@yopmail.com");
       // driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password']")).sendKeys("pragra@123");
    }

    public WebDriver ios_Setup() throws IOException {
        URL url=new URL("http://0.0.0.0:4723/wd/hub");
        dc=new DesiredCapabilities();
        dc.setCapability("platformName", "IOS");
        dc.setCapability("appium:platformVersion", "12.0"); // Ensure platformVersion is a string
        dc.setCapability("appium:deviceName", "iphone_13");
        dc.setCapability("appium:udid", "emulator-5554");
        dc.setCapability("appium:app", "/Users/sachintanwar/Downloads/facebook.apk");
        dc.setCapability("appium:appPackage","com.facebook.katana");
        dc.setCapability("appium:appActivity","com.facebook.katana.LoginActivity");
        driver = new IOSDriver<>(url, dc);
        return driver;
    }

    public WebDriver mobileSetup() throws IOException {
        prop=rf.readConfig("config");
        String mobileOS=prop.getProperty("mobile_OS");
        switch (mobileOS)
        {
            default:
                driver=android_Setup();
                break;
            case "ios":
                driver=ios_Setup();
                break;
        }
        return driver;
    }

    public void startAppiumServer(String ipAddress,int port) {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.withIPAddress(ipAddress);
        serviceBuilder.usingPort(port); // Set the port number
        try {
            appiumDriverLocalService = AppiumDriverLocalService.buildService(serviceBuilder);
            appiumDriverLocalService.start();
        } catch (Exception e) {
            System.out.println("Exception occurred while starting Appium server: " + e.getMessage());
            // Handle the exception as needed
        }
    }

    public  void stopAppiumServer() {
        if (appiumDriverLocalService != null && platformName.contentEquals("mobile")) {
            appiumDriverLocalService.stop();
        }
    }
}
