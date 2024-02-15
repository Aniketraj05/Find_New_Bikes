package PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.JavaScriptManager;

public class UpcomingBike extends BasePage{

	// contructor
	public UpcomingBike(WebDriver driver) {
		super(driver);
	}
	
	// Web Elements
	@FindBy(xpath="//select[@id='makeId']")
	WebElement manufacturer_dpdwn_loc;
	
	@FindBy(css=".lnk-hvr.block.of-hid.h-height")
	List<WebElement> bikeName_loc;
	
	@FindBy(xpath="//li[contains(@class,'modelItem')]")
	List<WebElement> bikePrice_loc;
	
	@FindBy(xpath="//div[contains(text(), 'Launch Date : ')]")
	List<WebElement> launch_date_loc;
	
	@FindBy(xpath="//span[contains(text(), 'View More Bikes')]")
	WebElement view_more_btn_loc;
	
	@FindBy(xpath="//a[normalize-space()='Latest Bikes']")
	WebElement latestBike_loc;
	
	// Utitlities
	ExcelUtility excelUtility = new ExcelUtility();
	
	// Action Methods
	public void selectFromDropDown() throws IOException {
		JavaScriptManager.highlightElement(driver, manufacturer_dpdwn_loc);
		manufacturer_dpdwn_loc.click();
		Select select = new Select(manufacturer_dpdwn_loc);
		select.selectByVisibleText("Honda");
		BaseClass.screenshot("dropDown");
	}
	
	public void clickViewMoreButton() throws IOException {
		
		JavaScriptManager.scrollIntoView(driver, latestBike_loc);
		JavaScriptManager.highlightElement(driver, view_more_btn_loc);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		BaseClass.screenshot("ViewMoreButton");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", view_more_btn_loc);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public void getBikeDetails() throws IOException {
		excelUtility.setCellData("UpcomingBike", 0, 0, "BikeName");
		excelUtility.setCellData("UpcomingBike", 0, 1, "Price");
		excelUtility.setCellData("UpcomingBike", 0, 2, "LaunchDate");
		int row = 1;
		for(int i = 0; i < bikeName_loc.size(); i++) {
			String bikeName = bikeName_loc.get(i).getText();
			String launchDate = launch_date_loc.get(i).getText();
			int price = Integer.parseInt(bikePrice_loc.get(i).getAttribute("data-price"));
			if(price < 400000) {
				System.out.println(bikeName +"\n" + price + "\n" + launchDate);
				System.out.println("--------------------------------");
				excelUtility.setCellData("UpcomingBike", row, 0, bikeName);
				excelUtility.setCellData("UpcomingBike", row, 1, price+"");
				excelUtility.setCellData("UpcomingBike", row, 2, launchDate);
				row++;
			}
		}
	}
	
	// Utitlity Methods
	
	public double extractNumber(String price) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(price);
		if(matcher.find()) {
			return Double.parseDouble(matcher.group());
		}
		return 0.0;
	}
	
	
}
