package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;

public class ReusableMethods {

	public static void tapWithActions(AndroidDriver Driver, int x, int y) throws InterruptedException {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(100);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	public static void horizontalSwipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int startX, int endX, int centerY) {

		boolean elementFound = false;
		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								endX, centerY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));
				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}

		}

	}

	public static void verticalswipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int centerX, int startY, int endY) {

		boolean elementFound = false;

		while (!elementFound && swipeCount < maxSwipes) {
			try {
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								centerX, endY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));

				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}
		}
	}

	public static void swipeCorinates(AndroidDriver Driver, int startX, int startY, int endX, int endY,
			int swipeCount) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		for (int i = 1; i <= swipeCount; i++) {
			Sequence swipe = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			Driver.perform(Arrays.asList(swipe));

			System.out.println("Performed swipe #" + i);
		}
	}

	public static void longpressElement(AndroidDriver Driver, WebElement elementtolongpress) {
		int elementX = elementtolongpress.getRect().getX() + (elementtolongpress.getRect().getWidth() / 2);
		int elementY = elementtolongpress.getRect().getY() + (elementtolongpress.getRect().getHeight() / 2);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence longPress = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementX, elementY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
				.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(), elementX,
						elementY)) // Hold for 2 seconds
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release
		Driver.perform(Arrays.asList(longPress));

	}

	public static void cleartextandenterinput(AndroidDriver Driver, WebElement textbox, String text) {
		textbox.click();
		textbox.clear();
		textbox.sendKeys(text);
		Driver.hideKeyboard();
	}

	public static void longPressWithActions(AndroidDriver Driver, int x, int y, int time) throws InterruptedException {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(time);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		Driver.perform(Collections.singletonList(tap));
	}

	public static void captureScreenshot(String path, String testcasename) {
		try {
			File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String filePath = path + testcasename + "_" + timestamp + ".png";
			FileUtils.copyFile(screenshot, new File(filePath));
			System.out.println("Screenshot saved: " + filePath);
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}

	// keep rowCounter separate per thread/class
	private static ThreadLocal<Integer> rowCounter = ThreadLocal.withInitial(() -> 0);
	private static ThreadLocal<StringBuilder> tableContent = ThreadLocal.withInitial(() -> new StringBuilder());

	public static void logTableStart(String tableName) {
		rowCounter.set(0);
		tableContent.set(new StringBuilder());
		tableContent.get().append("<h3>").append(tableName).append("</h3>");
		tableContent.get()
				.append("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>");
		tableContent.get().append("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>");
	}

	public static void logTableRow(String path, String testCase, String status, long timeTaken) {
		int currentCount = rowCounter.get() + 1;
		rowCounter.set(currentCount);
		String statusColor = "";
		String statusTextStyle = "color: white; font-weight: bold;";
		if ("Fail".equalsIgnoreCase(status)) {
			statusColor = "background-color: red;";
			try {
				File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
				String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
				String filePath = path + testCase + "_" + timestamp + ".png";
				FileUtils.copyFile(screenshot, new File(filePath));
				System.out.println("Screenshot saved: " + filePath);
			} catch (IOException e) {
				System.out.println("Failed to capture screenshot: " + e.getMessage());
			}

		} else if ("Pass".equalsIgnoreCase(status)) {
			statusColor = "background-color: green;";
		}
		tableContent.get().append("<tr><td>").append(currentCount).append("</td><td>").append(testCase)
				.append("</td><td style='").append(statusColor).append(statusTextStyle).append("'>").append(status)
				.append("</td><td>").append(timeTaken).append("</td></tr>");
	}

	public static void logTableEnd() {
		tableContent.get().append("</table>");
		Reporter.log(tableContent.get().toString(), true);
	}
}
