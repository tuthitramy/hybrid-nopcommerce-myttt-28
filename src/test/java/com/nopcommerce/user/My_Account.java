package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
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
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class My_Account extends BaseTest {
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccountPage;
	private UserProductPageObject productPage;
	private String phoneNumber;
	String loginEmail, loginPassword;
	String firstName = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String password = DataHelper.getDataHelper().getValidPassword();
	String email = DataHelper.getDataHelper().getEmailAddress();
	String company = DataHelper.getDataHelper().getCompanyName();
	String address01 = DataHelper.getDataHelper().getAddress1();
	String address02 = DataHelper.getDataHelper().getAddress2();
	String city = DataHelper.getDataHelper().getCityName();
	String ZIP = DataHelper.getDataHelper().getPostalCode();
	String faxNumber = DataHelper.getDataHelper().getFaxNumber();
	String newPassword = DataHelper.getDataHelper().getValidPassword();
	String title, reviewContent;

	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		Random rand = new Random();
		homePage = NopCommercePageGeneratorManager.getUserHomePage(driver);

		title = "REVIEW TITLE " + rand.nextInt();
		reviewContent = "REVIEW " + homePage.getRandomNumberbyDateTime();
		phoneNumber = homePage.getRandomPhoneNumber();

		loginEmail = Common_01_Register_New_Account.randEmailAddress;
		loginPassword = Common_01_Register_New_Account.password;

		loginPage = (UserLoginPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");
		loginPage.userInputToTextboxByID(driver, "Email", loginEmail);
		loginPage.userInputToTextboxByID(driver, "Password", loginPassword);
		loginPage.userClickToButtonByText(driver, "Log in");
		loginPage.isLoginSuccessful();

	}

	@Test(enabled = true)
	public void My_Account_01_Customer_Infor(Method method) {
		ExtentTestManager.startTest(method.getName(), "Update Customer infor");

		ExtentTestManager.getTest().log(Status.INFO, "My Account - Step 01: Open My Account");
		myAccountPage = (UserMyAccountPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 02: Open Customer Infor Page");
		myAccountPage.openPageOfMyAccountListByName("Customer info");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 03: Update Gender Infor");
		myAccountPage.selectRandomGender();

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 04: Update First Name");
		myAccountPage.userInputToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 05: Update Last Name");
		myAccountPage.userInputToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 06: Update Date of Birth");
		myAccountPage.selectRandomDateOfBirthDay();
		myAccountPage.selectRandomMonthOfBirthDay();
		myAccountPage.selectRandomYearOfBirthDay();

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 07: Update email");
		myAccountPage.userInputToTextboxByID(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 08: Update Company name");
		myAccountPage.userInputToTextboxByID(driver, "Company", company);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 09: Click to button SAVE");
		myAccountPage.userClickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 09: Verify error message 'The customer info has been updated successfully.' is displayed");
		assertEquals(myAccountPage.getBarNotificationSuccessMessage(driver),
				"The customer info has been updated successfully.");

	}

	@Test(enabled = true)
	public void My_Account_02_Address_Infor(Method method) {
		ExtentTestManager.startTest(method.getName(), "Update Address infor");
		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 01: Open Addresses Page");
		myAccountPage.openPageOfMyAccountListByName("Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 02: Click to ADD NEW BUTTON");
		myAccountPage.userClickToButtonByText(driver, "Add new");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 03: Update First Name");
		myAccountPage.userInputToTextboxByID(driver, "Address_FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 04: Update Last Name");
		myAccountPage.userInputToTextboxByID(driver, "Address_LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 05: Update Email");
		myAccountPage.userInputToTextboxByID(driver, "Address_Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 06: Update Company");
		myAccountPage.userInputToTextboxByID(driver, "Address_Company", company);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 07: Update Country");
		myAccountPage.selectRandomValueInDropdownByID(driver, "Address_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 08: Update Province");
		myAccountPage.selectRandomProvince();

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 09: Update City");
		myAccountPage.userInputToTextboxByID(driver, "Address_City", city);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 10: Update Address 01");
		myAccountPage.userInputToTextboxByID(driver, "Address_Address1", address01);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 11: Update Address 02");
		myAccountPage.userInputToTextboxByID(driver, "Address_Address2", address02);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 12: Update Post Code");
		myAccountPage.userInputToTextboxByID(driver, "Address_ZipPostalCode", ZIP);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 13: Update Phone Number");
		myAccountPage.userInputToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 15: Click to button SAVE");
		myAccountPage.userClickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 16: Verify error message 'The new address has been added successfully.' is displayed");
		assertEquals(myAccountPage.getBarNotificationSuccessMessage(driver),
				"The new address has been added successfully.");

	}

	@Test(enabled = true)
	public void My_Account_03_Change_Password(Method method) {

		ExtentTestManager.startTest(method.getName(), "Change password");
		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 01: Open Change Password Page");
		myAccountPage.openPageOfMyAccountListByName("Change password");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 02: Input to field Old Password");
		myAccountPage.userInputToTextboxByID(driver, "OldPassword", loginPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 03:  Input to field New Password");
		myAccountPage.userInputToTextboxByID(driver, "NewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 04: Input to field Confirm Password");
		myAccountPage.userInputToTextboxByID(driver, "ConfirmNewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 05: Click to button CHANGE PASSWORD");
		myAccountPage.userClickToButtonByText(driver, "Change password");
		System.out.println("Old Password:" + loginPassword);
		System.out.println("New Password: " + newPassword);
		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 06: Verify error message 'Password was changed' is displayed");
		assertEquals(myAccountPage.getBarNotificationSuccessMessage(driver), "Password was changed");

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 07: Click to button Cancel in bar- notification");
		myAccountPage.clickToCloseButtonAtBarNotificationMessage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 08: Click to button Log out");
		myAccountPage.userOpenPageAtHeaderLinkByClassName(driver, "ico-logout");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 09: Click to button Log in");
		homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 10: Input to field Email");
		loginPage.userInputToTextboxByID(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 11: Input to field Password with Old Password");
		loginPage.userInputToTextboxByID(driver, "Password", loginPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 12: Click to button Log in");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 13: Verify Log in was failed");
		assertEquals(loginPage.getValidationErrorMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 14: Input to field Password with New Password");
		loginPage.userInputToTextboxByID(driver, "Password", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 15: Click to button Log in");
		loginPage.userClickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 16: Verify Log in was successful");
		loginPage.isLoginSuccessful();

	}

	@Test(enabled = true)
	public void My_Account_04_My_Product_Reviews(Method method) {

		ExtentTestManager.startTest(method.getName(), "Verify added review is displayed in My Account Page");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 01: Click to Random Category Page");
		productPage = NopCommercePageGeneratorManager.getUserProductPage(driver);
		productPage.selectRandomProductPage();

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 02: Select sub-category item");
		productPage.selectRandomProduct();
		homePage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 03: Click to textlink Add review");
		homePage.clickToAddReviewTextlink();

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 04: Input Review Title");
		homePage.userInputToTextboxByID(driver, "AddProductReview_Title", title);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 05: Input review Content");
		homePage.sendKeysToReviewTextArea(reviewContent);

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 06: Click to button SUBMIT REVIEW");
		homePage.userClickToButtonByText(driver, "Submit review");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 07: Click to My Account Textlink");
		myAccountPage = (UserMyAccountPageObject) homePage.userOpenPageAtHeaderLinkByClassName(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "My Account  - Step 08: Open My Product Review Page");
		myAccountPage.openPageOfMyAccountListByName("My product reviews");

		ExtentTestManager.getTest().log(Status.INFO,
				"My Account  - Step 09: Verify title is displayed in My Product Review");
		assertTrue(myAccountPage.isReviewAddedSuccess(title));

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
