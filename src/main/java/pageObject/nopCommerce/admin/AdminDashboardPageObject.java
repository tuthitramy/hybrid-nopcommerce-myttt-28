package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.server.handler.SendKeys;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminDashboardPageUIs;

public class AdminDashboardPageObject extends BasePage {
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, AdminDashboardPageUIs.DASHBOARD_HEADER);
	}

}