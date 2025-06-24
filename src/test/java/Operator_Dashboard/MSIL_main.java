package Operator_Dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MSIL_main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://maruti.moving.tech/training");
        driver.manage().window().maximize();

        Operator_login login = new Operator_login(driver);
        login.run();

        Operator_Profile Profile = new Operator_Profile(driver);
        Profile.run();  
        
        // driver.quit(); // optional 
        
    }
} 
 