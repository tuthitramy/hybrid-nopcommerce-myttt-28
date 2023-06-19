package pageUIs.nopCommerce.user;

public class UserBasePageUI {
	public static final String DYNAMIC_PAGE_AT_HEADER_AREA = "xpath=//div[@class='header-links']//a[@class='%s']";
	public static final String DYNAMIC_PAGE_AT_FOOTER_AREA = "xpath=//div[@class='footer-upper']//a[text()='%s']";

	// Register page, Login Page
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";

	// Register page,
	public static final String VALIDATION_SUMMARY_ERROR_MESSAGE = "xpath=//div[@class='message-error validation-summary-errors']";
	public static final String TITLE_AT_HEADER_MENU = "xpath=//ul[@class='top-menu notmobile']/li/a";

	public static final String CLOSE_BUTTON_AT_NOTIFICATION_BAR_MESSAGE = "xpath=//span[@title='Close']";
	public static final String NO_DATA_MESSAGE = "xpath=//div[@class='page-body']//div[@class='no-data']";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "xpath=//select[@id='%s']/option";

}
