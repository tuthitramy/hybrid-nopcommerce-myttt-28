package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class UserLoginPageObject extends BasePage {

	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLoginSuccessful() {
		return isElementDisplayed(driver, UserBasePageUI.DYNAMIC_PAGE_AT_HEADER_AREA, "ico-logout");
	}

}
