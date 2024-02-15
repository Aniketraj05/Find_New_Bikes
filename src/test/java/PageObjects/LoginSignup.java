package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSignup extends BasePage{
	public LoginSignup(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[@class='zw i-b mt-10 pt-2 zw-srch-logo']")
	WebElement logo_loc;
	
	@FindBy(xpath = "//div[@id='des_lIcon']")
	WebElement loginSignUp;
	
	@FindBy(xpath="//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
	WebElement loginWithGoogle;
	
	public void clickLogoBtn() {
		logo_loc.click();
	}
	
	public void clickLoginSignUp() {
		loginSignUp.click();
	}
	
	public void loginWithGoogle() {
		loginWithGoogle.click();
	}
	
}
