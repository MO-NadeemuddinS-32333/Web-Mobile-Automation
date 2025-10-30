package nadeem;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.AppiumServerManager;
import drivers.DriverFactory;
import utils.ReusableMethods;

public class chromeII extends AppiumServerManager {
	String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\DS, AI & ML\\New folder\\";

	@BeforeClass
	public void reportTableStart() {

	}

	@BeforeTest
	public void launchandroid() {
		try {
			DriverFactory.initDriver("chrome");
			DriverFactory.getDriver();
			System.out.println("Android driver initialized successfully");
		} catch (Exception e) {
			System.err.println("Failed to initialize Android driver: " + e.getMessage());
			throw new RuntimeException("Android setup failed", e);
		}
	}

	@Test(priority = 1)
	public void test1() {
		ReusableMethods.logTableStart("Module2");
		ReusableMethods.logTableRow(path, "This is test 1", "Fail", 1000);
	}

	@Test(priority = 2)
	public void test2() {
		ReusableMethods.logTableRow(path, "This is test 2", "Pass", 1000);
	}

	@Test(priority = 3)
	public void Android() {
		ReusableMethods.logTableRow(path, "This is test 3", "Fail", 1000);
		ReusableMethods.logTableEnd();
	}

	@AfterClass
	public void reportTableEnd() {

	}

}
