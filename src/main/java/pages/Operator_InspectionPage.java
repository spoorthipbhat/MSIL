package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

public class Operator_InspectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public Operator_InspectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Helper method to click an element with retry on StaleElementReferenceException
    private void safeClick(By by) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    // Helper method to sendKeys with retry on StaleElementReferenceException
    private void safeSendKeys(By by, String text) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
                el.clear();
                el.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        el.clear();
        el.sendKeys(text);
    }

    // Actions for the inspection flow
    public void navigateToInspectionTab() {
        safeClick(By.xpath("//div[text()='Inspection']"));
    }

    public void selectPendingStatus() {
        By statusButton = By.xpath("//button[@aria-label='status:All']");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(statusButton));

        // Scroll into view
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        // Use Actions to move and click (simulates real user interaction)
        Actions actions = new Actions(driver);
        actions.moveToElement(button).pause(java.time.Duration.ofMillis(200)).click().perform();

        // Wait for dropdown to render
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Now try to find and click the 'Pending' option
        By pendingOption = By.xpath("//div[contains(text(),'Pending') and not(ancestor::div[contains(@style,'display: none')])]");
        try {
            WebElement pending = wait.until(ExpectedConditions.visibilityOfElementLocated(pendingOption));
            wait.until(ExpectedConditions.elementToBeClickable(pendingOption)).click();
        } catch (TimeoutException e) {
            System.err.println("Could not find 'Pending' status option after opening dropdown. Page source:\n" + driver.getPageSource());
            throw e;
        }
    }

    public void selectOnboardingInspectionType() {
        safeClick(By.xpath("//button[@aria-label='inspectionType:All']"));
        safeClick(By.xpath("//div[@data-dropdown-value='Onboarding Inspection']"));
    }

    public void selectDateRange() throws InterruptedException {
        safeClick(By.xpath("//button[@aria-label='[fromDate]➟[ToDate]']"));
        Thread.sleep(2000); // Wait for the date picker to load
        // Navigate to April, 2025 using year and month navigation
        while (true) {
            WebElement calendarDateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-calendar-date]")));
            String calendarDate = calendarDateElement.getText();
            if (calendarDate.equals("April, 2025")) {
                break;
            } else {
                if (calendarDate.endsWith(", 2024")) {
                    safeClick(By.xpath("//*[name()='svg' and @data-iconsvg='angle-double-right']"));
                } else if (calendarDate.endsWith(", 2026")) {
                    safeClick(By.xpath("//*[name()='svg' and @data-iconsvg='angle-double-left']"));
                } else {
                    safeClick(By.xpath("//*[name()='svg' and @data-iconsvg='chevron-right']"));
                }
                Thread.sleep(1000);
            }
        }
        // Select the start date (April 1, 2025)
        safeClick(By.xpath("//span[@data-calender-date='normal' and text()='01']"));
        Thread.sleep(1000);
        // Navigate to June, 2025
        while (true) {
            WebElement calendarDateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-calendar-date]")));
            String calendarDate = calendarDateElement.getText();
            if (calendarDate.equals("June, 2025")) {
                break;
            } else {
                safeClick(By.xpath("//*[name()='svg' and @data-iconsvg='chevron-right']"));
                Thread.sleep(1000);
            }
        }
        // Select the end date (June 25, 2025)
        safeClick(By.xpath("//td[.//span[@data-calender-date='selected' and normalize-space(text())='25']]"));
        Thread.sleep(1000);
    }

    public void enterDriverMobile(String mobile) {
        safeSendKeys(By.xpath("//input[@placeholder='Driver Mobile No.']"), mobile);
    }

    public void clickVehicleNumber(String vehicleNumber) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(normalize-space(text()),'" + vehicleNumber + "')]"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        el.click();
    }

    public void waitForInspectionForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Total cab driving experience of the driver']")));
    }

    public void answerInspectionQuestions() {
        String[] inputXPaths = {
            "//input[@name='Total cab driving experience of the driver']",
            "//input[@name='Total cab driving experience in Delhi NCR region']",
            "//input[@name='Which other platforms the driver is active on and which is his/her preferred platform?']",
            "//input[@name='Average rating and lifetime no. of trips on platforms currently using or used in the past']",
            "//input[@name='Experience in driving for reputed fleets (Everest, WTI, etc.)']",
            "//input[@name='Enter chassis no.']",
            "//input[@name='Enter engine no.']",
            "//input[@name='Enter odometer reading']",
            "//input[@name='Is there any branding on body or windows of any company or other competitors?']"
        };
        String[] inputAnswers = {
            "spoorthi", "p", "bhat", "spoo", "pinky", "ganapati", "deva", "TCSDM", "pprotect."
        };
        for (int i = 0; i < inputXPaths.length; i++) {
            try {
                safeSendKeys(By.xpath(inputXPaths[i]), inputAnswers[i]);
            } catch (Exception e) {
                System.err.println("Failed to fill input for xpath: " + inputXPaths[i] + " | " + e.getMessage());
            }
        }
        String[] yesButtonXPaths = {
            "//div[contains(text(), '10. Do the passenger seats have seat cover')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '11. Are the tail lights functional and not damaged?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '12. Are the head lights functional and not damaged?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '13. Does the external body of vehicle have any dents, damages, etc.?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '14. Are the interiors of the vehicle clean and not damaged?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '15. Is AC functional?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '16. Is the vehicle music system functional?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '17. Does the vehicle have a carrier installed?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '18. Is there is any branding on body or windows of any company or other competes?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '19. Is the vehicle fitted with AIS 140 certified vehicle tracking and monitoring with panic buttons?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '20. Is fire extinguisher present in the vehicle?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '21. Is child lock mechanism disabled?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '22. Is manual override for the central locking system is enabled?')]/following::div[text()='Yes'][1]",
            "//div[contains(text(), '23. Is there a coloured stripe on vehicle exterior as mandated by state?')]/following::div[text()='Yes'][1]"
        };
        for (String xpath : yesButtonXPaths) {
            try {
                safeClick(By.xpath(xpath));
            } catch (Exception e) {
                System.err.println("Failed to click Yes for xpath: " + xpath + " | " + e.getMessage());
            }
        }
    }

    public void rejectWithReason(String reason) {
        try { safeClick(By.xpath("//div[contains(@class,'cursor-pointer') and text()='Reject']")); } catch (Exception e) {}
        try { safeClick(By.xpath("//button[@aria-label='reject']")); } catch (Exception e) {}
        try { safeSendKeys(By.xpath("//input[@placeholder='Enter Reason']"), reason); } catch (Exception e) {}
        try { safeClick(By.xpath("//div[text()='Cancel']")); } catch (Exception e) {}
    }
} 