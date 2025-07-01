package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class TrackDrivers {

    private WebDriver driver;

    private By refreshBtn = By.xpath("//div[contains(text(),'Refresh')]");

    public TrackDrivers(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUploadForMobile(String mobileNo) throws InterruptedException {
        Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            try {
                driver.findElement(refreshBtn).click(); 
                System.out.println("Refresh button is clicked");
                return true;

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                return false;
            }
        });
        Thread.sleep(2000);

        String xpath = "//div[text()='"+mobileNo+"']/ancestor::tr//div[text()='Upload']";
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
            .click();
            Thread.sleep(2000);

    }
    
}