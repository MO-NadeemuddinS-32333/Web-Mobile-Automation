package nadeem;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.AppiumServerManager;
import drivers.DriverFactory;
import utils.ReusableMethods;

public class edge extends AppiumServerManager {
	String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\DS, AI & ML\\New folder\\";

	@BeforeClass
	public void reportTableStart() {

	}

	@BeforeTest
	public void beforeTest() throws Exception {
		DriverFactory.initDriver("edge");
		DriverFactory.getDriver();
	}

	@Test(priority = 1)
	public void test1() {
		ReusableMethods.logTableStart("ModuleIII");
		ReusableMethods.logTableRow(path, "This is test 1", "Pass", 1000);

	}

	@Test(priority = 2)
	public void test2() {
		ReusableMethods.logTableRow(path, "This is test 2", "Pass", 1000);
	}

	@Test(priority = 3)
	public void Ios() {
		ReusableMethods.logTableRow(path, "This is test 3", "Fail", 1000);
		ReusableMethods.logTableEnd();
	}

	@AfterClass
	public void reportTableEnd() {

	}

}
