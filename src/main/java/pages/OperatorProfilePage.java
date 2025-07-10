package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
public class OperatorProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By operatorIcon = By.xpath("//div[text()='Operator']");
    private final By viewProfile = By.xpath("//div[text()='View Profile']");
    private final By editBtn = By.xpath("//div[text()='Edit']");
    private final By nameInput = By.xpath("//input[@placeholder='Enter Name']");
    private final By emailInput = By.xpath("//input[@placeholder='Enter Email']");
    private final By updateBtn = By.xpath("//div[text()='Update']");
    private final By closeModalBtn = By.xpath("(//*[local-name()='svg' and @data-iconsvg='new_cross_icon'])[1]");
    private final By editProfileModal = By.cssSelector("div[data-modal-component*='modal:Edit Profile Details']");
    public OperatorProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void goToEditProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(operatorIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewProfile)).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
    }
    public void enterNameAndEmail(String name, String email) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        System.out.println("Name before: '" + nameInput.getAttribute("value") + "'");
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", nameInput, name != null ? name : "");
        System.out.println("Name after: '" + nameInput.getAttribute("value") + "'");
        System.out.println("Email before: '" + emailInput.getAttribute("value") + "'");
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", emailInput, email != null ? email : "");
        System.out.println("Email after: '" + emailInput.getAttribute("value") + "'");
        wait.until(ExpectedConditions.elementToBeClickable(updateBtn)).click();
        System.out.println("tapped on update button");
}  }