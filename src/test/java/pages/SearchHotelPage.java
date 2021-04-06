package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchHotelPage {
	
	@FindBy(xpath="//input[@name='Submit']")
	WebElement searchButton;
	
	public void searchButton()
	{
		searchButton.click();
	}

}
