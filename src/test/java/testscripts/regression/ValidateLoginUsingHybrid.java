package testscripts.regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import keywords.LoginKeywords;

public class ValidateLoginUsingHybrid {
	
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
	
	@Test(dataProvider="getData")
	public void loginTest(String un,String pass,String title)
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
				String text=null;
				if(locatorKey.equals("txt_username"))
				{
					 text=un;
					
				}
				else if(locatorKey.equals("txt_password"))
				{
					 text=pass;
					
				}
				
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
			else if(action.equals("validateTitle"))
			{
				keys.validateTitle(title);	
			}
			
			
			
			
		}
		
		
			
		
	}
	

	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("src\\test\\resources\\testdata\\excels\\LoginCredentials.xlsx");
		
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet ws=wb.getSheet("Sheet1");
		
		Row row=ws.getRow(0);
		
		Object[][] obj=new Object[ws.getPhysicalNumberOfRows()-1][row.getPhysicalNumberOfCells()];
		
		for(int i=0;i<ws.getPhysicalNumberOfRows()-1;i++)
		{
			
			
			
			for(int j=0;j<row.getPhysicalNumberOfCells();j++)
			{
				
				obj[i][j]=ws.getRow(i+1).getCell(j).getStringCellValue();
				
				
			}
		}
		
		return obj;
		
		
		
		
	
	
	
	
	
	
	
}


}
