package com.nopcommerce.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.admin.AdminProductPageObject;
import reportConfig.ExtentTestManager;

public class Search_Product extends BaseTest {
	private AdminDashboardPageObject dashboardPage;
	private AdminProductPageObject productPage;
	private AdminLoginPageObject loginPage;

	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = NopCommercePageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = loginPage.clickToLoginButton();

	}

	@Test(enabled = true)
	public void Search_01_With_Product_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Product Name");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Click to Catalog Menu");
		dashboardPage.clickToNaviItemTreeviewByText(driver, "Catalog");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 02: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 03: Input 'Lenovo IdeaCentre 600 All-in-One PC' to Product name Field");
		productPage.adminInputToTextboxByText(driver, "Product name", "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Click To Search Button");
		productPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 05: Verify product is contained in Seach Result");
		assertTrue(productPage.isProductContainsInSearchResult(driver, "Lenovo IdeaCentre 600 All-in-One PC"));

	}

	@Test(enabled = true)
	public void Search_02_Product_Name_Parent_Category_Unchecked(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Product Name+Parent Category+Unchecked");
		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 01: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 02: Input 'Lenovo IdeaCentre 600 All-in-One PC' to Product name Field");
		productPage.adminInputToTextboxByText(driver, "Product name", "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 03: Select Computers in Category Dropdown");
		productPage.selectValueByTextInDropdownByName(driver, "Category", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Click To Search Button");
		productPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 05: Verify empty data message is displayed in  Seach Result Table");
		assertEquals(productPage.getDataTableEmptyMessage(driver), "No data available in table");
	}

	@Test(enabled = true)
	public void Search_03_Product_Name_Parent_Category_Checked(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Product Name+Parent Category+Unchecked");
		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 01: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 02: Input 'Lenovo IdeaCentre 600 All-in-One PC' to Product name Field");
		productPage.adminInputToTextboxByText(driver, "Product name", "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 03: Select Computers in Category Dropdown");
		productPage.selectValueByTextInDropdownByName(driver, "Category", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Check to checkbox Search subcategories");
		productPage.checkToCheckboxByFieldName(driver, "Search subcategories");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 05: Click To Search Button");
		productPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 06: Verify product is contained in Seach Result");
		assertTrue(productPage.isProductContainsInSearchResult(driver, "Lenovo IdeaCentre 600 All-in-One PC"));

	}

	@Test(enabled = true)
	public void Search_04_Product_Name_Child_Category(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Product Name+Child Category+Unchecked");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 01: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 02: Input 'Lenovo IdeaCentre 600 All-in-One PC' to Product name Field");
		productPage.adminInputToTextboxByText(driver, "Product name", "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 03: Select Computers in Category Dropdown");
		productPage.selectValueByTextInDropdownByName(driver, "Category", "Computers >> Desktops");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Click To Search Button");
		productPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 05: Verify product is contained in Seach Result");
		assertTrue(productPage.isProductContainsInSearchResult(driver, "Lenovo IdeaCentre 600 All-in-One PC"));
	}

	@Test(enabled = true)
	public void Search_05_Product_Name_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search with Product Name+Manufacturer");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 01: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);
		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 02: Input 'Lenovo IdeaCentre 600 All-in-One PC' to Product name Field");
		productPage.adminInputToTextboxByText(driver, "Product name", "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 03: Select Computers in Category Dropdown");
		productPage.selectValueByTextInDropdownByName(driver, "Category", "All");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Select Apple in Manufacturer Dropdown");
		productPage.selectValueByTextInDropdownByName(driver, "Manufacturer", "Apple");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 05: Click To Search Button");
		productPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 06: Verify empty data message is displayed in  Seach Result Table");
		assertEquals(productPage.getDataTableEmptyMessage(driver), "No data available in table");

	}

	@Test(enabled = true)
	public void Search_06_Product_SKU(Method method) {
		ExtentTestManager.startTest(method.getName(), "Go directly to product SKU");
		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 01: Navigate to Products Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Products");
		productPage = NopCommercePageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Search  - Step 02: Input 'LE_IC_600' to Go directly to product SKU Field");
		productPage.adminInputToTextboxByText(driver, "Go directly to product SKU", "LE_IC_600");

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 03: Click To Go button");
		productPage.clickToGoButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 04: Verify go to product detail successfully");
		assertTrue(productPage.isProductDetailPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Search  - Step 05: Verify information of product");
		assertTrue(productPage.isProductNameDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
