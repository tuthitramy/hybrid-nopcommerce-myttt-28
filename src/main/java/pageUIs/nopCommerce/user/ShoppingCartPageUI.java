package pageUIs.nopCommerce.user;

public class ShoppingCartPageUI {
	public static final String PRODUCT_TITLE = "xpath=//tr//td[@class='product']//a[@class='product-name']";
	public static final String EDIT_TEXTLINK = "xpath=//div[@class='edit-item']//a";
	public static final String DYNAMIC_PRODUCT_NAME_BY_NAME_AT_SHOPPING_CART_PAGE = "xpath=//td[@class='product']/a[text()='%s']";
	public static final String DYNAMIC_PRICE_BY_NAME_AT_SHOPPING_CART_PAGE = "xpath=//a[text()='%s']//parent::td[@class='product']//following-sibling::td[@class='subtotal']/span";
	public static final String REMOVE_BUTTON = "xpath=//button[@class='remove-btn']";
	public static final String QUANTITY_TEXTBOX = "xpath=//input[@class='qty-input']";
	public static final String TOTAL_PRICE = "xpath=//span[@class='value-summary']/strong";
	public static final String PRODUCT_PRICE = "xpath=//td[@class='unit-price']//span";
	public static final String UPDATE_SHOPPING_CART_BUTTON = "xpath=//button[@id='updatecart']";
	public static final String ACCEPT_TERM_OF_SERVICE_CHECKBOX = "xpath=//input[@id='termsofservice']";
	public static final String DYNAMIC_CONTINUE_BUTTON_AT_BILLING_ADDRESS_BY_ONCLICK_TYPE = "xpath=//button[text()='Continue' and @onclick='%s']";
	public static final String SHIPPING_METHOD_RADIO_BUTTON = "xpath=//ul[@class='method-list']//div[@class='method-name']//input";
	public static final String BILLING_ADDRESS_COUNTRY_DROPDOWN = "xpath=//select[@id='BillingNewAddress_CountryId']//option";
	public static final String SHIPPING_METHOD_AT_CONFIRM_INFORMATION = "xpath=//li[@class='shipping-method']//span[@class='value']";
	public static final String SUCCESS_ORDER_MESSAGE = "xpath=//div[@class='section order-completed']//div[@class='title']/strong";
	public static final String ORDER_NUMBER_AT_ORDER_INFOR = "xpath=//div[@class='order-number']//strong";
	public static final String ORDER_NUMBER_AT_ORDER_COMPLETED_PAGE = "xpath=//div[@class='order-number']//strong";
	public static final String CREDIT_CARD_PAYMENT_METHOD = "xpath=//div[@class='payment-details']//input[@value='Payments.Manual']";
	public static final String CREDIT_CARD_TYPE_DROPDOWN = "xpath=//select[@id='CreditCardType']//option";
	public static final String EXPIRED_YEAR_DROPDOWN = "xpath=//select[@id='ExpireYear']";
	public static final String WRONG_CARD_NUMBER_MESS = "xpath=//div[@class='message-error validation-summary-errors']//li[text()='Wrong card number']";
	public static final String CARD_NUMBER_TEXTBOX = "xpath=//input[@id='CardNumber']";
	public static final String BILLING_ADDRESS_DROPDOWN = "xpath=//select[@id='billing-address-select']";
	public static final String NEXT_DAY_AIR_RADIOBUTTON = "xpath=//div[@class='method-name']//label[text()='Next Day Air ($0.00)']//preceding-sibling::input";
	public static final String CHECK_OUT_BUTTON = "xpath=//button[@id='checkout']";
}
