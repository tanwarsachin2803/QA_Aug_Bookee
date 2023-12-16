import Utilities.BaseClass;
import WebPages.HomePage;
import io.qameta.allure.*;
import listener.ListenerClass;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.io.IOException;
@Log4j2
@Listeners(ListenerClass.class)
public class HomePageTest {
    BaseClass bs;
    HomePage hp;
    WebDriver driver;

    @BeforeSuite
    public void setupTest() throws IOException {
        log.info("Starting the setup");
        bs = new BaseClass();
        bs.platformSetup();
        driver = bs.driver;
        hp = new HomePage(bs);
        bs.getUrl("https://facebook.com");
    }

    @Test(priority = 0, description = "Verifying the logo of Facebook")
    @Description("Verifying the logo of Facebook")
    public void verifyingLogo() {
        log.info("Starting logo verification test");
        hp.verify_Logo();
    }

    @Test(priority = 1, description = "Verifying user is not able to login with invalid credentials")
    @Description("Verifying user is not able to login with invalid credentials")
    public void verifyingLoginWithInValidCred() throws IOException, InterruptedException {
        log.info("Starting invalid login credentials test");
        hp.enterUsername("pragra123@yopmail.com");
        hp.enterPassword("Pragra@12345");
        hp.clickLogin();
        hp.verifyNotLogin();
    }

    @Test(priority = 2, description = "Verifying user is able to login with valid credentials", groups = "sanity")
    @Description("Verifying user is able to login with valid credentials")
    public void verifyingLoginWithValidCred() throws IOException, InterruptedException {
        log.info("Starting valid login credentials test");
        bs.getUrl("https://facebook.com");
        hp.clickOkButton();
        hp.enterUsername("pragra123@yopmail.com");
        hp.enterPassword("Pragra@123");
        hp.clickLogin();
        hp.verifyLogin();
    }

    @Test(priority = 3, description = "Verifying scrolling of the page")
    @Description("Performing page scrolling")
    public void scrollPage() {
        log.info("Starting page scrolling test");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_UP).perform();
        actions.sendKeys(Keys.END).perform();
        actions.sendKeys(Keys.HOME).perform();
        log.info("Page scrolling done");
    }

    @AfterClass
    public void quitBrowser() {
        log.info("Quitting the browser");
        driver.quit();
    }
// Commited new changes
    // Helper method for logging info
}
// new change 1

//rupinder adding comment for rupinder1 branch

