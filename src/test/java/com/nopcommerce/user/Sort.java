package com.nopcommerce.user;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Sort extends BaseTest {
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	DataHelper datahelper = new DataHelper();

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);
		productPage = NopCommercePageGeneratorManager.getUserProductPage(driver);
		productPage.selectRandomProductPage();
	}

	@Test(enabled = true)
	public void TC_01_Sort_Name_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort products with A to Z Name");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select Name: A to Z ");
		productPage.selectSortTypeByText("Name: A to Z");
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Sort - Step 01: Sort Item A to Z and Verify item are Sorted Correctly");
		assertTrue(productPage.areProductNameSortedAscending(driver));

	}

	@Test(enabled = true)
	public void TC_02_Sort_Name_Z_To_A(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort products with Z to A Name");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select Name: Z to A ");
		productPage.selectSortTypeByText("Name: Z to A");
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Sort - Step 02: Sort Item Z to A and Verify item are Sorted Correctly");
		assertTrue(productPage.areProductNameSortedDescending(driver));

	}

	@Test(enabled = true)
	public void TC_03_Sort_Price_Low_To_High(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort products with Price Low To High");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select Price: Low to High ");
		productPage.selectSortTypeByText("Price: Low to High");
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Sort - Step 02: Sort Item with price Low to High and Verify item are Sorted Correctly");
		assertTrue(productPage.areProductPriceSortedAscending(driver));
	}

	@Test(enabled = true)
	public void TC_04_Sort_High_To_Low(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort products with Price: High to Low");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select Price: High to Low ");
		productPage.selectSortTypeByText("Price: High to Low");
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Sort - Step 02: Sort Item with Price High to Low and Verify item are Sorted Correctly");
		assertTrue(productPage.areProductPriceSortedDescending(driver));

	}

	@Test(enabled = true)
	public void TC_05_Displayed_Three_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "Display with three products/page");

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 01: Click to Computers");
		productPage.clickToProductMenuTitleByText("Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 02: Click to Notebooks");
		productPage.clickToSubCategoryMenuByText("Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 03: Select 3 pagesize from dropdown");
		productPage.selectPageSizeByText("3");

		ExtentTestManager.getTest().log(Status.INFO,
				"Display - Step 04: Verify 3 or less than 3 products are displayed");
		assertTrue(productPage.areNumberOfProductDisplayedCorrectByPageSize(3));

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 05: Verify next icon is displayed");
		assertTrue(productPage.isNextOrPreviousIconDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 06: Click to 2 PageNumber");
		productPage.clickToPageNumberByText("2");
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 07: Verify previous icon is displayed");
		assertTrue(productPage.isNextOrPreviousIconDisplayed());

	}

	@Test(enabled = true)
	public void TC_06_Display_Six_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "Display with six products/page");

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 01: Select 6 pagesize from dropdown");
		productPage.selectPageSizeByText("6");
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Display - Step 02: Verify 6 or less than 6 products are displayed");
		assertTrue(productPage.areNumberOfProductDisplayedCorrectByPageSize(6));

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 03: Verify pagination is displayed");
		assertFalse(productPage.isPaginationDisplayed());

	}

	@Test(enabled = true)
	public void TC_07_Display_Nine_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "Display with nine products/page");

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 01: Select 9 pagesize from dropdown");
		productPage.selectPageSizeByText("9");

		ExtentTestManager.getTest().log(Status.INFO,
				"Display - Step 02: Verify 9 or less than 9 products are displayed");
		assertTrue(productPage.areNumberOfProductDisplayedCorrectByPageSize(9));

		ExtentTestManager.getTest().log(Status.INFO, "Display - Step 03: Verify pagination is displayed");
		assertFalse(productPage.isPaginationDisplayed());

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
