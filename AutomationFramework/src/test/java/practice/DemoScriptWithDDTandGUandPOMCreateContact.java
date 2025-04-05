package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepository.ContactsInfoPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactsPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGUandPOMCreateContact
{
	public static void main(String[] args) throws IOException
	{
		PropertyFileUtility putil= new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//to read data from property file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//to read data from excel file 
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
		
		//Step1:
		WebDriver driver = null;
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.toMaximize(driver);
		wutil.toWaitForElements(driver);
		
		//step2: login to application with valid credentials
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		//STEP3: NAVIGATE TO CONTACTS LINK
		HomePage hp=new HomePage(driver);
		hp.getContacts().click();
		
		//STEP 4: click on create contact loop up image
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContactIcon().click();
		
		//step5: create contact with manadatory fields
		CreateContactsPage ccp= new CreateContactsPage(driver);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		
		//step6: save and verify
		ContactsInfoPage cip= new ContactsInfoPage(driver);
		String lastname = cip.getContactsHeader().getText();
		if (lastname.contains(LASTNAME))
		{
			System.out.println(lastname+"....passed");
		}
		else
		{
			System.out.println(lastname+"....failed");	
		}

		//STEP 7: logout of application
		wutil.toMouseHover(driver,hp.getAdministrator());
		hp.getSignoutLink();
		
		//step8: close the browser
		driver.quit();		
	}
}