package pageObjects.nopCommerce.user;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;
import utilities.DataHelper;

public class UserShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;

	}

	public void clickToEditTextlink() {
		waitForElementVisible(driver, ShoppingCartPageUI.EDIT_TEXTLINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_TEXTLINK);
	}

	public boolean isProductNameDisplayed(String productName) {
		return isElementDisplayed(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_NAME_BY_NAME_AT_SHOPPING_CART_PAGE,
				productName);
	}

	public String getProductPriceByProductNameAtShoppingCartPage(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_PRICE_BY_NAME_AT_SHOPPING_CART_PAGE, productName);
		return getTextElement(driver, ShoppingCartPageUI.DYNAMIC_PRICE_BY_NAME_AT_SHOPPING_CART_PAGE, productName);
	}

	public void clickToRemoveButton() {
		waitForElementVisible(driver, ShoppingCartPageUI.REMOVE_BUTTON);
		clickToElementByJS(driver, ShoppingCartPageUI.REMOVE_BUTTON);
	}

	public void inputToQuantityField(int number) {
		waitForElementVisible(driver, ShoppingCartPageUI.QUANTITY_TEXTBOX);
		sendkeysToElement(driver, ShoppingCartPageUI.QUANTITY_TEXTBOX, String.valueOf(number));
	}

	public float getTotalPrice() {
		waitForElementVisible(driver, ShoppingCartPageUI.TOTAL_PRICE);
		String productPrice = getTextElement(driver, ShoppingCartPageUI.TOTAL_PRICE);
		return Float.parseFloat(productPrice.replace("$", "").replace(",", "").trim());
	}

	public String getProductPrice() {
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_PRICE);
		return getTextElement(driver, ShoppingCartPageUI.PRODUCT_PRICE);
	}

	public void clickToUpdateShoppingCartButton() {
		waitForElementVisible(driver, ShoppingCartPageUI.UPDATE_SHOPPING_CART_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.UPDATE_SHOPPING_CART_BUTTON);
	}

	public void clickToAcceptTermServiceCheckbox() {
		waitForElementVisible(driver, ShoppingCartPageUI.ACCEPT_TERM_OF_SERVICE_CHECKBOX);
		clickToElement(driver, ShoppingCartPageUI.ACCEPT_TERM_OF_SERVICE_CHECKBOX);

	}

	public void clickToContinueButtonByOnClickType(String onclickType) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_CONTINUE_BUTTON_AT_BILLING_ADDRESS_BY_ONCLICK_TYPE,
				onclickType);
		clickToElement(driver, ShoppingCartPageUI.DYNAMIC_CONTINUE_BUTTON_AT_BILLING_ADDRESS_BY_ONCLICK_TYPE,
				onclickType);
	}

	public String getSuccessOrderMessage() {
		waitForElementVisible(driver, ShoppingCartPageUI.SUCCESS_ORDER_MESSAGE);
		return getTextElement(driver, ShoppingCartPageUI.SUCCESS_ORDER_MESSAGE);
	}

	public void clickToCreditCardPaymentMethod() {
		waitForElementVisible(driver, ShoppingCartPageUI.CREDIT_CARD_PAYMENT_METHOD);
		clickToElement(driver, ShoppingCartPageUI.CREDIT_CARD_PAYMENT_METHOD);
	}

	public void inputToCardNumberField() {
		String cardNumber = DataHelper.getDataHelper().getRandomCardNumber();
		waitForElementVisible(driver, ShoppingCartPageUI.CARD_NUMBER_TEXTBOX);
		sendkeysToElement(driver, ShoppingCartPageUI.CARD_NUMBER_TEXTBOX, cardNumber);
		selectItemByTextInDefaultDropdown(driver, ShoppingCartPageUI.EXPIRED_YEAR_DROPDOWN, "2025");
		userInputToTextboxByID(driver, "CardCode", "123");
		clickToContinueButtonByOnClickType("PaymentInfo.save()");
		// Check if the error message element is displayed
		boolean isErrorMessageDisplayed = isElementDisplayed(driver, ShoppingCartPageUI.WRONG_CARD_NUMBER_MESS);
		if (!isErrorMessageDisplayed) {
			clickToContinueButtonByOnClickType("PaymentInfo.save()");
		} else {
			// Retry entering the card number until the error message is not displayed
			while (isErrorMessageDisplayed) {
				try {
					WebElement cardNumberTextbox = getWebElement(driver, ShoppingCartPageUI.CARD_NUMBER_TEXTBOX);
					cardNumberTextbox.clear();
					selectRandomDropdownButton(driver, ShoppingCartPageUI.CREDIT_CARD_TYPE_DROPDOWN);
					String newCardNumber = DataHelper.getDataHelper().getRandomCardNumber();
					cardNumberTextbox.sendKeys(newCardNumber);
					clickToContinueButtonByOnClickType("PaymentInfo.save()");
					sleepInSecond(3);
					isErrorMessageDisplayed = isElementDisplayed(driver, ShoppingCartPageUI.WRONG_CARD_NUMBER_MESS);
				} catch (StaleElementReferenceException e) {
					// Handle the stale element exception by finding the element again
					continue; // Continue the loop
				}
			}
		}
	}

	public void selectNewAddress() {
		waitForElementVisible(driver, ShoppingCartPageUI.BILLING_ADDRESS_DROPDOWN);
		selectItemByTextInDefaultDropdown(driver, ShoppingCartPageUI.BILLING_ADDRESS_DROPDOWN, "New Address");

	}

	public void selectNextDayAirRadiobutton() {
		waitForElementVisible(driver, ShoppingCartPageUI.NEXT_DAY_AIR_RADIOBUTTON);
		checktoDefaultCheckboxOrRadio(driver, ShoppingCartPageUI.NEXT_DAY_AIR_RADIOBUTTON);

	}

	public void clickToCheckOutButton() {
		waitForElementVisible(driver, ShoppingCartPageUI.CHECK_OUT_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.CHECK_OUT_BUTTON);
	}

	public String getOrderNumberTextAtOrderInforPage() {
		waitForElementVisible(driver, ShoppingCartPageUI.ORDER_NUMBER_AT_ORDER_INFOR);
		return getTextElement(driver, ShoppingCartPageUI.ORDER_NUMBER_AT_ORDER_INFOR).replace("ORDER ", "").replace("#",
				"");

	}

	public String getProductTitleAtShoppingCartPage() {
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_TITLE);
		return getTextElement(driver, ShoppingCartPageUI.PRODUCT_TITLE);

	}

	public boolean isProductUndisplayed() {
		return isElementUndisplayed(driver, ShoppingCartPageUI.PRODUCT_TITLE);
	}

	public String selectRandomShippingMethod() {
		WebElement clickedRadiobutton = selectRandomRadiobutton(driver,
				ShoppingCartPageUI.SHIPPING_METHOD_RADIO_BUTTON);
		String clickedRadioButtonxpath = getElementXPath(driver, clickedRadiobutton);
		String clickedTextRadioButtonXpath = "xpath=" + clickedRadioButtonxpath + "//following-sibling::label";
		String paymentMethodClicked = getTextElement(driver, clickedTextRadioButtonXpath);
		return paymentMethodClicked;

	}

	public boolean isOrderNumberDisplayedAtOrderCompletedPage() {
		return isElementDisplayed(driver, ShoppingCartPageUI.ORDER_NUMBER_AT_ORDER_COMPLETED_PAGE);

	}

	public String getShippingMethodAtConfirmInformation() {
		waitForElementVisible(driver, ShoppingCartPageUI.SHIPPING_METHOD_AT_CONFIRM_INFORMATION);
		return getTextElement(driver, ShoppingCartPageUI.SHIPPING_METHOD_AT_CONFIRM_INFORMATION);
	}	
	
}
