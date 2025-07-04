package tests;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.AddFleet;
import pages.LoginPage;

public class AddFleetTest extends BaseClass{

    @Test
    public void addFleet() throws InterruptedException{
        
        AddFleet addfleet = new AddFleet(driver);
        LoginPage loginpage = new LoginPage(driver);
        // To login in operator dashboard
        loginpage.loginAsOperator("8111111111", "7891");
        // To add fleet with documents in operator and delete the added operator
        addfleet.addFleetInOperator("6244211512", "7891");
    }
    
}