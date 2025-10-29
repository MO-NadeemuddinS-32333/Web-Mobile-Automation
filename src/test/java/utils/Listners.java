package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import drivers.DriverFactory;

public class Listners implements ITestListener {

	private static ExtentReports extent; // single static instance
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // thread-safe for parallel tests

	public Listners() {
		if (extent == null) {
			extent = getReporterObject();
		}
	}

	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir") + "/reports/index.html";
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		ExtentSparkReporter reporter = new ExtentSparkReporter(path + timestamp);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Tester", "Nadeem Sayed");
		return extentReport;
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String folderPath = "D:\\Mo Trading Automation\\newproject\\Screenshots\\";
		try {
			File folder = new File(folderPath);
			if (!folder.exists())
				folder.mkdirs();

			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String screenshotFile = folderPath + result.getMethod().getMethodName() + "_" + timestamp + ".png";
			File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFile));
			System.out.println("Screenshot saved: " + screenshotFile);
			// Attach screenshot to Extent report
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile).build());
		} catch (IOException e) {
			test.get().fail("Failed to capture screenshot: " + e.getMessage());
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); // writes everything to the report
	}

}
