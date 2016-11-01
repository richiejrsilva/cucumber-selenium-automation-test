package pt.richiesilva.browser.automation.page;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pt.richiesilva.browser.automation.util.PageHandler;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;

public abstract class BBCPage extends Page {

	private static final Logger LOG = LogManager.getLogger(BBCPage.class);
	
	//@FindBy tags does not allow to use the enum values. 
	//So I decide to leave the element locator constants here because there are not a lot.
	private static final String PAGE_HEADER_ID_XPATH = "orb-header";
	
	private static final String PAGE_CENTER_ID_XPATH = "orb-modules";
	
	private static final String PAGE_FOOTER_ID_XPATH = "orb-footer";
	
	private static final String HEADER_NAV_RELATIVE_XPATH = ".//li[starts-with(@class,'orb-nav-')]//a";
	
	@FindBy(id = PAGE_HEADER_ID_XPATH)
	private WebElement pageHeader;

	@FindBy(id = PAGE_CENTER_ID_XPATH)
	private WebElement pageCenterContent;

	@FindBy(id = PAGE_FOOTER_ID_XPATH)
	private WebElement pageFooter;

	@FindBy(xpath = HEADER_NAV_RELATIVE_XPATH)
	private List<WebElement> navigatorMenu;

	public BBCPage(WebDriver driver, String url) {

		super(driver, url);
		
		if (getDriver() != null && !getDriver().getCurrentUrl().equals(url))//to differentiate a page to be open and the link click
			PageHandler.openPage(getDriver(), getUrl());
		
		PageHandler.initPage(getDriver(), this);

		setTitle(getDriver().getTitle());

	}

	/**
	 * This method serves the sub page which has an equal footer
	 *  like Sports main page and all specific sport page.
	 */
	@Override
	public boolean isFooterValid(List<List<String>> footerComponents) {
	
		boolean isFooterValid = true;
		
		Iterator<List<String>> i = footerComponents.iterator();
		
		while (i.hasNext()) {

			List<String> linkToCheck = i.next();

			WebElement footerLink = PageHandler.findElementByLinkText(getPageFooter(), linkToCheck.get(ApplicationConstants.LABEL_INDEX.valueInteger()));
			
			if (footerLink == null) {
				return false;
			}
			
			PageHandler.navigateToIgnoreInvisible(footerLink, getDriver());
			
			LOG.log(Level.DEBUG, ApplicationConstants.EXPECTED_TITLE_LABEL.value() + 
			linkToCheck.get(ApplicationConstants.TITLE_INDEX.valueInteger()) + ApplicationConstants.FOUND_LABEL.value() + getDriver().getTitle());
			
			//it also can be checked by response 404 not found as example or by url etc
			if (!getDriver().getTitle().contains(linkToCheck.get(ApplicationConstants.TITLE_INDEX.valueInteger()))) {
				return false;
			}
			
			getDriver().navigate().back();
			
		}

		return isFooterValid;
	}

	@Override
	protected WebElement getPageHeader() {
		return pageHeader;

	}

	@Override
	protected WebElement getPageCenterContent() {
		return pageCenterContent;

	}

	@Override
	protected WebElement getPageFooter() {
		return pageFooter;

	}

	@Override
	public String getFooterTitle(String title) {
		return PageHandler.getElementTextByXPath(getPageFooter(), ApplicationConstants.RELATIVE_XPATH_FOR_FOOTER_TITLE.value());
	}
	
	/**
	 * @return
	 */
	protected List<WebElement> getNavigatorMenu() {
		return navigatorMenu;
	}

	/**
	 * @param rightsReservedLabel
	 * @return
	 */
	public String getRightsReservedLabel(String rightsReservedLabel) {
		return PageHandler.getElementTextByXPath(getPageFooter(), ApplicationConstants.RELATIVE_XPATH_FOR_FOOTER_RIGTHS_RESERVED_LABEL.value());
	}
	
	/**
	 * @param page
	 * @param linkText
	 * @return
	 */
	public <T extends Page> T navigateTo(Class<T> page, String linkText){
		
		T newPage = null;
		
		WebElement navUrl = PageHandler.getLinkByText(getNavigatorMenu(), linkText);
		
		PageHandler.NavigateTo(navUrl);
		
	    try {
	    	
	    	newPage = page.getDeclaredConstructor(WebDriver.class, String.class)
	    				  .newInstance(getDriver(), getDriver().getCurrentUrl());
	    	
		} catch (InstantiationException | IllegalAccessException//java 8 allows multiple exceptions in line
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) { 
			
			LOG.log(Level.ERROR, e.getMessage());// handle here according to the expected
			
		}
	    
		return newPage;
	}

}


