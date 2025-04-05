package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptToCreateOrganization2 {

	public static void main(String[] args) {
		//Step 1:-launch broswer
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3:- Navigation to Organization Link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4:- Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5:- Create organization with mandatory field
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("Infosys" + random);
		
		//Step 6:- save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String createOrg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (createOrg.contains("Infosys" + random)) {
			System.out.println(createOrg + "---Passed");
		} else {
			System.out.println(createOrg + "---Failed");
		}
		
		// Step 7 :- Logout the Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8 :-Close the Browser
		driver.quit();
	}
}