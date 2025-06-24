package Operator_Dashboard;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operator_login {
    private WebDriver driver;

    public Operator_login(WebDriver driver) {
        this.driver = driver;
    }

    public void run() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement mobileField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Enter mobile no.']")));
            mobileField.sendKeys("7811111112");

            WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@type='checkbox']")));
            checkBox.click();

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='continue']")));
            continueBtn.click();

            // Enter the OTP
            String otpToEnter = "7891";

            // Updated XPath using class parts
            String otpBoxesXpath = "//input[contains(@class, 'text-center') and contains(@class, 'h-10') and contains(@class, 'rounded-lg')]";

            List<WebElement> otpFields = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(otpBoxesXpath)));

            for (int i = 0; i < otpToEnter.length(); i++) {
                if (i < otpFields.size()) {
                    WebElement otpField = wait.until(ExpectedConditions.elementToBeClickable(otpFields.get(i)));
                    otpField.sendKeys(String.valueOf(otpToEnter.charAt(i)));
                    System.out.println("Entered '" + otpToEnter.charAt(i) + "' into OTP box " + (i + 1));
                } else {
                    System.err.println("Not enough OTP boxes found");
                    break;
                }
            }

            WebElement continueBtn2 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='continue']")));
            continueBtn2.click();

            System.out.println("Login successful");

        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }
}


