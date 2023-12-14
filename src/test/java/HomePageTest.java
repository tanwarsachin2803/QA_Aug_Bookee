import Utilities.BaseClass;
import WebPages.cucumberHomePage.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class HomePageTest {
    BaseClass bs;
    HomePage hp;
    WebDriver driver;

    @BeforeSuite
    public void setupTest() throws IOException {
        bs = new BaseClass();
        bs.platformSetup();
        driver = bs.driver;
        hp = new HomePage(bs);
        bs.getUrl("https://facebook.com");
    }

    @Test(priority = 0,description = "Verifying the logo of facebook",enabled = false)
    public void verifyingLogo()
    {
        hp.verify_Logo();
    }

    @Test(priority = 1,description = "Verifying user is not able to login with invalid credentials")
    public void verifyingLoginWithInValidCred() throws IOException, InterruptedException {
        hp.enterUsername("pragra123@yopmail.com");
        hp.enterPassword("Pragra@12345");
        hp.clickLogin();
        hp.verifyNotLogin();
    }
    @Test(priority = 2,description = "Verifying user is able to login with valid credentials",groups = "sanity")
     public void verifyingLoginWithValidCred() throws IOException, InterruptedException {
        bs.getUrl("https://facebook.com");
        hp.enterUsername("pragra123@yopmail.com");
        hp.enterPassword("Pragra@123");
        hp.clickLogin();
        hp.verifyLogin();
    }
    @Test(priority = 3,description = "Verifying scrolling of page",enabled = false)
    public void scrollPage()
    {
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform(); //just scroll 50% of page
        actions.sendKeys(Keys.PAGE_UP).perform(); //just scroll 50% of page
        actions.sendKeys(Keys.END).perform(); //just scroll end of the page
        actions.sendKeys(Keys.HOME).perform(); //just scroll top of the page
        System.out.println("scrolling done");
    }

    @AfterClass
    public void quitBrowser()
    {
        driver.quit();
    }

}
