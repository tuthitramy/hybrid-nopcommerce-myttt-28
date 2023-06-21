package pageUIs.nopCommerce.user;

public class SearchPageUI {
	public static final String SEARCH_RESULT_MESSAGE = "xpath=//div[@class='search-results']//div[@class='products-wrapper']//div";
	public static final String ITEM_TITLE_AT_SEARCH_RESULT = "xpath=//div[@class='item-grid']//h2/a";
	public static final String DYNAMIC_CHECKBOX_BY_TEXT = "xpath=//label[text()='%s']//preceding-sibling::input";
	public static final String DYNAMIC_DROPDOWN_BY_TEXT = "xpath=//label[text()='%s']//parent::div//select";
	public static final String SEARCH_BUTTON_AT_PRODUCTPAGE = "xpath=//div[@class='buttons']//button[text()='Search']";
}
