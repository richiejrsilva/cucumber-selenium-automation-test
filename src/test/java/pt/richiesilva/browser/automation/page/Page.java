package pt.richiesilva.browser.automation.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *  Assuming (after a quick check) that all pages for BBC domain and
 *  subdomains have an header, a body content and a footer
 *
 */
public abstract class Page {
	
	private WebDriver driver;
	
	private String url;

	private String title;
	
	public Page(WebDriver driver, String url) {
		this.url = url;
		this.driver = driver; 
	}
	
	protected String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	protected WebDriver getDriver() {
		return driver;
	}
	
	protected void setTitle(String title) {
		this.title = title;
	}

	protected abstract WebElement getPageHeader();

	protected abstract WebElement getPageCenterContent();

	protected abstract WebElement getPageFooter();

	protected abstract String getFooterTitle(String title);
	
	protected abstract boolean isFooterValid(List<List<String>> footerComponents);

}
