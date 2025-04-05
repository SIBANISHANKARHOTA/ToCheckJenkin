package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage {
	//Constructor
	public CreateContactsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy (xpath ="//img[@src='themes/softed/images/select.gif']")
	private WebElement orgplus;

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrgplus() {
		return orgplus;
	}
}