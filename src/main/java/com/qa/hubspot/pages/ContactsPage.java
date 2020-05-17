package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class ContactsPage 
{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	By createContact = By.xpath("(//span[text()='Create contact'])[1]");
	By createContactForm = By.xpath("(//span[text()='Create contact'])[2]");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	By contactsNavigationLink = By.xpath("(//nav[@role='navigation'])[2]/a/span/i18n-string[text()='Contacts']");
	
	
	
	
	
	public ContactsPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	    jsUtil = new JavaScriptUtil(driver);
	}
	
	public String getContactsPageTitle()
	{
		return elementUtil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 5);
		
	}
	
	
	public String createNewContact(String emailId, String firstname, String lastname, String jobtitle) throws InterruptedException
	{
		elementUtil.waitForElmentToBeClickable(createContact, 10);
		elementUtil.doClick(createContact);
		elementUtil.waitForElmentToBePresent(email, 5).sendKeys(emailId);;
		elementUtil.waitForElmentToBePresent(firstName, 5).sendKeys(firstname);
		elementUtil.waitForElmentToBePresent(lastName, 5).sendKeys(lastname);
		elementUtil.waitForElmentToBePresent(jobTitle, 5).sendKeys(jobtitle);
		elementUtil.waitForElmentToBeClickable(createContactForm, 10);
		WebElement contactForm = elementUtil.getElement(createContactForm);
		jsUtil.clickElementByJS(contactForm);
		String fullname = firstname+" "+lastname;
	//	String nameXpath= "(//span[text()='"+fullname+"'])[2]"; // This is not working
		String nameXpath= "//h2[@class='m-bottom-1']/div/span/span[text()='"+fullname+"']";
		elementUtil.waitForElmentToBePresent(contactsNavigationLink, 10);
		String contactName = elementUtil.doGetText(By.xpath(nameXpath)).trim();
		elementUtil.doClick(contactsNavigationLink);
		return contactName;
		
	}
	
	
	
}
