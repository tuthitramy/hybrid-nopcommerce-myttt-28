package com.nopcommerce.user;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.user.common.Common_01_Register_New_Account;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyAccountPageObject;
import pageObjects.nopCommerce.user.UserProductPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;
import pageUIs.nopCommerce.user.UserBasePageUI;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Order extends BaseTest {
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserMyAccountPageObject myAccountPage;
	private UserProductPageObject productPage;
	DataHelper datahelper = new DataHelper();
	String firstName = DataHelper.getDataHelper().getFirstName();
	String firstName1 = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String lastName1 = DataHelper.getDataHelper().getLastName();
	String email1 = DataHelper.getDataHelper().getEmailAddress();
	String address01 = DataHelper.getDataHelper().getAddress1();
	String city = DataHelper.getDataHelper().getCityName();
	String ZIP = DataHelper.getDataHelper().getPostalCode();
	String cardNumber = DataHelper.getDataHelper().getRandomCardNumber();
	private String phoneNumber, phoneNumber1;
	private String registeredEmail, registeredPassword;

	String productTitle;
	String productTitleAtDetailPage, productTitleAtWishlistPage, productTitleAtShoppingCartPage;
	int randomQuantity;

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);

		phoneNumber = homePage.getRandomPhoneNumber();
		phoneNumber1 = homePage.getRandomPhoneNumber();
		registeredEmail = Common_01_Register_New_Account.randEmailAddress;
		registeredPassword = Common_01_Register_New_Account.password;

		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");
		loginPage.userInputToTextboxByID(driver, "Email", registeredEmail);
		loginPage.userInputToTextboxByID(driver, "Password", registeredPassword);
		loginPage.userClickToButtonByText(driver, "Log in");
		loginPage.isLoginSuccessful();

	}

	@Test(enabled = true)
	public void TC_01_Add_Product_To_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add product to Cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 01: Open Random Product Page");
		productPage = NopCommercePageGeneratorManager.getUserProductPage(driver);
		productPage.selectRandomProductPage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 02: Click to open random Product Detail");
		productPage.selectRandomProduct();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 03: Check product title clicked to");
		productTitleAtDetailPage = productPage.getProductNameAtProductDetailPage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 04: Input to all Required Field(if required)");
		productPage.inputToRequiredField();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 05: Click to button Add To Cart");
		productPage.getAddToCartSuccessMessage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 06: Verify message 'The product has been added to your shopping cart' is displayed");
		assertEquals(productPage.getBarNotificationSuccessMessage(driver),
				"The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 07: Click to close button at bar notification success message");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 08: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();
		productTitleAtShoppingCartPage = shoppingCartPage.getProductTitleAtShoppingCartPage();
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 09: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtShoppingCartPage);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 10: Click to remove button");
		shoppingCartPage.clickToRemoveButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 11: Verify message'Your Shopping Cart is empty'");
		assertEquals(shoppingCartPage.getNoDataMessage(driver), "Your Shopping Cart is empty!");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 12: Verify selected product is  not displayed in shopping cart page");
		assertTrue(shoppingCartPage.isProductUndisplayed());

	}

	@Test(enabled = true)
	public void TC_02_Edit_Product_In_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "Edit product in Shopping Cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 01: Add Build Your Own Computer to Shopping Cart");
		productPage.clickToBuildYourComputerProduct();

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 02: Input to product information");
		productPage.selectToProcessorField("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");
		productPage.selectToRamField("8GB [+$60.00]");
		productPage.selectHDDField("400 GB [+$100.00]");
		productPage.selectOSField("Vista Premium [+$60.00]");

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 03: Click to button Add To Cart");
		productPage.getAddToCartSuccessMessage();

		ExtentTestManager.getTest().log(Status.INFO, "Edit product in Shopping Cart - Step 04: Click to Close button");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 05: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();

		ExtentTestManager.getTest().log(Status.INFO, "Edit product in Shopping Cart - Step 06: Click to Edit textlink");
		shoppingCartPage.clickToEditTextlink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 07: Edit product information");
		productPage.selectToProcessorField("2.2 GHz Intel Pentium Dual-Core E2200");
		productPage.selectToRamField("4GB [+$20.00]");
		productPage.selectHDDField("320 GB");
		productPage.selectOSField("Vista Home [+$50.00]");
		productPage.sendKeyToQuantityTextbox("2");

		ExtentTestManager.getTest().log(Status.INFO, "Edit product in Shopping Cart - Step 08: Verify product price");
		assertEquals(productPage.getProducPriceAtProductDetailPage(), "$1,320.00");

		ExtentTestManager.getTest().log(Status.INFO, "Edit product in Shopping Cart - Step 09: Click To Update Button");
		productPage.clickToUpdateButton();
		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 10: Verify message 'The product has been added to your shopping cart' is displayed");
		assertEquals(homePage.getBarNotificationSuccessMessage(driver),
				"The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO, "Edit product in Shopping Cart - Step 11: Click To Close button");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 12: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 13: Verify product is updated and information is update successfully");
		shoppingCartPage.isProductNameDisplayed("Build your own computer");

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit product in Shopping Cart - Step 14: Verify product price is update to $2,640.00");
		assertEquals(shoppingCartPage.getProductPriceByProductNameAtShoppingCartPage("Build your own computer"),
				"$2,640.00");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 15: Click to remove button");
		shoppingCartPage.clickToRemoveButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 16: Verify message'Your Shopping Cart is empty'");

		assertEquals(shoppingCartPage.getNoDataMessage(driver), "Your Shopping Cart is empty!");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 17: Verify selected product is  not displayed in shopping cart page");
		assertTrue(shoppingCartPage.isProductUndisplayed());

	}

	@Test(enabled = false)
	public void TC_03_Update_Product_In_Shopping_Cart_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "Update shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 01: Open Random Product Page");
		productPage.selectRandomProductPage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 02: Click to open random Product Detail");
		productPage.selectRandomProduct();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 03: Check product title clicked to");
		productTitleAtDetailPage = productPage.getProductNameAtProductDetailPage();
		System.out.println("Product clicked: " + productTitleAtDetailPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 04: Input to all Required Field(if required)");
		productPage.inputToRequiredField();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 05: Click to button Add To Cart");
		productPage.getAddToCartSuccessMessage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 06: Verify message 'The product has been added to your shopping cart' is displayed");
		assertEquals(productPage.getBarNotificationSuccessMessage(driver),
				"The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 07: Click to close button at bar notification success message");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 08: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();
		productTitleAtShoppingCartPage = shoppingCartPage.getProductTitleAtShoppingCartPage();
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 09: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtShoppingCartPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 10: Input to Quantity Textbox");

		Random rand = new Random();
		randomQuantity = rand.nextInt(10);
		System.out.println(randomQuantity);
		shoppingCartPage.inputToQuantityField(randomQuantity);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 11: Click To update shopping cart button");
		shoppingCartPage.clickToUpdateShoppingCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 11: Verify total price");
		String productPrice = shoppingCartPage.getProductPrice();
		float parsedProductPrice = Float.parseFloat(productPrice.replace("$", "").replace(",", "").trim());
		float totalPrice = parsedProductPrice * randomQuantity;
		System.out.println(totalPrice);
		System.out.println(shoppingCartPage.getTotalPrice());
		assertEquals(totalPrice, shoppingCartPage.getTotalPrice());

	}

	@Test(enabled = true)
	public void TC_04_Order_Product_Payment_Method_By_Cheque(Method method) {
		ExtentTestManager.startTest(method.getName(), "Order product by cheque");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 01: Open Random Product Page");
		productPage.clickToAppleMacbook13Pro();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 02: Click to button Add To Cart");
		productPage.getAddToCartSuccessMessage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 03: Verify message 'The product has been added to your shopping cart' is displayed");
		assertEquals(productPage.getBarNotificationSuccessMessage(driver),
				"The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 04: Click to close button at bar notification success message");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 05: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();
		productTitleAtShoppingCartPage = shoppingCartPage.getProductTitleAtShoppingCartPage();
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 06: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtShoppingCartPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 7: Check to Accept Term Service Checkbox");
		shoppingCartPage.clickToAcceptTermServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 08: Click To check out button");
		shoppingCartPage.userClickToButtonByText(driver, " Checkout ");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 09: Select Country");
		shoppingCartPage.selectRandomValueInDropdownByID(driver, "BillingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 10: Input City");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_City", city);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 11: Input Address1");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_Address1", address01);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 12: Input ZIP");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", ZIP);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 13: Input phone number");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 14: Click to continue button at Billing Address");
		shoppingCartPage.clickToContinueButtonByOnClickType("Billing.save()");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 15: Select random Shipping Method");
		shoppingCartPage.selectRandomShippingMethod();
		String paymentMethodClicked = shoppingCartPage.selectRandomShippingMethod();
		System.out.println("Payment method is clicked: " + paymentMethodClicked);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 16: Click to continue button ");
		shoppingCartPage.clickToContinueButtonByOnClickType("ShippingMethod.save()");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 17: Click to continue button at Payment Method");
		shoppingCartPage.clickToContinueButtonByOnClickType("PaymentMethod.save()");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 18: Verify payment information");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 19: Click to continue button ");
		shoppingCartPage.clickToContinueButtonByOnClickType("PaymentInfo.save()");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 20: Verify order information");
		assertEquals(paymentMethodClicked, shoppingCartPage.getShippingMethodAtConfirmInformation());

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 21: Click to confirm button ");
		shoppingCartPage.userClickToButtonByText(driver, "Confirm");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 22: Verify sucess message and order number");
		assertEquals(shoppingCartPage.getSuccessOrderMessage(), "Your order has been successfully processed!");

		assertTrue(shoppingCartPage.isOrderNumberDisplayedAtOrderCompletedPage());
		String orderNumberAtShoppingCart = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		System.out.println("order number at shopping cart: " + orderNumberAtShoppingCart);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 23: open My Account Page");
		shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 24: open My Account Page");
		myAccountPage = (UserMyAccountPageObject) shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver,
				"ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 25: Click To Order Textlink");
		myAccountPage.clickToOrderTextLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 26: Open detail Order Product");
		myAccountPage.userClickToButtonByText(driver, "Details");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 27: Verify Order number");
		String orderNumberAtOrderInforPage = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		assertEquals(orderNumberAtShoppingCart, orderNumberAtOrderInforPage);

	}

	@Test(enabled = true)
	public void TC_05_Order_Product_Payment_Method_By_Credit_Card(Method method) {
		ExtentTestManager.startTest(method.getName(), "Order product by Credit card");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 01: Open Random Product Page");
		productPage.clickToAppleMacbook13Pro();
		productPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 02: Click to button Add To Cart");
		productPage.getAddToCartSuccessMessage();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 03: Verify message 'The product has been added to your shopping cart' is displayed");
		assertEquals(homePage.getBarNotificationSuccessMessage(driver),
				"The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 04: Click to close button at bar notification success message");
		productPage.clickToCloseButtonAtBarNotificationMessage(driver);
		productPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 05: Navigate to Shopping Cart Page");
		shoppingCartPage = productPage.clickToShoppingCartTextlink();
		productTitleAtShoppingCartPage = shoppingCartPage.getProductTitleAtShoppingCartPage();
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 06: Verify product is added successfully");
		assertEquals(productTitleAtDetailPage, productTitleAtShoppingCartPage);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 7: Check to Accept Term Service Checkbox");
		shoppingCartPage.clickToAcceptTermServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 08: Click To check out button");
		shoppingCartPage.userClickToButtonByText(driver, " Checkout ");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 15: Click to continue button at Billing Address");
		shoppingCartPage.clickToContinueButtonByOnClickType("Billing.save()");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 16: Select random Shipping Method");
		String paymentMethodClicked = shoppingCartPage.selectRandomShippingMethod();
		System.out.println("Payment method is clicked: " + paymentMethodClicked);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 17: Click to continue button ");
		shoppingCartPage.clickToContinueButtonByOnClickType("ShippingMethod.save()");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 18: Select credit card payment method ");
		shoppingCartPage.clickToCreditCardPaymentMethod();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 19: Click to continue button at Payment Method");
		shoppingCartPage.clickToContinueButtonByOnClickType("PaymentMethod.save()");
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 20: Input card information ");
		shoppingCartPage.selectRandomDropdownButton(driver, ShoppingCartPageUI.CREDIT_CARD_TYPE_DROPDOWN);
		shoppingCartPage.userInputToTextboxByID(driver, "CardholderName", firstName);
		shoppingCartPage.inputToCardNumberField();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 21: Click to confirm button ");
		shoppingCartPage.userClickToButtonByText(driver, "Confirm");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 22: Verify sucess message and order number");
		assertEquals(shoppingCartPage.getSuccessOrderMessage(), "Your order has been successfully processed!");
		assertTrue(shoppingCartPage.isOrderNumberDisplayedAtOrderCompletedPage());

		String orderNumberAtShoppingCart = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		System.out.println("order number at shopping cart: " + orderNumberAtShoppingCart);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 23: open My Account Page");
		shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 24: open My Account Page");
		myAccountPage = (UserMyAccountPageObject) shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver,
				"ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 25: Click To Order Textlink");
		myAccountPage.clickToOrderTextLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 26: Open detail Order Product");
		myAccountPage.userClickToButtonByText(driver, "Details");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 27: Verify Order number");
		String orderNumberAtOrderInforPage = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		assertEquals(orderNumberAtShoppingCart, orderNumberAtOrderInforPage);

		assertEquals(orderNumberAtShoppingCart, orderNumberAtOrderInforPage);
	}

	private boolean assertEquals(Object lastNumberProductName, Object productNameListAtRecentlyViewdPage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Test(enabled = true)
	public void TC_06_Re_Order_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Reorder product");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 01: Click to Re order button");
		myAccountPage.userClickToButtonByText(driver, "Re-order");
		shoppingCartPage = NopCommercePageGeneratorManager.getUserShoppingCartPage(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 02: Update QTY");
		shoppingCartPage.inputToQuantityField(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 03: Click To button Update Shopping Cart");
		shoppingCartPage.userClickToButtonByText(driver, "Update shopping cart");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 04: Check to Accept Term Service Checkbox");
		shoppingCartPage.clickToAcceptTermServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 05: Click To check out button");
		shoppingCartPage.clickToCheckOutButton();
		shoppingCartPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 06: Click To New Address of Billing Address Pulldown");
		shoppingCartPage.selectNewAddress();

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 07: Input FirstName");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_FirstName", firstName1);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 08: Input LastName");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_LastName", lastName1);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 09: Input Address1");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_Email", email1);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 10: Select Country");
		shoppingCartPage.selectRandomValueInDropdownByID(driver, "BillingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 11: Input City");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_City", city);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 12: Input Address1");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_Address1", address01);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 13: Input ZIP");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", ZIP);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 14: Input phone number");
		shoppingCartPage.userInputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber1);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 15: Click to continue button at Billing Address");
		shoppingCartPage.clickToContinueButtonByOnClickType("Billing.save()");

		ExtentTestManager.getTest().log(Status.INFO, "Re-order  - Step 16: Select radiobutton Next Day Air");
		shoppingCartPage.selectNextDayAirRadiobutton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 17: Click to continue button at Shipping Method");
		shoppingCartPage.clickToContinueButtonByOnClickType("ShippingMethod.save()");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 18: Click to continue button at Payment Method");
		shoppingCartPage.clickToContinueButtonByOnClickType("PaymentMethod.save()");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 19: Click to continue button at Payment Infor");
		shoppingCartPage.clickToContinueButtonByOnClickType("PaymentInfo.save()");
		shoppingCartPage.sleepInSecond(10);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 20: Click to confirm button ");
		shoppingCartPage.clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, "Confirm");
		shoppingCartPage.cancelAlert(driver);
		shoppingCartPage.sleepInSecond(10);
		shoppingCartPage.clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, "Confirm");

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 21: Verify sucess message and order number");
		assertEquals(shoppingCartPage.getSuccessOrderMessage(), "Your order has been successfully processed!");

		assertTrue(shoppingCartPage.isOrderNumberDisplayedAtOrderCompletedPage());
		String orderNumberAtShoppingCart = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		System.out.println("order number at shopping cart: " + orderNumberAtShoppingCart);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 22: open My Account Page");
		shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 23: open My Account Page");
		myAccountPage = (UserMyAccountPageObject) shoppingCartPage.userOpenPageAtHeaderLinkByClassName(driver,
				"ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 24: Click To Order Textlink");
		myAccountPage.clickToOrderTextLink();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add product to Shopping Cart - Step 25: Open detail Order Product");
		myAccountPage.userClickToButtonByText(driver, "Details");
		shoppingCartPage = NopCommercePageGeneratorManager.getUserShoppingCartPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add product to Shopping Cart - Step 26: Verify Order number");
		String orderNumberAtOrderInforPage = shoppingCartPage.getOrderNumberTextAtOrderInforPage();
		assertEquals(orderNumberAtShoppingCart, orderNumberAtOrderInforPage);

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
