package OrganizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepository.CreateOrgPage;
import ObjectRepository.HomePage;
import ObjectRepository.OrgInfoPage;
import ObjectRepository.OrgPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
	@Listeners(genericUtility.ListenersImplementation.class)
	public class ToCreateOrgWithType4Test extends BaseClass {
		@Test(groups="Regression")
		public void toCreateOrgWithTypeDropdown_003() throws EncryptedDocumentException, IOException {
			HomePage hp = new HomePage(driver);
			hp.getOrganizations().click();
			OrgPage op = new OrgPage(driver);
			op.getOrglookupimg().click();
			CreateOrgPage cop = new CreateOrgPage(driver);
			ExcelFileUtility eutil = new ExcelFileUtility();
			Random r = new Random();
			int random = r.nextInt(1000);
			String ORGNAME = eutil.toReadDataFromExcel("Organization", 1, 2);
			cop.getOrgname().sendKeys(ORGNAME + random);
			WebDriverUtility wutil = new WebDriverUtility();
			wutil.toHandleDropdown("Energy", cop.getIndustryDropdown());
			wutil.toHandleDropdown("Customer", cop.getTypeDropdown());
			cop.getSaveorg().click();
			//fail
			//Assert.fail();                                                                  //listeners
			OrgInfoPage oip = new OrgInfoPage(driver);
			String orgname = oip.getOrginfo().getText();
			Assert.assertTrue(orgname.contains(ORGNAME));
			Reporter.log("Organization created with type dropdown successfully",true);
			/*if (orgname.contains(ORGNAME)) {
				System.out.println(orgname + "---Passed");
			} else {
				System.out.println(orgname + "---Failed");
			}*/
		}
	}