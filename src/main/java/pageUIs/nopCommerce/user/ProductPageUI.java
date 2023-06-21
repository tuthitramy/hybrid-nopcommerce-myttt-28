package pageUIs.nopCommerce.user;

public class ProductPageUI {
	public static final String SUB_CATEGORY_ITEM = "xpath=//div[@class='sub-category-item']//h2";
	public static final String DYNAMIC_SUB_CATEGORY_BY_TEXT = "xpath=//h2[@class='title']/a[text()=' %s ']";
	public static final String DYNAMIC_PRODUCT_MENU_TITLE_BY_TEXT = "xpath=//ul[@class='top-menu notmobile']//a[text()='%s ']";
	public static final String PAGESIZE_BY_TEXT = "xpath=//select[@name='products-pagesize']";
	public static final String CURRENT_PAGE = "xpath=//ul//li[@class='current-page']//span";
	public static final String NEXT_PAGE_ICON = "xpath=//ul//li[@class='next-page']//a";
	public static final String PREVIOUS_PAGE_ICON = "xpath=//ul//li[@class='previous-page']//a";
	public static final String DYNAMIC_PAGE_NUMBER_BY_TEXT = "xpath=//ul//li[@class='individual-page']//a[text()='%s']";
	public static final String PAGINATION = "xpath=//div[@class='pager']";
	public static final String DYNAMIC_DETAIL_PRODUCT_TEXTBOX_BY_INDEX = "xpath=//span[@class='required']//parent::div//parent::div//div[%s]//input";
	public static final String DYNAMIC_DETAIL_PRODUCT_TEXTBOX = "xpath=//span[@class='required']//parent::div//parent::div//div//label//following-sibling::input[@type='text' or @type='email']";
	public static final String DYNAMIC_DETAIL_PRODUCT_DROPDOWN = "xpath=//span[@class='required']//parent::dt//following-sibling::dd//select";
	public static final String DYNAMIC_DETAIL_PRODUCT_DROPDOWN_BY_INDEX = "xpath=//div[@class='attributes']//dd[%s]//select//option[not(@value='0')]";
	public static final String DYNAMIC_DETAIL_PRODUCT_RADIOBUTTON = "xpath=//input[@type='radio']//ancestor::ul";
	public static final String DYNAMIC_DETAIL_PRODUCT_RADIOBUTTON_BY_INDEX = "xpath=//div[@class='attributes']//dd[%s]//input[@type='radio']";
	public static final String DYNAMIC_DETAIL_PRODUCT_IMAGE = "xpath=//div[@class='attributes']//dd//ul//li//span[@class='attribute-square-container']//span";
	public static final String PRODUCT_NAME_AT_PRODUCT_DETAIL_PAGE = "xpath=//div[@class='product-name']/h1";
	public static final String ADD_TO_WISHLIST_BUTTON = "xpath=//button[text()='Add to wishlist']";
	public static final String START_DATE_FIELD = "xpath=//div[@class='attribute-data']//input[@id='rental_start_date_40']";
	public static final String END_DATE_FIELD = "xpath=//div[@class='attribute-data']//input[@id='rental_end_date_40']";
	public static final String PRODUCT_TITLE_LINK_AT_PRODUCT_PAGE = "xpath=//h2[@class='product-title']//a";
	public static final String DYNAMIC_ADD_TO_WISHLIST_BY_INDEX_AT_PRODUCT_PAGE = "xpath=//div[@class='item-grid']//div[@class='item-box'][%s]//button[@title='Add to compare list']";
	public static final String ADD_TO_COMPARE_LIST_BUTTON_AT_PRODUCT_PAGE = "xpath=//div[@class='item-grid']//div[@class='item-box']//button[@title='Add to compare list']";
	public static final String ADD_TO_CART_BUTTON = "xpath=//div[@class='add-to-cart-panel']//button[text()='Add to cart']";
	public static final String COMPUTER_TEXTLINK = "xpath=//div[@class='header-menu']//ul[@class='top-menu notmobile']//a[@href='/computers']";
	public static final String BUILD_YOUR_COMPUTER_PRODUCT = "xpath=//h2[@class='product-title']/a[text()='Build your own computer']";
	public static final String DESKTOP_PRODUCT = "xpath=//h2[@class='title']//a[text()=' Desktops ']";
	public static final String PROCESSOR_DROPDOWN = "xpath=//label[text()=' Processor ']//parent::dt//following-sibling::dd/select[@id='product_attribute_1']";
	public static final String RAM_DROPDOWN = "xpath=//label[text()=' RAM ']//parent::dt//following-sibling::dd/select";
	public static final String DYNAMIC_HDD_RADIO_BUTTON_BY_TEXT = "xpath=//label[text()=' HDD ']//parent::dt//following-sibling::dd[1]//ul[@class='option-list']//li//label[text()='%s']//preceding-sibling::input";
	public static final String DYNAMIC_OS_RADIO_BUTTON_BY_TEXT = "xpath=//label[text()=' OS ']//parent::dt//following-sibling::dd[1]//ul[@class='option-list']//li//label[text()='%s']//preceding-sibling::input";
	public static final String QUANTITY_PRODUCT_TEXTBOX = "xpath=//input[@id='product_enteredQuantity_1']";
	public static final String UPDATE_BUTTON = "xpath=//button[text()='Update']";
	public static final String PRODUCT_PRICE_AT_DETAIL_PAGE = "xpath=//div[@class='product-price']//span";
	public static final String NOTEBOOKS_TEXTLINK = "xpath=//h2[@class='title']//a[text()=' Notebooks ']";
	public static final String APPLE_MACBOOK_TEXTLINK = "xpath=//h2[@class='product-title']/a[text()='Apple MacBook Pro 13-inch']";
	public static final String DYNAMIC_SORT_TYPE_BY_TEXT = "xpath=//select[@id='products-orderby']";
	public static final String BAR_NOTIFICATION_ERROR_MESSAGE = "xpath=//div[@class='bar-notification error']";

	// Xpath for sort product
	public static final String PRODUCT_PRICE = "xpath=//div[@class='prices']//span[@class='price actual-price' or @class='price actual-price']";
	public static final String PRODUC_TITLE = "xpath=//div[@class='product-item']//h2[@class='product-title']//a";

}
