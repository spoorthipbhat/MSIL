package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.OperatorProfilePage;
import utils.OtpFetcher;

import java.util.Map;
import java.io.IOException;

public class OperatorProfileTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
    public void operatorProfile(Map<String, String> row) throws InterruptedException, IOException {
        String mobile = "6111111111";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.triggerOtpSend(mobile);
        String otp = OtpFetcher.getOtpBasedOnEnv(env, mobile);
        loginPage.enterOtp(otp);
        loginPage.clickVerify();

        OperatorProfilePage profile = new OperatorProfilePage(driver);
        profile.goToEditProfile();
        profile.enterNameAndEmail(row.get("name"), row.get("email"));
    }
}