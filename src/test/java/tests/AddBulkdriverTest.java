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
import pages.AddBulkdriver;
import utils.MobileNumbergen;

public class AddBulkdriverTest extends BaseClass{

    @Test(dataProvider="activeDrivers", dataProviderClass= utils.TestDataProvider.class)
    public void bulkdriver (Map<String, String> data) throws Exception{
        LoginPage loginpage = new LoginPage(driver);
        loginpage.loginAsOperator("8111111111", "7891");
        String csvPath = MobileNumbergen.generateDriverCSV("valid_driver_data.csv", 100);
        AddBulkdriver bulk= new AddBulkdriver(driver);
        bulk.addingbulkdrivers(csvPath);

    }
}