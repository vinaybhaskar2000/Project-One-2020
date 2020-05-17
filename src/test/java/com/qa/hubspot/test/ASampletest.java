package com.qa.hubspot.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ASampletest {

	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "E:/New_Selenium_June 2017/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		options.addArguments("--no-proxy-server");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		
		WebDriver driver = new ChromeDriver(options);
		//driver.get("https://classic.crmpro.com/index.html");
		driver.get("https://app.hubspot.com/login");
		Thread.sleep(5000);
		
		driver.findElement(By.id("username")).sendKeys("bvbhaskar.quality@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Alkapuri@123");
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.id("nav-primary-contacts-branch")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("nav-secondary-contacts")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Create contact']")).click();
		Thread.sleep(2000);
		
		
		
		
		By email = By.xpath("//input[@data-field='email']");
		By firstName = By.xpath("//input[@data-field='firstname']");
		By lastName = By.xpath("//input[@data-field='lastname']");
		By jobTitle = By.xpath("//input[@data-field='jobtitle']");
		
		driver.findElement(email).sendKeys("hgth@gmail.com");
		driver.findElement(firstName).sendKeys("hyderabd");
		driver.findElement(lastName).sendKeys("Saroornagar");
		driver.findElement(jobTitle).sendKeys("Alkapuri");
		
	}

}
