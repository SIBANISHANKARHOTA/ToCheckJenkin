package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContact1Test extends BaseClass
{
	@Test(groups = "Smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.getContacts().click();
		ContactsPage cp= new ContactsPage(driver);
		cp.getCreateContactIcon().click();
		CreateContactsPage ccp=new CreateContactsPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
		ccp.getLastnameTextField().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		//fail
		//Assert.fail();																			//listener
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String lastname=cip.getContactsHeader().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
		Reporter.log("Contact Created successfully",true);
		
		/*if (lastname.contains(LASTNAME))
		{
			System.out.println(lastname+".....passed");	
		}
		else 
		{
			System.out.println(lastname+".....failed");
		}*/
	}
}