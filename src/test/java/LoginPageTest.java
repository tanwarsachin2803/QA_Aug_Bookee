import Utilities.BaseClass;
import Utilities.ReadFile;
import WebPages.SignUp.LoginPage;

import java.io.IOException;
import java.util.Properties;

public class LoginPageTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Properties prop;
        ReadFile readFile=new ReadFile();
        prop=readFile.readConfig("config");
        System.out.println(prop.get("name"));
        BaseClass bs=new BaseClass();
        bs.setup();
        bs.getUrl("https://f10boxing.weebly.com/reserve.html#/login");
        Thread.sleep(5000);
        bs.switchToIframe("name");
        LoginPage lp=new LoginPage(bs);
        lp.enterEmail("Sachin Tanwar");
    }
}
