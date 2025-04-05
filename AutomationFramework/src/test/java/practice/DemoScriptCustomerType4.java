package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScriptCustomerType4 {

	public static void main(String[] args) {
		// Step 1: launch browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2 : Login to Application with Valid Credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step 3 : Navigate to Organization Link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4 : Click on create Organization look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 5 : Create Organization with mandatory Fields
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("Debajit"+random);

		// Step 6 : Select Energy in the industry Dropdown
		WebElement dropValue = driver.findElement(By.xpath("//select[@name='industry']"));
		Select dropact = new Select(dropValue);
		dropact.selectByValue("Energy");

		// Step 6 : Select Customer in the Type Dropdown
		WebElement dropTypeValue = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select dropType = new Select(dropTypeValue);
		dropType.selectByValue("Customer");

		// Step 7 : Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgname.contains("Debajit"+random)) {
			System.out.println(orgname + "---- testcase Pass");
		} else {
			System.out.println(orgname + "---- testcase Fail");
		}

		// Step 8 : Logout of Application
		WebElement logoutElm = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutElm).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 9 : Quit
		driver.quit();
	}
}