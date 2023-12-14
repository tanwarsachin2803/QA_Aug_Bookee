package WebPages.cucumberHomePage;

import Utilities.BaseClass;
import Utilities.ReadFile;
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
import java.time.Duration;
import java.util.Properties;
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

    @FindBy(xpath = "//android.widget.Button[@text='OK']")
    WebElement btn_Ok;
    public HomePage() {
    }
    public HomePage(BaseClass bs) throws IOException {
        this.bs=bs;
        this.driver=bs.driver;
        rf=new ReadFile();
        prop=rf.readConfig("config");
        platformName=prop.getProperty("Platform_OS");
        wait=new WebDriverWait(driver,20);
        PageFactory.initElements(driver,this);
    }
    public void verify_Logo()
    {
        Assert.assertEquals(true,logo_Facebook.isDisplayed(),"Logo is not present");
    }
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(input_Username)).sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(input_Password)).sendKeys(password);
    }
    public void clickLogin() throws InterruptedException {
        btn_Login.click();
        Thread.sleep(4000);
    }
    public void clickForgotPassword() {
        btn_ForgotPassword.click();
    }
    public void clickCreateAccount() {
        btn_CreateAccount.click();
    }
    public void verifyNotLogin()
    {
        if(platformName.equals("mobile"))
        {
            System.out.println("Verifying");
            WebElement logginText=driver.findElement(By.xpath("//android.widget.TextView[@text='Incorrect Password']"));
            Boolean actualResult=wait.until(ExpectedConditions.visibilityOf(logginText)).isDisplayed(); //true
            Assert.assertEquals(actualResult,true,"Not verified");
            btn_Ok.click();
        }
        else
            Assert.assertEquals("Error",driver.getTitle(),"User is not able to Login");
    }
    public void verifyLogin()
    {
        if(platformName.equals("mobile"))
        {
            System.out.println("Verifying");
            WebElement logginText=driver.findElement(By.xpath("//android.widget.TextView[@text='Save Your Login Info']"));
            Boolean actualResult=wait.until(ExpectedConditions.visibilityOf(logginText)).isDisplayed();
            Assert.assertEquals(actualResult,true,"Not logged in");
        }
        else
            Assert.assertEquals("(1) Facebook",driver.getTitle(),"User is not able to Login");
    }
    //android.widget.TextView[@resource-id="com.facebook.katana:id/(name removed)" and @text="Incorrect Password"]
}
