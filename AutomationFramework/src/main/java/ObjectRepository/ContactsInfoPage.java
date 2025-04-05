package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage
{
	//Constructor
	public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactsHeader;
	
	public WebElement getContactsHeader()
	{
		return contactsHeader;
		}
	}