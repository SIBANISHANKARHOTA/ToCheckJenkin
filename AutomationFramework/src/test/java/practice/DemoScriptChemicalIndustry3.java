package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScriptChemicalIndustry3 {

	public static void main(String[] args) {
        //step 1:-Launch Broswer
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2 :- Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		// step 3:-Navigation to Organization Link
		driver.findElement(By.linkText("Organizations")).click();
		
		// step 4:-Click on create organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		// step 5:- Create organization with mandatory field
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("KIRAN"+random);
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select indusSelect = new Select(industryDropdown);
		indusSelect.selectByValue("Chemicals");
		
		// save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String createOrg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (createOrg.contains("KIRAN" + random)) {
			System.out.println(createOrg + "---Passed");
		} else {
			System.out.println(createOrg + "---Failed");
		}

		// Step 6 :- Logout the Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 7 :-Close the Browser
		driver.quit();
	}
}