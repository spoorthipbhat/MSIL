package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import utils.FileUploads;
import org.apache.commons.io.FileUtils;
import base.BaseClass;
import java.io.IOException;

public class VehicleInspection extends BaseClass {
    private WebDriverWait wait;

    private final By inspectionTab = By.xpath("//div[text()='Inspection']");
    private final By statusBtn = By.xpath("//button[@aria-label='status:All']");
    private final By pendingStatus = By.xpath("//div[text()='Status: Pending']");
    private final By calendarBtn = By.xpath("//button[@aria-label='[fromDate]➟[ToDate]']");
    private final By calendarMonth = By.xpath("//div[@data-calendar-date]");
    private final By approveBtn = By.xpath("(//div[text()='Approve'])[1]");
    private final By approvePopupBtn = By.xpath("(//div[text()='Approve'])[2]");
    private final By uploadInput = By.xpath("//input[@type='file']");

    public VehicleInspection(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToInspectionTab() {
        wait.until(ExpectedConditions.elementToBeClickable(inspectionTab)).click();
        try {
            Thread.sleep(3000); // wait for the status button to appear
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        wait.until(ExpectedConditions.elementToBeClickable(statusBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(pendingStatus)).click();
    }

    public void selectDate(Map<String, String> data) throws InterruptedException {
        String startDay = data.getOrDefault("StartDay", "").trim();
        String endDay = data.getOrDefault("EndDay", "").trim();
        String startMonth = data.getOrDefault("StartMonth", "").trim();
        String endMonth = data.getOrDefault("EndMonth", "").trim();
        String year = data.getOrDefault("Year", "").trim();

        if (startDay.isEmpty() || endDay.isEmpty() || startMonth.isEmpty() || endMonth.isEmpty() || year.isEmpty()) {
            throw new IllegalArgumentException("Invalid date values: " + data);
        }

        List<String> months = List.of("January", "February", "March", "April", "May", "June",
                                      "July", "August", "September", "October", "November", "December");

        // Open calendar
        wait.until(ExpectedConditions.elementToBeClickable(calendarBtn)).click();
        Thread.sleep(500);

        // Navigate to Start Month & Year
        String startTarget = startMonth + ", " + year;
        while (true) {
            String currentText = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarMonth)).getText().trim();
            if (currentText.equals(startTarget)) break;

            String currentMonth = currentText.split(",")[0].trim();
            boolean back = months.indexOf(currentMonth) > months.indexOf(startMonth);
            String navIcon = back ? "chevron-left" : "chevron-right";
            driver.findElement(By.xpath("//*[name()='svg' and @data-iconsvg='" + navIcon + "']")).click();
            Thread.sleep(400);
        }

        // Select Start Day
        String formattedStartDay = startDay.length() == 1 ? "0" + startDay : startDay;
        String startDayXpath = "//span[normalize-space(text())='" + formattedStartDay + "']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(startDayXpath))).click();
        Thread.sleep(300);

        // Navigate to End Month (only if different)
        if (!startMonth.equalsIgnoreCase(endMonth)) {
            String endTarget = endMonth + ", " + year;
            while (true) {
                String currentText = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarMonth)).getText().trim();
                if (currentText.equals(endTarget)) break;

                String currentMonth = currentText.split(",")[0].trim();
                boolean back = months.indexOf(currentMonth) > months.indexOf(endMonth);
                String navIcon = back ? "chevron-left" : "chevron-right";
                driver.findElement(By.xpath("//*[name()='svg' and @data-iconsvg='" + navIcon + "']")).click();
                Thread.sleep(400);
            }
        }

        // Select End Day
        String formattedEndDay = endDay.length() == 1 ? "0" + endDay : endDay;
        String endDayXpath = "//span[normalize-space(text())='" + formattedEndDay + "']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(endDayXpath))).click();
        Thread.sleep(300);
    }

    public void selectVehicleByNumber(String vehicleNumber) {
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//tr[.//td[1]//div[contains(@class,'font-medium') and normalize-space(text())='" + vehicleNumber + "']]")
        ));

        if (rows.size() == 0) {
            throw new NoSuchElementException("No matching rows found for vehicle: " + vehicleNumber);
        }

        WebElement targetRow = rows.size() == 1 ? rows.get(0) : rows.get(rows.size() - 1);
        wait.until(ExpectedConditions.elementToBeClickable(targetRow)).click();
    }

    public void fillVerificationList(Map<String, String> data) {
        for (int i = 1; i <= 9; i++) {
            String value = data.get("key" + i);
            if (value != null && !value.isEmpty()) {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("key" + i)));
                input.clear();
                input.sendKeys(value);
            }
        }

        for (int i = 10; i <= 23; i++) {
            String ans = data.get(String.valueOf(i));
            if (ans != null && (ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("No"))) {
                By answer = By.xpath("//div[contains(text(),'" + i + ".')]/following::div[text()='" + ans + "'][1]");
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(answer)).click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(answer)));
                }
            }
        }
    }

  public void uploadVideoFromDrive(String fileId) throws IOException, InterruptedException {
    String filePath = FileUploads.downloadTemp(fileId, "video");
    FileUploads.uploadHiddenFile(driver, uploadInput, filePath);
}

    public void approve() {
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(approvePopupBtn)).click();
    }
}
