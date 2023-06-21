package pageUIs.nopCommerce.admin;

public class AdminBasePageUI {
	public static final String DYNAMIC_NAVI_ITEM_TREEVIEW_BY_TEXT = "xpath=//li[@class='nav-item has-treeview']/a/i/following-sibling::p[contains(text(),'%s')]/parent::a";
	public static final String DYNAMIC_CHILD_NAVI_ITEM_BY_TEXT = "xpath=//i/following-sibling::p[text()=' %s']/parent::a";
	public static final String DYNAMIC_INPUT_TYPE_TEXTBOX_BY_TEXT = "xpath=//label[text()='%s']/parent::div/parent::div/following-sibling::div//input";
	public static final String DYNAMIC_CHECKBOX_BY_TEXT = "xpath=//label[text()='%s']/parent::div/parent::div/following-sibling::div/input[@class='check-box']";
	public static final String DYNAMIC_DROPDOWN_BY_TEXT = "xpath=//label[text()='%s']/parent::div/parent::div/following-sibling::div/select";
	public static final String DATA_TABLE_EMPTY_MESSAGE = "xpath=//tr/td[@class='dataTables_empty']";

}
