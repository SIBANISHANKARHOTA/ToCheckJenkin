package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	//Constructor
	public OrgInfoPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orginfo;

	public WebElement getOrginfo() {
		return orginfo;
	}
}