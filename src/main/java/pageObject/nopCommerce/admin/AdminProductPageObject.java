package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.server.handler.SendKeys;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminProductPageUI;

public class AdminProductPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementVisible(driver, AdminProductPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductPageUI.SEARCH_BUTTON);
	}

	public void clickToGoButton() {
		waitForElementVisible(driver, AdminProductPageUI.SKU_GO_BUTTON);
		clickToElement(driver, AdminProductPageUI.SKU_GO_BUTTON);
	}

	public boolean isProductDetailPageDisplayed() {
		return isElementDisplayed(driver, AdminProductPageUI.PRODUCT_DETAIL_TITLE);
	}

	public boolean isProductNameDisplayed(String value) {
		return isElementDisplayed(driver, AdminProductPageUI.DYNAMIC_PRODUCTNAME_TEXTBOX_BY_VALUE, value);
	}

}