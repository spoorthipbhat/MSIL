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

    // Thread-safe WebDriver for parallel execution
    private static ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();
    protected WebDriver driver;

    // To avoid re-initializing driver across classes
    private static boolean isDriverInitialized = false;

    // 🔹 Env variable accessible from tests
    protected static String env;

    @Parameters({"browser", "env", "incognito"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser,
                      @Optional("master") String environment,
                      @Optional("false") String incognito) {

        env = environment;

        if (!isDriverInitialized) {
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
                    localDriver = new org.openqa.selenium.safari.SafariDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            localDriver.manage().window().maximize();
            localDriver.get(url);

            TL_DRIVER.set(localDriver);
            driver = TL_DRIVER.get();
            isDriverInitialized = true;

        } else {
            driver = TL_DRIVER.get(); // reuse if already present
        }
    }

    public static WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        // Leave blank if not quitting after each class
        // Or handle conditional quitting
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUpDriver() {
        if (TL_DRIVER.get() != null) {
            TL_DRIVER.get().quit();
            TL_DRIVER.remove();
            isDriverInitialized = false;
        }
    }
}
