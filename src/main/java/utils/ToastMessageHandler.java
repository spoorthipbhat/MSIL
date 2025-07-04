package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;

public class ToastMessageHandler extends BaseClass {

    private WebDriver driver;

    public ToastMessageHandler(WebDriver driver) {
        this.driver = driver;
    }

    // Check for known error toasts
    public boolean checkAndPrintToastError() {
        String[] knownToasts = {
            "Hits limit reached",
            "Fleet already associated with operator",
            "Invalid OTP"
        };

        for (String msg : knownToasts) {
            // By default: don't print each message being checked
            By toastLocator = By.xpath("//*[contains(text(),'" + msg + "')]");

            if (isToastVisible(toastLocator)) {
                String toastText = driver.findElement(toastLocator).getText();
                System.out.println("❌ Error: " + toastText);
                return true;
            }

            if (driver.getPageSource().contains(msg)) {
                System.out.println("⚠️ Toast text found in page source: " + msg);
                return true;
            }
        }

        return false; // Don't print success if toast isn't found
    }

    private boolean isToastVisible(By locator) {
        try {
            for (int i = 0; i < 10; i++) {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    return true;
                }
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Exception during toast polling: " + e.getMessage());
        }
        return false;
    }
}