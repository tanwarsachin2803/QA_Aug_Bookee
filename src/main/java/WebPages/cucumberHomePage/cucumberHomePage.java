package WebPages.cucumberHomePage;

import Utilities.BaseClass;
import Utilities.ReadFile;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class cucumberHomePage {
    BaseClass bs;
    Properties prop;
    WebDriver driver;
    WebDriverWait wait;
    ReadFile rcf = new ReadFile();

    @FindBy(xpath = "//img[@class='fb_logo _8ilh img']")
    WebElement logo_Facebook;
    @FindBy(id = "email")
    WebElement input_Username;
    @FindBy(id = "pass")
    WebElement input_Password;
    @FindBy(name = "login")
    WebElement btn_Login;
    @FindBy(xpath = "//a[contains(text(),'Forgotten password')]")
    WebElement btn_ForgotPassword;
    @FindBy(xpath = "//a[contains(text(),'Create new account')]")
    WebElement btn_CreateAccount;

    public cucumberHomePage() throws IOException {
        this.driver = setBrowser();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @Given("Open Browser")
    public void browserOpened()
    {
        System.out.println("browser opened");
    }
    @BeforeSuite
    public WebDriver setBrowser() throws IOException {
        prop = rcf.readConfig("config");
        String browsername = prop.getProperty("browser");
        switch (browsername) {
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
        }
        return driver;
    }

    @And("Open Facebook Url")
    public void openUrl() throws IOException {
        prop = rcf.readConfig("config");
        String url = prop.getProperty("url");
        driver.get(url);
    }

    @Then("Verify Logo")
    public void verify_Logo() {
        Assert.assertEquals(true, logo_Facebook.isDisplayed(), "Logo is not present");
    }

    @And("Enter username {string}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(input_Username)).sendKeys(username);
    }

    @And("Enter password {string}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(input_Password)).sendKeys(password);
    }
    @And("Click login button")
    public void clickLogin() {
        btn_Login.click();
    }

    public void clickForgotPassword() {
        btn_ForgotPassword.click();
    }

    public void clickCreateAccount() {
        btn_CreateAccount.click();
    }

    @Then("Verify you are login")
    public void verifyNotLogin() {
        Assert.assertEquals("https://www.facebook.com/?sk=welcome", driver.getCurrentUrl(), "User is not able to Login");
    }

    @Then("Verify you are not login")
    public void verifyLogin() {
        Assert.assertEquals("Error", driver.getTitle(), "User is not able to Login");
    }
    @Then("Quit Browser")
    public void quitBrowser()
    {
        driver.quit();
    }

}
