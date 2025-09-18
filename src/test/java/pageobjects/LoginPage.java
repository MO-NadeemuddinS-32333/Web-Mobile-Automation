package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import drivers.DriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//android.view.View[@content-desc=\"Login\"]")
	public WebElement loginToRiseText;

	@FindBy(className = "android.widget.EditText")
	public WebElement userID;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Next\"]")
	public WebElement nextbutton;

	@AndroidFindBy(accessibility = "Enter Your Password")
	public WebElement enterYourPasswordText;

	@FindBy(className = "android.widget.EditText")
	public WebElement passwordTextField;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Login\"]")
	public WebElement loginButton;

	@AndroidFindBy(accessibility = "Verify details to Login")
	public WebElement verifyDobText;

	@FindBy(className = "android.widget.EditText")
	public List<WebElement> dobTextField;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Confirm\"]")
	public WebElement confirmDobButton;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Explore the app\"]")
	public WebElement exploreTheAppButton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"I Understand\"]")
	public WebElement iUnderstandRddButton;

}
