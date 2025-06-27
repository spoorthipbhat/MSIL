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



private By upload = By.xpath("//div[text()='Upload']");


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

// public void enterMobileno(String mobileNo){

//     driver.findElement(oparatormobileNumber).sendKeys(mobileNo);

// }

// public void checkcheckbox(){

//     driver.findElement(checkBox).click();

// }

// public void clickOnContinue(){

//     driver.findElement(continueButton).click();
// }

// public void enterOtp(String otp){

//     WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(10));
//     Wait.until(ExpectedConditions.visibilityOfElementLocated(otpFields));

//    List <WebElement> fields= driver.findElements(otpFields); 
// for( int i=0; i<otp.length(); i++){
//     fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
// }
// }

// public void taponAddDriver (){
//     WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(10));
//     Wait.until(ExpectedConditions.visibilityOfElementLocated(addDriver));
//     driver.findElement(addDriver).click();
// }

// public void tapOnSingleDriver (){
//     driver.findElement(singleDriver).click();

// }

// public void tapdriverMobileno (String mobilenod){
    
//     driver.findElement(driverMobileno).sendKeys(mobilenod);

// }
// public void tapsendOTPbutton (){

// driver.findElement(sendOTPbutton).click();

// }
// public void enterdriverOTP(String driverotp){
//     WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(10));
//     Wait.until(ExpectedConditions.visibilityOfElementLocated(driverOTP));

//    List <WebElement> fields= driver.findElements(driverOTP); 
// for( int i=0; i<driverotp.length(); i++){
//     fields.get(i).sendKeys(String.valueOf(driverotp.charAt(i)));
// } 


// }
// public void tapsubmitButton(){
//     driver.findElement(submitButton).click();

// }
// public void tapupload(){
//     driver.findElement(upload).click();
// }
}
