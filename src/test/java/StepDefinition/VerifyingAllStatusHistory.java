package StepDefinition;

import java.util.List;
import java.util.Map;
import org.junit.Assert;
import com.DriverFactory.DriverFactory;
import com.Pages.*;
import StoringLocalValue.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class VerifyingAllStatusHistory {

	private LoginPage Login = new LoginPage(DriverFactory.getDriver());
	private MenuNavBar Menu = new MenuNavBar(DriverFactory.getDriver());
	private VerifyingHistoryStatus History = new VerifyingHistoryStatus(DriverFactory.getDriver());
	public String Appname;
	public String CompanyName;
	public String AEmail;
	public String Env;
	public String Password;
	public String Browser;

	public void GetFromWindowFormApp() {

		Appname = ScenarioContext.get("AppName");
		AEmail = ScenarioContext.get("AEmail");
		Password = ScenarioContext.get("Password");
		Env = ScenarioContext.get("Environment");
		CompanyName = ScenarioContext.get("CompanyName");
	}

	@Given("Open the Erequisition Application")
	public void open_the_erequisition_application() {
		GetFromWindowFormApp();
		if (Env.equals("QA")) {
			DriverFactory.getDriver().get("https://qaerequisition.e-bizsoft.net");
		} else if (Env.equals("STAGING")) {
			DriverFactory.getDriver().get("https://ereqstg.e-bizsoft.net");
		} else {
			Assert.fail("wrong Environment :" + Env);
		}
	}

	@Then("Enter the Credentials and login")
	public void enter_the_credentials_and_login() throws InterruptedException {
		Login.EnterUsernameandpassword(AEmail, Password);
		Login.LoginBTN();
		Thread.sleep(2000);
		Login.SelectProduct(Appname);
		Login.SelectCompany(CompanyName);
		Login.AfterLoginDoSomewait(Appname);
	}

	@Then("Open the history page")
	public void open_the_history_page() throws InterruptedException {
		Menu.HistoryMenu();
	}

	@Then("check all status of txo trx standalone and verify them")
	public void check_all_status_of_txo_trx_standalone_and_verify_them() throws InterruptedException {
		String getApp = ScenarioContext.get("AppName");
		System.out.print(getApp);
		if (getApp.equals("TXO")) {
			Thread.sleep(2000);
			History.GetCountForTxoStatus("Under Review");
			History.GetCountForTxoStatus("Rejected");
			History.GetCountForTxoStatus("PO Created");
			History.GetCountForTxoStatus("Cancelled");
			History.GetCountForTxoStatus("Closed");
		}

		else if (getApp.equals("TRX")) {
			Thread.sleep(2000);
			History.GetCountForTxoStatus("Under Review");
			History.GetCountForTxoStatus("Rejected");
			History.GetCountForTxoStatus("PO Synced");
			History.GetCountForTxoStatus("Cancelled");
			History.GetCountForTxoStatus("Closed");
			History.GetCountForTxoStatus("Pending Sync");
			History.GetCountForTxoStatus("Sync Error");
			History.GetCountForTxoStatus("PO in Editing");

		} else if (getApp.equals("Standalone")) {
			Thread.sleep(2000);
			History.GetCountForTxoStatus("Under Review");
			History.GetCountForTxoStatus("Rejected");
			History.GetCountForTxoStatus("PO Created");
			History.GetCountForTxoStatus("Cancelled");
			History.GetCountForTxoStatus("Closed");

		}
	}

	@Then("check the status to Mine from everyone")
	public void check_the_status_to_mine_from_everyone(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			History.DisablityCheckOfDDLHistoryPageForCanseeEveryoneHistory();
			Thread.sleep(10000);
			History.DisablityCheckofSeeEveryOneHistory(mapdata.get("UserName"));
		}
	}

}
