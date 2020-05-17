package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage 
{
    // initially we have to write 2 methods.
	// Initialize the driver    and 
	// Initialize the property.
	
	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;
	// for example : 
	//private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	// like the above we are maintaining driver.
	// We have to maintain the driver which is the type of WebDriver type.
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	

	
	
/**
 * This Method is used to initialize the driver on the basis of given browser.	
 * @param browserName
 * @return driver
 * @throws InterruptedException 
 */
	public WebDriver init_driver(Properties prop) throws InterruptedException
	{
		optionsManager = new OptionsManager(prop);
	    ChromeOptions cd = optionsManager.getChromeOptions();
	    
		String browserName = prop.getProperty("browser");
		
		System.out.println("Browser Name is : "+browserName);
		
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		//	driver = new ChromeDriver();
			
		}
		
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			FirefoxOptions fd = optionsManager.getFireFoxOptions();
			WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
		//	driver = new FirefoxDriver(fd);
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			
		}
		
		else if (browserName.equalsIgnoreCase("safari")) 
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else 
		{
		   System.out.println(browserName+" : is not found, Please pass the correct Browser Name...!");		
		}
		
	//	driver.manage().deleteAllCookies();
	//	driver.manage().window().maximize();
	//	driver.manage().window().fullscreen();
	//	driver.get(prop.getProperty("url"));
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		
	    return getDriver();
	}
	
	
	// Initialize the property.
	// Single try block and multiple catch blocks.
	

	/**
	 * This Method is used to initialize or load the properties from Config file.
	 * @return
	 */
	
	public Properties init_Prop() 
	{
		 prop = new Properties();
		 try {
			FileInputStream ip = new FileInputStream("E:\\NAVEEN SELENIUM ONLINE TRAINING2\\Feb2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return prop;
		
	}
	
	
	public String getScreenshot()
	{
		 File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		 String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";;
		 File destination = new File(path);
	//	 File file = new File(path);
		 
		 try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return path;
	}
	
}
