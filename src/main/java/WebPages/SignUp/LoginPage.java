package WebPages.SignUp;

import Utilities.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    BaseClass bs;
    WebDriver driver;
    @FindBy(xpath="//input[@name='email']")
    WebElement inpEmail;

    public LoginPage(BaseClass bs)
    {
        this.bs=bs;
        this.driver=bs.driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmail(String email)
    {
        inpEmail.sendKeys("sachintanwar");
    }
}
