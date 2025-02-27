package com.Pages;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class LoginPage {


	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,LoginPage.this );
	}
	
	@FindBy(id ="txtUserName")
	WebElement txt_username;
	
	@FindBy(id ="txtPWD")
	WebElement txt_password;
	
	@FindBy(id ="Img_Login")
	WebElement btn_login;
	
	@FindBy(id = "cboProductSelection")
	WebElement ProductSelectDDL;
	
	@FindBy(id ="cboMultiCompany")
	WebElement CompanyselectionDDL;
	
	@FindBy(id="lblUserLoginError")
	WebElement LgnErrorMsg;

	@FindBy(id = "btnClose")
	WebElement CloseModalofUnconnectedCOmpany;
	
	String LoginPageTitle = "eRequisition Login";
	String InvalidUserNameandPass_MSG = "Invalid username or password, please try again.";
	String SelectProduct_MSG = "Please select your product.";
	String SelectCompany_MSG = "Please select your company.";
	String Inactivemsg = "User cannot login,user status inactive!";
	String UpdatedAppName;
	
	
	public void AfterLoginDoSomewait(String AppName) throws InterruptedException {
		if (AppName.equals("TXO")) {
			if (CloseModalofUnconnectedCOmpany.isDisplayed()) {
				Assert.fail("QBO is not Connected");
			}
			Thread.sleep(30000);
		} else if (AppName.equals("TRX")) {
			Thread.sleep(3000);
		} else if (AppName.equals("Standalone")) {
			Thread.sleep(3000);
		} else {
			Assert.fail(AppName + " This App Name is In Correct its must be 'TXO' 'TRX' 'Standalone'");
		}
	}
	
	public void CheckTitleNameofTXOLoginPage() {
		String TitleofLoginPage = driver.getTitle();
		Assert.assertEquals(TitleofLoginPage, LoginPageTitle, "Actual Title is : "+TitleofLoginPage+" is not same as Expected Title : " +LoginPageTitle);		
	}
	
	public void EnterUsernameandpassword(String User_Name,String Password) throws InterruptedException {
		txt_username.clear();
		txt_password.clear();
		txt_username.sendKeys(User_Name);
		txt_password.sendKeys(Password);
		Thread.sleep(2000);
	}
	
	public void SelectProduct(String ProductName) throws InterruptedException {
		try{
			if(ProductName.equals("TXO")) {
				UpdatedAppName = "Intuit QuickBooks® Online";
			}else if(ProductName.equals("TRX")) {
				UpdatedAppName = "Intuit QuickBooks® Desktop";
			}else if(ProductName.equals("Standalone")) {
				UpdatedAppName = "Standalone";
			}else {
				Assert.fail("Product Type is not Correct : "+ ProductName);
			}
		String Title = driver.getTitle();
		if(Title.equals(LoginPageTitle)) {
			Thread.sleep(1000);
			Select select = new Select(ProductSelectDDL);
			select.selectByVisibleText(UpdatedAppName);
			Thread.sleep(2000);
			LoginBTN();
			Thread.sleep(3000);
		}
	}catch(NoSuchElementException x1) {
//		x1.printStackTrace();
		
	}catch(ElementNotInteractableException x2) {
//		x2.printStackTrace();
	}
	}
	
	public void SelectCompany(String companyname) throws InterruptedException {
		String Title = driver.getTitle();
		if(Title.equals(LoginPageTitle)) {
			Thread.sleep(1000);
			Select select = new Select(CompanyselectionDDL);
			select.selectByValue(companyname);
			Thread.sleep(2000);
			LoginBTN();
			Thread.sleep(4000);
		}
			}
	
	public void LoginBTN() {
		btn_login.click();
	}

	
}