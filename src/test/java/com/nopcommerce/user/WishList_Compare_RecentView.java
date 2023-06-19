package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserCompareProductListPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserRecentlyViewedProductPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class WishList_Compare_RecentView extends BaseTest {
	private UserHomePageObject homePage;
	private UserWishlistPageObject wishlistPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCompareProductListPageObject compareProductPage;
	private UserRecentlyViewedProductPageObject recentlyViewedProductPage;
	private UserProductPageObject productPage;
	DataHelper datahelper = new DataHelper();
	String firstName = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String password = DataHelper.getDataHelper().getValidPassword();
	String email = DataHelper.getDataHelper().getEmailAddress();
	String productTitle;
	String productTitleAtDetailPage, productTitleAtWishlistPage, productTitleAtShoppingCartPage;

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);
		productPage = NopCommercePageGeneratorManager.getUserProductPage(driver);

	}

	@Test(enabled = true)
	public void TC_01_Add_To_WishtList(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add product to Wishlist");

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 01: Open Random Product Page");
		productPage.selectRandomProductPage();

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 02: Click to open random Product Detail");
		productPage.selectRandomProduct();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 03: Check product title clicked to");
		productTitleAtDetailPage = productPage.getProductNameAtProductDetailPage();
		System.out.println("Product clicked: " + productTitleAtDetailPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Whishlist - Step 04: Verify message 'The product has been added to your wishlist' is displayed");
		assertEquals(productPage.getAddToWishlistSuccessMessage(), "The product has been added to your wishlist");
		productPage.sleepInSecond(5);
		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 05: Navigate to Wishlist Page");
		wishlistPage = (UserWishlistPageObject) productPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-wishlist");
		productTitleAtWishlistPage = wishlistPage.getProductTitleAtWishListPage();
		wishlistPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 06: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtWishlistPage);

	}

	@Test(enabled = true)
	public void TC_02_Add_Product_To_Cart_From_Wishlist_Page(Method method) {

		ExtentTestManager.startTest(method.getName(), "Add Product to Cart from Wishlist Page");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product to Cart - Step 01: Check to checkbox Add To Cart  ");
		wishlistPage.checkToAddToCartCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Add Product to Cart - Step 02: Click To button ADD TO CART");
		wishlistPage.userClickToButtonByText(driver, "Add to cart");
		wishlistPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product to Cart - Step 03: Verify product is Added To Cart");
		shoppingCartPage = NopCommercePageGeneratorManager.getUserShoppingCartPage(driver);
		productTitleAtShoppingCartPage = shoppingCartPage.getProductTitleAtShoppingCartPage();
		assertEquals(productTitleAtDetailPage, productTitleAtShoppingCartPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add Product to Cart - Step 04: Verify product is not displayed in Wishlist Page");
		wishlistPage = (UserWishlistPageObject) shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver,
				"ico-wishlist");
		assertTrue(wishlistPage.isProductNameUnDisplayedAtWishListPage());
	}

	@Test(enabled = true)
	public void TC_03_Remove_Product_From_Wishlist_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "Remove Product from WishlistPage");

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 01: Open Random Product Page");
		productPage.selectRandomProductPage();

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 02: Click to open random Product Detail");
		productPage.selectRandomProduct();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 03: Check product title clicked to");
		productTitleAtDetailPage = productPage.getProductNameAtProductDetailPage();
		System.out.println("Product clicked: " + productTitleAtDetailPage);

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 04: Click to button Add wishlist");
		assertEquals(productPage.getAddToWishlistSuccessMessage(), "The product has been added to your wishlist");
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 05: Navigate to Wishlist Page");
		wishlistPage = (UserWishlistPageObject) productPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-wishlist");
		productTitleAtWishlistPage = wishlistPage.getProductTitleAtWishListPage();

		ExtentTestManager.getTest().log(Status.INFO, "Whishlist - Step 06: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtWishlistPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Whishlist - Step 07: Click to button remove product from Wishtlist Page");
		wishlistPage.clickToRemoveButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Whishlist - Step 08: Verify selected product is  not displayed in wishlist page");
		assertTrue(wishlistPage.isProductNameUnDisplayedAtWishListPage());

		ExtentTestManager.getTest().log(Status.INFO,
				"Whishlist - Step 09: Verify the messsage 'The wishlist is empty!' is displayed");
		assertEquals(wishlistPage.getNoDataMessage(driver), "The wishlist is empty!");
		wishlistPage.sleepInSecond(5);

	}

	@Test(enabled = true)
	public void TC_04_Add_Product_To_Compare(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add products to compare");

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Compare- Step 01: Open Random Product Page");
		productPage.selectRandomProductPage();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add Product To Compare- Step 02: Select 2 random products to compare and verify update message is displayed");
		productPage.clickRandomCompareButtons(driver, 2);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Compare- Step 03: Open compare product list page");
		productPage.UserOpenPageAtFooterMenuByText(driver, "Compare products list");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add Product To Compare- Step 04:Verify các thông tin được hiển thị");
		compareProductPage = (UserCompareProductListPageObject) productPage.UserOpenPageAtFooterMenuByText(driver,
				"Compare products list");
		assertTrue(compareProductPage.areProductNameDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Compare- Step 05:Click to button Clear list");
		compareProductPage.clickToClearListButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add Product To Compare- Step 06:Verify message 'You have no items to compare.'is displayed");
		System.out.println(compareProductPage.getNoDataMessage(driver));
		assertEquals(compareProductPage.getNoDataMessage(driver), "You have no items to compare.");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add Product To Compare - Step 07: Verify Elements are no longer displayed in page");
		assertFalse(compareProductPage.areProductNameDisplayed());
	}

	@Test(enabled = true)
	public void TC_05_Recently_Viewed_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Recently viewed product");

		ExtentTestManager.getTest().log(Status.INFO,
				"Recently viewed product- Step 01: Open Random Product Page and get product name list that click to view");
		List<String> productNameListAtDetailPage = productPage.getNumRandomProductName(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Recently viewed product- Step 02: Get three last product clicked to view");
		List<String> lastThreeProductNameListAtDetailPage = productPage
				.getLastNumberProductName(productNameListAtDetailPage, 3);
		System.out.println("Last three item: " + lastThreeProductNameListAtDetailPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Recently viewed product- Step 03: Open Recently Viewed Product Page");
		recentlyViewedProductPage = (UserRecentlyViewedProductPageObject) homePage
				.UserOpenPageAtFooterMenuByText(driver, "Recently viewed products");

		ExtentTestManager.getTest().log(Status.INFO,
				"Recently viewed product- Step 04: Get product name list at recently viewed product page");
		List<String> recentlyViewedProductNameList = recentlyViewedProductPage.getProductNameListAtRecentlyViewdPage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Recently viewed product- Step 05: Verify viewed products are displayed in page");
		System.out.println("lastThreeProductNameListAtDetailPage: " + lastThreeProductNameListAtDetailPage);
		System.out.println("recentlyViewedProductNameList: " + recentlyViewedProductNameList);

		assertTrue(lastThreeProductNameListAtDetailPage.equals(recentlyViewedProductNameList));

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
		closeBrowserDriver();
	}

}
