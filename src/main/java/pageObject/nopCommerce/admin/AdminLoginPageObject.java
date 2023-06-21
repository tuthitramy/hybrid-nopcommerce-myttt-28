package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.server.handler.SendKeys;

import commons.BasePage;
import commons.NopCommercePageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return NopCommercePageGeneratorManager.getAdminDashboardPage(driver);
	}

}