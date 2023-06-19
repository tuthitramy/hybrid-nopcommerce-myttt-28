package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.WishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void checkToAddToCartCheckbox() {
		waitForElementVisible(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
		clickToElement(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
	}

	public void clickToRemoveButton() {
		waitForElementVisible(driver, WishlistPageUI.REMOVE_PRODUCT_BUTTON);
		clickToElement(driver, WishlistPageUI.REMOVE_PRODUCT_BUTTON);
	}

	public String getProductTitleAtWishListPage() {
		waitForElementVisible(driver, WishlistPageUI.PRODUCT_NAME);
		return getTextElement(driver, WishlistPageUI.PRODUCT_NAME);

	}

	public boolean isProductNameUnDisplayedAtWishListPage() {
		return isElementUndisplayed(driver, WishlistPageUI.PRODUCT_NAME);
	}

}
