
package tests;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.DriverRegistration;
import pages.TrackDrivers;
import pages.UploadDriverdocs;
import utils.LoginHelper;
import java.util.Map;
import utils.TestDataProvider;
import pages.UploadDriverdocs;
import java.io.IOException;                       


public class DriverRegistrationTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)

    public void testDriverRegistration(Map<String, String> row) throws IOException, InterruptedException {
        // ✅ Operator login
        String operatorMobile = row.get("OperatorMobileNumber");
        LoginHelper loginHelper = new LoginHelper(driver, env);
        loginHelper.loginAsOperator(operatorMobile);

        // ✅ Driver registration (OTP handled inside)
        DriverRegistration driverpage = new DriverRegistration(driver);
        String driverMobile = row.get("DriverMobileNumber");
        driverpage.driverRegistration(driverMobile, env); // 🔁 env passed here

        // ✅ Upload driver documents

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

