package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.UpcomingBike;
import testBase.BaseClass;

public class TC_002_UpcomingBike extends BaseClass{
	
	@Test(priority=1, groups= {"smoke","regression", "master"})
	public void verifyNewBike() throws InterruptedException {
		logger.info("**** Starting TC_002_UpcomingBike ****");
		try {
			logger.info("Verifing New Bike");
			HomePage homepage = new HomePage(driver);
			homepage.hoverNewBike();
			homepage.clickUpcomingBike();
		} catch (Exception e) {
			logger.info("New Bike Verification Failed!!");
			e.printStackTrace();
			Assert.fail();
		}finally {
			logger.info("Verifying New Bike successful.");
		}
	}
	
	@Test(priority=2, dependsOnMethods= {"verifyNewBike"}, groups={"regression", "master"})
	public void verifyUpcomingBike() {
		try {
			logger.info("Verifing Upcoming Bike");
			UpcomingBike upcomingBike = new UpcomingBike(driver);
			upcomingBike.selectFromDropDown();
			upcomingBike.clickViewMoreButton();
			upcomingBike.getBikeDetails();
		} catch (Exception e) {
			logger.info("Verification of upcoming bike Failed!!");
			e.printStackTrace();
			Assert.fail();
		} finally {
			logger.info("Verifing Upcoming Bike successful");
		}
		logger.info("**** Finished TC_002_UpcomingBike ****");
	}
}
