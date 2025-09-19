package drivers;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import utils.Commons;

public class DriverFactory {

	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
	private static List<WebDriver> storedDrivers = new ArrayList<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				Thread.sleep(1000);
				storedDrivers.forEach(WebDriver::quit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}));
	}

	public static WebDriver getDriver() {
		return drivers.get();
	}

	private static void addDriver(WebDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);
	}

	public static void removeDriver() {
		WebDriver driver = drivers.get();
		if (driver != null) {
			storedDrivers.remove(driver);
			drivers.remove();
		}
	}

	public static void quitDriver() {
		WebDriver driver = drivers.get();
		if (driver != null) {
			driver.quit();
			removeDriver();
		}
	}

	public static void initDriver() throws Exception {
		String platform = Commons.getGlobalPropertiesValue("platform");

		switch (platform.toLowerCase()) {
		case "android":
			initAndroid();
			break;
		case "ios":
			initIOS();
			break;
		case "chrome":
			initChrome();
			break;
		case "firefox":
			initFirefox();
			break;
		case "edge":
			initEdge();
			break;
		case "browserstackios":
			initBrowserStackIos();
			break;
		case "browserstackandroid":
			initBrowserStackandroid();
			break;
		default:
			throw new RuntimeException("Unsupported platform in config: " + platform);
		}
	}

	// ---------------- MOBILE ----------------
	private static void initAndroid() throws IOException, InterruptedException {
		System.out.println("Initializing Android Appium...");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", Commons.getGlobalPropertiesValue("androidVersion"));
		capabilities.setCapability("deviceName", Commons.getGlobalPropertiesValue("androidDevice"));
		capabilities.setCapability("udid", Commons.getGlobalPropertiesValue("androidUdid"));
		capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package"));
		capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("noReset", true);

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		addDriver(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
	}

	private static void initIOS() throws IOException, InterruptedException {
		System.out.println("Initializing iOS Appium...");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", Commons.getGlobalPropertiesValue("iosVersion"));
		capabilities.setCapability("deviceName", Commons.getGlobalPropertiesValue("iosDevice"));
		capabilities.setCapability("udid", Commons.getGlobalPropertiesValue("iosUdid"));
		capabilities.setCapability("bundleId", Commons.getGlobalPropertiesValue("iosBundleId"));
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("noReset", true);

		IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		addDriver(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
	}

	// ---------------- Browser stack ----------------
	private static void initBrowserStackIos() throws Exception {
		System.out.println("Initializing BrowserStack...");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		HashMap<String, Object> bstackOptions = new HashMap<>();
		bstackOptions.put("userName", Commons.getGlobalPropertiesValue("bstackUser"));
		bstackOptions.put("accessKey", Commons.getGlobalPropertiesValue("bstackKey"));
		bstackOptions.put("appiumVersion", "2.0.1");
		bstackOptions.put("debug", "true");
		bstackOptions.put("interactiveDebugging", "true");
		// you can also add build name, project name, session name here
		// bstackOptions.put("buildName",
		// Commons.getGlobalPropertiesValue("buildName"));
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("appium:platformVersion", "17.0");
		capabilities.setCapability("appium:deviceName", "iPhone 15");
		capabilities.setCapability("appium:app", Commons.getGlobalPropertiesValue("bstackApp"));
		capabilities.setCapability("appium:automationName", "XCUITest");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("bstack:options", bstackOptions);

		IOSDriver driver = new IOSDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);

		addDriver(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(500);
	}

	private static void initBrowserStackandroid() throws Exception {
		System.out.println("Initializing BrowserStack...");

		UiAutomator2Options capabilities = new UiAutomator2Options();

		HashMap<String, Object> bstackOptions = new HashMap<>();
		bstackOptions.put("userName", Commons.getGlobalPropertiesValue("bstackUser"));
		bstackOptions.put("accessKey", Commons.getGlobalPropertiesValue("bstackKey"));
		bstackOptions.put("appiumVersion", "2.0.1");
		bstackOptions.put("debug", "true");
		bstackOptions.put("interactiveDebugging", "true");
		// you can also add build name, project name, session name here
		// bstackOptions.put("buildName",
		// Commons.getGlobalPropertiesValue("buildName"));

		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("appium:platformVersion", "14.0");
		capabilities.setCapability("appium:deviceName", "Google Pixel 8 Pro");
		capabilities.setCapability("appium:app", Commons.getGlobalPropertiesValue("bstackApp"));
		capabilities.setCapability("appium:automationName", "UIAutomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("bstack:options", bstackOptions);

		AndroidDriver driver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);

		addDriver(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(500);
	}

	// ---------------- WEB ----------------
	private static void initChrome() throws IOException {
		System.out.println("Launching Chrome...");
		System.setProperty("webdriver.chrome.driver", Commons.getGlobalPropertiesValue("chromeDriverPath"));
		ChromeDriver chromeDriver = new ChromeDriver();
		addDriver(chromeDriver);
		chromeDriver.get(Commons.getGlobalPropertiesValue("url"));
		chromeDriver.manage().window().maximize();
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	private static void initFirefox() throws IOException {
		System.out.println("Launching Firefox...");
		System.setProperty("webdriver.gecko.driver", Commons.getGlobalPropertiesValue("geckoDriverPath"));
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		addDriver(firefoxDriver);
		firefoxDriver.get(Commons.getGlobalPropertiesValue("url"));
		firefoxDriver.manage().window().maximize();
		firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	private static void initEdge() throws IOException {
		System.out.println("Launching Edge...");
		System.setProperty("webdriver.edge.driver", Commons.getGlobalPropertiesValue("edgeDriverPath"));
		EdgeDriver edgeDriver = new EdgeDriver();
		addDriver(edgeDriver);
		edgeDriver.get(Commons.getGlobalPropertiesValue("url"));
		edgeDriver.manage().window().maximize();
		edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

}
