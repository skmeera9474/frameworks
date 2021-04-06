package testscripts.regression;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LoginPage;

public class ValidateLoginPOM extends BasePage {
	
	@Test(dataProvider="getData")
	public void validateLoginPOMTest(String un,String pass,String title)
	{
		
		
		LoginPage loginPage=new LoginPage(driver);
		
		
		loginPage.username(un);
		
		loginPage.password(pass);
		
		loginPage.login();
		
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
