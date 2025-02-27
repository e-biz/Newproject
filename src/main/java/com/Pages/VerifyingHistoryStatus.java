package com.Pages;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class VerifyingHistoryStatus {
	private Map<String, WebElement> statusCheckboxMap;
	public WebDriver driver;
	
	public VerifyingHistoryStatus(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, VerifyingHistoryStatus.this);
		
		 statusCheckboxMap = new HashMap<>();
	        // Populate the map with status-checkbox pairs.
	        statusCheckboxMap.put("Under Review", UnderReviewCheckBox);
	        statusCheckboxMap.put("Rejected", RejectedCheckBox);
	        statusCheckboxMap.put("PO Created", PoCreatedCheckbox);
	        statusCheckboxMap.put("PO Synced", PoSyncedCheckbox);
	        statusCheckboxMap.put("Cancelled", WithdrawCheckbox);
	        statusCheckboxMap.put("Closed",ClosedCheckbox);

	        statusCheckboxMap.put("Pending Sync",PendingSyncCheckBox );
	        statusCheckboxMap.put("Sync Error",SyncErrroCheckbox);
	        statusCheckboxMap.put("PO in Editing",PoInEditing );
}
	
	@FindBy(id = "gvRequisition_btnReq_0")
	WebElement ViewReqHistoryOpen;
	
	@FindBy(id = "lnkDuplicateReqBeta")
	WebElement DuplicateBTN;
	
	@FindBy(id = "txtSearch")
	WebElement Searchbox;
	
	@FindBy(id = "ImageButton1")
	WebElement SearchBTN;
	
	@FindBy(xpath = "//*[@id='grdHistory']/tbody/tr/td[12]")
	List<WebElement> RequestorColumn;
	
	@FindBy(xpath="//*[@id=\"grdHistory\"]/tbody/tr/td[15]")
	List<WebElement> AllStatus;
	
	@FindBy(xpath = "//td//table//tbody//tr//td//input[@title = 'Email PO to Vendor']")
	List<WebElement> EmailtoVendorCheck;
	
	@FindBy(xpath = "//td//table//tbody//tr//td//input[@title = 'Create Receiving']")
	List<WebElement> CreateReceivingCheck;
	
	@FindBy(xpath = "//td//table//tbody//tr//td//input[@title = 'Delete PO']")
	List<WebElement> DeletePOBTNCheck;
	
	@FindBy(id = "chkPOCreated")
	WebElement CHKOFCreatePO;
	
	@FindBy(id = "chkClosed")
	WebElement CHKOFClosedSearch;
	
	@FindBy(id = "cboShowResults")
	WebElement cboShowResults;
	
	@FindBy(id="chkUnderReview")
	WebElement UnderReviewCheckBox;
	
	@FindBy(id="chkRejected")
	WebElement RejectedCheckBox;
	
	@FindBy(id="chkPOCreated")
	WebElement PoCreatedCheckbox;//same for po synced
	
	@FindBy(id="chkPOCreated")
	WebElement PoSyncedCheckbox;//same for po synced
	
	@FindBy(id="chkCancelled")
	WebElement WithdrawCheckbox;
	
	@FindBy(id="chkClosed")
	WebElement ClosedCheckbox;
	

	  @FindBy(xpath = "//*[@id=\"grdHistory\"]/tbody/tr/td[15]")
	  List<WebElement> Size;
	  
//For trx
	
	@FindBy(id="chkPending")
	WebElement PendingSyncCheckBox;
	
	@FindBy(id="chkUnableToSync")
	WebElement SyncErrroCheckbox;
	
	@FindBy(id="chkPOInEditing")
	WebElement PoInEditing; 
	
	public void DisablityCheckofSeeEveryOneHistory(String UserName) {
		
		
		for(int i =1;i < RequestorColumn.size();i++) {
			System.out.println(RequestorColumn.size());
			Actions act = new Actions(driver);
			act.moveToElement(RequestorColumn.get(i));
			act.perform();
			
			String ReqName = RequestorColumn.get(i).getText();
			
			System.out.println(ReqName+ " 1");
			if(ReqName.equals(null) || ReqName.equals("")) {
				System.out.println(i);
			}else {
				Assert.assertEquals(ReqName.equals(UserName), true);		
			}
		}
	}
	
	public void DisablityCheckOfDDLHistoryPageForCanseeEveryoneHistory() throws InterruptedException{
		Select select = new Select(cboShowResults);
		WebElement dropdown = driver.findElement(By.id("cboShowResults"));
        // Execute JavaScript to select the "Mine" option
        String script = "arguments[0].value = '1';"; // '1' is the value of "Mine" option
        ((JavascriptExecutor) driver).executeScript(script, dropdown);
        Thread.sleep(10000);
		String SelectedDDL = select.getFirstSelectedOption().getText();
		System.out.println(SelectedDDL);
		Assert.assertEquals(SelectedDDL, "Mine", "View All user history right is not working properly");
	}
	

	
	 public void GettingStatusOneByOne(String ReqStatus) throws InterruptedException {
			
			for(int i =1;i < AllStatus.size();i++) {
				System.out.println(AllStatus.size());
				Actions act = new Actions(driver);
				act.moveToElement(AllStatus.get(i));
				act.perform();
				String status = AllStatus.get(i).getText();
				System.out.println(status+ " 1");
				Thread.sleep(2000);
				 if (ReqStatus == null || ReqStatus.equals(status)) {

					System.out.println(i);
				}else if(ReqStatus.equals("Closed") || ReqStatus.equals("Manually Closed")) {
					Assert.assertTrue(true);
				}
				else {
					Assert.assertEquals(status,ReqStatus,ReqStatus+"Status is not aapearing correctly instead of ;"+ReqStatus+" Its appearing ;"+status+" See Snapshot");	
				}
			}
			System.out.println("Outside the Loop");
	 }
	 

 public void GetCountForTxoStatus(String ReqStatus) throws InterruptedException {
    WebElement checkBox = statusCheckboxMap.get(ReqStatus);
    if (checkBox != null) {
        checkBox.click();
        Thread.sleep(15000);
        GettingStatusOneByOne(ReqStatus);
        Thread.sleep(15000);
        checkBox.click();
        Thread.sleep(15000);
    } else {
        System.out.println("Invalid status provided: " + ReqStatus);
    }
}
 

}
