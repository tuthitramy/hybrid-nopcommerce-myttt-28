package pageObjects.nopCommerce.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage {

	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getSearchResultMessage() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_RESULT_MESSAGE);
		return getTextElement(driver, SearchPageUI.SEARCH_RESULT_MESSAGE);
	}

	public String[] getItemsInSearchResult(WebDriver driver) {
		List<WebElement> itemList = getListElement(driver, SearchPageUI.ITEM_TITLE_AT_SEARCH_RESULT);
		List<String> itemTitleList = new ArrayList<>();
		for (WebElement item : itemList) {
			String itemTitle = item.getText();
			System.out.println(itemTitle);
			itemTitleList.add(itemTitle);

		}
		String[] itemTitleArray = itemTitleList.toArray(new String[0]);
		return itemTitleArray;

	}

	public boolean isProductContainsInSearchResult(String product) {
		String[] itemTitles = getItemsInSearchResult(driver);

		for (String itemTitle : itemTitles) {
			if (itemTitle.contains(product)) {
				return true; // Product found in search result
			}
		}

		return false; // Product not found in search result
	}

	public void checkToCheckboxByText(String text) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_CHECKBOX_BY_TEXT, text);
		checktoDefaultCheckboxOrRadio(driver, SearchPageUI.DYNAMIC_CHECKBOX_BY_TEXT, text);
	}

	public void selectValueByTextFromDropdownByText(String dropdownName, String valueSelect) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_DROPDOWN_BY_TEXT, dropdownName);
		selectItemByTextInDefaultDropdown(driver, SearchPageUI.DYNAMIC_DROPDOWN_BY_TEXT, valueSelect, dropdownName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON_AT_PRODUCTPAGE);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON_AT_PRODUCTPAGE);
	}

}
