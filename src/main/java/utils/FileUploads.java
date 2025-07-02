package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class FileUploads {

    public static String downloadTemp(String fileId, String tag) throws IOException {
        String tmp = System.getProperty("java.io.tmpdir");
        String path = tmp + tag + "_" + System.currentTimeMillis() + ".jpeg";
        GoogleDrive.downloadFileFromDrive(fileId, path);
        return path;
    }

    public static void uploadHiddenFile(WebDriver driver, By locator, String filePath) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);" +
                "arguments[0].removeAttribute('disabled');" +
                "arguments[0].removeAttribute('hidden');",
                input);

        input.sendKeys(filePath);
        Thread.sleep(3000); // You can replace with smarter waits
    }
}
