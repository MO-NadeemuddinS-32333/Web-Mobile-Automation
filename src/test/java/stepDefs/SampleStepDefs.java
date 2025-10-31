package stepDefs;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.AppiumServerManager;
import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;

public class SampleStepDefs extends AppiumServerManager {
	WebDriver driver;
	HomePage homepage;

	@Given("Application is launch")
	public void application_is_launch() throws Exception {
		startServer();
		DriverFactory.initDriver();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
	}

	@Given("user login")
	public void user_login() throws InterruptedException {
		try {
		} catch (Exception biometricLoginException) {
			System.out.println("Login failed");
		}
	}

	@When("user clicks on global search")
	public void user_clicks_on_global_search() throws InterruptedException {
		homepage = new HomePage(DriverFactory.getDriver());
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(1000);
	}

	@When("enter input as Yes Bank")
	public void enter_input_as_yes_bank() {
		homepage = new HomePage(DriverFactory.getDriver());
		homepage.Globalsearchaftertap.click();
		homepage.Globalsearchaftertap.sendKeys("Yes bank");
	}

	@When("clicks on result")
	public void clicks_on_result() {
		homepage = new HomePage(DriverFactory.getDriver());
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
	}

	@Then("Yesbank get quote page is displayed")
	public void yesbank_get_quote_page_is_displayed() {
		homepage = new HomePage(DriverFactory.getDriver());
		try {
			homepage.Globalsearchresult.isDisplayed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		hideKeyboard(driver);
		drivers.DriverFactory.getDriver().navigate().back();
	}

	@Given("order placement")
	public void order_placement() {
		System.out.println("This is Login");
	}

	@Given("place f&O order")
	public void place_f_o_order() {
		HomePage homepage = new HomePage(DriverFactory.getDriver());
		homepage.Globalsearchbeforetap.click();
		System.out.println("This is Login");
	}

	@When("price input")
	public void price_input() {

		System.out.println("This is Login");
	}

	@When("click on place order")
	public void click_on_place_order() {
		System.out.println("This is Login");
	}

	@Then("order book updated")
	public void order_book_updated() {
		System.out.println("This is Login");
	}

	@Then("close application")
	public void close_application() {
		drivers.DriverFactory.quitDriver();
		stopServer();
	}

	public static void hideKeyboard(WebDriver driver) {
		try {
			((AndroidDriver) driver).hideKeyboard();
		} catch (Exception e) {
			System.out.println("Keyboard not present");
		}
	}

}
