package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoscriptWithDDTandWDU {
	public static void main(String[] args) throws IOException {
PropertyFileUtility putil = new PropertyFileUtility();
ExcelFileUtility eutil = new ExcelFileUtility();
WebDriverUtility wutil = new WebDriverUtility();

// To read data from property file
String BROWSER=putil.toReadDataFromPropertyFile("browser");
String URL=putil.toReadDataFromPropertyFile("url");
String USERNAME=putil.toReadDataFromPropertyFile("username");
String PASSWORD=putil.toReadDataFromPropertyFile("password");

// to Read data from excel file
String LASTNAME=eutil.toReadDataFromExcel("Contacts", 1, 2);

// Step 1:-Launch Browser
WebDriver driver = null;
if (BROWSER.contains("chrome")) {
	driver = new ChromeDriver();
} else if (BROWSER.contains("edge")) {
	driver = new EdgeDriver();
} else if (BROWSER.contains("firefox")) {
	driver = new FirefoxDriver();
}
wutil.toMaximize(driver);//WEBDRIVER UTILITY
wutil.toWaitForElements(driver);// WEBDRIVER UTILITY

// Step 2 :- Login to application with valid credentials
driver.get(URL);
driver.findElement(By.name("user_name")).sendKeys(USERNAME);
driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
driver.findElement(By.id("submitButton")).click();

// Step 3 :-Navigate to contact link
driver.findElement(By.linkText("Contacts")).click();

// Step 4 :- Click on create contact link up image
driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

//Step 5: Create Contacts with manadatory fields
driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

// Step 6 :- Save and Verify
driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
if (lastname.contains(LASTNAME)) {
	System.out.println(lastname + "---Passed");
} else {
	System.out.println(lastname + "---Failed");
}

// Step 7 :- Logout the Application
WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
wutil.toMouseHover(driver, logout); //WEBDRIVER UTILITY
driver.findElement(By.linkText("Sign Out")).click();

// Step 8 :-Close the Browser
driver.quit();
	}
}