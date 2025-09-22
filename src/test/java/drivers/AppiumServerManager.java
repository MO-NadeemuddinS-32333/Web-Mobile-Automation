package drivers;

import java.io.File;
import java.io.IOException;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.Commons;

public class AppiumServerManager {

	private static AppiumDriverLocalService service;

	public static void startServer() throws IOException {
		File nodePath = new File(Commons.getGlobalPropertiesValue("nodepath"));
		File appiumMainJs = new File(Commons.getGlobalPropertiesValue("appiumMainJsPath"));

		service = new AppiumServiceBuilder().usingDriverExecutable(nodePath).withAppiumJS(appiumMainJs)
				.withIPAddress("127.0.0.1").usingPort(4723).withArgument(() -> "--base-path", "/wd/hub")
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withLogFile(new File("AppiumServerLogs.txt")).build();

		service.start();
		System.out.println("Appium Server started at: " + service.getUrl());
	}

	public static void stopServer() {
		if (service != null) {
			service.stop();
			System.out.println("Appium Server stopped.");
		}
	}

}
