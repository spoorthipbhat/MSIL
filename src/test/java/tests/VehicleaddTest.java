package tests;
import base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TrackDrivers;
import pages.VehicleaddPage;
import utils.TestDataProvider;
import java.io.IOException;
import java.util.Map;

public class VehicleaddTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = TestDataProvider.class)
    public void testAddVehicle(Map<String, String> row) throws InterruptedException, IOException {

        TrackDrivers drivers = new TrackDrivers(driver);
        drivers.clickUploadForMobile(row.get("DriverMobileNumber"));

        VehicleaddPage vehiclePage = new VehicleaddPage(driver);
        vehiclePage.addVehicle(
            row.get("DriverMobileNumber"),
            row.get("rcNum"),
            row.get("rcImg"),
            row.get("permitImg"),
            row.get("insuranceimg"),
            row.get("fitnessImg"),
            row.get("pucImg"),
            row.get("frontImg"),
            row.get("backImg"),
            row.get("rightImg"),
            row.get("leftImg"),
            row.get("frontIntImg"),
            row.get("backIntImg"),
            row.get("odoImg")
        );
    }
}
