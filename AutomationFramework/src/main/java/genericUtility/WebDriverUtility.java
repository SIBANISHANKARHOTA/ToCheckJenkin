package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	/**
	 * This method is used to maximize
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will be wait until the webelement
	 * 
	 * @param driver
	 */
	public void toWaitForElements(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method is used to wait until the element is clickable provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to wait until the element is visible provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to handle dropdown using index
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropdown using using value
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle dropdown using Visible text
	 * @param text
	 * @param element
	 */
	public void toHandleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		;
	}

	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to handle frame using String or id
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * This method is used to handle frame using WebElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * this method is used to switch back the driver control to main page 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to handle frame using defalutcontent
	 * 
	 * @param driver
	 */
	public void toHandleFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to Mouse Hover Action on the element(mouse actions)
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * this method is used to double click provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * this method is used to perform right click provided driver and element
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to Click and Hold Action
	 * 
	 * @param driver
	 * @param element
	 */
	public void toClickAndHold(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.clickAndHold(element).perform();
	}

	/**
	 * This method is used to perform Drag And Drop Action provided driver and webelements are source element and target element
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to handle alert popup by accepting it (ok button)
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to handle alert popup by dismissing it (cancel button)
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to capture message in alert popup and accept it
	 * @param driver
	 * @return
	 */
	public String toHandleAlertPopupAndCaptureText(WebDriver driver) {

		Alert alertPopup = driver.switchTo().alert();
		String popupMsg = alertPopup.getText();
		alertPopup.accept();
		return popupMsg;
	}

	/**
	 * This method is used to take screenshot provided driver and screenshot name
	 * 
	 * @param driver
	 * @param screenshotname
	 * @return 
	 * @throws IOException
	 */
	public String toTakeScreenShot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotname + ".jpeg");
		FileHandler.copy(temp, src);
		String pathScreenshotstored = src.getAbsolutePath();                                                      //Extent Report                                  
		return pathScreenshotstored;																			   //Extent Report
	}

	/**
	 * This method is used to transfer driver control to windows provided driver and partial title
	 * 
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			String windowTitle = driver.switchTo().window(id).getTitle();
			if (windowTitle.contains(partialTitle)) {
				break;
			}
		}
	}
}