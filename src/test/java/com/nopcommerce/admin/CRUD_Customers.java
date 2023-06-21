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
import pageObject.nopCommerce.admin.AdminCustomerPageObject;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class CRUD_Customers extends BaseTest {
	private AdminDashboardPageObject dashboardPage;
	private AdminCustomerPageObject customerPage;
	private AdminLoginPageObject loginPage;
	String password = DataHelper.getDataHelper().getValidPassword();
	String email = DataHelper.getDataHelper().getEmailAddress();
	String firstName = DataHelper.getDataHelper().getFirstName();
	String lastName = DataHelper.getDataHelper().getLastName();
	String companyName = DataHelper.getDataHelper().getCompanyName();
	String comment = DataHelper.getDataHelper().getRandomString();
	String customerName = firstName + " " + lastName;
	String address01 = DataHelper.getDataHelper().getAddress1();
	String cityName = DataHelper.getDataHelper().getCityName();
	String ZIP = DataHelper.getDataHelper().getPostalCode();
	String cardNumber = DataHelper.getDataHelper().getRandomCardNumber();
	private String phoneNumber;

	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = NopCommercePageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = loginPage.clickToLoginButton();
		phoneNumber = loginPage.getRandomPhoneNumber();

	}

	@Test(enabled = true)
	public void Customer_01_Create_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Create New Customer");

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 01: Click to Customers Menu");
		dashboardPage.clickToNaviItemTreeviewByText(driver, "Customers");

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer  - Step 02: Navigate to Customers Page");
		dashboardPage.clickToChildNaviItemByText(driver, "Customers");
		customerPage = NopCommercePageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 03: Click To Add new Button");
		customerPage.clickToAddNewCustomerButton();

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 04: Input to Email Field");
		customerPage.adminInputToTextboxByText(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 05: Input to Password Field");
		System.out.println("Password: " + password);
		customerPage.adminInputToTextboxByText(driver, "Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 06: Input to First Name Field");
		customerPage.adminInputToTextboxByText(driver, "First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 07: Input to Last Name Field");
		customerPage.adminInputToTextboxByText(driver, "Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 08: Select Random Gender");
		customerPage.selectRandomGender();

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 09: Input to Company name Field");
		customerPage.adminInputToTextboxByText(driver, "Company name", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 10: Select Random Date of Birth");
		customerPage.selectRandomDateOfBirth();

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 11: Select Random Customer Roles");
		customerPage.clickToDeleteRoleButton();
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 12:Input to Admin Comment TextArea");
		customerPage.inputToAdminCommentTextArea(comment);

		ExtentTestManager.getTest().log(Status.INFO,
				"Create Customer - Step 13:Click To Save and Continue Edit button");
		customerPage.clickToSaveAndContinueEditButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Create Customer - Step 14:Verify message 'The new customer has been added successfully.' is displayed");
		assertTrue(customerPage.getCreateSuccessMessage().contains("The new customer has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO,
				"Create Customer - Step 15: Click button Close Button At Message Bar");
		customerPage.clickToCloseButtonAtMessage();

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 16: Click button Back To Customer List");
		customerPage.clickToBackToCustomerListButton();

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 17: Select Guests in Dropdown");
		customerPage.clickToDeleteRoleButton();
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 18: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Create Customer - Step 19: Verify customer name is displayed in Search result");
		String customerName = firstName + " " + lastName;
		System.out.println("Customer name :" + customerName);
		assertTrue(customerPage.isCustomerNameContainsInSearchResult(driver, customerName));

	}

	@Test(enabled = true)
	public void Customer_02_Search_Customer_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search Customer with Email");
		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 01: Input Email to textbox Email");
		customerPage.adminInputToTextboxByText(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 02: Select Guests in Dropdown");
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search - Step 03: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search - Step 04: Verify customer is displayed in search result");
		System.out.println("Customer name :" + customerName);
		assertTrue(customerPage.isCustomerRowDisplayedByName(customerName));

	}

	@Test(enabled = true)
	public void Customer_03_Search_Customer_FirstName_And_LastName(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search Customer with Email");
		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search  - Step 01: Input FirstName to textbox FirstName");
		customerPage.adminInputToTextboxByText(driver, "First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 02: Input LastName to textbox LastName");
		customerPage.adminInputToTextboxByText(driver, "Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 03: Select Guests in Dropdown");
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search- Step 04: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search - Step 05: Verify customer is displayed in search result");
		System.out.println("Customer name :" + customerName);
		assertTrue(customerPage.isCustomerRowDisplayedByName(customerName));

	}

	@Test(enabled = true)
	public void Customer_04_Search_Full_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search Customer with Full Data");

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 01: Input email to textbox Email");
		customerPage.adminInputToTextboxByText(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search  - Step 02: Input FirstName to textbox FirstName");
		customerPage.adminInputToTextboxByText(driver, "First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 03: Input LastName to textbox LastName");
		customerPage.adminInputToTextboxByText(driver, "Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search  - Step 04: Input Company Name to textbox Conpany");
		customerPage.adminInputToTextboxByText(driver, "Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 05: Select Guests in Dropdown");
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search - Step 06: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer Search - Step 07: Verify customer is displayed in search result");
		System.out.println("Customer name :" + customerName);
		assertTrue(customerPage.isCustomerRowDisplayedByName(customerName));
	}

	@Test(enabled = true)
	public void Customer_05_Edit_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Edit Customer");

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer  - Step 01: Click To Edit Button");
		customerPage.clickToEditButton();

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 02: Input to Email Field");
		System.out.println("Edit" + email);
		customerPage.adminInputToTextboxByText(driver, "Email", "Edit" + email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 03: Input to First Name Field");
		System.out.println("Edit" + firstName);
		customerPage.adminInputToTextboxByText(driver, "First name", "Edit " + firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 04: Input to Last Name Field");
		System.out.println("Edit" + lastName);
		customerPage.adminInputToTextboxByText(driver, "Last name", "Edit " + lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 05: Input to Company name Field");
		System.out.println("Edit" + companyName);
		customerPage.adminInputToTextboxByText(driver, "Company name", "Edit " + companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 06: Select Random Date of Birth");
		customerPage.selectRandomDateOfBirth();

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 07:Input to Admin Comment TextArea");
		customerPage.inputToAdminCommentTextArea("Edit " + comment);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 08:Click To Save button");
		customerPage.clickToSaveButton();
		ExtentTestManager.getTest().log(Status.INFO,
				"Edit Customer - Step 09:Verify message 'The customer has been updated successfully.' is displayed");
		assertTrue(customerPage.getCreateSuccessMessage().contains("The customer has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer  - Step 10: Input email to textbox Email");
		customerPage.adminInputToTextboxByText(driver, "Email", "Edit" + email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer  - Step 11: Input FirstName to textbox FirstName");
		customerPage.adminInputToTextboxByText(driver, "First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer  - Step 12: Input LastName to textbox LastName");
		customerPage.adminInputToTextboxByText(driver, "Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer  - Step 13: Input Company Name to textbox Conpany");
		customerPage.adminInputToTextboxByText(driver, "Company", "Edit " + companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 14: Select Guests in Dropdown");
		customerPage.clickToDeleteRoleButton();
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 15: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit Customer - Step 16: Verify customer is displayed in search result");
		String editCustomerName = "Edit " + firstName + " " + "Edit " + lastName;
		System.out.println("Customer name :" + editCustomerName);
		assertTrue(customerPage.isCustomerRowDisplayedByName(editCustomerName));

	}

	@Test(enabled = true)
	public void Customer_06_Add_New_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add New Address");
		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 01: Click To Edit Button");
		customerPage.clickToEditButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 02: Scroll to Address Header");
		customerPage.clickToOpenAddressTable();
		customerPage.sleepInSecond(5);
		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 03: Click To Add New Address Button");
		customerPage.clickToAddNewAddressButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add New Address  - Step 04: Input FirstName to textbox FirstName");
		customerPage.adminInputToTextboxByText(driver, "First name", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 05: Input LastName to textbox LastName");
		customerPage.adminInputToTextboxByText(driver, "Last name", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Customer Search  - Step 06: Input email to textbox Email");
		customerPage.adminInputToTextboxByText(driver, "Email", email);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 07: Input to Company name Field");
		customerPage.adminInputToTextboxByText(driver, "Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 08: Select random Country");
		customerPage.selectRandomCountry();

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 09: Input City ");
		customerPage.adminInputToTextboxByText(driver, "City", cityName);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 10: Input Address1 ");
		customerPage.adminInputToTextboxByText(driver, "Address 1", address01);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 11: Input ZIP ");
		customerPage.adminInputToTextboxByText(driver, "Zip / postal code", address01);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 12: Input Phone Number ");
		customerPage.adminInputToTextboxByText(driver, "Phone number", phoneNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address  - Step 13: Click to save address button ");
		customerPage.clickToSaveAddressButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Add New Address  - Step 14:Verify message 'The new address has been added successfully.' is displayed");
		assertTrue(customerPage.getCreateSuccessMessage().contains("The new address has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO,
				"Add New Address - Step 15: Click button Close Button At Message Bar");
		customerPage.clickToCloseButtonAtMessage();

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 16: Click button Back To Customer List");
		customerPage.clickToBackToCustomerDetailsButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add New Address - Step 17: Click to Address Header");
		customerPage.clickToOpenAddressTable();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add New Address - Step 18: Verify address information is displayed");
		assertTrue(customerPage.isCustomerRowDisplayedByEmail(email));

	}

	@Test(enabled = true)
	public void Customer_07_Edit_Address(Method method) {

		ExtentTestManager.startTest(method.getName(), "Edit address in customer detail");

		ExtentTestManager.getTest().log(Status.INFO, "Edit address  - Step 01: Navigate to Customers Page");
		customerPage.clickToChildNaviItemByText(driver, "Customers");
		customerPage = NopCommercePageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 02: Input to Email Field");
		customerPage.adminInputToTextboxByText(driver, "Email", "Edit" + email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 03: Input to First Name Field");
		customerPage.adminInputToTextboxByText(driver, "First name", "Edit " + firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 04: Input to Last Name Field");
		customerPage.adminInputToTextboxByText(driver, "Last name", "Edit " + lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 05: Input to Company name Field");
		customerPage.adminInputToTextboxByText(driver, "Company", "Edit " + companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 06: Select Guests in Dropdown");
		customerPage.clickToDeleteRoleButton();
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 07: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 08: Click To Edit Button");
		customerPage.clickToEditButton();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 09: Click To Edit Address Button");
		customerPage.clickToOpenAddressTable();
		customerPage.sleepInSecond(5);
		customerPage.clickToEditButton();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 10: Input to Email Field");
		customerPage.adminInputToTextboxByText(driver, "Email", "Edit" + email);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 11: Input to First Name Field");
		customerPage.adminInputToTextboxByText(driver, "First name", "Edit " + firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 12: Input to Last Name Field");
		customerPage.adminInputToTextboxByText(driver, "Last name", "Edit " + lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 13: Input to Company name Field");
		customerPage.adminInputToTextboxByText(driver, "Company", "Edit " + companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 14: Click To Save button");
		customerPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit address - Step 15:Verify message 'The address has been updated successfully.' is displayed");
		assertTrue(customerPage.getCreateSuccessMessage().contains("The address has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO,
				"Edit address - Step 16: Click button Close Button At Message Bar");
		customerPage.clickToCloseButtonAtMessage();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 17: Click button Back To Customer Details");
		customerPage.clickToBackToCustomerDetailsButton();

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 18: Click to Address Header");
		customerPage.clickToOpenAddressTable();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Edit address - Step 19: Verify address information is displayed");
		assertTrue(customerPage.isCustomerRowDisplayedByEmail("Edit" + email));

	}

	@Test(enabled = true)
	public void Customer_08_Delete_Address(Method method) {

		ExtentTestManager.startTest(method.getName(), "Delete address in customer detail");

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 01: Navigate to Customers Page");
		customerPage.clickToChildNaviItemByText(driver, "Customers");
		customerPage = NopCommercePageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 02: Input to Email Field");
		customerPage.adminInputToTextboxByText(driver, "Email", "Edit" + email);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 03: Input to First Name Field");
		customerPage.adminInputToTextboxByText(driver, "First name", "Edit " + firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 04: Input to Last Name Field");
		customerPage.adminInputToTextboxByText(driver, "Last name", "Edit " + lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 05: Input to Company name Field");
		customerPage.adminInputToTextboxByText(driver, "Company", "Edit " + companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 06: Select Guests in Dropdown");
		customerPage.clickToDeleteRoleButton();
		customerPage.selectCustomerRoles("Guests");

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 07: Click To Search Button");
		customerPage.clickToSearchButton();
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 08: Click To Edit Button");
		customerPage.clickToEditButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Delete address - Step 09: Click To button delete to delete address");
		customerPage.clickToDeleteAddressButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Delete address - Step 10: Click To button OK in confirm delete alert");
		customerPage.acceptAlert(driver);
		customerPage.sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, "Delete address - Step 11: Verify delete address successfully");
		assertEquals(customerPage.getNoDataAddressMessage(), "No data available in table");

	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
//		closeBrowserDriver();
	}

}
