package pageObjects.nopCommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.CompareProductPageUI;

public class UserCompareProductListPageObject extends BasePage {
	private WebDriver driver;

	public UserCompareProductListPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public boolean areProductNameDisplayed() {
		List<WebElement> numCompareProducts = getListElement(driver, CompareProductPageUI.PRODUCT_NAME);
		int elementSize = numCompareProducts.size();
		System.out.println("Size: " + elementSize);

		if (elementSize > 0) {
			for (int i = 1; i <= elementSize; i++) {
				String productName = getTextElement(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME,
						String.valueOf(i));
				System.out.println("Product name: " + productName);
				sleepInSecond(5);
			}
			return true;

		} else {
			return false;
		}
	}

	public void clickToClearListButton() {
		waitForElementVisible(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		sleepInSecond(10);
	}

}
