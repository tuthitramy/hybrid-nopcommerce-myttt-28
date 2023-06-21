package pageUIs.nopCommerce.user;

public class CompareProductPageUI {
	public static final String PRODUCT_NAME = "xpath=//tr[@class='product-name']//label[text()='Name']//parent::td//following-sibling::td";
	public static final String DYNAMIC_PRODUCT_NAME = "xpath=//tr[@class='product-name']//label[text()='Name']//parent::td//following-sibling::td[%s]";
	public static final String CLEAR_LIST_BUTTON = "xpath=//a[text()='Clear list']";

}
