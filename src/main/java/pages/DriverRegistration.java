package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class DriverRegistration{

    private WebDriver driver;

private By addDriverBtn = By.xpath("//div[text()='Add Driver']");
private By singleDriverOption = By.xpath("//div[text()='Single Driver']");
private By mobileField = By.xpath("//input[contains(@placeholder,'Enter Mobile Number')]");
private By sendOtpBtn = By.xpath("//div[text()='Send OTP']");
// private By otpInputs = By.xpath("//div[contains(@class,'flex justify-center px-10')]");
private By submitBtn = By.xpath("//div[text()='Submit']");

public DriverRegistration(WebDriver driver){
    this.driver = driver;
}
public void driverRegistration(String mobile, String otp){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOfElementLocated(addDriverBtn));
    driver.findElement(addDriverBtn).click();

    // driver.findElement(addDriverBtn).click();
    driver.findElement(singleDriverOption).click();
    driver.findElement(mobileField).sendKeys(mobile);
    driver.findElement(sendOtpBtn).click();
    // new LoginPage(driver).otpInputs(otp);
    driver.findElement(submitBtn).click();

}

}