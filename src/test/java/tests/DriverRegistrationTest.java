package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.DriverRegistration;
import pages.LoginPage;
import pages.TrackDrivers;
import pages.UploadDriverdocs;

import java.util.Map;
import java.io.IOException;

public class DriverRegistrationTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
    public void testDriverRegistration(Map<String, String> row) throws IOException, InterruptedException {
        // ✅ Operator login
        String operatorMobile = row.get("OperatorMobileNumber");
        LoginPage loginPage = new LoginPage(driver); 
        loginPage.triggerOtpSend(operatorMobile);

        String operatorOtp = utils.OtpFetcher.getOtpBasedOnEnv(env, operatorMobile);
        System.out.println("🔐 Operator OTP for " + operatorMobile + " → " + operatorOtp);
        loginPage.enterOtp(operatorOtp);
        loginPage.clickVerify();

        // ✅ Driver registration (OTP handled inside)
        DriverRegistration driverpage = new DriverRegistration(driver);
        String driverMobile = row.get("DriverMobileNumber");
        Thread.sleep(3000);

        driverpage.driverRegistration(driverMobile, env); // 🔁 env passed here

        // ✅ Upload driver documents
        TrackDrivers dashboard = new TrackDrivers(driver);
        dashboard.clickUploadForMobile(driverMobile);

        UploadDriverdocs docPage = new UploadDriverdocs(driver);
        docPage.AddalldriverDocsandinfo(
            row.get("dlNum"), row.get("dob"), row.get("aadhaarNum"),
            row.get("panNum"), row.get("panDoc"), row.get("aadhaarFront"),
            row.get("aadhaarBack"), row.get("dlFront")
        );
    }
}
