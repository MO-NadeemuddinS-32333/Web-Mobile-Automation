package nadeem;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;

public class demotest {
	WebDriver driver;
	// AndroidDriver driver;
	String status;
	WebDriverWait wait;

	@BeforeTest
	public void launchapp() throws Exception {
		DriverFactory.initDriver();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
	}

	@Test
	public void sampletest() throws InterruptedException {
		Thread.sleep(15000);

	}

}
