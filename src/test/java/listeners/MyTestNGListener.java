package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.BasePage;


public class MyTestNGListener extends BasePage implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("In onTestSuccess");
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		
		date=date.replace(":", "-");
		
		System.out.println(date);
		
		
		String testName=result.getMethod().getMethodName();
		
		test=reports.createTest(testName);
		
		
		File screenShotsDir=new File(System.getProperty("user.dir")+"\\Screenshots");
		
		screenShotsDir.mkdir();
		
		String fileName=System.getProperty("user.dir")+"\\Screenshots\\"+testName+date+".png";
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(srcFile, new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(fileName);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
System.out.println("In onTestFailure");
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		
		date=date.replace(":", "-");
		
		System.out.println(date);
		
		
		String testName=result.getMethod().getMethodName();
		
		
		
		File screenShotsDir=new File(System.getProperty("user.dir")+"\\Screenshots");
		
		screenShotsDir.mkdir();
		
		String fileName=System.getProperty("user.dir")+"\\Screenshots\\"+testName+date+".png";
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(srcFile, new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}
	
	

}
