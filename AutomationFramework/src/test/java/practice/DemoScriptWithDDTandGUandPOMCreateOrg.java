package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ObjectRepository.CreateOrgPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrgInfoPage;
import ObjectRepository.OrgPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGUandPOMCreateOrg{
	
	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//Read data from Property file
		String URL=putil.toReadDataFromPropertyFile("url");
		String BROWSER=putil.toReadDataFromPropertyFile("browser");
		String USERNAME=putil.toReadDataFromPropertyFile("username");
		String PASSWORD=putil.toReadDataFromPropertyFile("password");
		
		//Read data from excel file
		String ORGNAME=eutil.toReadDataFromExcel("Organization", 1, 2);
		
		//Step-1 Launch Browser
		 WebDriver driver=null;
		 if(BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
			 }else if(BROWSER.equals("edge")) {
				 driver=new EdgeDriver();
				 }else if(BROWSER.equals("firefox")) {
					 driver=new FirefoxDriver();
					 }
		 
		 wutil.toMaximize(driver);
		 wutil.toWaitForElements(driver);
		 driver.get(URL);
		 
		 //Step-2 Login to application
		 LoginPage lp=new LoginPage(driver);
		 lp.getUsernameTextfield().sendKeys(USERNAME);
		 lp.getPasswordTextField().sendKeys(PASSWORD);
		 lp.getLoginButton().click();
	       
	     //step-3 home page -click on Organization  link
		 HomePage hp=new HomePage(driver);
		 hp.getOrganizations().click();
	       
	     //step-4  Click on create Organization look up image
		 OrgPage op=new OrgPage(driver);
		 op.getOrglookupimg().click();
	       
	     //Step-5 fill the details
		 CreateOrgPage cop=new CreateOrgPage(driver);
		 Random r=new Random();
		 int random=r.nextInt(1000);
		 cop.getOrgname().sendKeys(ORGNAME+random);
		 
		 //step-6 save and verify
		 cop.getSaveorg().click();
		 OrgInfoPage oip=new OrgInfoPage(driver);
		 String orgheadername=oip.getOrginfo().getText();
		 if(orgheadername.contains(ORGNAME+random)) {
			 System.out.println(orgheadername+" is passed ");
			 }else {
				 System.out.println(orgheadername+" is failed");
				 }
		 //step-7 Logout
		 wutil.toMouseHover(driver,hp.getAdministrator());
		 hp.getSignoutLink();
		 
		 //step8-close
		 driver.quit();
	}
}