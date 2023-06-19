package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.NopCommercePageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public UserWishlistPageObject openWishlistPage() {
		try {
			waitForElementClickable(driver, HomePageUI.WISHLIST_TEXTLINK);
			clickToElement(driver, HomePageUI.WISHLIST_TEXTLINK);
		} catch (Exception e) {
			System.out.println("Page has not wish list button");
		}
		return NopCommercePageGeneratorManager.getUserWishlistPage(driver);

	}

	public void clickToAddReviewTextlink() {
		waitForElementClickable(driver, HomePageUI.ADD_REVIEW_TEXTLINK);
		clickToElement(driver, HomePageUI.ADD_REVIEW_TEXTLINK);
	}

	public void sendKeysToReviewTextArea(String text) {
		waitForElementVisible(driver, HomePageUI.REVIEW_TEXT_AREA);
		sendkeysToElement(driver, HomePageUI.REVIEW_TEXT_AREA, text);
	}

}
