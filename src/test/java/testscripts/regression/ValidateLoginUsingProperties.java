package testscripts.regression;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidateLoginUsingProperties {
	
	
	WebDriver driver;
	FileInputStream fis2;
	Properties pr2;
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		
		FileInputStream fis1=new FileInputStream("Properties\\config.properties");
		
		Properties pr1=new Properties();
		
		pr1.load(fis1);
		
		 fis2=new FileInputStream("Properties\\locators.properties");
		
		 pr2=new Properties();
		
		pr2.load(fis2);
		
		
		String browserForExecution=pr1.getProperty("browser");
		
		System.out.println("Starting the browser "+browserForExecution);
		
		
		
		
		switch(browserForExecution)
		{
			
			case "chrome":
				
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
				
				driver=new ChromeDriver();
				
				break;
				
			case "firefox":
				
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
				
				driver=new FirefoxDriver();
				
				break;
				
			default:
				
				throw new RuntimeException("Not a valid browser for execution");
				
				
		}	
		
		
		driver.get(pr1.getProperty("url"));
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr1.getProperty("timeToload")), TimeUnit.SECONDS);
		
		
	}
	
	
	@Test
	public void loginTest() throws InterruptedException
	{
		
		
		driver.findElement(By.xpath(pr2.getProperty("txt_username"))).sendKeys("reyaz009");
		driver.findElement(By.xpath(pr2.getProperty("txt_password"))).sendKeys("reyaz123");
		driver.findElement(By.xpath(pr2.getProperty("btn_login"))).click();
		
		Assert.assertEquals(driver.getTitle(), "Adactin.com - Search Hotel");
		
	}
	
	
	
	@AfterMethod
	public void teardown()
	{
		
		driver.quit();
	}
	
	

}
