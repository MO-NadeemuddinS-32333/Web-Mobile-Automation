package nadeem;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.AppiumServerManager;
import drivers.DriverFactory;

public class serverdrivertest extends AppiumServerManager {

	@BeforeTest
	public void driverserversetup() throws Exception {
		startServer();
		DriverFactory.initDriver();
		DriverFactory.getDriver();
	}

	@Test
	public void f() throws InterruptedException {
		Thread.sleep(15000);
		System.out.println("method F");
	}

	@AfterTest
	public void teardown() {
		DriverFactory.quitDriver();
		stopServer();
	}
}
