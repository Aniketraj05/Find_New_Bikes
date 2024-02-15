package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_HomePage extends BaseClass {
	@Test(priority=1, groups= {"sanity", "master"})
	public void verifyTitle() {
		logger.info("**** Starting TC_001_HomePage ****");
		try {
			
			logger.info("Getting the title.");
			HomePage homepage = new HomePage(driver);
			logger.info("Validating the title.");
			boolean status = homepage.verifyTitle();
			screenshot("HomePage");
			Assert.assertEquals(status, true);
			logger.info("Title Validation successful.");

		} catch (Exception e) {
			logger.info("Title verification Failed!!!!");
			e.printStackTrace();
			Assert.fail();
		}
		finally {
			logger.info("Verify Title successful.");
		}
		logger.info("**** Finished TC_001_HomePage ****");
	}
}
