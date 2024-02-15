package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;
import utilities.JavaScriptManager;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}	
	
	@FindBy(xpath="//a[contains(text(), 'New Bikes')]")
	WebElement navNewBike_loc;
	
	@FindBy(xpath="//span[contains(text(), 'Upcoming Bikes')]")
	WebElement upcomingBike_loc;
	
	public boolean verifyTitle() {
		String expectedTitle = driver.getTitle();
		String actualTitle= "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com";
		if(expectedTitle.equals(actualTitle)) {
			System.out.println("Title: " + expectedTitle);
			return true;
		}else {
			System.out.println("Title: " + expectedTitle);
			return false;
		}
	}
	
	public void hoverNewBike() {
		JavaScriptManager.highlightElement(driver, navNewBike_loc);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(navNewBike_loc).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickUpcomingBike() throws IOException {
		Actions actObj = new Actions(driver);
		
		JavaScriptManager.highlightElement(driver, upcomingBike_loc);
		BaseClass.screenshot("UpcomingBike");
		
		actObj.moveToElement(upcomingBike_loc).click().build().perform();
	}
}
