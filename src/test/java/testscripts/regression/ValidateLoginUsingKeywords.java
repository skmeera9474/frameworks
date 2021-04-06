package testscripts.regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import keywords.LoginKeywords;

public class ValidateLoginUsingKeywords {
	
	FileInputStream fis;
	FileInputStream fis1;
	FileInputStream fis2;
	
	XSSFWorkbook wb;
	
	XSSFSheet ws;
	
	Properties pr1;
	
	Properties pr2;
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		
		fis=new FileInputStream("src\\test\\resources\\testdata\\excels\\LoginKeywords.xlsx");
		
		wb=new XSSFWorkbook(fis);
		
		
		ws=wb.getSheet("Sheet1");
		
		fis1=new FileInputStream("Properties\\config.properties");
		
		pr1=new Properties();
		
		pr1.load(fis1);
		
		fis2=new FileInputStream("Properties\\locators.properties");
		
		pr2=new Properties();
		
		pr2.load(fis2);
		
	}
	
	@Test
	public void loginTest()
	{
		Iterator<Row> rows=ws.iterator();
		
		
		rows.next();
		
		Row row=null;
		
		LoginKeywords keys=new LoginKeywords();
		
		while(rows.hasNext())
		{
			
			row=rows.next();
			
			String action=row.getCell(3).getStringCellValue();
			
			if(action.equals("launchBrowser"))
			{
				
				keys.launchBrowser(pr1.getProperty("browser"));
			}
			
			else if(action.equals("navigateToURL"))
			{
				
				keys.navigateToURL(pr1.getProperty("url"));
			}
			else if(action.equals("enterText"))
			{
				String locatorKey=row.getCell(4).getStringCellValue();
				String text=row.getCell(5).getStringCellValue();
				keys.enterText(pr2.getProperty(locatorKey), text);
			}
			else if(action.equals("clickButton"))
			{
				String locatorKey=row.getCell(4).getStringCellValue();
				keys.clickButton(pr2.getProperty(locatorKey));
			}
			else if(action.equals("closeBrowser"))
			{
				keys.closeBrowser();
			}
			
			
			
		}
		
		
	}

}
