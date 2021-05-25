package test.selenium;

import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestDriver {
	
	static String url = "C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe";

	public SeleniumTestDriver() {
		super();
	}
	
	public static ChromeDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", url);
		return new ChromeDriver();
	}
}
