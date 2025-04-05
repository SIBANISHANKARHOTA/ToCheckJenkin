package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript1 {

	public static void main(String[] args) {
		// Step 1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step 3 :-Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4 :- Click on create contact link up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5:Create Contact With Mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Hota");

		// Step 6 :- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("Hota")) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---Failed");
		}

		// Step 7 :- Logout the Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8 :-Close the Browser
		driver.quit();
	}
}