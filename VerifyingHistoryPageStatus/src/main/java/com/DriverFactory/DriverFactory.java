package com.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("incognito");
			tlDriver.set(new ChromeDriver(option));
			
		} else if (browser.equals("firefox")) {
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//	        firefoxProfile.setPreference("browser.private.browsing.autostart",true);
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
////	        caps.setCapability("moz:firefoxOptions",options);
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(options));
		} else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

	//	getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}



}

