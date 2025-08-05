package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.AddBulkdriver;
import utils.MobileNumbergen;
import utils.OtpFetcher;
import java.util.Map;

public class AddBulkdriverTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
    public void bulkdriver(Map<String, String> data) throws Exception {

        String mobile = data.get("mobile");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.triggerOtpSend(mobile);

        String otp = OtpFetcher.getOtpBasedOnEnv(env, mobile);
        loginPage.enterOtp(otp);
        loginPage.clickVerify();

        String csvPath = MobileNumbergen.generateDriverCSV("valid_driver_data.csv", 100);
        AddBulkdriver bulk = new AddBulkdriver(driver);
        bulk.addingbulkdrivers(csvPath);
    }
}
