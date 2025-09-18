package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class demotest {
	WebDriver driver;
	// AndroidDriver driver;
	String status;
	WebDriverWait wait;

	@BeforeTest
	public void launchapp() throws MalformedURLException, IOException, InterruptedException {
		DriverFactory.initDriver();
		this.driver = DriverFactory.getDriver();
		Thread.sleep(5000);
	}

	@Test
	public void sampletest() throws InterruptedException {
		Thread.sleep(5000);
		assert driver != null : "Driver is null - app not launched";

		if (driver instanceof AndroidDriver) {
			String currentPackage = ((AndroidDriver) driver).getCurrentPackage();
			assert "com.mosl.mobile".equals(currentPackage)
					: "Wrong app launched. Expected: com.mosl.mobile, Actual: " + currentPackage;
			System.out.println("Test executed successfully - App launched: " + currentPackage);
		} else {
			System.out.println("Test executed successfully - Web browser launched");
		}
	}

}
