package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileUploads;
import java.io.IOException;
import java.time.Duration;

public class VehicleaddPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public VehicleaddPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By addVehicleBtn = By.xpath("//div[text()='Add Vehicle']");
    By enterRcNo = By.xpath("//input[@placeholder='Enter RC No.']");
    By rcUpload = By.xpath("//div[contains(text(), 'Registration Certificate')]/following::label//input[@type='file']");
    By uploadrcBtn = By.xpath("//div[text()='Upload RC']");
    By verifyRc = By.xpath("//div[text()='Verify RC']");
    By permitUpload = By.xpath("//div[contains(text(), 'Permit')]/following::label//input[@type='file']");
    By insuranceUpload = By.xpath("//div[contains(text(), 'Insurance')]/following::label//input[@type='file']");
    By fitnessUpload = By.xpath("//div[contains(text(), 'Fitness')]/following::label//input[@type='file']");
    By pucUpload = By.xpath("//div[contains(text(), 'PUC')]/following::label//input[@type='file']");
    By frontUpload = By.xpath("//div[text()='Front']/following::label//input[@type='file']");
    By backUpload = By.xpath("//div[text()='Back']/following::label//input[@type='file']");
    By rightUpload = By.xpath("//div[text()='Right']/following::label//input[@type='file']");
    By leftUpload = By.xpath("//div[text()='Left']/following::label//input[@type='file']");
    By frontIntUpload = By.xpath("//div[text()='Front Interior']/following::label//input[@type='file']");
    By backIntUpload = By.xpath("//div[text()='Back Interior']/following::label//input[@type='file']");
    By odoMtrUpload = By.xpath("//div[text()='Odometer']/following::label//input[@type='file']");
    By operatorBtn = By.xpath("//div[contains(text(), 'Hub')]/following::div[text()='Operation']");
    By udyogvHub = By.xpath("//div[@data-text='Udyog Vihar Operation Hub']");
    By addVehicleendBtn = By.xpath("//button[@aria-label='addVehicle']");

    public void addVehicle(String mobile, String rcNum, String rcImg, String permitImg, String insuranceimg,
                           String fitnessImg, String pucImg, String frontImg, String backImg, String rightImg,
                           String leftImg, String frontIntImg, String backIntImg, String odoImg)
            throws InterruptedException, IOException {

        Thread.sleep(5000);
        driver.findElement(addVehicleBtn).click();
        Thread.sleep(2000);
        driver.findElement(enterRcNo).sendKeys(rcNum);
        FileUploads.uploadHiddenFile(driver, rcUpload, FileUploads.downloadTemp(rcImg, "RC"));
        driver.findElement(uploadrcBtn).click();
        Thread.sleep(3000);
        driver.findElement(verifyRc).click();
        Thread.sleep(8000);

        FileUploads.uploadHiddenFile(driver, permitUpload, FileUploads.downloadTemp(permitImg, "PERMIT"));
        FileUploads.uploadHiddenFile(driver, insuranceUpload, FileUploads.downloadTemp(insuranceimg, "INSURANCE"));
        FileUploads.uploadHiddenFile(driver, fitnessUpload, FileUploads.downloadTemp(fitnessImg, "FITNESS"));
        FileUploads.uploadHiddenFile(driver, pucUpload, FileUploads.downloadTemp(pucImg, "PUC"));
        FileUploads.uploadHiddenFile(driver, frontUpload, FileUploads.downloadTemp(frontImg, "FRONT"));
        FileUploads.uploadHiddenFile(driver, backUpload, FileUploads.downloadTemp(backImg, "BACK"));
        FileUploads.uploadHiddenFile(driver, rightUpload, FileUploads.downloadTemp(rightImg, "RIGHT"));
        FileUploads.uploadHiddenFile(driver, leftUpload, FileUploads.downloadTemp(leftImg, "LEFT"));
        FileUploads.uploadHiddenFile(driver, frontIntUpload, FileUploads.downloadTemp(frontIntImg, "FRONT_INT"));
        FileUploads.uploadHiddenFile(driver, backIntUpload, FileUploads.downloadTemp(backIntImg, "BACK_INT"));
        FileUploads.uploadHiddenFile(driver, odoMtrUpload, FileUploads.downloadTemp(odoImg, "ODOMETER"));
        Thread.sleep(3000);
        driver.findElement(operatorBtn).click();
        driver.findElement(udyogvHub).click();
        Thread.sleep(3000);
        driver.findElement(addVehicleendBtn).click();
        Thread.sleep(6000);

    }
}
