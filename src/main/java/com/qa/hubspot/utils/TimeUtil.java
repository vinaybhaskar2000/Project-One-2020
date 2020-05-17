package com.qa.hubspot.utils;

public class TimeUtil 
{
	public static void shortWait() throws InterruptedException
	{
		Thread.sleep(3000);
	}
	
	public static void mediumWait() throws InterruptedException
	{
		Thread.sleep(5000);
	}
	public static void longWait() throws InterruptedException
	{
		Thread.sleep(10000);
	}
	
}
