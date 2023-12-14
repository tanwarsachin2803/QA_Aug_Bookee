package WebPages;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class cucumberHomePage2 {
    WebDriver driver;

    @Given("Open Browser2")
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
    }

    @When("Open Url2")
    public void openUrl()
    {
        driver.get("https://facebook.com");
    }

    @And("Enter username2")
    public void enter_username()
    {
        driver.findElement(By.id("email")).sendKeys("pragra123@yopmail.com");
    }

    @And("Enter password2")
    public void enter_password()
    {
        driver.findElement(By.id("pass")).sendKeys("pragra@123");

    }

    @And("Click login button2")
    public void click_login()
    {
        driver.findElement(By.name("login")).click();
    }

    @Then("Verify Login2")
    public void verify_login()
    {
        Assert.assertEquals("(1) Facebook",driver.getTitle(),"User is not able to Login");
    }


}
