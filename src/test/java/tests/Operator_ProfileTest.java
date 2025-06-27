package tests;

import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.Operator_ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Operator_ProfileTest extends BaseClass {

    /*
    // OP_TC_183: Verify operator name and mobile number are displayed when profile icon is clicked
    @Test
    public void testOperatorProfileNameAndMobileVisible_OP_TC_183() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");

        // Open profile panel by clicking the Operator icon
        Operator_ProfilePage profilePage = new Operator_ProfilePage(driver);
        profilePage.openProfilePanel();
        profilePage.openViewProfile();

        // Get and assert operator name and mobile number are visible and non-empty
        String name = profilePage.getDisplayedOperatorName();
        String mobile = profilePage.getDisplayedOperatorMobile();
        System.out.println("Operator Name: " + name);
        System.out.println("Operator Mobile: " + mobile);
        org.testng.Assert.assertNotNull(name, "Operator name should not be null");
        org.testng.Assert.assertFalse(name.trim().isEmpty(), "Operator name should not be empty");
        org.testng.Assert.assertNotNull(mobile, "Operator mobile should not be null");
        org.testng.Assert.assertFalse(mobile.trim().isEmpty(), "Operator mobile should not be empty");
    }
    */

    /*
    /**
     * OP_TC_184: Verify the 'View Profile' link is displayed below operator info
    
    @Test
    public void testViewProfileLinkVisible_OP_TC_184() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");

        // Open profile panel by clicking the Operator icon
        Operator_ProfilePage profilePage = new Operator_ProfilePage(driver);
        profilePage.openProfilePanel();

        // Assert 'View Profile' link is visible
        boolean isVisible = profilePage.isViewProfileLinkVisible();
        System.out.println("Is 'View Profile' link visible? " + isVisible);
        org.testng.Assert.assertTrue(isVisible, "'View Profile' link should be visible below operator info");
    }
    */

    /*
    /**
     * OP_TC_185: Verify 'Logout' button is displayed in the profile panel
     
    @Test
    public void testLogoutButtonVisible_OP_TC_185() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");

        // Open profile panel by clicking the Operator icon
        Operator_ProfilePage profilePage = new Operator_ProfilePage(driver);
        profilePage.openProfilePanel();

        // Assert 'Logout' button is visible
        boolean isVisible = profilePage.isLogoutButtonVisible();
        System.out.println("Is 'Logout' button visible? " + isVisible);
        org.testng.Assert.assertTrue(isVisible, "'Logout' button should be present in the profile panel");
    }
    */

    /**
     * OP_TC_186: Verify logout button functionality (just perform logout action)
     
    @Test
    public void testLogoutFunctionality_OP_TC_186() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("6111111111", "7891");

        // Open profile panel and click Logout
        Operator_ProfilePage profilePage = new Operator_ProfilePage(driver);
        profilePage.openProfilePanel();
        profilePage.clickLogoutButton();

        // No assertion, just perform logout as per test case description
        System.out.println("Logout button clicked successfully.");
    }

    /**
     * OP_TC_187: Verify profile details screen opens on clicking 'View Profile' and shows name, mobile, and email
     */
    @Test
    public void testProfileDetailsScreen_OP_TC_187() throws InterruptedException {
        // Login as operator
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsOperator("7811111112", "7891");

        // Open profile panel and click 'View Profile'
        Operator_ProfilePage profilePage = new Operator_ProfilePage(driver);
        profilePage.openProfilePanel();
        profilePage.openViewProfile();

        // Assert name, mobile, and email are visible and correct
        String name = profilePage.getDisplayedOperatorName();
        String mobile = profilePage.getDisplayedOperatorMobile();
        String email = profilePage.getDisplayedOperatorEmail();
        System.out.println("Operator Name: " + name);
        System.out.println("Operator Mobile: " + mobile);
        System.out.println("Operator Email: " + email);
        org.testng.Assert.assertNotNull(name, "Operator name should not be null");
        org.testng.Assert.assertFalse(name.trim().isEmpty(), "Operator name should not be empty");
        org.testng.Assert.assertNotNull(mobile, "Operator mobile should not be null");
        org.testng.Assert.assertFalse(mobile.trim().isEmpty(), "Operator mobile should not be empty");
        org.testng.Assert.assertNotNull(email, "Operator email should not be null");
        org.testng.Assert.assertFalse(email.trim().isEmpty(), "Operator email should not be empty");
    }
}
