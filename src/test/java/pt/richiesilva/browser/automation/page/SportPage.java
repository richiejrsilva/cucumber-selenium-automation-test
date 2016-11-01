package pt.richiesilva.browser.automation.page;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pt.richiesilva.browser.automation.util.PageHandler;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;

public class SportPage extends BBCPage {

	private static final Logger LOG = LogManager.getLogger(SportPage.class);
	
	//the page structure is the same for Sports page and for each specific sport.
	//Sport stories are located on the same html block
	
	//@FindBy tags does not allow to use the enum values. 
	//So I decide to leeave the element locator constants here because there are not a lot.
	
	private static final String SPORTS_NAV_MENU_XPATH = ".//div[contains(@class,'primary-nav')]//div[contains(@class,'gel-wrap')]//a[starts-with(@class,'primary-nav')]";
			
	private static final String ALL_SPORTS_BUTTON_XPATH = ".//a[contains(@class,'all-sports-button') and contains(@class,'global-header')]";
			
	private static final String SPORTS_STORIES_LIST_XPATH = "//section[@id='top-stories']//article";
			
	@FindBy(xpath = SPORTS_NAV_MENU_XPATH)
	private List<WebElement> sportsNavigatorMenu;

	@FindBy(xpath = ALL_SPORTS_BUTTON_XPATH)
	private WebElement allSportsButton;
	
	@FindBy(xpath = SPORTS_STORIES_LIST_XPATH)
	private List<WebElement> sportStoriesList;
	
	private WebElement selectedSportStory;
	
	public SportPage(WebDriver driver, String url) {
		super(driver, url);

	}
	
	protected String getSportsNavSubXPath() {
		return ApplicationConstants.SPORTS_NAV_RELATIVE_XPATH.value();
	}

	protected WebElement getAllSportsButton() {
		return allSportsButton;
	}

	protected List<WebElement> getSportStoriesList() {
		return sportStoriesList;
	}

	protected List<WebElement> getSportsNavigatorMenu() {
		return sportsNavigatorMenu;
	}
 
	/**
	 * Return the text link of a selected sport story
	 */
	public String getSportStoryTitle() {
		return selectedSportStory.getText();
	}
	
	/**
	 * Return a sport story available on the page based on its position (example first story = 0)
	 * Note that the page layout is the same for all sport page, Inclusive the main Sports page
	 * That's the reason the method is implemented here and can give us the firts spors headline for each sport
	 */
	public void moveToSportStory(int storyPosition) {
		selectedSportStory = sportStoriesList.get(storyPosition);
	}
	
	/**
	 * org.openqa.selenium.interactions.Actions can be used instead of this way
	 */
	protected void openSportsNavigatorMenu() {
		PageHandler.navigateToIgnoreInvisible(allSportsButton, getDriver());
	}

	/**
	 * You can open Spots main page (inclusive) and all extended pages//classes
	 * @param page
	 * @param sportLinkText
	 * @return
	 */
	public <T extends SportPage> T navigateToSport(Class<T> page, String sportLinkText) {

		T newPage = null;

		openSportsNavigatorMenu();
		
		WebElement navUrl = PageHandler.getLinkByText(getSportsNavigatorMenu(), sportLinkText, getSportsNavSubXPath());

		PageHandler.navigateToIgnoreInvisible(navUrl, getDriver());

		try {

			newPage = page.getDeclaredConstructor(WebDriver.class, String.class, String.class)
					      .newInstance(getDriver(), getDriver().getCurrentUrl(), sportLinkText);
			
		} catch (InstantiationException | IllegalAccessException// java 8 allow multiple exceptions in line
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			
			LOG.log(Level.ERROR, e.getMessage());// handle here according to the expected
			
		}

		return newPage;
	}

	public WebElement getSelectedSportStory() {
		return selectedSportStory;
	}

}
