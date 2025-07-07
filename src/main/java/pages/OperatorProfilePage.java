package pages;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
public class OperatorProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Locators
    private final By operatorIconBy = By.xpath("//div[text()='Operator']");
    private final By viewProfileBy = By.xpath("//div[text()='View Profile']");
    private final By nameDivBy = By.xpath("//div[contains(@class, 'text-xl')]");
    private final By mobileDivBy = By.xpath("//div[text() and string-length(text())=10 and number(text())=number(text())]");
    private final By emailDivBy = By.xpath("//div[contains(text(), '@')]");
    private final By logoutBtnBy = By.xpath("//div[text()='Logout']");
    private final By editBtnBy = By.xpath("//div[text()='Edit']");
    private final By nameInputBy = By.xpath("//input[@placeholder='Enter Name']");
    private final By emailInputBy = By.xpath("//input[@placeholder='Enter Email']");
    private final By updateBtnBy = By.xpath("//div[text()='Update']");
    private final By closeEditProfileModalBtnBy = By.xpath("(//*[local-name()='svg' and @data-iconsvg='new_cross_icon'])[1]");
    private final By editProfileModalBy = By.cssSelector("div[data-modal-component*='modal:Edit Profile Details']");
    public OperatorProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // Clicks the Operator profile icon
    public void openProfilePanel() {
        closeEditProfileModalIfPresent();
        driver.navigate().refresh();
        WebDriverWait reloadWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement operatorProfile = reloadWait.until(ExpectedConditions.elementToBeClickable(operatorIconBy));
        operatorProfile.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewProfileBy));
    }
    // Clicks the View Profile link
    public void openViewProfile() {
        WebElement viewProfile = wait.until(ExpectedConditions.elementToBeClickable(viewProfileBy));
        viewProfile.click();
    }
    // Returns the displayed operator name using a generalized XPath for any name
    public String getDisplayedOperatorName() {
        WebElement nameDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(nameDivBy));
        return nameDiv.getText();
    }
    // Returns the displayed operator mobile number using the provided XPath
    public String getDisplayedOperatorMobile() {
        WebElement mobileDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileDivBy));
        return mobileDiv.getText();
    }
    // Returns the displayed operator email using a generalized XPath for any email address
    public String getDisplayedOperatorEmail() {
        WebElement emailDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(emailDivBy));
        return emailDiv.getText();
    }
    // Returns true if the 'View Profile' link is visible after opening the profile panel
    public boolean isViewProfileLinkVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(viewProfileBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    // Returns true if the 'Logout' button is visible after opening the profile panel
    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtnBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    // Clicks the 'Logout' button in the profile panel
    public void clickLogoutButton() {
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutBtnBy));
        logoutBtn.click();
    }
    // Clicks the 'Edit' button on the profile details screen
    public void clickEditProfile() {
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editBtnBy));
        editBtn.click();
    }
    // Verifies that the Edit Profile page is open by checking for the editable name field
    public boolean isEditProfilePageOpen() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputBy));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void closeEditProfileModalIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement closeBtn = shortWait.until(ExpectedConditions.elementToBeClickable(closeEditProfileModalBtnBy));
            closeBtn.click();
            shortWait.until(ExpectedConditions.invisibilityOfElementLocated(editProfileModalBy));
        } catch (TimeoutException ignored) {
            // Modal was not present, or disappeared quickly
        }
    }
    public void enterName(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Name']")));
        System.out.println("Name field value before clearing: " + nameInput.getAttribute("value"));
        nameInput.clear();
        nameInput.sendKeys(Keys.chord(Keys.COMMAND, "a")); // Select all (for Mac)
        nameInput.sendKeys(Keys.DELETE); // Delete selected text
        System.out.println("Name field value after clearing: " + nameInput.getAttribute("value"));
        nameInput.sendKeys(name);
        System.out.println("Name field value after entering new name: " + nameInput.getAttribute("value"));
    }
    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputBy));
        emailInput.clear();
        emailInput.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        emailInput.sendKeys(Keys.DELETE);
        emailInput.sendKeys(email);
    }
    public void clickUpdateProfile() {
        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(updateBtnBy));
        updateBtn.click();
    }
    public boolean isErrorMessageVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            // Look for common error message patterns
            By errorMessageBy = By.xpath("//*[contains(text(), 'error') or contains(text(), 'Error') or contains(text(), 'invalid') or contains(text(), 'Invalid') or contains(text(), 'required') or contains(text(), 'Required')]");
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getErrorMessageText() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By errorMessageBy = By.xpath("//*[contains(text(), 'error') or contains(text(), 'Error') or contains(text(), 'invalid') or contains(text(), 'Invalid') or contains(text(), 'required') or contains(text(), 'Required')]");
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy)).getText();
        } catch (TimeoutException e) {
            return "No error message found";
        }
    }
} 