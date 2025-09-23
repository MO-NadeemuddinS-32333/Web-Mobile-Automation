package nadeem;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.AppiumServerManager;
import drivers.DriverFactory;
import pageobjects.HomePage;
import utils.Commons;

public class serverdrivertest extends AppiumServerManager {

	@BeforeTest
	public void driverserversetup() throws Exception {
		try {
			"android".equalsIgnoreCase(Commons.getGlobalPropertiesValue("platform"));
			startServer();
			Thread.sleep(2000);
		} catch (Exception e) {
			"ios".equalsIgnoreCase(Commons.getGlobalPropertiesValue("platform"));
			startServer();
			Thread.sleep(2000);
		} finally {
			DriverFactory.initDriver();
			DriverFactory.getDriver();
		}
	}

	@Test(priority = 1, enabled = true)
	public void passtestmethod() throws InterruptedException {
		Thread.sleep(5000);
		HomePage homepage = new HomePage(DriverFactory.getDriver());
		try {
			homepage.portfolioBottombar.isDisplayed();
		} catch (Exception e) {
			System.out.println("Element not found");
		}
	}

	@Test(priority = 2, dependsOnMethods = { "passtestmethod" }, enabled = true)
	public void failedtestmethod() {
		HomePage homepage = new HomePage(DriverFactory.getDriver());

		homepage.sipwith500.isDisplayed();

	}

	@Test(priority = 3, dependsOnMethods = { "failedtestmethod" }, enabled = true)
	public void skippedtestmethod() {

	}

	@AfterTest
	public void teardown() {
		DriverFactory.quitDriver();
		stopServer();
	}
}
