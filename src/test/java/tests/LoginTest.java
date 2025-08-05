package tests;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import utils.LoginHelper;
import java.util.Map;
import java.io.IOException;

public class  LoginTest extends BaseClass{
    @Test
    public void loginAsOperator(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.loginAsOperator("6111111111", "7891");
        System.out.println("Title of the page is:" +driver.getTitle());
    
    }
}

}}
