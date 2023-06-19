package pageObjects.nopCommerce.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commons.BasePage;
import commons.NopCommercePageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;
import pageUIs.nopCommerce.user.ProductPageUI;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;
import pageUIs.nopCommerce.user.UserBasePageUI;
import utilities.DataHelper;

public class UserProductPageObject extends BasePage {
	private WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void selectRandomProductPage() {
		selectRandomItem(driver, UserBasePageUI.TITLE_AT_HEADER_MENU);
		List<WebElement> elements = getListElement(driver, ProductPageUI.SUB_CATEGORY_ITEM);
		if (!elements.isEmpty()) {
			selectRandomItem(driver, ProductPageUI.SUB_CATEGORY_ITEM);
		}
	}

	public void selectRandomProduct() {
		waitForElementVisible(driver, ProductPageUI.PRODUC_TITLE);
		selectRandomItem(driver, ProductPageUI.PRODUC_TITLE);
	}

	public void clickToSubCategoryMenuByText(String text) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_SUB_CATEGORY_BY_TEXT, text);
		clickToElement(driver, ProductPageUI.DYNAMIC_SUB_CATEGORY_BY_TEXT, text);
	}

	public boolean areNumberOfProductDisplayedCorrectByPageSize(int pageSize) {
		List<WebElement> productList = getListElement(driver, ProductPageUI.PRODUC_TITLE);
		int numberOfProduct = productList.size();
		System.out.println("pagesize: " + pageSize);
		System.out.println("number of products: " + numberOfProduct);

		if (numberOfProduct <= pageSize) {
			return true;
		} else {
			return false;
		}

	}

	public void clickToProductMenuTitleByText(String text) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_PRODUCT_MENU_TITLE_BY_TEXT, text);
		clickToElement(driver, ProductPageUI.DYNAMIC_PRODUCT_MENU_TITLE_BY_TEXT, text);
	}

	public void selectPageSizeByText(String text) {
		waitForElementVisible(driver, ProductPageUI.PAGESIZE_BY_TEXT, text);
		selectItemByTextInDefaultDropdown(driver, ProductPageUI.PAGESIZE_BY_TEXT, text);
		sleepInSecond(5);
	}

	public boolean isNextOrPreviousIconDisplayed() {
		String currentPage = getTextElement(driver, ProductPageUI.CURRENT_PAGE);
		int currentPageNumber = Integer.parseInt(currentPage);
		System.out.println("current Page: " + currentPageNumber);
		if (currentPageNumber == 1) {
			return isElementDisplayed(driver, ProductPageUI.NEXT_PAGE_ICON);

		} else {
			return isElementDisplayed(driver, ProductPageUI.PREVIOUS_PAGE_ICON);
		}
	}

	public void clickToPageNumberByText(String pageNumber) {
		try {
			waitForElementVisible(driver, ProductPageUI.DYNAMIC_PAGE_NUMBER_BY_TEXT, pageNumber);
			clickToElement(driver, ProductPageUI.DYNAMIC_PAGE_NUMBER_BY_TEXT, pageNumber);
			sleepInSecond(5);
		} catch (Exception e) {
			System.out.println("page number is invalid");

		}
	}

	public boolean isPaginationDisplayed() {
		List<WebElement> elements = getListElement(driver, ProductPageUI.PAGINATION);
		if (elements.size() != 0) {
			return true;
		} else {
			return false;
		}

	}

	public int getTextboxElementCount() {
		return getElementTypeCount(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_TEXTBOX);
	}

	public void inputIntoTextBoxByIndex() {
		int elementSize = getTextboxElementCount();
		for (int i = 1; i <= elementSize; i++) {
			System.out.println(i);
			WebElement element = getWebElement(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_TEXTBOX_BY_INDEX,
					String.valueOf(i));
			String type = element.getAttribute("type");

			if ("text".equals(type)) {
				sendkeysToElement(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_TEXTBOX_BY_INDEX,
						DataHelper.getDataHelper().getLastName() + String.valueOf(i), String.valueOf(i));
			} else if ("email".equals(type)) {
				sendkeysToElement(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_TEXTBOX_BY_INDEX,
						DataHelper.getDataHelper().getEmailAddress(), String.valueOf(i));
				System.out.println(DataHelper.getDataHelper().getEmailAddress());
			}
		}
	}

	public int getDropdownElementCount() {
		return getElementTypeCount(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_DROPDOWN);
	}

	public void selectRandomInDropdown() {
		int elementSize = getDropdownElementCount();
		for (int i = 1; i <= elementSize; i++) {
			selectRandomDropdownButton(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_DROPDOWN_BY_INDEX,
					String.valueOf(i));

		}
	}

	public int getRadiobuttonElementCount() {
		int a = getElementTypeCount(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_RADIOBUTTON);
		return a;
	}

	public void selectRandomInRadioField() {
		int elementSize = getDropdownElementCount();
		for (int i = 3; i <= elementSize + 2; i++) {
			selectRandomRadiobutton(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_RADIOBUTTON_BY_INDEX,
					String.valueOf(i));

		}
	}

	public int getImageElementCount() {
		return getElementTypeCount(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_IMAGE);
	}

	public void selectRandomImage() {
//		selectRandomItem(driver, HomePageUI.DYNAMIC_DETAIL_PRODUCT_IMAGE);

		List<WebElement> options = getListElement(driver, ProductPageUI.DYNAMIC_DETAIL_PRODUCT_IMAGE);
		if (options.isEmpty()) {
			// Handle the case where no links are found
			throw new IllegalStateException("No links found in the header menu.");
		}
		Random rand = new Random();
		int list = rand.nextInt(options.size());
		options.get(list).click();
	}

	public void selectRandomDate() {
		String dateFormatPattern = "MM/dd/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Define the range of dates
		LocalDate startDate = currentDate;
		LocalDate endDate = currentDate.plusDays(365);
		LocalDate randomStartDate;
		LocalDate randomEndDate;
		WebElement startDateElement = getWebElement(driver, ProductPageUI.START_DATE_FIELD);
		WebElement endDateElement = getWebElement(driver, ProductPageUI.END_DATE_FIELD);
		do {
			// Generate random dates within the range
			randomStartDate = generateRandomDate(startDate, endDate);
			randomEndDate = generateRandomDate(startDate, endDate);

			// Check if start date is after end date
			if (randomStartDate.isAfter(randomEndDate)) {
				// Randomize dates again
				continue;
			}

			// Format the random dates
			String formattedRandomStartDate = dateFormat.format(java.sql.Date.valueOf(randomStartDate));
			String formattedRandomEndDate = dateFormat.format(java.sql.Date.valueOf(randomEndDate));

			// Enter the random dates in both start and end date fields
			startDateElement.sendKeys(formattedRandomStartDate);
			endDateElement.sendKeys(formattedRandomEndDate);

		} while (randomStartDate.isAfter(randomEndDate));
	}

	private LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
		long startEpochDay = startDate.toEpochDay();
		long endEpochDay = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomEpochDay);
	}

	public void inputToRequiredField() {
		if (getTextboxElementCount() == 0 && getRadiobuttonElementCount() == 0 && getDropdownElementCount() == 0
				&& getDropdownElementCount() == 0 && getElementSize(driver, ProductPageUI.START_DATE_FIELD) == 0
				&& getElementSize(driver, ProductPageUI.END_DATE_FIELD) == 0) {
			return;

		}
		if (getTextboxElementCount() != 0) {
			inputIntoTextBoxByIndex();

		}
		if (getDropdownElementCount() != 0) {
			selectRandomInDropdown();

		}
		if (getRadiobuttonElementCount() != 0) {
			selectRandomInRadioField();
		}
		if (getImageElementCount() != 0) {
			selectRandomImage();

		}

	}

	public String getAddToWishlistSuccessMessage() {
		while (getElementSize(driver, ProductPageUI.ADD_TO_WISHLIST_BUTTON) == 0) {
			selectRandomProductPage();
			selectRandomProduct();
		}

		clickToElement(driver, ProductPageUI.ADD_TO_WISHLIST_BUTTON);

		// Retrieve the success message immediately after clicking the element
		String successMessage = getBarNotificationSuccessMessage(driver);

		if (getElementSize(driver, ProductPageUI.BAR_NOTIFICATION_ERROR_MESSAGE) != 0) {
			clickToCloseButtonAtBarNotificationMessage(driver);
			inputToRequiredField();
			clickToElement(driver, ProductPageUI.ADD_TO_WISHLIST_BUTTON);

			// Retrieve the success message after handling errors
			successMessage = getBarNotificationSuccessMessage(driver);
		}

		return successMessage;

	}

	public String getAddToCartSuccessMessage() {

		while (getElementSize(driver, ProductPageUI.ADD_TO_CART_BUTTON) == 0
				|| getElementSize(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, "Rent") == 0) {
			selectRandomProductPage();
			selectRandomProduct();
		}
		String successMessage = null;
		if (isElementDisplayed(driver, ProductPageUI.ADD_TO_CART_BUTTON) == true) {
			clickToElement(driver, ProductPageUI.ADD_TO_CART_BUTTON);
			if (getElementSize(driver, ProductPageUI.BAR_NOTIFICATION_ERROR_MESSAGE) != 0) {
				clickToCloseButtonAtBarNotificationMessage(driver);
				inputToRequiredField();
				clickToElement(driver, ProductPageUI.ADD_TO_CART_BUTTON);
				// Retrieve the success message immediately after clicking the element
				successMessage = getBarNotificationSuccessMessage(driver);

			}

		} else if (isDynamicElementDisplayed(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, "Rent") == true) {
			if (getElementSize(driver, ProductPageUI.START_DATE_FIELD) != 0
					|| getElementSize(driver, ProductPageUI.END_DATE_FIELD) != 0) {
				selectRandomDate();
			}
			clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, "Rent");
			successMessage = getBarNotificationSuccessMessage(driver);

		}
		return successMessage;

	}

	public int getTitleLinkElementCountAtProductPage() {
		return getElementSize(driver, ProductPageUI.PRODUCT_TITLE_LINK_AT_PRODUCT_PAGE);

	}

	public int getAddToCompareListElementCount() {
		int a = getElementTypeCount(driver, ProductPageUI.ADD_TO_COMPARE_LIST_BUTTON_AT_PRODUCT_PAGE);
		return a;
	}

	public void clickRandomCompareButtons(WebDriver driver, int numButtonsToClick) {
		int elementSize = getAddToCompareListElementCount();
		// Create a list to store the XPaths of the web elements
		List<String> elementXPaths = new ArrayList<>();
		if (elementSize < numButtonsToClick) {
			throw new IllegalArgumentException("Number of buttons to click is greater than the available buttons.");
		}
		for (int i = 1; i <= elementSize; i++) {

			String xpath = getDynamicXpath(ProductPageUI.DYNAMIC_ADD_TO_WISHLIST_BY_INDEX_AT_PRODUCT_PAGE,
					String.valueOf(i));
			elementXPaths.add(xpath);

		}
		Collections.shuffle(elementXPaths);
		System.out.println("xpath list:");
		System.out.println(elementXPaths);
		for (int i = 0; i < numButtonsToClick && i < elementXPaths.size(); i++) {
			String randomXPath = elementXPaths.get(i);
			System.out.println("randomXpath: " + randomXPath);
			clickToElement(driver, randomXPath);
			Assert.assertEquals(getTextElement(driver, HomePageUI.BAR_NOTIFICATION_SUCCESS_MESSAGE),
					"The product has been added to your product comparison");
			sleepInSecond(5);
		}

	}

	public List<String> getNumRandomProductName(int numberProductClick) {
		List<String> productNameList = new ArrayList<>(); // Move the list declaration outside the loop

		for (int i = 1; i <= numberProductClick; i++) {
			selectRandomProductPage();
			sleepInSecond(5);
			selectRandomProduct();
			String productNameAtDetailPage = getTextElement(driver, ProductPageUI.PRODUCT_NAME_AT_PRODUCT_DETAIL_PAGE);
			System.out.println("Product " + i + ": " + productNameAtDetailPage);
			productNameList.add(productNameAtDetailPage);
		}
		return productNameList;
	}

	public List<String> getLastNumberProductName(List<String> productNameList, int lastProductNameNumber) {
		List<String> lastNumberProductNameList = new ArrayList<>();
		// Print the last three product names
		int startIndex = Math.max(0, productNameList.size() - lastProductNameNumber); // Get the starting index
		for (int i = startIndex; i < productNameList.size(); i++) {
			lastNumberProductNameList.add(productNameList.get(i));
		}
		Collections.reverse(lastNumberProductNameList);
		return lastNumberProductNameList;
	}

	public UserShoppingCartPageObject clickToShoppingCartTextlink() {
		waitForElementVisible(driver, HomePageUI.SHOPPING_CART_TEXTLINK);
		clickToElement(driver, HomePageUI.SHOPPING_CART_TEXTLINK);
		return NopCommercePageGeneratorManager.getUserShoppingCartPage(driver);
	}

	public void clickToBuildYourComputerProduct() {
		waitForElementVisible(driver, ProductPageUI.COMPUTER_TEXTLINK);
		clickToElement(driver, ProductPageUI.COMPUTER_TEXTLINK);
		waitForElementVisible(driver, ProductPageUI.DESKTOP_PRODUCT);
		clickToElement(driver, ProductPageUI.DESKTOP_PRODUCT);
		waitForElementVisible(driver, ProductPageUI.BUILD_YOUR_COMPUTER_PRODUCT);
		clickToElement(driver, ProductPageUI.BUILD_YOUR_COMPUTER_PRODUCT);
	}

	public void clickToAppleMacbook13Pro() {
		waitForElementVisible(driver, ProductPageUI.COMPUTER_TEXTLINK);
		clickToElement(driver, ProductPageUI.COMPUTER_TEXTLINK);
		waitForElementVisible(driver, ProductPageUI.NOTEBOOKS_TEXTLINK);
		clickToElement(driver, ProductPageUI.NOTEBOOKS_TEXTLINK);
		waitForElementVisible(driver, ProductPageUI.APPLE_MACBOOK_TEXTLINK);
		clickToElement(driver, ProductPageUI.APPLE_MACBOOK_TEXTLINK);
	}

	public void selectToProcessorField(String textValue) {
		waitForElementVisible(driver, ProductPageUI.PROCESSOR_DROPDOWN);
		selectItemByTextInDefaultDropdown(driver, ProductPageUI.PROCESSOR_DROPDOWN, textValue);
	}

	public void selectToRamField(String textValue) {
		waitForElementVisible(driver, ProductPageUI.RAM_DROPDOWN);
		selectItemByTextInDefaultDropdown(driver, ProductPageUI.RAM_DROPDOWN, textValue);
	}

	public void selectHDDField(String... textValue) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_HDD_RADIO_BUTTON_BY_TEXT, textValue);
		checktoDefaultCheckboxOrRadio(driver, ProductPageUI.DYNAMIC_HDD_RADIO_BUTTON_BY_TEXT, textValue);
	}

	public void selectOSField(String... textValue) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_OS_RADIO_BUTTON_BY_TEXT, textValue);
		checktoDefaultCheckboxOrRadio(driver, ProductPageUI.DYNAMIC_OS_RADIO_BUTTON_BY_TEXT, textValue);
	}

	public void sendKeyToQuantityTextbox(String num) {
		waitForElementVisible(driver, ProductPageUI.QUANTITY_PRODUCT_TEXTBOX);
		sendkeysToElement(driver, ProductPageUI.QUANTITY_PRODUCT_TEXTBOX, num);
	}

	public void clickToUpdateButton() {
		waitForElementClickable(driver, ProductPageUI.UPDATE_BUTTON);
		clickToElement(driver, ProductPageUI.UPDATE_BUTTON);
	}

	public String getProducPriceAtProductDetailPage() {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_PRICE_AT_DETAIL_PAGE);
		return getTextElement(driver, ProductPageUI.PRODUCT_PRICE_AT_DETAIL_PAGE);

	}

	public void selectSortTypeByText(String sortType) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_SORT_TYPE_BY_TEXT, sortType);
		selectItemByTextInDefaultDropdown(driver, ProductPageUI.DYNAMIC_SORT_TYPE_BY_TEXT, sortType);

	}

	public boolean areProductNameSortedAscending(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUC_TITLE);
		return isDataStringSortedAscendingString(driver, ProductPageUI.PRODUC_TITLE);
	}

	public boolean areProductNameSortedDescending(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUC_TITLE);
		return isDataStringSortedDescendingString(driver, ProductPageUI.PRODUC_TITLE);
	}

	public boolean areProductPriceSortedDescending(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_PRICE);
		return isDataFloatSortedDescending(driver, ProductPageUI.PRODUCT_PRICE);
	}

	public boolean areProductPriceSortedAscending(WebDriver driver) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_PRICE);
		return isDataFloatSortedAscending(driver, ProductPageUI.PRODUCT_PRICE);
	}

	public String getProductNameAtProductDetailPage() {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_NAME_AT_PRODUCT_DETAIL_PAGE);
		return getTextElement(driver, ProductPageUI.PRODUCT_NAME_AT_PRODUCT_DETAIL_PAGE);
	}

	public boolean isProductUndisplayed() {
		return isElementUndisplayed(driver, ShoppingCartPageUI.PRODUCT_TITLE);
	}

}
