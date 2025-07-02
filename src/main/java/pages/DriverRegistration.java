package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;



public class DriverRegistration{

    private WebDriver driver;

private By addDriverBtn = By.xpath("//div[text()='Add Driver']");
private By singleDriverOption = By.xpath("//div[text()='Single Driver']");
private By mobileField = By.xpath("//input[contains(@placeholder,'Enter Mobile Number')]");
private By sendOtpBtn = By.xpath("//div[text()='Send OTP']");
private By otpInputs = By.cssSelector("div.flex.items-center.h-20.w-20 input");
private By submitBtn = By.xpath("//div[text()='Submit']");
public DriverRegistration(WebDriver driver){
    this.driver = driver;
}
public void driverRegistration(String mobile, String otp){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOfElementLocated(addDriverBtn));
    driver.findElement(addDriverBtn).click();
    driver.findElement(singleDriverOption).click();
    driver.findElement(mobileField).sendKeys(mobile);
    driver.findElement(sendOtpBtn).click();
    wait.until(driver ->
    driver.findElements(otpInputs).size() > 0);
    List<WebElement> fields = driver.findElements(otpInputs);
    for (int i = 0; i < otp.length(); i++) {
    fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
}

  driver.findElement(submitBtn).click();

}

}