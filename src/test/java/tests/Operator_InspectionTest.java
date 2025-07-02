package tests;

import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.Operator_InspectionPage;

public class Operator_InspectionTest extends BaseClass {

    @Test
    public void testOperatorInspectionFlow() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");

        // Run the inspection flow
        Operator_InspectionPage inspectionPage = new Operator_InspectionPage(driver);
        inspectionPage.navigateToInspectionTab();
        inspectionPage.selectPendingStatus();
        inspectionPage.selectOnboardingInspectionType();
        inspectionPage.selectDateRange();
        inspectionPage.enterDriverMobile("7811111112");
        inspectionPage.clickVehicleNumber("KA02AJ9125");
        inspectionPage.waitForInspectionForm();
        inspectionPage.answerInspectionQuestions();
        inspectionPage.rejectWithReason("Test rejection reason");

        System.out.println("Operator inspection flow completed.");
    }
}