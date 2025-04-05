package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPage {
	//Constructor
	public OrgPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		}

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement orglookupimg;

	public WebElement getOrglookupimg() {
		return orglookupimg;
	}
}