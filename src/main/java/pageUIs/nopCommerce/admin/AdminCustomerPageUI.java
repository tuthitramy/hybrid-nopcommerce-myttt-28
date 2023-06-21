package pageUIs.nopCommerce.admin;

public class AdminCustomerPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//a[@class='btn btn-primary']";
	public static final String RANDOM_GENDER_RADIO_BUTTON = "xpath=//div[@class='form-check']//input[@name='Gender']";
	public static final String DATE_OF_BIRTH_TEXTBOX = "xpath=//input[@name='DateOfBirth']";
	public static final String ADMIN_COMMENT_TEXTAREA = "xpath=//textarea[@name='AdminComment']";
	public static final String CUSTOMER_ROLE_DROPDOWN = "xpath=//select[@id='SelectedCustomerRoleIds']";
	public static final String SAVE_CONTINUE_EDIT_BUTTON = "xpath=//button[@name='save-continue']";
	public static final String CREATE_SUCCESS_MESSAGE = "xpath=//div[@class='alert alert-success alert-dismissable']";
	public static final String BACK_TO_CUSTOMER_LIST_BUTTON = "xpath=//a[text()='back to customer list']";
	public static final String BACK_TO_CUSTOMER_DETAIL_BUTTON = "xpath=//a[text()='back to customer details']";
	public static final String DELETE_BUTTON_CUSTOMER_ROLE = "xpath=//span[@title='delete']";
	public static final String CLOSE_BUTTON_AT_SUCCESS_MESSAGE = "xpath=//div[@class='alert alert-success alert-dismissable']//button[@class='close']";
	public static final String SEARCH_BUTTON = "xpath=//button[@id='search-customers']";
	public static final String CUSTOMER_NAME_TEXT = "xpath=//tbody/tr//td[3]";
	public static final String CUSTOMER_EMAIL_TEXT = "xpath=//tbody/tr//td[2]";
	public static final String DYNAMIC_CUSTOMER_ROW_BY_CUSTOMER_NAME = "xpath=//td[text()='%s']/parent::tr";
	public static final String DYNAMIC_ADDRESS_BY_CUSTOMER_EMAIL = "xpath=//td[text()='%s']//parent::tr";
	public static final String EDIT_BUTTON = "xpath=//a[contains(text(),'Edit')]";
	public static final String SAVE_BUTTON = "xpath=//button[@name='save']";
	public static final String ADD_NEW_ADDRESS_BUTTON = "xpath=//div[@id='customer-address']//button[@class='btn btn-primary']";
	public static final String RANDOM_COUNTRY = "xpath=//select[@id='Address_CountryId']//option";
	public static final String SAVE_ADDRESS_BUTTON = "xpath=//button[contains(text(),'Save')]";
	public static final String ADDRESS_OPEN_BUTTON = "xpath=//div[contains(text(),'Addresses')]//following-sibling::div/button//i[@class='fa toggle-icon fa-plus']";
	public static final String ADDRESS_CLOSE_BUTTON = "xpath=//div[contains(text(),'Addresses')]//following-sibling::div/button//i[@class='fa toggle-icon fa-minus']";
	public static final String DELETE_ADDRESS_BUTTON = "xpath=//a[text()='Delete']";
	public static final String NO_DATA_ADDRESS_MESSAGE = "xpath=//div[text()='Addresses']/parent::div/following-sibling::div//td[@class='dataTables_empty']";

}
