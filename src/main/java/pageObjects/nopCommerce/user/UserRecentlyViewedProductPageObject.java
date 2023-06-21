package pageObjects.nopCommerce.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.RecentlyViewedProductPageUI;

public class UserRecentlyViewedProductPageObject extends BasePage {

	private WebDriver driver;

	public UserRecentlyViewedProductPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public List<String> getProductNameListAtRecentlyViewdPage() {
		List<WebElement> viewedRecentlyproductNameElementList = new ArrayList<>();
		List<String> viewedRecentlyproductNameList = new ArrayList<>();
		viewedRecentlyproductNameElementList = getListElement(driver,
				RecentlyViewedProductPageUI.PRODUCT_NAME_AT_RECENTLY_VIEWED_PAGE);
		for (WebElement product : viewedRecentlyproductNameElementList) {
			viewedRecentlyproductNameList.add(product.getText());

		}
		return viewedRecentlyproductNameList;

	}

}
