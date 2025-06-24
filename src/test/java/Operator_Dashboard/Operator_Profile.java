package Operator_Dashboard;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operator_Profile {
private WebDriver driver;

public Operator_Profile(WebDriver driver) {
	this.driver = driver;
}

 public void run() {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 
	 try {
		  WebElement Operator_Profile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Operator']")));
		  Operator_Profile.click();
		  
		   WebElement View_Profile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='View Profile']")));
		   View_Profile.click();
		   
		   WebElement  Edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Edit']")));
		   Edit.click();
		   
		 

		// Clear and enter name
		WebElement Enter_Name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Name']")));
		Enter_Name.click();
		Enter_Name.sendKeys(Keys.chord(Keys.COMMAND, "a"));  // select all (Cmd+A on Mac also works)
		
		Enter_Name.sendKeys(Keys.BACK_SPACE);                // delete selected text
		Enter_Name.sendKeys("Spoorthi P  Bhat");
  
		// Clear and enter email
		WebElement Enter_Email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Email']")));
		Enter_Email.click();
		Enter_Email.sendKeys(Keys.chord(Keys.COMMAND, "a"));
		Enter_Email.sendKeys(Keys.BACK_SPACE);
		Enter_Email.sendKeys("spoorthipbhat13@gmail.cgmyu");

		   
		   WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Update']")));
		   Update.click();
		   
		   System.out.println(" Operator updated successful");
	 } catch (Exception e) {
         System.err.println("Error during login: " + e.getMessage());   
		   
		    
	 }
	 }
 } 
 /*
Operator_Profile = //div[text()='Operator']
View_Profile = //div[text()='View Profile']
Edit= //div[text()='Edit']
 Enter Name = //input[@placeholder='Enter Name'] , sendkeys : 
		 Enter Email =	//input[@placeholder='Enter Email']
  
			 update =  	//div[text()='Update']  
			  */