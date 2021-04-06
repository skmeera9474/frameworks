package keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class LoginKeywords {
	
	public static WebDriver driver;
	
	public void launchBrowser(String browserForExecution)
	{
		
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
		
		
	}
	
	
	public void navigateToURL(String url)
	{
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
	}
	
	
	
	public void enterText(String xpath,String text)
	{
		
		
		driver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	
	public void clickButton(String xpath)
	{
		
		
		driver.findElement(By.xpath(xpath)).click();
	}
	
	
	public void closeBrowser()
	{
		
		
		driver.close();
	}
	
	public void validateTitle(String title)
	{
		Assert.assertEquals(driver.getTitle(), title);
	}


}
