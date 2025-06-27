package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class TrackDrivers {

    private WebDriver driver;

    public TrackDrivers(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUploadForMobile(String mobile) {
        String xpath = "//div[text()='" + mobile + "']/ancestor::tr//div[text()='Upload']";
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
            .click();
    }
    
}