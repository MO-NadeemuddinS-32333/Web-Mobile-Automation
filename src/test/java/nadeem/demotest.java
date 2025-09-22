package nadeem;

import org.openqa.selenium.By;
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
		System.out.println("app started");
		WebDriver driver = DriverFactory.getDriver();

		try {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]")).click();
		} catch (Exception e) {
			System.out.println("Location popup not displayed");
		}
		Thread.sleep(1500);
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Login\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeImage[@name=\"Login using Password\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Proceed\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Enter Mobile No. or Client ID or Email\"]"))
				.click();
		driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"Enter Mobile No. or Client ID or Email\"]"))
				.sendKeys("Y05120");
		try {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Return']")).click();
		}
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Next\"]")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Enter Your
		// Password\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Enter Your Password\"]")).sendKeys("abc@123");
		// driver.navigate().back();
		try {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Return']")).click();
		}

		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Next\"]")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"DD/MM/YYYY\"]")).click();
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"DD/MM/YYYY\"]")).sendKeys("18051995 ");
		driver.navigate().back();
		// ((IOSDriver) DriverFactory.getDriver()).hideKeyboard();
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Next\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Explore the app\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Options\"]")).click();
		System.out.println("test completed");

	}

	@Test
	public void asserstionorcondition() {
		
		
		
		long startTime = System.currentTimeMillis();
		try {
			try {
				// asserstion 1
			} catch (Exception e) {
				System.out.println("Assertion 1 failed");
			}
			try {
				// asserstion 2
			} catch (Exception e) {
				System.out.println("Assertion 2 failed");
			}
			try {
				// asserstion 3
			} catch (Exception e) {
				System.out.println("Assertion 3 failed");
			}
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();

		}
	}

}
