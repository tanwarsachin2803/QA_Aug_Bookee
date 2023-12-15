package WebPages;

import Utilities.BaseClass;
import Utilities.ReadFile;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;
import io.qameta.allure.*;

@Log4j2
public class HomePage {
    BaseClass bs;
    WebDriver driver;
    WebDriverWait wait;
    ReadFile rf;
    Properties prop;
    String platformName="";
    @FindBy(xpath="//img[@class='fb_logo _8ilh img']")
    WebElement logo_Facebook;

    //first we have the xpath for the browser testing
    // mobile application testing
    @FindAll({
            @FindBy(xpath = "//input[@id='email']"), //for webbrowser
            @FindBy(xpath = "//android.widget.AutoCompleteTextView[@content-desc='Username']") // for mobile_app
    })
    WebElement input_Username;
    @FindAll({
            @FindBy(id = "pass"),
            @FindBy(xpath = "//android.widget.EditText[@content-desc='Password']")
    })
    WebElement input_Password;
    @FindAll({
            @FindBy(xpath = "//button[@name='login']"),
            @FindBy(xpath = "//android.view.ViewGroup[@content-desc='Log In']")
    })
    WebElement btn_Login;
    @FindBy(xpath = "//a[contains(text(),'Forgotten password')]")
    WebElement btn_ForgotPassword;

    @FindBy(xpath="//a[contains(text(),'Create new account')]")
    WebElement btn_CreateAccount;

    public @FindBy(xpath = "//android.widget.Button[@text='OK']")
    WebElement btn_Ok;

    public HomePage(BaseClass bs) throws IOException {
        this.bs=bs;
        this.driver=bs.driver;
        rf=new ReadFile();
        prop=rf.readConfig("config");
        platformName=prop.getProperty("Platform_OS");
        PageFactory.initElements(driver,this);
        wait=new WebDriverWait(driver,20);

    }
    @Step("Verifying the Facebook logo")
    public void verify_Logo() {
        if(platformName.contentEquals("website")){
        log.info("Verifying the Facebook logo");
        Assert.assertTrue(logo_Facebook.isDisplayed(), "Logo is not present");}
        else
        {
            log.info("We are testing mobile application");
        }
    }

    @Step("Entering username: {0}")
    public void enterUsername(String username) {
        log.info("Entering username: " + username);
        wait.until(ExpectedConditions.visibilityOf(input_Username)).sendKeys(username);
    }

    @Step("Entering password {0}")
    public void enterPassword(String password) {
        log.info("Entering password");
        wait.until(ExpectedConditions.visibilityOf(input_Password)).sendKeys(password);
    }

    @Step("Clicking the Login button")
    public void clickLogin() throws InterruptedException {
        log.info("Clicking the Login button");
        btn_Login.click();
        Thread.sleep(4000);
    }

    @Step("Clicking Forgot Password")
    public void clickForgotPassword() {
        log.info("Clicking Forgot Password");
        btn_ForgotPassword.click();
    }

    @Step("Clicking Create Account")
    public void clickCreateAccount() {
        log.info("Clicking Create Account");
        btn_CreateAccount.click();
    }

    @Step("Verifying unsuccessful login")
    public void verifyNotLogin() {
        log.info("Verifying unsuccessful login");
        if (platformName.equals("mobile")) {
            System.out.println("Verifying");
            WebElement logginText=driver.findElement(By.xpath("//android.widget.TextView[@text='Incorrect Password']"));
            Boolean actualResult=wait.until(ExpectedConditions.visibilityOf(logginText)).isDisplayed(); //true
            Assert.assertEquals(actualResult,true,"Not verified");
        } else {
            Assert.assertEquals("Error", driver.getTitle(), "User is not able to Login");
        }
    }

    @Step("Verifying successful login")
    public void verifyLogin() throws InterruptedException {
        log.info("Verifying successful login");
        if (platformName.equals("mobile")) {
            Thread.sleep(2000);
            WebElement logginText=driver.findElement(By.xpath("//android.widget.TextView[@text='Save Your Login Info']"));
            Boolean actualResult=wait.until(ExpectedConditions.visibilityOf(logginText)).isDisplayed();
            Assert.assertEquals(actualResult,true,"Not logged in");
        } else {
            Assert.assertEquals("(1) Facebook", driver.getTitle(), "User is not able to Login");
        }
    }

    @Step("Click ok button")
    public void clickOkButton()
    {
        log.info("Click ok button");
        if(platformName.contentEquals("mobile"))
        {
            btn_Ok.click();
        }
        else {
            log.info("We are testing website");
        }
    }
}