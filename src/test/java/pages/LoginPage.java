package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='login']")
	WebElement login;
	
	
	public LoginPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		
	}
	public void username(String text)
	{
		
		username.sendKeys(text);
	}
	
	public void password(String text)
	{
		
		password.sendKeys(text);
	}
	
	public void login()
	{
		
		login.click();
	}


}
