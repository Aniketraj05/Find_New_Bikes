package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.UsedCar;
import testBase.BaseClass;

public class TC_003_UsedCar extends BaseClass {
	
	@Test(priority = 1, groups= {"regression", "master"})
	public void verifyUsedBike() {
		logger.info("**** Starting TC_003_UsedCar ****");
		try {
			logger.info("Verifying usedCar bike");
			UsedCar usedCar = new UsedCar(driver);
			usedCar.hoverUsedCar();
			usedCar.clickUsedCarLocation();
			usedCar.clickPopularModels();
		} catch (Exception e) {
			logger.info("Used Car Verification Failed!!!");
			e.printStackTrace();
			Assert.fail();
		} finally {
			logger.info("Used Car Verification successful");
		}
		logger.info("**** Finished TC_003_UsedCar ****");
	}
}
