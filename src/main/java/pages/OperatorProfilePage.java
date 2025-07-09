package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
public class OperatorProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By operatorIconBy = By.xpath("//div[text()='Operator']");
    private final By viewProfileBy = By.xpath("//div[text()='View Profile']");
    private final By editBtnBy = By.xpath("//div[text()='Edit']");
    private final By nameInputBy = By.xpath("//input[@placeholder='Enter Name']");
    private final By emailInputBy = By.xpath("//input[@placeholder='Enter Email']");
    private final By updateBtnBy = By.xpath("//div[text()='Update']");
    private final By closeModalBtnBy = By.xpath("(//*[local-name()='svg' and @data-iconsvg='new_cross_icon'])[1]");
    private final By editProfileModalBy = By.cssSelector("div[data-modal-component*='modal:Edit Profile Details']");
    public OperatorProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void goToEditProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(operatorIconBy)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewProfileBy)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtnBy)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputBy));
    }
    public void enterNameAndEmail(String name, String email) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputBy));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputBy));
        System.out.println("Name before: '" + nameInput.getAttribute("value") + "'");
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", nameInput, name != null ? name : "");
        System.out.println("Name after: '" + nameInput.getAttribute("value") + "'");
        System.out.println("Email before: '" + emailInput.getAttribute("value") + "'");
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", emailInput, email != null ? email : "");
        System.out.println("Email after: '" + emailInput.getAttribute("value") + "'");
        wait.until(ExpectedConditions.elementToBeClickable(updateBtnBy)).click();
        System.out.println("tapped on update button");
}  }