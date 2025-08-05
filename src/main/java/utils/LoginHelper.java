package utils;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginHelper {

    private WebDriver driver;
    private String env;

    public LoginHelper(WebDriver driver, String env) {
        this.driver = driver;
        this.env = env;
    }

    public void loginAsOperator(String operatorMobile) {
        performLogin(operatorMobile, "Operator");
    }

    public void loginAsFleetOwner(String fleetMobile) {
        performLogin(fleetMobile, "FleetOwner");
    }

    private void performLogin(String mobile, String userType) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.triggerOtpSend(mobile);

        String otp = OtpFetcher.getOtpBasedOnEnv(env, mobile);
        System.out.println("🔐 " + userType + " Login → Mobile: " + mobile + ", OTP: " + otp);

        loginPage.enterOtp(otp);
        loginPage.clickVerify();
    }
}
