package PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.JavaScriptManager;

public class UsedCar extends BasePage {

	// Constructor
	public UsedCar(WebDriver driver) {
		super(driver);
	}
	
	// Web Elements
	@FindBy(xpath="//a[contains(text(),'Used Cars')]")
	WebElement navUsedCar_loc;
	
	@FindBy(xpath="//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	WebElement usedCarLocation_loc;
	
	@FindBy(xpath="//span[normalize-space()='Brand and Model']")
	WebElement brand_and_model_loc;
	
	@FindBy(xpath="//a[@class='zw i-b mt-10 pt-2 zw-srch-logo']")
	WebElement logo_loc;
	
	@FindBy(xpath="//li[@id='mmvLi_21_314']")
	WebElement MahindraPopularBrand;
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']")
	WebElement popularModelBox;
	
	@FindBy(xpath="//ul[@class=\"zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10\"]/li/label")
	List<WebElement>modelsList;
	
	// Utility
	ExcelUtility excelUtility = new ExcelUtility();
	
	// Action methods
	public void hoverUsedCar() {
		JavaScriptManager.scrollToTop(driver);
		Actions actions = new Actions(driver);
		JavaScriptManager.highlightElement(driver, navUsedCar_loc);
		actions.moveToElement(navUsedCar_loc).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickUsedCarLocation() throws IOException {
		JavaScriptManager.highlightElement(driver, usedCarLocation_loc);
		BaseClass.screenshot("UsedCarLocation");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", usedCarLocation_loc);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickPopularModels() throws IOException {
		JavaScriptManager.highlightElement(driver, popularModelBox);
		System.out.println("Size of Popular model :"+ modelsList.size());
		excelUtility.setCellData("PopularModel", 0, 0, "PopularModel");
		System.out.println("-----------------------------");
		int row = 1;
		JavaScriptManager.scrollInsideDiv(driver, modelsList.get(0));
		BaseClass.screenshot("popularModel");
		
		for(WebElement model : modelsList) {
			System.out.println(model.getText());
			excelUtility.setCellData("PopularModel", row, 0, model.getText());
			row++;
		}
	}
}
