package pageObject.nopCommerce.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerPageUI;

public class AdminCustomerPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerPageObject(WebDriver driver) {
		// TODO Auto-generated construcr stub
		this.driver = driver;
	}

	public void clickToAddNewCustomerButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.ADD_NEW_BUTTON);

	}

	public void selectRandomGender() {
		waitForElementVisible(driver, AdminCustomerPageUI.RANDOM_GENDER_RADIO_BUTTON);
		selectRandomRadiobutton(driver, AdminCustomerPageUI.RANDOM_GENDER_RADIO_BUTTON);

	}

	private String generateRandomDateOfBirth(String dateFormatPattern) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormatPattern);

		// Define the range of dates
		LocalDate startDate = LocalDate.of(1900, 1, 1);
		LocalDate endDate = LocalDate.now();

		// Generate a random date within the range
		long startEpochDay = startDate.toEpochDay();
		long endEpochDay = endDate.toEpochDay();
		long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
		LocalDate randomDateOfBirth = LocalDate.ofEpochDay(randomEpochDay);

		// Format the random date
		return randomDateOfBirth.format(dateFormatter);
	}

	public void selectRandomDateOfBirth() {
		String dateFormatPattern = "MM/dd/yyyy";
		WebElement dateOfBirthTextbox = getWebElement(driver, AdminCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);

		// Generate a random date of birth
		String randomDateOfBirth = generateRandomDateOfBirth(dateFormatPattern);

		// Enter the random date of birth into the text box
		dateOfBirthTextbox.sendKeys(randomDateOfBirth);
	}

	public void clickToDeleteRoleButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.DELETE_BUTTON_CUSTOMER_ROLE);
		clickToElementByJS(driver, AdminCustomerPageUI.DELETE_BUTTON_CUSTOMER_ROLE);
	}

	public void selectCustomerRoles(String role) {

		// Find the select element
		WebElement selectElement = getWebElement(driver, AdminCustomerPageUI.CUSTOMER_ROLE_DROPDOWN);

		// Execute JavaScript to change the style attribute and make the element visible
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.display = 'block';", selectElement);

		// Now you can interact with the select element and select an option
		Select select = new Select(selectElement);
		select.selectByVisibleText(role);
	}

	public void inputToAdminCommentTextArea(String comment) {
		waitForElementVisible(driver, AdminCustomerPageUI.ADMIN_COMMENT_TEXTAREA);
		sendkeysToElement(driver, AdminCustomerPageUI.ADMIN_COMMENT_TEXTAREA, comment);
	}

	public void clickToSaveAndContinueEditButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.SAVE_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.SAVE_CONTINUE_EDIT_BUTTON);
	}

	public String getCreateSuccessMessage() {
		waitForElementVisible(driver, AdminCustomerPageUI.CREATE_SUCCESS_MESSAGE);
		return getTextElement(driver, AdminCustomerPageUI.CREATE_SUCCESS_MESSAGE);

	}

	public void clickToBackToCustomerListButton() {
		waitForElementClickable(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_LIST_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_LIST_BUTTON);
	}

	public void clickToBackToCustomerDetailsButton() {
		waitForElementClickable(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_DETAIL_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_DETAIL_BUTTON);
	}

	public void clickToCloseButtonAtMessage() {
		waitForElementVisible(driver, AdminCustomerPageUI.CLOSE_BUTTON_AT_SUCCESS_MESSAGE);
		clickToElementByJS(driver, AdminCustomerPageUI.CLOSE_BUTTON_AT_SUCCESS_MESSAGE);

	}

	public void clickToSearchButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.SEARCH_BUTTON);
	}

	public String[] getCustomerNameInSearchResult(WebDriver driver) {
		List<WebElement> customerList = getListElement(driver, AdminCustomerPageUI.CUSTOMER_NAME_TEXT);
		List<String> customerNameList = new ArrayList<>();
		for (WebElement customer : customerList) {
			String customerName = customer.getText();
			System.out.println(customerName);
			customerNameList.add(customerName);

		}
		String[] customerNameArray = customerNameList.toArray(new String[0]);
		return customerNameArray;

	}

	public boolean isCustomerNameContainsInSearchResult(WebDriver driver, String customerName) {
		String[] customerNames = getCustomerNameInSearchResult(driver);

		for (String customerNameAtSearchResult : customerNames) {
			if (customerNameAtSearchResult.contains(customerName)) {
				return true; // Product found in search result
			}
		}

		return false; // Product not found in search result
	}

	public void clickToEditButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.EDIT_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.EDIT_BUTTON);
	}

	public void clickToSaveButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.SAVE_BUTTON);
	}

	public void clickToAddNewAddressButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
	}

	public void selectRandomCountry() {
		waitForElementVisible(driver, AdminCustomerPageUI.RANDOM_COUNTRY);
		selectRandomDropdownButton(driver, AdminCustomerPageUI.RANDOM_COUNTRY);
	}

	public void clickToSaveAddressButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.SAVE_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.SAVE_ADDRESS_BUTTON);
	}

	public void clickToOpenAddressTable() {
		try {
			scrollToElement(driver, AdminCustomerPageUI.ADDRESS_OPEN_BUTTON);
			if (isElementDisplayed(driver, AdminCustomerPageUI.ADDRESS_OPEN_BUTTON) == true) {
				clickToElementByJS(driver, AdminCustomerPageUI.ADDRESS_OPEN_BUTTON);
			}

		} catch (NoSuchElementException e) {
			// Button not found, continue with the next step
		}

	}

	public void clickToDeleteAddressButton() {
		waitForElementVisible(driver, AdminCustomerPageUI.DELETE_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerPageUI.DELETE_ADDRESS_BUTTON);
	}

	public String getNoDataAddressMessage() {
		waitForElementVisible(driver, AdminCustomerPageUI.NO_DATA_ADDRESS_MESSAGE);
		return getTextElement(driver, AdminCustomerPageUI.NO_DATA_ADDRESS_MESSAGE);
	}

	public boolean isCustomerRowDisplayedByName(String customerName) {
		return isElementDisplayed(driver, AdminCustomerPageUI.DYNAMIC_CUSTOMER_ROW_BY_CUSTOMER_NAME, customerName);
	}

	public boolean isCustomerRowDisplayedByEmail(String email) {
		return isElementDisplayed(driver, AdminCustomerPageUI.DYNAMIC_ADDRESS_BY_CUSTOMER_EMAIL, email);
	}

}