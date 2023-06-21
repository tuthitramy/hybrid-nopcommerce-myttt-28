package com.nopcommerce.user;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Search extends BaseTest {
	private UserHomePageObject homePage;
	private UserSearchPageObject searchPage;
	DataHelper datahelper = new DataHelper();
	String firstName = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String password = DataHelper.getDataHelper().getValidPassword();
	String email = DataHelper.getDataHelper().getEmailAddress();

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);
		searchPage = (UserSearchPageObject) homePage.UserOpenPageAtFooterMenuByText(driver, "Search");

	}

	@Test(enabled = true)
	public void Search_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Empty Data");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Click to button Search");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 02: Verify message is displayed");
		Assert.assertEquals(searchPage.getSearchResultMessage(), "Search term minimum length is 3 characters");

	}

	@Test(enabled = true)
	public void Search_02_Data_Not_Exist(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Data is not Existed");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Macbook Pro 2050' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Macbook Pro 2050");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to button Search");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 03: Verify message 'No products were found that matched your criteria.' is displayed");
		Assert.assertEquals(searchPage.getSearchResultMessage(), "No products were found that matched your criteria.");

	}

	@Test(enabled = true)
	public void Search_03_Product_Name_Relative(Method method) {
		ExtentTestManager.startTest(method.getName(), " Relative Search with Product Name");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Lenovo' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Lenovo");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify 2 items are found");
		assertTrue(searchPage.isProductContainsInSearchResult("Lenovo IdeaCentre 600 All-in-One PC"));
		assertTrue(searchPage.isProductContainsInSearchResult("Lenovo Thinkpad X1 Carbon Laptop"));

	}

	@Test(enabled = true)
	public void Search_04_Product_Name_Absolute(Method method) {
		ExtentTestManager.startTest(method.getName(), " Absolute Search with Product Name");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Lenovo' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Lenovo Thinkpad X1 Carbon");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify 1 item is found");
		assertTrue(searchPage.isProductContainsInSearchResult("Lenovo Thinkpad X1 Carbon Laptop"));

	}

	@Test(enabled = true)
	public void Search_05_Advanced_Search_Parent_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "Advance Search with Parent Categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Apple MacBook Pro' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Category:", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search - Step 05: Verify 'No products were found that matched your criteria.' is displayed");
		Assert.assertEquals(searchPage.getSearchResultMessage(), "No products were found that matched your criteria.");

	}

	@Test(enabled = true)
	public void Search_06_Advanced_Search_Sub_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "Advance Search with Sub Categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Apple MacBook Pro' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Category:", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify 1 item is found");
		assertTrue(searchPage.isProductContainsInSearchResult("Apple MacBook Pro 13-inch"));

	}

	@Test(enabled = true)
	public void Search_07_Incorrect_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Advance Search with Incorrect Manufacturer");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Apple MacBook Pro' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Category:", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Manufacturer:", "HP");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search - Step 07: Verify 'No products were found that matched your criteria.'");
		Assert.assertEquals(searchPage.getSearchResultMessage(), "No products were found that matched your criteria.");

	}

	@Test(enabled = true)
	public void Search_08_Correct_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Advance Search with Incorrect Manufacturer");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Input 'Apple MacBook Pro' to Search keyword");
		searchPage.userInputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Category:", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check to checkbox Advanced Search");
		searchPage.checkToCheckboxByText("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Select Computer from dropdown");
		searchPage.selectValueByTextFromDropdownByText("Manufacturer:", "Apple");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Click to button Search");
		searchPage.clickToSearchButton();
		searchPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search - Step 07: Verify 'No products were found that matched your criteria.'");
		Assert.assertTrue(searchPage.isProductContainsInSearchResult("Apple MacBook Pro 13-inch"));

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
