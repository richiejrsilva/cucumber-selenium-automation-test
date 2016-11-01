package pt.richiesilva.browser.automation.util;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pt.richiesilva.browser.automation.page.Page;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;
import pt.richiesilva.browser.automation.util.enums.HtmlTagsAtributes;

/**
 * PageHandler is responsible to handle pages actions and its elements
 * For parallel test execution method shouldn't be static or should be synchronized
 */
public class PageHandler {
	
	private static final Logger LOG = LogManager.getLogger(PageHandler.class);
	/**
	 * @param driver
	 * @param page
	 */
	public static void initPage(WebDriver driver, Page page) {
			PageFactory.initElements(driver, page);
	}

	/**
	 * @param driver
	 * @param url
	 */
	public static void openPage(WebDriver driver, String url) {	
		driver.get(url);
	}
	
	/**
	 * @param url
	 */
	public static void NavigateTo(WebElement url) {
		url.click();
	}
	
	/**
	 * @param navigator
	 * @param linkText
	 * @param subXpath
	 * @return
	 */
	public static WebElement getLinkByText(List<WebElement> linkList, String linkText, String subXpath) {

		WebElement link = null;

		//We can catch exception here. For example element not found
		//And handle it according to needed. For example capture a screen shot
		
		for (WebElement navLink : linkList) {
			
			if ((navLink.getAttribute(HtmlTagsAtributes.HREF.value()).trim().length() != 0) 
			&& navLink.findElement(By.xpath(subXpath)).getAttribute(HtmlTagsAtributes.INNER_TEXT.value()).equals(linkText)) {
				link = navLink;
				break;
			}
			
		}

		return link;
	}

	/**
	 * @param navigator
	 * @param linkText
	 * @return
	 */
	public static WebElement getLinkByText(List<WebElement> linkList, String linkText) {
		
		//We can catch exception here. For example element not found
		//And handle it according to needed. For example capture a screen shot
		
		WebElement urlLink = null;
		
		for (WebElement navUrl : linkList) {
			
			if ((navUrl.getAttribute(HtmlTagsAtributes.HREF.value()).trim().length() != 0) 
				&& (navUrl.getText().trim().equals(linkText))) {//finding the right url link 
				urlLink = navUrl;
				break;
			}
			
		}
		
		return urlLink;
	}

	/**
	 * JS click to handle invisible possible cases
	 * @param clickable
	 * @param driver
	 */
	public static void navigateToIgnoreInvisible(WebElement clickable, WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(HtmlTagsAtributes.JSCRIPT_ARG_SPEC_CLICK.value(), clickable);
		
	}

	/**
	 * @param element
	 * @param linkText
	 * @return
	 */
	public static WebElement findElementByLinkText(WebElement element ,String linkText) {
		
		WebElement e = null;
		
		try {
			e = element.findElement(By.linkText(linkText));
		} catch (NoSuchElementException ex) {
			
			LOG.log(Level.ERROR, ApplicationConstants.ERROR_OCCURS_AT.value() + "findElementByLinkText -> "
					+ ex.getMessage());
			//Handle it according to needed. For example capture a screen shot
		}
		return e;
	}

	/**
	 * @param element
	 * @param xpathExpression
	 * @return
	 */
	public static String getElementTextByXPath(WebElement element, String xpathExpression) {
		
		String result = null;

		try {
			result = element.findElement(By.xpath(xpathExpression)).getText();
		} catch (NoSuchElementException ex) {

			LOG.log(Level.ERROR, ApplicationConstants.ERROR_OCCURS_AT.value()
					+ "findElementByLinkText -> " + ex.getMessage());
			//Handle it according to needed. For example capture a screenshot
		}
		return result;
		
	}
	
}
