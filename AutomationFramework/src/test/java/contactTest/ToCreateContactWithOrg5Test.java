package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository.ContactsInfoPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactsPage;
import ObjectRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
	@Listeners(genericUtility.ListenersImplementation.class)
	public class ToCreateContactWithOrg5Test extends BaseClass {
		@Test(groups="Smoke")
		public void toCreateContactWithOrg_002() throws EncryptedDocumentException, IOException{
			//click on Contacts
			HomePage hp=new HomePage(driver);
			hp.getContacts().click();
			
			//click on create contact
			ContactsPage cp= new ContactsPage(driver);
			cp.getCreateContactIcon().click();
			
			//to pass data in Lastname
			ExcelFileUtility eutil=new ExcelFileUtility();
			String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
			CreateContactsPage ccp=new CreateContactsPage(driver);
			ccp.getLastnameTextField().sendKeys(LASTNAME);

			//to click on org look up image
			ccp.getOrgplus().click();
			
			//to switch to sub window
			WebDriverUtility wutil = new WebDriverUtility();
			wutil.toSwitchWindow(driver, "Accounts");
			
			//click on org name
			driver.findElement(By.xpath("//a[text()='Infosys913']")).click();
			
			//to switch back from sub window
			wutil.toSwitchWindow(driver, "Contacts");
			
			//save and verify
			ccp.getSaveButton().click();
			//fail
			//Assert.fail();                                                                  //listeners
			ContactsInfoPage cip = new ContactsInfoPage(driver);
			String lastname = cip.getContactsHeader().getText();
			Assert.assertTrue(lastname.contains(LASTNAME));
			Reporter.log("Contact Created with organization succssfully",true);
			/*
			if (lastname.contains(LASTNAME)) {
				System.out.println(lastname + "---Passed");
			} else {
				System.out.println(lastname + "---Failed");
			}*/
		}
}