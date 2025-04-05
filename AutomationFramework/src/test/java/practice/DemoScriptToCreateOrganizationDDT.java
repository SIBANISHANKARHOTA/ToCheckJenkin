package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptToCreateOrganizationDDT {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//To read data from propertyfile
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		//TO READ DATA FROM EXCEL FILE
		FileInputStream efis=new FileInputStream(".\\src\\test\\resources\\testData8am.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		String ORGNAME=wb.getSheet("Organization").getRow(1).getCell(2).toString();
		
		//step1n: launch browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		} else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		// Step 2 :- Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 3 :-Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4 :- Click on create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5:Create Organization With Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		// Step 6 :- Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String Orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (Orgname.contains(ORGNAME)) {
		System.out.println(Orgname + "---Passed");
		} else {
		System.out.println(Orgname + "---Failed");
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