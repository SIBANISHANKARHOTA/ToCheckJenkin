package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(linkText="Contacts")
	private WebElement contacts;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizations;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink ;

	public WebElement getContacts() {
		return contacts;
	}

	public WebElement getOrganizations() {
		return organizations;
	}

	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}
}