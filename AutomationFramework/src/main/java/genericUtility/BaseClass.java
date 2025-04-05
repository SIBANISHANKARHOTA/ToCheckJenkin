package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class BaseClass
{
	PropertyFileUtility putil=new PropertyFileUtility();
	ExcelFileUtility eutil=new ExcelFileUtility();
	WebDriverUtility wutil= new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sDriver;                                                 //listeners 
	
	@BeforeSuite(groups = {"Smoke","Regression"})
	public void bsConfig()
	{
		Reporter.log("---Database Connection Established---",true);
	}
	
	//@Parameters("browser")														 //CBT 
	//@BeforeTest																	 //CBT
	@BeforeClass(groups = {"Smoke","Regression"})									 //CBT
	public void bcConfig(/*String BROWSER*/) throws IOException						 //CBT argument pass as string
	{
		String BROWSER = putil.toReadDataFromPropertyFile("browser");                //CBT stop read data from property file
		String URL = putil.toReadDataFromPropertyFile("url");
		//WebDriver driver = null;
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver=driver;                                                               //listener
		Reporter.log("Browser got Launched Sucessfully",true);
		wutil.toMaximize(driver);
		wutil.toWaitForElements(driver);
		driver.get(URL);
	}
	
	@BeforeMethod(groups = {"Smoke","Regression"})
	public void bmConfig() throws IOException
	{
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Navigated to Vtiger HomePage Sucessfully",false);
	}
	
	@AfterMethod(groups = {"Smoke","Regression"})
	public void amConfig()
	{
		HomePage hp= new HomePage(driver);
		wutil.toMouseHover(driver, hp. getAdministrator());
		hp.getSignoutLink().click();
		Reporter.log("Logged out Successfully",true);
	}
	
	@AfterClass(groups = {"Smoke","Regression"})
	public void acConfig()
	{
		Reporter.log("Browser got closed Successfully",true);
		driver.quit();
	}

	@AfterSuite(groups = {"Smoke","Regression"})
	public void asConfig()
	{
		Reporter.log("---Database Disconnected Sucessfully---",true);
	}
}