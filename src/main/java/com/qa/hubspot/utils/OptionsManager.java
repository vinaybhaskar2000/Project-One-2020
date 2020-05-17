package com.qa.hubspot.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager 
{
	public ChromeOptions co;
	public FirefoxOptions fo;
	Properties prop; // Properties class reference
	
	public OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	
	// Creatig Two methods: 1 is for Chrome and 2 is for Firefox.
	
  // For Chrome i want to run in: Headless and Incognito mode.	
	
	
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		
// For HeadLess value:		
		
		String headlessPorperty = prop.getProperty("headless");
	    // 	 headlessPorperty  returns true/false in String mode.
		// below i am using if condition which accepts True/false as boolean value.
		// i am getting String, But If accepts boolean value.
		// So convert from String to boolean we have to use: ParseBoolean.
		
		boolean valueOfHeadless = Boolean.parseBoolean(headlessPorperty);
		
	    // also we can write as :       if(Boolean.parseBoolean(prop.getProperty("headless")))
    	// But to avoid confusion i am writing like below:	
		
 //in one line we can write like this:     if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");	
		
		if (valueOfHeadless) 
		{
			co.addArguments("--headless");
		}
		
// For Incognito value:		
	 
		String incognitoPorperty = prop.getProperty("incognito");
		// same as above.
		boolean valueOfincognito = Boolean.parseBoolean(incognitoPorperty);
		if (valueOfincognito) 
		{
			co.addArguments("--incognito");
		}
		
//in one line we can write like this:  if(Boolean.parseBoolean(incognitoPorperty)) co.addArguments("incognito");
		
		return co;
		
	}
	

	
	// Similarly i am writing method for firefox as well.
	// For Firefox i want to run only in: Headless and NOT in Incognito mode, so not adding the code.
	
	public FirefoxOptions getFireFoxOptions()
	{
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		// But i  dont want to run incognito mode.
		return fo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
