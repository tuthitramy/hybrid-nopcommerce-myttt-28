package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.NopCommercePageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Register extends BaseTest {
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String randEmailAddress = DataHelper.getDataHelper().getEmailAddress();
	String firstName = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String validPassword = DataHelper.getDataHelper().getValidPassword();
	String invalidPassword = DataHelper.getDataHelper().getInvalidPassword();
	String invalidEmail = "aaa";

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);
		log.info("Pre-condition: Navigate to 'Register' page");
		registerPage = (UserRegisterPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-register");
		log.info("Pre-condition:Verify that Register Page is displayed ");
		registerPage.isRegisterPageDisplayed();
	}

	@Test
	public void Register_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Register to system with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Register with empty data - Step 01: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");
		ExtentTestManager.getTest().log(Status.INFO,
				"Register with empty data - Step 02: Verify error message is displayed in fields: First Name");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "FirstName-error"),
				"First name is required.");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with empty data - Step 03: Verify error message is displayed in fields: Last Name");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "LastName-error"),
				"Last name is required.");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with empty data - Step 04: Verify error message is displayed in fields: Email");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "Email-error"), "Email is required.");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with empty data - Step 05: Verify error message is displayed in fields: Password");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "Password-error"),
				"Password is required.");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with empty data - Step 06: Verify error message is displayed in fields: Confirm Password");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "ConfirmPassword-error"),
				"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email(Method method) {
		registerPage = NopCommercePageGeneratorManager.getUserRegisterPage(driver);
		ExtentTestManager.startTest(method.getName(), "Register to system with invalid email");
		ExtentTestManager.getTest().log(Status.INFO,
				"Register with invalid data - Step 01: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with invalid email - Step 02: Input to LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with invalid email - Step 03: Input to invalid value to Email Textbox");
		registerPage.userInputToTextboxByID(driver, "Email", invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register with invalid Email - Step 04: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with invalid email - Step 05: Verify error message 'Wrong email' is displayed in field Email");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "Email-error"), "Wrong email");

	}

	@Test
	public void Register_03_Register_Success(Method method) {
		registerPage = NopCommercePageGeneratorManager.getUserRegisterPage(driver);

		ExtentTestManager.startTest(method.getName(), "Register to system successfully");

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 01: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register successfully- Step 02: Input to valid value to  LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register successfully - Step 03: Input to valid email to Email Textbox: " + randEmailAddress);
		registerPage.userInputToTextboxByID(driver, "Email", randEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register successfully  - Step 04: Input to valid value to Password Textbox" + validPassword);
		registerPage.userInputToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register successfully - Step 05: Input to valid value to Confirm Password Textbox");
		registerPage.userInputToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register successfully - Step 06: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register successfully - Step 07: Verify error message 'Your registration completed' is displayed");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void Register_04_Existing_Email(Method method) {

		registerPage = (UserRegisterPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-register");

		ExtentTestManager.startTest(method.getName(), "Register to system existing email");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 01: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 02: Input to valid value to  LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 03: Input to valid value to Email Textbox");
		System.out.println(randEmailAddress);
		registerPage.userInputToTextboxByID(driver, "Email", randEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 04: Input to valid value to Password Textbox");
		registerPage.userInputToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 05: Input to valid value to Confirm Password Textbox");
		registerPage.userInputToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 06: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with existing email - Step 07: Verify error message 'The specified email already exists' is displayed");
		assertEquals(registerPage.getValidationErrorMessage(driver), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars(Method method) {
		registerPage = (UserRegisterPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-register");

		ExtentTestManager.startTest(method.getName(), "Register to system with Password is less than 6 characters");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 01: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 02: Input to valid value to  LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 03: Input to valid value to Email Textbox");
		System.out.println(randEmailAddress);
		registerPage.userInputToTextboxByID(driver, "Email", randEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 04: Input to  Password is less than 6 characters Textbox");
		registerPage.userInputToTextboxByID(driver, "Password", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 05: Input to valid value to Confirm Password Textbox");
		registerPage.userInputToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 06: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Password is less than 6 characters - Step 07: Verify error message 'Password must meet the following rules: must have at least 6 characters is displayed'");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "Password-error"),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Confirm_Password_Not_Match(Method method) {
		registerPage = (UserRegisterPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-register");

		ExtentTestManager.startTest(method.getName(), "Register to system with Confirm Password is not match");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 01: Input to FirstName Textbox");
		registerPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 02: Input to valid value to  LastName Textbox");
		registerPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 03: Input to valid value to Email Textbox");
		registerPage.userInputToTextboxByID(driver, "Email", randEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 04: Input to  Password is less than 6 characters Textbox");
		registerPage.userInputToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 05: Input to valid value to Confirm Password Textbox");
		registerPage.userInputToTextboxByID(driver, "ConfirmPassword", "123458");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 06: Click to Register button");
		registerPage.userClickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Register with Confirm Password is not match - Step 07: Verify error message 'The password and confirmation password do not match.'");
		assertEquals(registerPage.userGetDynamicErrorMessageByIDTextBox(driver, "ConfirmPassword-error"),
				"The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
