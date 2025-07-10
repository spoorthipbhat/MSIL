package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FileUploads;
import utils.ToastMessageHandler;

public class FleetRegistration {

    private WebDriver driver;
    private WebDriverWait wait;

    // Fleet tab locators
    By individualFleetButton      = By.xpath("//div[text() = 'Individual Fleet']");
    By businessFleetButton        = By.xpath("//div[text() = 'Business Fleet']");

    // Input Locators
    By inputAadhaarNumber         = By.xpath("//input[@placeholder='Enter Aadhaar Number']");
    By enterFleetName             = By.xpath("//input[@placeholder='Enter Fleet Name']");
    By enterFleetEmail            = By.xpath("//input[@placeholder='Enter Email ID']");
    By enterGstNumber             = By.xpath("//input[@placeholder='Enter GST Number']");
    By enterPanNumber             = By.xpath("//input[@placeholder='Enter PAN Number']");
    By enterOperatorCode          = By.xpath("//input[@placeholder='Enter Operator Code']");
    By selectCityDropdown         = By.xpath("//button[@data-value='city']");
    By enterBusinessLicenseNumber = By.xpath("//input[@placeholder='Enter License Number']");
    By verifyButton               = By.xpath("//div[@data-id='Enter Operator Code']//div[contains(@class, 'cursor-pointer')]");
    By complRegistrationButton    = By.xpath("//button[@aria-label='completeRegistration']");

    // Upload Locators
    By uploadAadhaarFrontFile        = By.xpath("//div[contains(text(),'Upload Front Aadhaar')]/ancestor::label//input[@type='file']");    
    By uploadAadhaarBackFile         = By.xpath("//div[contains(text(),'Upload Back Aadhaar')]/ancestor::label//input[@type='file']");
    By uploadPan_Business            = By.xpath("(//input[@type='file' and @accept='.jpg,.jpeg,.png'])[4]");
    By uploadPan_Individual          = By.xpath("(//input[@type='file' and @accept='.jpg,.jpeg,.png'])[8]");
    By uploadAadhaarFront_Individual = By.xpath("(//label[.//div[contains(text(),'Upload Front Aadhaar')]]//input[@type='file'])[2]");
    By uploadAadhaarBack_Individual  = By.xpath("(//label[.//div[contains(text(),'Upload Back Aadhaar')]]//input[@type='file'])[2]");
    By uploadGstFile                 = By.xpath("//div[@class='block']//descendant::input[8]");
    By uploadBusinessLicensefile     = By.xpath("//div[@class='block']//descendant::input[12]");

    // Locators for Operator dashboard to add Fleet
    By fleetsTab                  =By.xpath("//div[text() = 'Fleets']");
    By addFleetButton             =By.xpath("//div[@class='flex w-full justify-end gap-2']//descendant::*[text()='Add Fleet']");
    By enterNumber                =By.xpath("//div[text()='Fleet Mobile Number']/following-sibling::input[@placeholder='Enter Mobile No.']");
    By sendOtpButton              =By.xpath("//div[text()= 'Send OTP']");
    By otpFields                  =By.xpath("//div[contains(@class, 'flex justify')]/div/input");
    By submitButton               =By.xpath("//div[contains(@class, 'bg-[#01044B]') and normalize-space(.)='Submit']");
    By searchBox                  =By.xpath("//input[@placeholder='Search by Name / Full Mobile No.']");
    By numberToVerify             =By.xpath("//td[@data-table-location='Fleet List_tr1_td2']/div/div");
    By menuOption                 =By.xpath("//div[@data-icon='more-verticle']");
    By uploadDocOption            =By.xpath("//div[@class='flex flex-col']/div/div[text()= 'Upload Document']");
    By viewFleet                  =By.xpath("//div[@class='flex flex-col']/div/div[text()= 'View']");
    By deleteFleet                =By.xpath("//div[@class='cursor-pointer ' and text()='Delete']");
    By confirmDeleteButton        =By.xpath("//div[text()='Delete' and contains(@class, 'cursor-pointer')]");

    public FleetRegistration(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Utility: Send keys to only visible element
    private void sendKeysToVisible(By locator, String value) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement el : elements) {
            if (el.isDisplayed()) {
                el.clear();
                el.sendKeys(value);
                return;
            }
        }
        throw new RuntimeException("No visible element found for locator: " + locator.toString());
    }
    // Utility: Click only visible and interactable element using JS if needed
    private void clickVisible(By locator) {
    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    List<WebElement> elements = driver.findElements(locator);

    for (WebElement el : elements) {
        try {
            // Wait for element to be visible and clickable
            shortWait.until(ExpectedConditions.visibilityOf(el));
            shortWait.until(ExpectedConditions.elementToBeClickable(el));

            if (el.isDisplayed() && el.isEnabled()) {
                try {
                    el.click();
                } catch (Exception e) {
                    // Fallback to JavaScript click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                }
                return;
            }
        } catch (Exception ignored) {
        }
    }
    throw new RuntimeException("No visible and clickable element found for locator: " + locator.toString());
}


    // Method @1: To onboard Fleet as Bussiness Fleet

    public void addBusinessFleet(String fleetName, String aadhaarNumber, String aadhaarFrontImage, String aadhaarBackImage, String fleetEmail,
    String fleetCity, String businessGstNumber, String gstImage, String businessPanNumber, String PanImage, String businessLicenseNumber, String licenseImage) throws InterruptedException, IOException {
        Thread.sleep(1000);
        sendKeysToVisible(enterFleetName, fleetName);
        System.out.println("✅ Entered Fleet name: "+ fleetName);

        sendKeysToVisible(inputAadhaarNumber, aadhaarNumber);
        FileUploads.uploadHiddenFile(driver, uploadAadhaarFrontFile, FileUploads.downloadTemp(aadhaarFrontImage, "AADHAAR_FRONT"));
        FileUploads.uploadHiddenFile(driver, uploadAadhaarBackFile, FileUploads.downloadTemp(aadhaarBackImage, "AADHAAR_BACK"));
        System.out.println("✅ Aadhaar number and image is uploaded: "+ aadhaarNumber);

        sendKeysToVisible(enterFleetEmail, fleetEmail);
        System.out.println("✅ Entered Fleet email: "+ fleetEmail);
        
        clickVisible(selectCityDropdown);
        By cityOption = By.xpath("//div[text() = 'MSIL PARTNER:" + fleetCity + "']");
        clickVisible(cityOption);
        System.out.println("✅ Selected City: " + fleetCity);

        sendKeysToVisible(enterGstNumber, businessGstNumber);
        FileUploads.uploadHiddenFile(driver, uploadGstFile, FileUploads.downloadTemp(gstImage, "GST"));
        System.out.println("✅ GST number and image is uploaded: "+ businessGstNumber);

        sendKeysToVisible(enterPanNumber, businessPanNumber);
        FileUploads.uploadHiddenFile(driver, uploadPan_Business, FileUploads.downloadTemp(PanImage, "PAN"));
        System.out.println("✅ PAN number and image is uploaded: "+ businessPanNumber);

        sendKeysToVisible(enterBusinessLicenseNumber, businessLicenseNumber);
        FileUploads.uploadHiddenFile(driver, uploadBusinessLicensefile, FileUploads.downloadTemp(licenseImage, "License"));
        System.out.println("✅ Business License Number and image is uploaded: "+ businessLicenseNumber);

        Thread.sleep(3000);
    }


    // Method @2: To onboard Fleet as Individual Fleet

    public void addIndividualFleet(String fleetName, String aadhaarNumber, String aadhaarFrontImage, String aadhaarBackImage, String fleetEmail, 
        String fleetCity, String individualPanNumber, String individualPanImage) throws InterruptedException, IOException {
        Thread.sleep(1000);
        clickVisible(individualFleetButton);
        
        sendKeysToVisible(enterFleetName, fleetName);
        System.out.println("✅ Entered Fleet name: "+ fleetName);

        sendKeysToVisible(inputAadhaarNumber, aadhaarNumber);
        FileUploads.uploadHiddenFile(driver, uploadAadhaarFront_Individual, FileUploads.downloadTemp(aadhaarFrontImage, "AADHAAR_FRONT"));
        FileUploads.uploadHiddenFile(driver, uploadAadhaarBack_Individual, FileUploads.downloadTemp(aadhaarBackImage, "AADHAAR_BACK"));
        System.out.println("✅ Aadhaar number and image is uploaded: "+ aadhaarNumber);

        sendKeysToVisible(enterFleetEmail, fleetEmail);
        System.out.println("✅ Entered Fleet email: "+ fleetEmail);
            
        clickVisible(selectCityDropdown);
        By cityOption = By.xpath("//div[text() = 'MSIL PARTNER:" + fleetCity + "']");
        clickVisible(cityOption);
        Thread.sleep(2000);
        System.out.println("✅ Selected City: " + fleetCity);

        sendKeysToVisible(enterPanNumber, individualPanNumber);
        FileUploads.uploadHiddenFile(driver, uploadPan_Individual, FileUploads.downloadTemp(individualPanImage, "PAN"));
        System.out.println("✅ PAN number and image is uploaded: "+ individualPanNumber);

        Thread.sleep(3000);
    }

    // Method @3: Adding fleet owner in operator dashboard

    public void addFleetInOperator(String MobileNo, String otp) throws InterruptedException{
        Thread.sleep(1000);
        ToastMessageHandler toastHandler = new ToastMessageHandler(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(fleetsTab)).click();     
        driver.findElement(addFleetButton).click();                                       
        driver.findElement(enterNumber).sendKeys(MobileNo);
        System.out.println("✅ Entered Mobile Number: " + MobileNo);

        wait.until(ExpectedConditions.elementToBeClickable(sendOtpButton)).click();
        Thread.sleep(1000);
        if (toastHandler.checkAndPrintToastError()) return;     // to check number is already associated or not

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(otpFields, otp.length() - 1));
        List<WebElement> fields = driver.findElements(otpFields);
        System.out.println("✅ Found " + fields.size() + " OTP input fields");
        for (int i = 0; i < otp.length(); i++) {
            fields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
        System.out.println("✅ Entered OTP: " + otp);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        System.out.println("✅ Fleet number " + MobileNo + " is added in operator ");
        Thread.sleep(2000);

        driver.findElement(searchBox).sendKeys(MobileNo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberToVerify));
        String displayedNumber = driver.findElement(numberToVerify).getText().trim();

        if (MobileNo.equals(displayedNumber)) 
        {
            driver.findElement(menuOption).click();
            driver.findElement(uploadDocOption).click();
            //driver.findElement(deleteFleet).click();
            //driver.findElement(confirmDeleteButton).click();
            Thread.sleep(3000);
        }
        else {
            System.out.println("Mismatch: Expected " + MobileNo + " but found " + displayedNumber);
        }

    }

    // Method @4: To add Operator referral code while registering the Fleet Owner

    public void operatorCode(String operatorCode)throws InterruptedException, IOException {

        sendKeysToVisible(enterOperatorCode, operatorCode);
        System.out.println("✅ Entered Operator referral code: " + operatorCode);
        clickVisible(verifyButton);
        
        By resultTextLocator =    By.xpath("//div[contains(@class, 'text-green-500') and string-length(normalize-space()) > 0]");
        WebElement resultText =   wait.until(ExpectedConditions.visibilityOfElementLocated(resultTextLocator));
        System.out.println("✅ Operator referral Code belongs to: '" + resultText.getText()+" '");
        Thread.sleep(2000);

    }

    // Method @5: To tap on complete Registration Button

    public void completeRegistration()throws InterruptedException, IOException {
        clickVisible(complRegistrationButton);
    }


}
