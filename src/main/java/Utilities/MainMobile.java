package Utilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;

public class MainMobile {
    public static WebDriver driver;
    static DesiredCapabilities dc;
    public static void main(String[] args) throws IOException {
        android_Setup();
    }
    public static void android_Setup() throws IOException {
        dc=new DesiredCapabilities();
        URL url=new URL("http://0.0.0.0:4723/wd/hub");
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:platformVersion", "12.0"); // Ensure platformVersion is a string
        dc.setCapability("appium:deviceName", "Pixel_6a");
        dc.setCapability("appium:udid", "emulator-5554");
        dc.setCapability("appium:app", "/Users/sachintanwar/Downloads/facebook.apk");
        dc.setCapability("appium:appPackage","com.facebook.katana");
        dc.setCapability("appium:appActivity","com.facebook.katana.LoginActivity");
        driver = new AndroidDriver<MobileElement>(url, dc);
         driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@content-desc='Username']")).sendKeys("pragra123@yopmail.com");
         driver.findElement(By.xpath("//android.widget.EditText[@content-desc='Password']")).sendKeys("pragra@123");
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='Log In']")).click();
        System.out.println("Test Case is passed");
        driver.quit();
    }
}
