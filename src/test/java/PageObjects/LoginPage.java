package PageObjects;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;
import utilities.JavaScriptManager;


public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='identifierId']")
	WebElement emailInputbox_loc;
	
	@FindBy(xpath="//span[normalize-space()='Next']")
	WebElement nextButton_loc;
	
	@FindBy(xpath="//div[@class='o6cuMc Jj6Lae']")
	WebElement errorMsg_loc;
	ExcelUtility excelUtility = new ExcelUtility();
	public void setEmailInput() {
		JavaScriptManager.highlightElement(driver, emailInputbox_loc);
		emailInputbox_loc.sendKeys(randomString() + "@gmail.com");
	}
	
	public  void clickNextBtn() {
		JavaScriptManager.highlightElement(driver, nextButton_loc);

		nextButton_loc.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void getErrorMsg() throws IOException{
		JavaScriptManager.highlightElement(driver, errorMsg_loc);

		String errorMsg = errorMsg_loc.getText();
		System.out.println("-----------------------------");
		System.out.println(errorMsg);
		excelUtility.setCellData("LoginPage", 0, 0, errorMsg);
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
}
