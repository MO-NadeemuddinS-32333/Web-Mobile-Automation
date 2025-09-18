package drivers;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import utils.Commons;

public class DriverFactory {

	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
	private static List<WebDriver> storedDrivers = new ArrayList<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					storedDrivers.forEach(WebDriver::quit);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static WebDriver getDriver() {
		return drivers.get();
	}

	public static void addDriver(WebDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);

	}

	public static void removeDriver(WebDriver driver) {
		storedDrivers.remove(driver);
		drivers.remove();
	}

	public static void initDriver() throws IOException, InterruptedException {

		if ("Android".equalsIgnoreCase(Commons.getGlobalPropertiesValue("platform"))) {

			System.out.println("Initializing Appium...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "13");
			capabilities.setCapability("deviceName", "CPH2467");
			capabilities.setCapability("udid", "97957054");
			capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package"));
			capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);
			AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			addDriver(driver);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			System.out.println("App launch request sent. Waiting for verification...");
			Thread.sleep(5000);

		} else if ("chrome".equalsIgnoreCase(Commons.getGlobalPropertiesValue("platform"))) {

			System.out.println("launching chrome");
			System.setProperty("webdriver.chrome.driver", "D:\\Mo Trading Automation\\Itops\\chromedriver.exe");
			ChromeDriver chromeDriver = new ChromeDriver();
			addDriver(chromeDriver);
			chromeDriver.get(Commons.getGlobalPropertiesValue("url"));
			chromeDriver.manage().window().maximize();
			chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}
}