package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	//constructor
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateContactIcon;

	public WebElement getCreateContactIcon() {
		return CreateContactIcon;
	}
	
}