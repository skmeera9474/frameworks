package testscripts.regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidateLoginDataProviderExcel {
	
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setUp()
	{
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
		
		driver=new ChromeDriver();
		
		
		driver.get("http://adactinhotelapp.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
		
	}
	
	
	@Test(dataProvider="getData")
	public void validateLoginTest(String un,String pass,String title) throws IOException
	{
		
		
		
					
			driver.findElement(By.xpath("//input[@name='username']")).sendKeys(un);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
			driver.findElement(By.xpath("//input[@name='login']")).click();
			
			
			Assert.assertEquals(driver.getTitle(), title);
			
			
			
			
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
