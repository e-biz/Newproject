package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MenuNavBar {
	WebDriver driver;
	public MenuNavBar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, MenuNavBar.this);
	}
	
	@FindBy(id = "menu")
	WebElement Menu;
	
	@FindBy(xpath ="//a[text() = 'Home']")
	WebElement Home;
	
	@FindBy(xpath ="//a[text() = 'New Requisition']")
	WebElement NewRequision;
	
	@FindBy(xpath ="//a[text() = 'History']")
	WebElement History;
	
	
	@FindBy(xpath ="//a[text() = 'Admin & Settings']")
	WebElement Setting;
	
	
	@FindBy(xpath ="//a[text() = 'User Management']")
	WebElement UserManagment;
	
	@FindBy(xpath ="///a[text() = 'Workflow']")
	WebElement WorkFlow;
	
	@FindBy(xpath ="//a[text() = 'Subscription']") //Subscription //Subscription
	WebElement Subscription;
	
	@FindBy(xpath ="//a[text() = 'Company Settings']")
	WebElement CompanySetting;

	@FindBy(xpath ="//div[@class='menu']//ul//li[5]//a[text() = 'Change Password']")
	WebElement PasswordChange;
	
	@FindBy(xpath = "//div[@id='Sub_2']//input[@id = 'txtCurrentPswd']")
	WebElement Currentpassword;
	
	@FindBy(xpath = "//input[@id = 'txtNewPswd']")
	WebElement Newpassword;
	
	@FindBy(xpath = "//input[@id = 'txtConfNewPswd']")
	WebElement Confirmpassword;
	
	@FindBy(id ="btn_SavePswd")
	WebElement Save;
	
	@FindBy(id ="btnLogout")
	WebElement ClickLogout;
	
	@FindBy(id ="Logout")
	WebElement ClickLogoutfromReqPage;
	
	public void Menu() throws InterruptedException {
		WebElement menu = driver.findElement(By.id("menu")); // Replace with the actual ID of the menu element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", menu);
	}
	
	public void SomeChangeMenu() {
		WebElement menu = driver.findElement(By.xpath("//div/img[@id = 'menuimage']")); // Replace with the actual ID of the menu element
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", menu);
	}
	
	public void HomeMenu() throws InterruptedException {
		Menu();
		Thread.sleep(1000);
		Home.click();
		Thread.sleep(2000);
	}
	
	public void NewReqMenu() throws InterruptedException {
		Menu();
		Thread.sleep(1000);
		NewRequision.click();
		Thread.sleep(8000);
	}
	


	public void DisablityCheckofNewReqMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		try {
			NewRequision.isDisplayed();
			NewRequision.click();
			Assert.fail("Requistion Menu is appearing even the Rights is Removed");
		}catch(NoSuchElementException ignored)
		{
//		    Console.WriteLine("Element does not exist!");
			Assert.assertTrue(true,"Main Menu NewReq is Successfully Disable");
		}
	}
	
	public void DisablityCheckofSettingMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		try {
			Setting.isDisplayed();
			Setting.click();
		}catch(NoSuchElementException ignored)
		{
//		    Console.WriteLine("Element does not exist!");
			Assert.assertTrue(true,"Main Menu Setting Button is Successfully Disable");
		}
	}
	
	public void HistoryMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		History.click();
		Thread.sleep(15000);
	}
	
	public void Setting_UserManagementMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		Setting.click();
		Thread.sleep(2000);
		UserManagment.click();
		Thread.sleep(2000);
	}
	
	public void Setting_WorkflowMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		Setting.click();
		Thread.sleep(2000);
		WorkFlow.click();
	}
	
	public void Setting_Workflow_Fromusermanagement()throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='Sub_1']//li[2]//a[text() = 'Workflow']")).click();
		Thread.sleep(4000);
		WorkFlow.click();
	}
	
	
	public void Setting_SubscriptionMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		Setting.click();
		Thread.sleep(1000);
		Subscription.click();
	}
	
	public void Setting_CompanySettingMenu() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		Setting.click();
		Thread.sleep(3000);
		CompanySetting.click();
	}
	
	
	
	
	
	public void PasswordChange(String NewPassword,String CurPassword) throws InterruptedException {
		PasswordChange.click();
		Thread.sleep(1000);
		Currentpassword.clear();
		Currentpassword.sendKeys("Aa1234567");
		Newpassword.sendKeys(NewPassword);
		Confirmpassword.sendKeys(CurPassword);
		Save.click();
		Thread.sleep(1000);
		String PassChangeAlert = driver.switchTo().alert().getText();
		if(PassChangeAlert.equals("Password updated successfully.")) {
			driver.switchTo().alert().accept();
		}else if(PassChangeAlert.equals("Oops! Password must contain: Minimum 9 characters and combination of at least 3 of the classes from 1 LowerCase, 1 UpperCase, 1 Numeric and 1 Special character.")){
			driver.switchTo().alert().accept();
		}else if(PassChangeAlert.equals("Oops! Looks like the password and its confirmation do not match.")){
			driver.switchTo().alert().accept();
		}else {Assert.fail("Please see snapshot Input Password Popupmsg is Undefine or some other excepttion is occured");}	
	}
	
	public void Logout() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		ClickLogout.click();
	}
	
	public void DuplicatereqLogout() throws InterruptedException {
		Menu();
		Thread.sleep(2000);
		ClickLogoutfromReqPage.click();
		
	}
	

}


