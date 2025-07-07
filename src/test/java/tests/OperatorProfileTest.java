package tests;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.OperatorProfilePage;
import java.util.UUID;

public class OperatorProfileTest extends BaseClass {
    private LoginPage loginPage;
    private OperatorProfilePage profilePage;

    @Parameters({"browser", "env"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser, @Optional("uat") String env) {
        super.setUp(browser, env);
        loginPage = new LoginPage(driver);
        profilePage = new OperatorProfilePage(driver);
        loginPage.loginAsOperator("6111111111", "7891");
    }

    @AfterClass
    public void tearDown() {
        if (profilePage != null) {
            profilePage.openProfilePanel();
            profilePage.clickLogoutButton();
        }
        super.tearDown();
    }

    @Test(priority = 1)
    public void testProfilePanelAndLogoutButtonVisibility() {
        System.out.println("\n--- Operator Profile: Profile panel and Logout button visibility ---");
        profilePage.openProfilePanel();
        boolean viewProfileVisible = profilePage.isViewProfileLinkVisible();
        boolean logoutVisible = profilePage.isLogoutButtonVisible();
        System.out.println("View Profile link is visible: " + viewProfileVisible);
        System.out.println("Logout button is visible: " + logoutVisible);
        Assert.assertTrue(viewProfileVisible, "View Profile link should be visible");
        Assert.assertTrue(logoutVisible, "Logout button should be visible");
        System.out.println("--- End of test: Profile panel and Logout button visibility ---\n");
    }

    @Test(priority = 2)
    public void testViewProfileDetails() {
        System.out.println("\n--- Operator Profile: View profile details ---");
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String name = profilePage.getDisplayedOperatorName();
        String mobile = profilePage.getDisplayedOperatorMobile();
        String email = profilePage.getDisplayedOperatorEmail();
        System.out.println("Operator Name: " + name);
        System.out.println("Operator Mobile: " + mobile);
        System.out.println("Operator Email: " + email);
        Assert.assertNotNull(name, "Operator name should be displayed");
        Assert.assertNotNull(mobile, "Operator mobile should be displayed");
        Assert.assertNotNull(email, "Operator email should be displayed");
        System.out.println("--- End of test: View profile details ---\n");
    }

    @Test(priority = 3)
    public void testUpdateProfileName() {
        System.out.println("\n--- Operator Profile: Update profile name ---");
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String currentName = profilePage.getDisplayedOperatorName();
        System.out.println("Current name before update: " + currentName);
        profilePage.clickEditProfile();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int idx = (int) (Math.random() * letters.length());
            sb.append(letters.charAt(idx));
        }
        String newName = sb.toString();
        System.out.println("Attempting to update name to: " + newName);
        profilePage.enterName(newName);
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        System.out.println("About to click Update button...");
        profilePage.clickUpdateProfile();
        System.out.println("Update button clicked");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        if (profilePage.isErrorMessageVisible()) {
            String errorMessage = profilePage.getErrorMessageText();
            System.out.println("Error message found: " + errorMessage);
        } else {
            System.out.println("No error message found after update");
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String updatedName = profilePage.getDisplayedOperatorName();
        System.out.println("Name after update attempt: " + updatedName);
        Assert.assertEquals(updatedName, newName, "Profile name should be updated");
        System.out.println("--- End of test: Update profile name ---\n");
    }

    @Test(priority = 4)
    public void testUpdateProfileEmail() {
        System.out.println("\n--- Operator Profile: Update profile email ---");
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        
        // Get current email before update
        String oldEmail = profilePage.getDisplayedOperatorEmail();
        System.out.println("Previous email: " + oldEmail);
        
        profilePage.clickEditProfile();
        String newEmail = "testop" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";
        System.out.println("Attempting to update email to: " + newEmail);
        
        profilePage.enterEmail(newEmail);
        profilePage.clickUpdateProfile();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String updatedEmail = profilePage.getDisplayedOperatorEmail();
        System.out.println("Updated email: " + updatedEmail);
        
        Assert.assertEquals(updatedEmail, newEmail, "Profile email should be updated");
        System.out.println("--- End of test: Update profile email ---\n");
    }

    @Test(priority = 5)
    public void testNameFieldAlphabetOnly() {
        System.out.println("\n--- Operator Profile: Name field alphabet-only validation ---");
        driver.navigate().refresh();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        profilePage.clickEditProfile();
        
        // Test with invalid name containing numbers and special characters
        String invalidName = "Test123!@#";
        System.out.println("Attempting to enter invalid name with numbers and special chars: '" + invalidName + "'");
        
        profilePage.enterName(invalidName);
        profilePage.clickUpdateProfile();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String name = profilePage.getDisplayedOperatorName();
        System.out.println("Name after entering invalid characters: " + name);
        
        if (name.contains("123") || name.contains("!@#")) {
            System.out.println("❌ FAILURE: Invalid characters (numbers/special chars) were accepted - alphabet-only validation not working");
        } else {
            System.out.println("✅ SUCCESS: Invalid name '" + invalidName + "' was NOT accepted - only valid characters were kept: '" + name + "'");
        }
        
        Assert.assertFalse(name.contains("123") || name.contains("!@#"), "Name field should not accept non-alphabet characters");
        System.out.println("--- End of test: Name field alphabet-only validation ---\n");
    }

    @Test(priority = 6)
    public void testNameFieldMaxLength() {
        System.out.println("\n--- Operator Profile: Name field max length validation ---");
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        
        // Get current name before test
        String previousName = profilePage.getDisplayedOperatorName();
        System.out.println("Previous name: " + previousName);
        
        profilePage.clickEditProfile();
        
        // Test with name longer than maximum allowed length (e.g., 60 characters)
        String longName = "A".repeat(60);
        System.out.println("Attempting to enter long name: " + longName + " (length: " + longName.length() + ")");
        
        profilePage.enterName(longName);
        profilePage.clickUpdateProfile();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String name = profilePage.getDisplayedOperatorName();
        System.out.println("Name after entering long value: " + name + " (length: " + name.length() + ")");
        
        if (name.equals(previousName)) {
            System.out.println("✅ SUCCESS: Long name was NOT accepted - previous name remained unchanged");
        } else if (name.length() <= 50) {
            System.out.println("✅ SUCCESS: Long name was truncated to valid length");
        } else {
            System.out.println("❌ FAILURE: Long name was accepted - max length validation not working");
        }
        
        Assert.assertTrue(name.length() <= 50, "Name field should restrict input to 50 characters");
        System.out.println("--- End of test: Name field max length validation ---\n");
    }

    @Test(priority = 7)
    public void testEmailFieldValidation() {
        System.out.println("\n--- Operator Profile: Email field validation ---");
        driver.navigate().refresh();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String[] invalidEmails = {"plainaddress", "@missingusername.com", "username@.com", "username@domain", "username@domain..com"};
        for (String email : invalidEmails) {
            profilePage.clickEditProfile();
            profilePage.enterEmail(email);
            profilePage.clickUpdateProfile();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            profilePage.openProfilePanel();
            profilePage.openViewProfile();
            String actualEmail = profilePage.getDisplayedOperatorEmail();
            System.out.println("Invalid email '" + email + "' was NOT accepted - previous email remains: " + actualEmail);
            Assert.assertNotEquals(actualEmail, email, "Invalid email should not be accepted: " + email);
        }
        profilePage.clickEditProfile();
        String validEmail = "validop" + UUID.randomUUID().toString().substring(0, 5) + "@mail.com";
        profilePage.enterEmail(validEmail);
        profilePage.clickUpdateProfile();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String updatedEmail = profilePage.getDisplayedOperatorEmail();
        System.out.println("Valid email '" + validEmail + "' was ACCEPTED and updated to: " + updatedEmail);
        Assert.assertEquals(updatedEmail, validEmail, "Valid email should be accepted");
        System.out.println("--- End of test: Email field validation ---\n");
    }

    @Test(priority = 8)
    public void testEmailFieldMaxLength() {
        System.out.println("\n--- Operator Profile: Email field max length validation ---");
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        
        // Get current email before test
        String previousEmail = profilePage.getDisplayedOperatorEmail();
        System.out.println("Previous email: " + previousEmail);
        
        profilePage.clickEditProfile();
        
        // Test with email longer than maximum allowed length (e.g., 60 characters)
        String longEmail = ("a".repeat(50)) + "@mail.com"; // 58 characters total
        System.out.println("Attempting to enter long email: " + longEmail + " (length: " + longEmail.length() + ")");
        
        profilePage.enterEmail(longEmail);
        profilePage.clickUpdateProfile();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        
        profilePage.openProfilePanel();
        profilePage.openViewProfile();
        String email = profilePage.getDisplayedOperatorEmail();
        System.out.println("Email after entering long value: " + email + " (length: " + email.length() + ")");
        
        if (email.equals(previousEmail)) {
            System.out.println("✅ SUCCESS: Long email was NOT accepted - previous email remained unchanged");
        } else if (email.length() <= 50) {
            System.out.println("✅ SUCCESS: Long email was truncated to valid length");
        } else {
            System.out.println("❌ FAILURE: Long email was accepted - max length validation not working");
        }
        
        Assert.assertTrue(email.length() <= 50, "Email field should restrict input to 50 characters");
        System.out.println("--- End of test: Email field max length validation ---\n");
    }
} 