package tests;
import org.testng.annotations.*;
import org.testng.ITestContext;
import org.testng.Assert;
import pages.OperatorProfilePage;
import base.BaseClass;
import utils.GoogleSheetReader;
import java.util.Map;
import pages.LoginPage; // For login
import utils.TestDataProvider;
import java.io.IOException;
public class OperatorProfileTest extends BaseClass {
    @Test(dataProvider = "activeDrivers", dataProviderClass = TestDataProvider.class)
    public void operatorProfile(Map<String, String> row) throws InterruptedException, IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");
        OperatorProfilePage profile= new OperatorProfilePage(driver);
        profile.goToEditProfile();
        profile.enterNameAndEmail(
            row.get("name"),
            row.get("email"));
    }
}