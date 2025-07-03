package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Optional;
import org.testng.annotations.Optional;

public class BaseClass {

protected WebDriver driver;
@Parameters("browser")
@BeforeMethod
public void setUp(@Optional("chrome") String browser) {
public void setUp(@Optional("chrome") String browser) {
    switch (browser.toLowerCase()){
        case "chrome":
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
        case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
        case "edge":
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
        case "safari":
        driver = new SafariDriver();
        break;

        default:
        throw new IllegalArgumentException("Unsupported browser:" +browser);
    }


    driver.manage().window().maximize();
    driver.get("https://maruti.moving.tech/");

}
@AfterMethod
public void tearDown()
{
if (driver != null){
    driver.quit();
}
}
}