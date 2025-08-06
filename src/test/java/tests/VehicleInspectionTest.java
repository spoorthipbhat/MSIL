package tests;

import base.BaseClass;
import pages.LoginPage;
import pages.VehicleInspection;
import utils.TestDataProvider;
import org.testng.annotations.Test;
import java.util.Map;
import utils.OtpFetcher;
import utils.LoginHelper;
public class VehicleInspectionTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = TestDataProvider.class)
    public void runVehicleInspectionFlow(Map<String, String> data) throws Exception {
        String operatorMobile = data.get("OperatorMobileNumber");
        LoginHelper loginHelper = new LoginHelper(driver, env);
        loginHelper.loginAsOperator(operatorMobile);

        VehicleInspection inspection = new VehicleInspection(driver);
        inspection.goToInspectionTab();                                 
        inspection.selectDate(data);                                  
        inspection.selectVehicleByNumber(data.get("vehicleNo"));    
        inspection.fillVerificationList(data);                
        inspection.uploadVideoFromDrive(data.get("UploadVideo"));
        inspection.approve();                                     
    }
}


