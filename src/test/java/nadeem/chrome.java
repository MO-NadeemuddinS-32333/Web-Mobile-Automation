package nadeem;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.AppiumServerManager;
import drivers.DriverFactory;
import pageobjects.LoginPage;
import utils.Commons;
import utils.ReusableMethods;

public class chrome extends AppiumServerManager {

	String path = "C:\\Users\\nadeemuddinsayed\\Desktop\\DS, AI & ML\\New folder\\";

	@BeforeTest
	public void BrowserLaunch() {
		try {
			DriverFactory.initDriver("chrome");
			DriverFactory.getDriver();
		} catch (Exception e) {
			System.err.println("Failed to initialize Android driver: " + e.getMessage());
			throw new RuntimeException("Android setup failed", e);
		}
	}

	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		ReusableMethods.logTableStart("chrome");
		LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
		loginpage.userID.sendKeys(Commons.getGlobalPropertiesValue("userid"));
		loginpage.password.sendKeys(Commons.getGlobalPropertiesValue("password"));
		loginpage.loginbutton.click();
		ReusableMethods.verifyElementAndLog(loginpage.acme, path, "user login");
	}

	@Test(priority = 2)
	public void scroll() {
		LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
		ReusableMethods.WebscrollByPixels(DriverFactory.getDriver(), 0, 1500);
		ReusableMethods.verifyElementAndLog(loginpage.templeate, path, "User is able to scroll");
	}

	@Test(priority = 3)
	public void mousehoverelement() {
		LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
		ReusableMethods.hoverOverElement(DriverFactory.getDriver(), loginpage.templeate);
		ReusableMethods.verifyElementAndLog(loginpage.templeate, path, "usermousehover on element");
		ReusableMethods.logTableEnd();
	}

	@AfterTest
	public void endtest() {
		DriverFactory.quitDriver();
	}

}
