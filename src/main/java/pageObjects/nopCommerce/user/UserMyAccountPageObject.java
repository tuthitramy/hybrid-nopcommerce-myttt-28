package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyAccountPageUI;

public class UserMyAccountPageObject extends BasePage {
	private WebDriver driver;

	public UserMyAccountPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void openPageOfMyAccountListByName(String pageName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_PAGE_BY_TEXT, pageName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_PAGE_BY_TEXT, pageName);

	}

	public void selectRandomGender() {
		selectRandomRadiobutton(driver, MyAccountPageUI.GENDER_RADIOBUTTON);
	}

	public void selectRandomDateOfBirthDay() {
		selectRandomDropdownButton(driver, MyAccountPageUI.DATE_OF_BIRTH_DAY_DROPDOWN);
	}

	public void selectRandomMonthOfBirthDay() {
		selectRandomDropdownButton(driver, MyAccountPageUI.DATE_OF_BIRTH_MONTH_DROPDOWN);
	}

	public void selectRandomYearOfBirthDay() {
		selectRandomDropdownButton(driver, MyAccountPageUI.DATE_OF_BIRTH_YEAR_DROPDOWN);
	}

	public void selectRandomProvince() {
		selectRandomDropdownButton(driver, MyAccountPageUI.ADDRESSES_PROVINCE_DROPDOWN);
	}

	public boolean isReviewAddedSuccess(String text) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TITLE, text);
		return isElementDisplayed(driver, MyAccountPageUI.REVIEW_TITLE, text);
	}

	public void clickToPageOnTheLeftMenuByText(String text) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_TEXTLINK_AT_LEFT_MENU, text);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_TEXTLINK_AT_LEFT_MENU, text);
	}

	public void clickToOrderTextLink() {
		waitForElementVisible(driver, MyAccountPageUI.ORDER_TEXTLINK);
		clickToElement(driver, MyAccountPageUI.ORDER_TEXTLINK);

	}

}
