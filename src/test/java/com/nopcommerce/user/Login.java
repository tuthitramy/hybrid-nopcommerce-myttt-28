package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

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
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Login extends BaseTest {
	private String randRegisteredValidEmailAddress, registeredPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;

	String notRegisteredEmail = "automationfc@gmail.com";
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

		randRegisteredValidEmailAddress = Common_01_Register_New_Account.randEmailAddress;
		registeredPassword = Common_01_Register_New_Account.password;

	}

	@Test(enabled = true)
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Login with empty data - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login with empty data - Step 02: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login with empty data - Step 03: Verify error message 'Please enter your email' is displayed");
		assertEquals(loginPage.userGetDynamicErrorMessageByIDTextBox(driver, "Email-error"), "Please enter your email");

	}

	@Test(enabled = true)
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system with invalid email");
		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login with invalid email - Step 02: Input invalid email to email textbox");
		loginPage.userInputToTextboxByID(driver, "Email", invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO,
				"Login with invalid email - Step 03: Input valid password to Password textbox");
		loginPage.userInputToTextboxByID(driver, "Password", registeredPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login with invalid email - Step 04: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login with invalid email - Step 05: Verify error message 'Wrong email' is displayed");
		assertEquals(loginPage.userGetDynamicErrorMessageByIDTextBox(driver, "Email-error"), "Wrong email");

	}

	@Test(enabled = true)
	public void Login_03_Not_Registered_Email(Method method) {

		ExtentTestManager.startTest(method.getName(), "Login to system with not registered email");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Input not registered email to Email textbox");
		loginPage.userInputToTextboxByID(driver, "Email", notRegisteredEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Input valid password to Password textbox");
		loginPage.userInputToTextboxByID(driver, "Password", registeredPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 05: Verify error message 'Login was unsuccessful. Please correct the errors and try again.\\nNo customer account found' is displayed!");
		assertEquals(loginPage.getValidationErrorMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test(enabled = true)
	public void Login_04_Registered_Email_Not_Input_To_Password(Method method) {

		ExtentTestManager.startTest(method.getName(), "Login to system with valid email and not input to Password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Input valid email to Email textbox");
		loginPage.userInputToTextboxByID(driver, "Email", randRegisteredValidEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 04: Verify error message 'Login was unsuccessful. Please correct the errors and try again.\\nThe credentials provided are incorrect'");
		assertEquals(loginPage.getValidationErrorMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test(enabled = true)
	public void Login_05_Registered_Email_Wrong_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system with valid email and input wrong password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Input valid email to Email textbox");
		loginPage.userInputToTextboxByID(driver, "Email", randRegisteredValidEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Input wrong password to password textbox");
		loginPage.userInputToTextboxByID(driver, "Password", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 05: Verify error message 'Login was unsuccessful. Please correct the errors and try again.\\nThe credentials provided are incorrect'");
		assertEquals(loginPage.getValidationErrorMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test(enabled = true)
	public void Login_06_Valid_Email_And_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login to system with valid email and password");
		ExtentTestManager.getTest().log(Status.INFO, "Login success - Step 01: Open Log in Page");
		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login success - Step 02: Input valid email to Email textbox");
		loginPage.userInputToTextboxByID(driver, "Email", randRegisteredValidEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO,
				"Login success - Step 03: Input valid password to password textbox");
		loginPage.userInputToTextboxByID(driver, "Password", registeredPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login success - Step 04: Click to Log in button");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Login success - Step 05: Verify login successfully!");
		loginPage.isLoginSuccessful();

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
