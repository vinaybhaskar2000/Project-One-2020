package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import javaScriptUtil.JavaScriptUtil;

public class ElementUtil 
{
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	/**
	 *  This method takes the Webdriver and pass to the variable.
	 * @param driver
	 */
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	

	
	// Now i will write a method to create a WebElement on the basis of Locator.
	
	// This method parameter is saying, give me the By locator./ By variable
	// On the Basis of this By locator :  i will be creating a Locator with WebElement.
	
	/**
	 * This method is used to get the Web Element
	 * @param locator
	 * @return
	 */
	
	public WebElement getElement(By locator)
	{
	/*	driver.findElement(		By.id("")				);
		driver.findElement(		By.xpath("")			);
		driver.findElement(		By.name("")				);
		driver.findElement(		By.linkText("")			);
		driver.findElement(		By.tagName("")			);
		driver.findElement(		By.cssSelector("")		);
		driver.findElement(		By.className("")		);
		driver.findElement(		By.partialLinkText("")	);   */
		
		
		
		WebElement element =  driver.findElement(locator);
		jsUtil.flash(element); // We will control this flashing through our Properties file. If highlet element = yes then only we will highliget otherwise no.
		return element;
	}	
	
	
	/**
	 * This method is used to send the Values / text
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * This method is used to click.
	 * @param locator
	 */
	public void doClick(By locator)
	{
	  getElement(locator).click();	
	}
	
	
	public void doActionSendKeys(By locator, String value)
	{
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();
	}
	
	public void doActionClick(By locator)
	{
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();
	}
	
	
	
	/**
	 * This method is used to get the Text
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public boolean  doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	
	
	/**
	 * This method is used to get the total values in DropDown by using getOptions() method.
	 * @param locator
	 */
	public void getDropdownValues(By locator)
	{
	/*	ElementUtil k = new ElementUtil(driver);
		WebElement elementName = k.getElement(locator);
		Select select = new Select(getElement(elementName)); */ // We can also write like this.
	
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("Total values in dropdown are : "+optionsList.size());
		
		for (int i = 0; i < +optionsList.size(); i++) 
		{
			String values = optionsList.get(i).getText();
			System.out.println(values); 
			
		}
	}
	
	/**
	 * This method is used to select the value in the dropdown by: VisibleText
	 * @param locator
	 * @param value
	 */
	public void selectDropDown(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	/**
	 * This method is used to select the value in the dropdown by: Index
	 * @param locator
	 * @param index
	 */
	
	public void selectDropDown(By locator, int index)
	{
		
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to get the total values in DropDown by using getOptions() method
	 * and select a particular value from the List.
	 * @param locator
	 * @param value
	 * @throws InterruptedException
	 */
	
	
	public void selectDropDownValue(By locator, String value) throws InterruptedException
	{
		Select select = new Select(getElement(locator));
		List<WebElement> valueList = select.getOptions();
		int size = valueList.size();
		System.out.println(size);
		Thread.sleep(1000);
		for (int i = 0; i < valueList.size(); i++) 
		{
			String webElementValue = valueList.get(i).getText();
			if (webElementValue.equals(value)) 
			{
				valueList.get(i).click();
				break;
			}
		}
	}
	
	/**
	 * This method is used to select the value from the drop down values without using Select Class in Selenium.
	 * But by using XPath.
	 * @param driver
	 * @param xpath
	 * @param value
	 * @throws InterruptedException
	 */
	public void doSelectValueFromDropDownwithoutSelect(String xpath, String value) throws InterruptedException
	{
		List<WebElement>valuesList = driver.findElements(By.xpath(xpath));
		int size = valuesList.size();
		System.out.println(size);
		Thread.sleep(1000);
		for (int i = 0; i < valuesList.size(); i++) 	
		{
			String webElementValue = valuesList.get(i).getText();
			if (webElementValue.equals(value)) 
			{
				valuesList.get(i).click();
				break;
			}
		}
	}
	
	/**
	 * Get the Dropdown values in the form of Array List.
	 * @param driver
	 * @param locator
	 * @return
	 */
	
	public List<String> getDropdownValuesList(By locator)
	{
		List<String> ar = new ArrayList<String>();
	//	ElementUtil elementUtil = new ElementUtil(driver); // Calling the method.
	//	WebElement element = elementUtil.getElement(locator); // calling the getElement method.
		Select select = new Select(getElement(locator));
		// Select select = new Select(elementUtil.getElement(locator)); we can write like this also.
		List<WebElement> optionsList = select.getOptions();
	//	System.out.println("Total values in dropdown are : "+optionsList.size());
		
		for (int i = 0; i < +optionsList.size(); i++) 
		{
			String values = optionsList.get(i).getText();
		//	System.out.println(values); 
			ar.add(values); // adding the values to the ArrayList.
		}
		 return ar;
	}
	
	// WAITs Custom Methods : these below methods are : Wait and Return
	
		public void visibilityOfAllTheElements(List<WebElement> elements, int timeout)
		{
			WebDriverWait wait = new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		}
	
	
	
	
	// This below method is wait and returning the webelement.
	public WebElement waitForElmentToBePresent(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement ele = getElement(locator);
		return ele;
	}
	
	// Element to be clickable
	
	public WebElement waitForElmentToBeClickable(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement ele = getElement(locator);
		return ele;
	}
	
	// Element to be Visible 
	
	public WebElement waitForElmentToBeVisible(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		WebElement element = getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	
	public WebElement waitForElmentVisibilityLocated(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		WebElement element = getElement(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public String waitForURL(String URL, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.urlContains(URL));
		return driver.getCurrentUrl();
	}
		
	// for Java Script Alerts only.
	public boolean waitAlertToBePresent(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	
	
	//  The wait is over and the element is clickable now, get the element and simply click on.
	// here there is no need to call to click the element explicitly.
	public void clickWhenReady(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	
	
	
	// This below method is wait and returning the String.
	public String waitForTitleToBePresent(String title, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.titleContains(title));
		String abc = driver.getTitle();
		return abc;
    }
	
	
	
	
}
