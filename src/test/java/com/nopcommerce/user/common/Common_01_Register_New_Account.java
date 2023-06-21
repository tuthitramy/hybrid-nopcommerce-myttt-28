package com.nopcommerce.user.common;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Common_01_Register_New_Account extends BaseTest {
	public static String randEmailAddress;
	UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	public static String firstName = DataHelper.getDataHelper().getFirstName();
	public static String lastName = DataHelper.getDataHelper().getLastName();
	public static String password = DataHelper.getDataHelper().getValidPassword();

	@Parameters({ "browser", "userUrl" })
	@BeforeTest(description = "Create new common User for All Class Test")
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);

		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage = (UserRegisterPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-register");
		randEmailAddress = registerPage.getRandomEmail();

		log.info("Register - Step 02: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		log.info("Register - Step 03: Input to valid value to  LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		log.info("Register - Step 04: Input to valid value to Email Textbox");
		System.out.println("Email: " + randEmailAddress);
		registerPage.userInputToTextboxByID(driver, "Email", randEmailAddress);

		log.info("Register - Step 05: Input to valid value to Password Textbox");
		System.out.println("Password" + password);
		registerPage.userInputToTextboxByID(driver, "Password", password);

		log.info("Register - Step 06: Input to valid value to Confirm Password Textbox");
		registerPage.userInputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register - Step 07:  Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		log.info("Register - Step 08: Verify error message 'Your registration completed' is displayed");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@AfterTest(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}
