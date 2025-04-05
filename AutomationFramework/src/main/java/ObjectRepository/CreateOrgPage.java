package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {
	//Constructor
	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements( driver,this);
		}
	
	@FindBy(name="accountname")
	private WebElement orgname;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveorg;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getOrgname() {
		return orgname;
	}

	public WebElement getSaveorg() {
		return saveorg;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}	
}