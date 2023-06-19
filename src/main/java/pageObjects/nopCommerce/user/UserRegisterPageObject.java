package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.nopCommerce.user.RegisterPageUI;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public boolean isRegisterPageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_TEXT_HEADER);
		return isElementDisplayed(driver, RegisterPageUI.REGISTER_TEXT_HEADER);
	}

	public String getRegisterSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getTextElement(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

}
