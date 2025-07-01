package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage{

    private WebDriver driver;

private By oparatormobileNumber = By.xpath("//input[@placeholder='Enter mobile no.']");
private By checkBox = By.xpath("//input[@type='checkbox']");
private By continueButton = By.xpath("//div[@data-button-text='Continue']");
private By otpFields = By.xpath("//div[contains(@class, 'text-otp')]/div/input");
private By getUploadBtnByMobile(String mobile) {
    return By.xpath("//div[text()='" + mobile + "']/parent::div/following-sibling::div//div[text()='Upload']");
}

public LoginPage(WebDriver driver){
this.driver=driver;
}

public void loginAsOperator(String MobileNo, String otp){
    driver.findElement(oparatormobileNumber).sendKeys(MobileNo);
    driver.findElement(checkBox).click();
    driver.findElement(continueButton).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(otpFields));
    List<WebElement> fields = driver.findElements(otpFields);
    for(int i = 0; i< otp.length(); i++){
        fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));

    }
    driver.findElement(continueButton).click();

}
}
