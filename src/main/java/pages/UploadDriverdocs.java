package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GoogleDrive;
import java.io.File; 
import java.io.IOException;                         
import java.time.Duration;

public class UploadDriverdocs {

    private WebDriver driver;
    private WebDriverWait wait;

    private By dlNumber  = By.xpath("//input[@name='dlNumber']");
    private By enterDob  = By.xpath("//input[@placeholder='DD-MM-YYYY']");
    private By aadhaarNumber = By.xpath("//input[@placeholder='Enter Aadhaar Number']");
    private final By dlUploadIn = By.xpath("//div[contains(text(),'Upload DL Document')]/ancestor::label//input[@type='file']");
    private final By aadFrontIn = By.xpath("//div[contains(text(),'Upload Front Aadhaar')]/ancestor::label//input[@type='file']");
    private final By aadBackIn  = By.xpath("//div[contains(text(),'Upload Back Aadhaar')]/ancestor::label//input[@type='file']");
    By panNumberInput = By.xpath("//input[@placeholder='Enter PAN Number']");
    By panUploadInput = By.xpath("//div[contains(text(),'Upload PAN Document')]/ancestor::label//input[@type='file']");
    private By subMii = By.xpath("//div[contains(text(),'Submit')]");


    public UploadDriverdocs(WebDriver driver){
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void AddalldriverDocsandinfo(String dlNum, String dob, String aadhaarNum, String panNum, String panDoc,
                                        String aadFrontId, String aadBackId, String dlFront)
                                        throws InterruptedException, IOException {
        driver.findElement(dlNumber).sendKeys(dlNum);
        Thread.sleep(2000);

        driver.findElement(enterDob).sendKeys(dob);
        Thread.sleep(2000);

        driver.findElement(aadhaarNumber).sendKeys(aadhaarNum);
        Thread.sleep(1000);

        driver.findElement(panNumberInput).sendKeys(panNum);

        String dlPath       = downloadTemp(dlFront, "DL");
        String aadFrontPath = downloadTemp(aadFrontId, "AAD_FRONT");
        String aadBackPath  = downloadTemp(aadBackId, "AAD_BACK");
        String panPath = downloadTemp(panDoc, "PAN");
        uploadHiddenFile(dlUploadIn, dlPath);
        uploadHiddenFile(aadFrontIn, aadFrontPath);
        uploadHiddenFile(aadBackIn, aadBackPath);
        uploadHiddenFile(panUploadInput, panPath);

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(subMii));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();
        Thread.sleep(5000);
    }

    private String downloadTemp(String fileId, String tag) throws IOException {
        String tmp   = System.getProperty("java.io.tmpdir");
        String path  = tmp + tag + "_" + System.currentTimeMillis() + ".jpeg";
        GoogleDrive.downloadFileFromDrive(fileId, path);
        return path;
    }

    private void uploadHiddenFile(By locator, String filePath) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);" +
            "arguments[0].removeAttribute('disabled');" +
            "arguments[0].removeAttribute('hidden');",
            input);

        input.sendKeys(filePath);
        Thread.sleep(10000);  // optional: replace with explicit wait

    }
}
