package Operator_Dashboard;
  
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operator_Inspection {
	private WebDriver driver;    
	
	public Operator_Inspection(WebDriver driver) {  
		this.driver = driver;
	}
	
	public void run() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  try {
			  WebElement Operator_Inspection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Inspection']")));
			  Operator_Inspection.click();

               WebElement status = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='status:All']")));
               status.click();  
             
               WebElement  Calendar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='calendar']")));
               Calendar.click(); 
              WebElement  Select_Month = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='April, 2025']")));
              Select_Month.click(); 
              WebElement  Select_Date = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='April, 2025']")));
              Select_Month.click();
             
              WebElement  Inspection_Type = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='inspectionType:All']")));
             Inspection_Type.click();
  
             WebElement  Driver_Mobile_No = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Driver Mobile No.']")));
             Driver_Mobile_No.click();
             Driver_Mobile_No.sendKeys("9999999978");
             
             WebElement  Clear_Filters = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='clearFilters']")));
             Clear_Filters.click();                                                                                                             
             
             
		  }
                  

		  
		  
		  }
		  }
}


 

//div[text()='Inspection']
//button[@aria-label='status:All']
//div[text()='Status: Pending']
//div[text()='Status: Approved']
//div[text()='Status: Rejected']
//button[@aria-label='inspectionType:All']
//div[text()='Onboarding Inspection']
//div[text()='Regular Inspection']
//input[@placeholder='Driver Mobile No.']
//button[@aria-label='clearFilters']
//button[@aria-label='calendar']
//div[text()='April, 2025']
(//span[@data-calender-date='selected' and text()='01'])[1]
//div[text()='June, 2025']
(//span[@data-calender-date='selected' and text()='13'])[1]		
//p[text()='9999999978']
		//input[@name='Total cab driving experience of the driver']
		//input[@name='Total cab driving experience in Delhi NCR region']
		//input[@name='Which other platforms the driver is active on and which is his/her preferred platform?']
		//input[@name='Average rating and lifetime no. of trips on platforms currently using or used in the past']
		//input[@name='Experience in driving for reputed fleets (Everest, WTI, etc.)']
		//input[@name='Enter chassis no.']
		//input[@name='Enter engine no.'] 
		//input[@name='Enter odometer reading']     
		//input[@name='Is there any branding on body or windows of any company or other competitors?']
		//div[contains(text(), '10. Do the passenger seats have seat cover')]/following::div[text()='Yes'][1]
		//div[contains(text(), '10. Do the passenger seats have seat cover')]/following::div[text()='No'][1]
		//div[contains(text(), '11. Are the tail lights functional and not damaged?')]/following::div[text()='Yes'][1]
		//div[contains(text(), '11. Are the tail lights functional and not damaged?')]/following::div[text()='No'][1]
		//div[contains(text(), '12. Are the head lights functional and not damaged?')]/following::div[text()='Yes'][1]
		//div[contains(text(), '12. Are the head lights functional and not damaged?')]/following::div[text()='No'][1]
		//div[contains(text(), '13. Does the external body of vehicle have any dents, damages, etc.?')]/following::div[text()='Yes'][1]
		//div[contains(text(), '13. Does the external body of vehicle have any dents, damages, etc.?')]/following::div[text()='No'][1]
		//div[contains(text(), '14. Are the interiors of the vehicle clean and not damaged?')]/following::div[text()='Yes'][1]
		//div[contains(text(), '14. Are the interiors of the vehicle clean and not damaged?')]/following::div[text()='No'][1]
		//div[contains(text(), '15. Is AC functional?')]/following::div[text()='Yes'][1]
		//div[contains(text(), '15. Is AC functional?')]/following::div[text()='No'][1]
		
		
		
		
		
		
		                                                  
		
		
		
		
		
		
		
		