package tests;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.DriverRegistration;
import pages.TrackDrivers;


public class DriverRegistrationTest extends BaseClass {

    @Test
    public void testDriverRegistration (){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.loginAsOperator("8111111111", "7891");

        DriverRegistration driverpage = new DriverRegistration(driver);
        driverpage.driverRegistration("6111133333", "7891");

        TrackDrivers dashboard = new TrackDrivers(driver);
        dashboard.clickUploadForMobile("6111133333");
    }
}