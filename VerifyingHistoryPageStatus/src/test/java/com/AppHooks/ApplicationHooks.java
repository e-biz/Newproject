package com.AppHooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.DriverFactory.DriverFactory;

import StoringLocalValue.ScenarioContext;

import javax.swing.SwingUtilities;
import WindowScreenRunner.TRXApplication;
import io.cucumber.java.*;


public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private TRXApplication trxApplication;
	public String Appname;
	 public String CompanyName;
	 public String AEmail;
	 public String Env;
	 public String Password;
	 public String Browser;

 public void GetFromWindowFormApp() {
		 
		 Appname = ScenarioContext.get("AppName");
		 AEmail =  ScenarioContext.get("AEmail");
		 Password = ScenarioContext.get("Password");
         Env = ScenarioContext.get("Environment");
         CompanyName =  ScenarioContext.get("CompanyName");
         Browser =  ScenarioContext.get("Browser");
	 }
	@Before(order = 0)
	public void launchInputScreen() {
		trxApplication = new TRXApplication();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                trxApplication.display();
            }
        });
	}

	@Before(order = 1)
	public void SubmitTheeRequistiionForm() {
		trxApplication.submitForm();
	}
	
	@Before(order = 2)
	public void waitBeforeSubmitisnotyetdone() {
		// Wait for the form to be submitted before proceeding
        while (!trxApplication.isFormSubmitted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	
	@Before(order = 3)
	public void ClosedUIScreen() {
		GetFromWindowFormApp();
		trxApplication.closeWindow();
	}
	
	@Before(order = 4)
	public void launchBrowser() {
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(Browser);
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
}
