package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

import utils.ConfigReader;

public class BaseClass {

    private static ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();
    protected WebDriver driver;

    @Parameters({"browser", "env", "incognito"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser,
                      @Optional("master") String env,
                      @Optional("false") String incognito) {

        ConfigReader config = new ConfigReader();
        config.loadProperties(env);
        String url = config.get("url");

        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (incognito.equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--incognito");
                }
                localDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (incognito.equalsIgnoreCase("true")) {
                    firefoxOptions.addArguments("-private");
                }
                localDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (incognito.equalsIgnoreCase("true")) {
                    edgeOptions.addArguments("-inprivate");
                }
                localDriver = new EdgeDriver(edgeOptions);
                break;

            case "safari":
                // Safari doesn't support incognito through automation
                localDriver = new org.openqa.selenium.safari.SafariDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        localDriver.manage().window().maximize();
        localDriver.get(url);

        TL_DRIVER.set(localDriver);
        driver = TL_DRIVER.get();
    }

    public static WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    @AfterMethod
    public void tearDown() {
        if (TL_DRIVER.get() != null) {
            TL_DRIVER.get().quit();
            TL_DRIVER.remove();
        }
    }
}
