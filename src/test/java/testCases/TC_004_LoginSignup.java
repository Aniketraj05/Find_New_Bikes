package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.LoginSignup;
import testBase.BaseClass;
import utilities.WindowHandel;

public class TC_004_LoginSignup extends BaseClass{
	@Test(priority=1, groups= {"master", "smoke"})
	public void verifyLoginSignUpPage() {
		logger.info("**** Starting TC_004_LoginSignup ****");
		try {
			logger.info("Verifying login/SignUp");
			LoginSignup loginSignup = new LoginSignup(driver);
			loginSignup.clickLogoBtn();
			loginSignup.clickLoginSignUp();
			
			screenshot("LoginSignUpCard");
			
			loginSignup.loginWithGoogle();
		} catch (Exception e) {
			logger.info("Verifying login/SignUp failed!!");
			e.printStackTrace();
			Assert.fail();
		} finally {
			logger.info("Verifying Login/SignUp Page successful");
		}
		
	}
	
	@Test(priority=2,groups= {"regression", "master"})
	public void verifyLoginPage(){
		logger.info("**** Starting TC_005_LoginPage ****");
		try {
			logger.info("Verifying loginPage");
			WindowHandel windHandleObj = new WindowHandel(driver);
			windHandleObj.navigateToWindow("Sign in - Google Accounts");
			
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailInput();
			loginPage.clickNextBtn();
			loginPage.getErrorMsg();
			
			screenshot("LoginErrorMsg");
		} catch (Exception e) {
			logger.info("LoginPage verification failed!!!");
			e.printStackTrace();
			Assert.fail();
		} finally {
			logger.info("Verifying Login Page successful.");
		}
		
		logger.info("**** Finished TC_004_LoginSignup ****");
	}
}
