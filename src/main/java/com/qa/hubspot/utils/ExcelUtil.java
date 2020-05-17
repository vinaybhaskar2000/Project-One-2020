package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil 
{
	public static String  TEST_DATA_SHEET="E:\\NAVEEN SELENIUM ONLINE TRAINING2\\Feb2020POMSeries\\src\\main\\java\\com\\qa\\hubspot\\testData\\HubSpotData.xlsx";
	public static Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public static Object[][] getTestData(String sheetname)
	{
		Object data[][]=null;
		
		
		// To read the data from the sheet we have to create Obj of FIS
		
	
			try {
				FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			    
							
							try {
										book =	WorkbookFactory.create(ip);
										sheet = book.getSheet(sheetname);
									
										int rows = sheet.getLastRowNum(); // count the no of rows
										int colms = sheet.getRow(0).getLastCellNum(); // count the no of columns
									   //data = new Object[No of rows][No of COlumns]
										data = new Object[rows][colms];
										
										// To read the data from excel book we have to maintain 2 for loops.
										
										for (int i = 0; i < rows; i++) 
										{
										    for (int j = 0; j < colms; j++) 
										    {
											    data[i][j] = sheet.getRow(i+1).getCell(j).toString();	
											
										    }
											
											
										}
								
							   
							   } 
							
							
							catch (InvalidFormatException e) 
							{
								// WBF1 TODO Auto-generated catch block
								e.printStackTrace();
							} 
							catch (IOException e) {
								// WBF2 TODO Auto-generated catch block
								e.printStackTrace();
							}
			    } 
			
			
			catch (FileNotFoundException e) 
			{
				// FIS - TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return data;
				
		
		
	}
	
}
