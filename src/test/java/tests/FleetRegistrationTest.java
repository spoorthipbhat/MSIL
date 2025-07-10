package tests;

import java.io.IOException;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.FleetRegistration;
import pages.LoginPage;

public class FleetRegistrationTest extends BaseClass {

    @Test(dataProvider = "activeDrivers", dataProviderClass = utils.TestDataProvider.class)
    public void testFleetRegistration(Map<String, String> row, ITestContext context) throws InterruptedException, IOException {
     
        // ✅ Get FlowId from XML (must be 1-4)
        String flowIdParam = context.getCurrentXmlTest().getParameter("FlowId");
        System.out.println("✅ FlowId = " + flowIdParam);
        if (flowIdParam == null || flowIdParam.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Missing 'FlowId' parameter in XML");
        }

        int flowId;
        try {
            flowId = Integer.parseInt(flowIdParam.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("❌ Invalid FlowId (not a number): " + flowIdParam);
        }

        // ✅ Login first
        LoginPage loginPage = new LoginPage(driver);
        

        FleetRegistration fleetRegistration = new FleetRegistration(driver);

        switch (flowId) {

            case 1: // Business Fleet → Operator Code
                loginPage.loginAsOperator("67211111111", "7891");
                fleetRegistration.addBusinessFleet(
                        row.get("fleetName"),
                        row.get("aadhaarNumber"),
                        row.get("aadhaarFrontImage"),
                        row.get("aadhaarBackImage"),
                        row.get("fleetEmail"),
                        row.get("fleetCity"),
                        row.get("businessGstNumber"),
                        row.get("gstImage"),
                        row.get("businessPanNumber"),
                        row.get("PanImage"),
                        row.get("businessLicenseNumber"),
                        row.get("licenseImage"));
                fleetRegistration.operatorCode(row.get("operatorCode"));
                fleetRegistration.completeRegistration();
                break;

            case 2: // Individual Fleet → Operator Code
                loginPage.loginAsOperator("67211111111", "7891");
                fleetRegistration.addIndividualFleet(
                        row.get("fleetName"),
                        row.get("aadhaarNumber"),
                        row.get("aadhaarFrontImage"),
                        row.get("aadhaarBackImage"),
                        row.get("fleetEmail"),
                        row.get("fleetCity"),
                        row.get("individualPanNumber"),
                        row.get("individualPanImage"));
                fleetRegistration.operatorCode(row.get("operatorCode"));
                fleetRegistration.completeRegistration();
                break;

            case 3: // Add Fleet in Operator → then Business Fleet
                loginPage.loginAsOperator("61111111111", "7891");
                fleetRegistration.addFleetInOperator(row.get("mobileNo"), row.get("otp"));
                fleetRegistration.addBusinessFleet(
                        row.get("fleetName"),
                        row.get("aadhaarNumber"),
                        row.get("aadhaarFrontImage"),
                        row.get("aadhaarBackImage"),
                        row.get("fleetEmail"),
                        row.get("fleetCity"),
                        row.get("businessGstNumber"),
                        row.get("gstImage"),
                        row.get("businessPanNumber"),
                        row.get("PanImage"),
                        row.get("businessLicenseNumber"),
                        row.get("licenseImage"));
                fleetRegistration.completeRegistration();
                break;

            case 4: // Add Fleet in Operator → then Individual Fleet
                loginPage.loginAsOperator("61111111111", "7891");   
                fleetRegistration.addFleetInOperator(row.get("mobileNo"), row.get("otp"));
                fleetRegistration.addIndividualFleet(
                        row.get("fleetName"),
                        row.get("aadhaarNumber"),
                        row.get("aadhaarFrontImage"),
                        row.get("aadhaarBackImage"),
                        row.get("fleetEmail"),
                        row.get("fleetCity"),
                        row.get("individualPanNumber"),
                        row.get("individualPanImage"));
                fleetRegistration.completeRegistration();
                break;

            default:
                throw new IllegalArgumentException("❌ Invalid FlowId in XML: " + flowId);
        }
    }
}
