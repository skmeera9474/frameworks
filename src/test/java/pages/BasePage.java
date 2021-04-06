package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePage {
	
	public static ExtentReports reports;
	public static ExtentTest test;
	
	
	public static WebDriver driver;
	FileInputStream fis1;
	Properties pr1;
	
	@BeforeTest
	public void reportSetUp()
	{
		
		reports=new ExtentReports();
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		
		date=date.replace(":", "-");
		
	
		
		File reportsDir=new File(System.getProperty("user.dir")+"\\Reports");
		
		reportsDir.mkdir();
		
		String fileName=System.getProperty("user.dir")+"\\Reports\\"+date+".html";
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(new File(fileName));
		
		reporter.config().setDocumentTitle("Regression Test Results");
		
		reporter.config().setReportName("Adactin Login Reuslts");
		
		reporter.config().setTheme(Theme.DARK);
		
		
		
		reports.attachReporter(reporter);
		
		
		
		
	}
	
	@BeforeClass
	public void setUp() throws IOException
	{
		
		fis1=new FileInputStream("Properties\\config.properties");
		
		pr1=new Properties();
		
		pr1.load(fis1);
		
	}
	
	@BeforeMethod
	public void startTest()
	{
		
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
	
	@AfterMethod
	public void tearDown()
	{
		
		
		driver.quit();
	}
	
	@AfterTest
	public void tearReports()
	{
		
		reports.flush();
		
	}


}
