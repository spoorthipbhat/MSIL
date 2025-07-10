package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.nio.file.Paths;

public class AddBulkdriver {

    private WebDriver driver;

    private By addDriverBtn = By.xpath("//div[text()='Add Driver']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By submitBtn = By.xpath("//div[@data-button-text='Submit']");

    public AddBulkdriver(WebDriver driver) {
        this.driver = driver;
    }

    public void addingbulkdrivers(String filePath) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addDriverBtn)).click();
        WebElement fileUploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        fileUploadElement.sendKeys(filePath); 
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

        Thread.sleep(5000); 
    }
}
