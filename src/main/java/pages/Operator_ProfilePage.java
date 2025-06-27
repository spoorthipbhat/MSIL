package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Operator_ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public Operator_ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Clicks the Operator profile icon
    public void openProfilePanel() {
        WebElement operatorProfile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Operator']")));
        operatorProfile.click();
    }

    // Clicks the View Profile link
    public void openViewProfile() {
        WebElement viewProfile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='View Profile']")));
        viewProfile.click();
    }

    // Returns the displayed operator name using a generalized XPath for any name
    public String getDisplayedOperatorName() {
        WebElement nameDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class, 'text-xl')]")));
        return nameDiv.getText();
    }

    // Returns the displayed operator mobile number using the provided XPath
    public String getDisplayedOperatorMobile() {
        WebElement mobileDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[text() and string-length(text())=10 and number(text())=number(text())]")));
        return mobileDiv.getText();
    }

    // Returns the displayed operator email using a generalized XPath for any email address
    public String getDisplayedOperatorEmail() {
        WebElement emailDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(), '@')]")));
        return emailDiv.getText();
    }

    // Returns true if the 'View Profile' link is visible after opening the profile panel
    public boolean isViewProfileLinkVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='View Profile']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Returns true if the 'Logout' button is visible after opening the profile panel
    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Logout']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Clicks the 'Logout' button in the profile panel
    public void clickLogoutButton() {
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Logout']")));
        logoutBtn.click();
    }
} 