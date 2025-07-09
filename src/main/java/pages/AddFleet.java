package pages;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ToastMessageHandler;

public class AddFleet {

    private WebDriver driver;
    // All Xpaths for Fleet Tab in Operator 
    By fleetsTab =           By.xpath("//div[text() = \'Fleets\']");
    By addFleetButton =      By.xpath("//div[@class=\'flex w-full justify-end gap-2\']//descendant::*[text()=\'Add Fleet\']");
    By enterNumber =         By.xpath("//div[text()='Fleet Mobile Number']/following-sibling::input[@placeholder='Enter Mobile No.']");
    By errorMessage =        By.xpath("//div[text()= 'Fleet already associated with operator']");
    By sendOtpButton =       By.xpath("//div[text()= 'Send OTP']");
    By otpFields =           By.xpath("//div[contains(@class, 'flex justify')]/div/input");
    By submitButton =        By.xpath("//div[contains(@class, 'bg-[#01044B]') and normalize-space(.)='Submit']");
    By searchBox =           By.xpath("//input[@placeholder='Search by Name / Full Mobile No.']");
    By numberToVerify =      By.xpath("//td[@data-table-location='Fleet List_tr1_td2']/div/div");
    By menuOption =          By.xpath("//div[@data-icon='more-verticle']");
    By uploadDocOption =     By.xpath("//div[@class='flex flex-col']/div/div[text()= 'Upload Document']");
    By viewFleet =           By.xpath("//div[@class='flex flex-col']/div/div[text()= 'View']");
    By deleteFleet =         By.xpath("//div[@class='cursor-pointer ' and text()='Delete']");
    By confirmDeleteButton = By.xpath("//div[text()='Delete' and contains(@class, 'cursor-pointer')]");
        
    public  WebDriverWait wait;
        public AddFleet(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Global wait
    }
    
    public void addFleetInOperator(String MobileNo, String otp) throws InterruptedException{
        ToastMessageHandler toastHandler = new ToastMessageHandler(driver);

        // Add new number in Operator as a Fleet
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fleetsTab)).click();     // Tap on Fleet Tab
        driver.findElement(addFleetButton).click();                                       
        driver.findElement(enterNumber).sendKeys(MobileNo);
        System.out.println("✅ Entered Mobile Number: " + MobileNo);

        wait.until(ExpectedConditions.elementToBeClickable(sendOtpButton)).click();
        Thread.sleep(1000);
        if (toastHandler.checkAndPrintToastError()) return;     // to check number is already associated or not

        // Wait for OTP fields to appear and to enter otp
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(otpFields, otp.length() - 1));
        List<WebElement> fields = driver.findElements(otpFields);
        System.out.println("✅ Found " + fields.size() + " OTP input fields");
        for (int i = 0; i < otp.length(); i++) {
            fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
        System.out.println("✅ Entered OTP: " + otp);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        System.out.println("✅ Fleet number " + MobileNo + " is added in operator ");
        Thread.sleep(3000);

        // To verify that number is added or not
        driver.findElement(searchBox).sendKeys(MobileNo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberToVerify));
        String displayedNumber = driver.findElement(numberToVerify).getText().trim();

        // to upload the document and delete the added fleet
        if (MobileNo.equals(displayedNumber)) 
        {
            driver.findElement(menuOption).click();
            //driver.findElement(uploadDocOption).click();
            driver.findElement(deleteFleet).click();;
            driver.findElement(confirmDeleteButton).click();;
            System.out.println("🗑️ Fleet with number " + MobileNo + ", is deleted successfully!");
            Thread.sleep(3000);
        }
        else {
            System.out.println("Mismatch: Expected " + MobileNo + " but found " + displayedNumber);
        }

    }
    
}