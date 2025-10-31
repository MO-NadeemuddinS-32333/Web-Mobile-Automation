package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import drivers.DriverFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver webDriver) {
		this.driver = webDriver;
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//input[@id='username']")
	public WebElement userID;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;

	@FindBy(xpath = "//a[@id='log-in']")
	public WebElement loginbutton;
	
	@FindBy(xpath = "//div[@class='logo-label']")
	public WebElement acme;
	
	@FindBy(xpath = "//span[normalize-space()='Templates Inc']")
	public WebElement templeate;

}
