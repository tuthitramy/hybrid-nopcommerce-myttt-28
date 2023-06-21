package commons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.nopCommerce.admin.AdminBasePageUI;
import pageUIs.nopCommerce.admin.AdminProductPageUI;
import pageUIs.nopCommerce.user.MyAccountPageUI;
import pageUIs.nopCommerce.user.RegisterPageUI;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class BasePage extends UserBasePageUI {

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);

	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();

	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();

	}

	protected Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(5);

	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());

	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void senkeytoAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected void switchToExpectWindow(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowsIDs = driver.getWindowHandles();
		for (String id : allWindowsIDs) {
			driver.switchTo().window(id);

			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;

			}

		}

	}

	private By getByLocator(String locatorType) {
		By by = null;
		System.out.println("Locator type= " + locatorType);
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
				|| locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));

		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=")
				|| locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator Type is not supported");
		}
		return by;

	}

	protected String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);

		}
		return locatorType;

	}

	protected WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	protected WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValue)));
	}

	public List<WebElement> getListElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));

	}

	public List<WebElement> getListElement(WebDriver driver, String locatorType, String... dynamicValue) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValue)));

	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendkeysToElement(WebDriver driver, String locatorType, String inputValue) {
		getWebElement(driver, getDynamicXpath(locatorType)).clear();
		getWebElement(driver, getDynamicXpath(locatorType)).sendKeys(inputValue);

	}

	public void sendkeysToElement(WebDriver driver, String locatorType, String inputValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(inputValue);

	}

	public void sendkeysToElement(WebDriver driver, String locatorType, String[] text) {
		getWebElement(driver, getDynamicXpath(locatorType)).clear();
		getWebElement(driver, getDynamicXpath(locatorType)).sendKeys(text);

	}

	public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType)));
		select.selectByValue(textItem);
	}

	public void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType)));
		select.selectByVisibleText(textItem);
	}

	public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByValue(textItem);

	}

	protected void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);

	}

	protected String getSelectedItemInDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	protected String getAttributeValue(WebDriver driver, String locatorType, String name) {
		return getWebElement(driver, locatorType).getAttribute(name);

	}

	protected String getTextElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();

	}

	protected String getTextElement(WebDriver driver, String locatorType) {
		return getWebElement(driver, getDynamicXpath(locatorType)).getText();

	}

	protected String getCssValue(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getCssValue(locatorType);

	}

	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checktoDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected() != true) {
			element.click();
		}
	}

	protected void checktoDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected() != true) {
			element.click();
		}
	}

	protected void unchecktoDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected() == true) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}

	}

	protected boolean isDynamicElementDisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
		try {
			return getWebElement(driver, locatorType, dynamicValue).isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}

	}

	protected void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListElement(driver, locatorType);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but undisplayed");
			return true;

		} else {
			System.out.println("Element is in DOM and visible");
			return false;
		}

	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListElement(driver, locatorType, dynamicValue);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but undisplayed");
			return true;

		} else {
			System.out.println("Element is in DOM and visible");
			return false;
		}

	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();

	}

	protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();

	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();

	}

	protected void switchToIframeFrame(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));

	}

	protected void switchToDefaultContent(WebDriver driver, String xpathLocator) {
		driver.switchTo().defaultContent();

	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();

	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorType, String dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, getDynamicXpath(locatorType)));
	}

	protected void scrollToElement(WebDriver driver, String locatorType, String... dynamicValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType, String... DynamicValues) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, getDynamicXpath(locatorType, DynamicValues)));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;

		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType)));
		return status;
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}

		fullFileName = fullFileName.trim();
		getWebElement(driver, locator).sendKeys(fullFileName);
	}

	protected boolean areFileUploadedSuccess(WebDriver driver, String locator, String... fileNames) {
		boolean status = false;
		for (String fileName : fileNames) {
			if (isElementDisplayed(driver, locator, fileName)) {
				status = true;
			} else {
				return status;
			}
		}
		return status;
	}

	protected String getDirectorySlash(String folderName) {
		String separator = System.getProperty("file.separator");
		return separator + folderName + separator;
	}

	protected boolean isDataStringSortedAscendingString(WebDriver driver, String locator) {
		// Declare array list
		ArrayList<String> arrayList = new ArrayList<>();

		// Find all elements matching the condition (Name/Price/...)
		List<WebElement> elementList = getListElement(driver, locator);

		// Get the text of each element and add it to the Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("--------Data on UI:");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy to a new array list to SORT in Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// SORT DESC:
		Collections.sort(sortedList);
		System.out.println("-------- asc sorted data in code:-----------");
		for (String name : sortedList) {
			System.out.println(name);
		}

		// Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
		// result is incorrect
		return sortedList.equals(arrayList);
	}

	protected boolean isDataStringSortedDescendingString(WebDriver driver, String locator) {
		// Declare array list
		ArrayList<String> arrayList = new ArrayList<>();

		// Find all elements matching the condition (Name/Price/...)
		List<WebElement> elementList = getListElement(driver, locator);

		// Get the text of each element and add it to the Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("--------Data on UI:");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy to a new array list to SORT in Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}

		// SORT DESC:
		Collections.sort(sortedList);
		System.out.println("--------asc sorted data in code:-----------");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Reverse data to sort DESC
		Collections.reverse(sortedList);
		System.out.println("--------desc sorted data in code:-----------");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
		// result is incorrect
		return sortedList.equals(arrayList);
	}

	protected boolean isDataFloatSortedDescending(WebDriver driver, String priceLocator) {
		// Declare array list
		ArrayList<Float> arrayList = new ArrayList<>();

		// Find all elements matching the condition (Name/Price/...)
		List<WebElement> elementList = getListElement(driver, priceLocator);

		// Get the text of each element and add it to the Array List
		for (WebElement element : elementList) {
			if (element.getText().contains(",")) {
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
			} else if (element.getText().isEmpty()) {
				arrayList.add((float) 0);

			} else if (element.getText().contains("per 1 day(s)")) {
				arrayList.add(Float.parseFloat(
						element.getText().replace("$", "").replace(",", "").replace("per 1 day(s)", "").trim()));
			} else
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		System.out.println("--------Data on UI:");
		for (Float price : arrayList) {
			System.out.println(price);
		}

		// Copy to a new array list to SORT in Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// SORT ASC:
		Collections.sort(sortedList);
		System.out.println("--------asc sorted data in code:-----------");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Reverse data để sort DESC
		Collections.reverse(sortedList);
		System.out.println("--------desc sorted data in code:-----------");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
		// result is incorrect
		return sortedList.equals(arrayList);
	}

	protected boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		// Declare array list
		ArrayList<Float> arrayList = new ArrayList<>();

		// Find all elements matching the condition (Name/Price/...)
		List<WebElement> elementList = getListElement(driver, locator);

		// Get the text of each element and add it to the Array List
		for (WebElement element : elementList) {
			if (element.getText().contains(",")) {
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
			} else if (element.getText().isEmpty()) {
				arrayList.add((float) 0);
			} else if (element.getText().contains("per 1 day(s)")) {
				arrayList.add(Float.parseFloat(
						element.getText().replace("$", "").replace(",", "").replace("per 1 day(s)", "").trim()));
			}

			else
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		System.out.println("--------Data on UI:");
		for (Float price : arrayList) {
			System.out.println(price);
		}

		// Copy to a new array list to SORT in Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}

		// Reverse data to sort DESC
		Collections.sort(sortedList);
		System.out.println("--------asc sorted data in code:-----------");
		for (Float price : sortedList) {
			System.out.println(price);
		}
		// Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
		// result is incorrect
		return sortedList.equals(arrayList);
	}

	public String getRandomEmail() {
		return "automation" + getRandomNumberbyDateTime() + "@live.com";
	}

	public String getRandomPhoneNumber() {
		return "0" + ThreadLocalRandom.current().nextInt(10000000, 99999999);
	}

	public long getRandomNumberbyDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}

	public BasePage userOpenPageAtHeaderLinkByClassName(WebDriver driver, String name) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_PAGE_AT_HEADER_AREA, name);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_AT_HEADER_AREA, name);
		switch (name) {
		case "ico-register":
			return NopCommercePageGeneratorManager.getUserRegisterPage(driver);
		case "ico-login":
			return NopCommercePageGeneratorManager.getUserLoginPage(driver);
		case "ico-logout":
			return NopCommercePageGeneratorManager.getUserHomePage(driver);
		case "ico-cart":
			return NopCommercePageGeneratorManager.getUserShoppingCartPage(driver);
		case "ico-account":
			return NopCommercePageGeneratorManager.getUserMyAccountPage(driver);
		case "ico-wishlist":
			return NopCommercePageGeneratorManager.getUserWishlistPage(driver);
		default:
			return null;
		}
	}

	public BasePage UserOpenPageAtFooterMenuByText(WebDriver driver, String text) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_AT_FOOTER_AREA, text);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_AT_FOOTER_AREA, text);
		switch (text) {
		case "Search":
			return NopCommercePageGeneratorManager.getUserSearchPage(driver);
		case "My account":
			return NopCommercePageGeneratorManager.getUserMyAccountPage(driver);
		case "Compare products list":
			return NopCommercePageGeneratorManager.getUserCompareProductListPage(driver);
		case "Recently viewed products":
			return NopCommercePageGeneratorManager.getUserRecentlyViewedProductPage(driver);
		default:
			return null;
		}
	}

	public void userClickToButtonByText(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonName);
	}

	public String getValidationErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.VALIDATION_SUMMARY_ERROR_MESSAGE);
		return getTextElement(driver, UserBasePageUI.VALIDATION_SUMMARY_ERROR_MESSAGE);
	}

	public WebElement selectRandomRadiobutton(WebDriver driver, String locator) {
		Random rnd = new Random();
		List<WebElement> radios = getListElement(driver, locator);
		WebElement randomRadio = radios.get(rnd.nextInt(radios.size()));
		randomRadio.click();
		return randomRadio;
	}

	public void selectRandomRadiobutton(WebDriver driver, String locator, String... dynamicValue) {
		Random rnd = new Random();
		List<WebElement> radios = getListElement(driver, locator, dynamicValue);
		if (!radios.isEmpty()) {
			radios.get(rnd.nextInt(radios.size())).click();
		}
	}

	public void selectRandomItemAtProductPage(WebDriver driver, String locator, String... dynamicValue) {
		Random rand = new Random();
		List<WebElement> items = getListElement(driver, locator, dynamicValue);
		if (!items.isEmpty()) {
			items.get(rand.nextInt(items.size())).click();
		}
	}

	public void selectRandomDropdownButton(WebDriver driver, String locator) {
		List<WebElement> options = getListElement(driver, locator);
		Random rand = new Random();
		if (!options.isEmpty()) {
			int list = rand.nextInt(options.size());
			options.get(list).click();
		}
	}

	public void selectRandomDropdownButton(WebDriver driver, String locator, String... dynamicValue) {
		List<WebElement> options = getListElement(driver, locator, dynamicValue);
		if (!options.isEmpty()) {
			Random rand = new Random();
			int list = rand.nextInt(options.size());
			options.get(list).click();
		}
	}

	public void selectRandomItem(WebDriver driver, String locator, String... dynamicValue) {
		List<WebElement> elements = getListElement(driver, locator, dynamicValue);
		if (elements.isEmpty()) {
			// Handle the case where no links are found
			throw new IllegalStateException("No links found in the header menu.");
		}
		Random rand = new Random();
		int list = rand.nextInt(elements.size());
		elements.get(list).click();
	}

	public void selectRandomItem(WebDriver driver, String locator) {
		List<WebElement> options = getListElement(driver, locator);
		if (options.isEmpty()) {
			// Handle the case where no links are found
			throw new IllegalStateException("No links found in the header menu.");
		}
		Random rand = new Random();
		int list = rand.nextInt(options.size());
		options.get(list).click();

	}

	public void userInputToTextboxByID(WebDriver driver, String ID, String inputValue) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, ID);
		sendkeysToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, inputValue, ID);
	}

	public String getBarNotificationSuccessMessage(WebDriver driver) {
		waitForElementVisible(driver, MyAccountPageUI.BAR_NOTIFICATION_SUCCESS_MESSAGE);
		return getTextElement(driver, MyAccountPageUI.BAR_NOTIFICATION_SUCCESS_MESSAGE);
	}

	public int getElementTypeCount(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	public void clickToCloseButtonAtBarNotificationMessage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CLOSE_BUTTON_AT_NOTIFICATION_BAR_MESSAGE);
		clickToElementByJS(driver, UserBasePageUI.CLOSE_BUTTON_AT_NOTIFICATION_BAR_MESSAGE);
	}

	public void selectRandomValueInDropdownByID(WebDriver driver, String id) {
		selectRandomDropdownButton(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_ID, id);
	}

	public String getElementXPath(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("function getElementXPath(element) {"
				+ "   if (element && element.id)" + "       return '//*[@id=\"' + element.id + '\"]';" + "   else"
				+ "       return getElementTreeXPath(element);" + "}" + "" + "function getElementTreeXPath(element) {"
				+ "   var paths = [];" + "   for (; element && element.nodeType == 1; element = element.parentNode) {"
				+ "       var index = 0;"
				+ "       for (var sibling = element.previousSibling; sibling; sibling = sibling.previousSibling) {"
				+ "           if (sibling.nodeType == Node.DOCUMENT_TYPE_NODE)" + "               continue;"
				+ "           if (sibling.nodeName == element.nodeName)" + "               ++index;" + "       }"
				+ "       var tagName = element.nodeName.toLowerCase();"
				+ "       var pathIndex = (index ? '[' + (index+1) + ']' : '');"
				+ "       paths.splice(0, 0, tagName + pathIndex);" + "   }"
				+ "   return paths.length ? '/' + paths.join('/') : null;" + "}" + ""
				+ "return getElementXPath(arguments[0]);", element);
	}

	public void clickToNaviItemTreeviewByText(WebDriver driver, String itemName) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_NAVI_ITEM_TREEVIEW_BY_TEXT, itemName);
		clickToElement(driver, AdminBasePageUI.DYNAMIC_NAVI_ITEM_TREEVIEW_BY_TEXT, itemName);
	}

	public void clickToChildNaviItemByText(WebDriver driver, String itemName) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_CHILD_NAVI_ITEM_BY_TEXT, itemName);
		clickToElement(driver, AdminBasePageUI.DYNAMIC_CHILD_NAVI_ITEM_BY_TEXT, itemName);
	}

	public void adminInputToTextboxByText(WebDriver driver, String textboxName, String inputValue) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_INPUT_TYPE_TEXTBOX_BY_TEXT, textboxName);
		sendkeysToElement(driver, AdminBasePageUI.DYNAMIC_INPUT_TYPE_TEXTBOX_BY_TEXT, inputValue, textboxName);
	}

	public String[] getProductsTitleInSearchResult(WebDriver driver) {
		List<WebElement> productList = getListElement(driver, AdminProductPageUI.PRODUCT_TITLE_AT_SEARCH_RESULT);
		List<String> productTitleList = new ArrayList<>();
		for (WebElement product : productList) {
			String productTitle = product.getText();
			System.out.println(productTitle);
			productTitleList.add(productTitle);

		}
		String[] productTitleArray = productTitleList.toArray(new String[0]);
		return productTitleArray;

	}

	public boolean isProductContainsInSearchResult(WebDriver driver, String productName) {
		String[] productTitles = getProductsTitleInSearchResult(driver);

		for (String productTitle : productTitles) {
			if (productTitle.contains(productName)) {
				return true; // Product found in search result
			}
		}

		return false; // Product not found in search result
	}

	public void selectValueByTextInDropdownByName(WebDriver driver, String fieldName, String textValue) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_DROPDOWN_BY_TEXT, fieldName);
		selectItemByTextInDefaultDropdown(driver, AdminBasePageUI.DYNAMIC_DROPDOWN_BY_TEXT, textValue, fieldName);
	}

	public void checkToCheckboxByFieldName(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, fieldName);
		checktoDefaultCheckboxOrRadio(driver, AdminBasePageUI.DYNAMIC_CHECKBOX_BY_TEXT, fieldName);
	}

	public String getDataTableEmptyMessage(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.DATA_TABLE_EMPTY_MESSAGE);
		return getTextElement(driver, AdminBasePageUI.DATA_TABLE_EMPTY_MESSAGE);

	}

	public String userGetDynamicErrorMessageByIDTextBox(WebDriver driver, String id) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_EROR_MESSAGE_BY_ID, id);
		return getTextElement(driver, RegisterPageUI.DYNAMIC_EROR_MESSAGE_BY_ID, id);
	}

	public String getNoDataMessage(WebDriver driver) {
		waitForElementVisible(driver, UserBasePageUI.NO_DATA_MESSAGE);
		return getTextElement(driver, UserBasePageUI.NO_DATA_MESSAGE);

	}

}
