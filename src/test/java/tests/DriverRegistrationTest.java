
package tests;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.DriverRegistration;
import pages.TrackDrivers;
import java.util.Map;
import utils.TestDataProvider;
import pages.UploadDriverdocs;
import java.io.IOException;                       


public class DriverRegistrationTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
    public void testDriverRegistration (Map<String, String> row) throws InterruptedException, IOException{
        LoginPage loginpage = new LoginPage(driver);
        loginpage.loginAsOperator("8111111111", "7891");
        DriverRegistration driverpage = new DriverRegistration(driver);
        driverpage.driverRegistration(row.get("DriverMobileNumber"), row.get("DriverOTP"));
        TrackDrivers dashboard = new TrackDrivers(driver);
        dashboard.clickUploadForMobile(row.get("DriverMobileNumber"));
        UploadDriverdocs docPage = new UploadDriverdocs(driver);
        docPage.AddalldriverDocsandinfo(
            row.get("dlNum"), 
            row.get("dob"), 
            row.get("aadhaarNum"),
            row.get("panNum"),
            row.get("panDoc"),
            row.get("aadhaarFront"),
            row.get("aadhaarBack"),        
            row.get("dlFront")
        );
    }
}

