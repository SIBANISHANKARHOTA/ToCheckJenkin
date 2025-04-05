package practice;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptOrgChildOrg5
{
	public static void main(String[] args) {
		//Launch The Browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				
		//Login to the Application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
				
		//Navigate to contacts links
		driver.findElement(By.linkText("Contacts")).click();
				
		//Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Create contacts with mandatory fields
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Debajit");
				
		// select the organization from organization look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		String singleWindows = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for(String id:allWindows)
		{
			driver.switchTo().window(id);
			}
		driver.findElement(By.linkText("Infosys384")).click();
		driver.switchTo().window(singleWindows);
				
	    //save and verify
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String lastName=driver.findElement(By.xpath("(//span[@class='dvHeaderText'])[1]")).getText();
		if(lastName.contains("Debajit")) {
			System.out.println(lastName+ "........Test passed");
			}else {
				System.out.println(lastName+ "........Test Failed");
				}
				
		//Logout from Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.xpath("//a[normalize-space()='Sign Out']")).click();
				
		//Close the Browser
		driver.quit();
		}
	}