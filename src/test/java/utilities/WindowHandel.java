package utilities;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import PageObjects.BasePage;

public class WindowHandel extends BasePage {
	public WindowHandel(WebDriver driver) {
		super(driver);
	}
	
	public  boolean navigateToWindow(String WebsiteTitle) {
		Set<String> window = driver.getWindowHandles();
		for(String id : window) {
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if(title.equals(WebsiteTitle)) {
				return true;
			}
		}
		return false;
	}
}
