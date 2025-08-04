package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import utils.OtpFetcher;


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


public void driverRegistration(String mobile, String env) {
    System.out.println("➡ Starting driver registration for: " + mobile);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(addDriverBtn)).click();
    wait.until(ExpectedConditions.elementToBeClickable(singleDriverOption)).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(mobileField)).sendKeys(mobile);
>>>>>>> Stashed changes
    driver.findElement(sendOtpBtn).click();

    // 🔄 Wait for OTP to be generated and fetched
    String otp = null;
    int attempts = 5;
    while (attempts-- > 0) {
        otp = env.equalsIgnoreCase("master")
            ? "7891"
            : OtpFetcher.getOtpBasedOnEnv(env, mobile);
        if (otp != null && !otp.isEmpty()) break;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    if (otp == null || otp.isEmpty()) {
        throw new RuntimeException("❌ Driver OTP not fetched for: " + mobile);
    }

    wait.until(driver -> driver.findElements(otpInputs).size() > 0);
    List<WebElement> fields = driver.findElements(otpInputs);

    for (int i = 0; i < otp.length() && i < fields.size(); i++) {
        fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
    }

    driver.findElement(submitBtn).click();
}



}