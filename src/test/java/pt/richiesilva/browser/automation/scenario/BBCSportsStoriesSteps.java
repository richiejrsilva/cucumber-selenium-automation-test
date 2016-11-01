package pt.richiesilva.browser.automation.scenario;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import pt.richiesilva.browser.automation.page.HomePage;
import pt.richiesilva.browser.automation.page.SportsPage;
import pt.richiesilva.browser.automation.page.SportPage;
import pt.richiesilva.browser.automation.util.ChromeDriverProvider;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BBCSportsStoriesSteps {
	
	ChromeDriverProvider chromeDriverProvider;
	
	private HomePage homePage;
	
	private SportPage sportPage;
	
	private SportsPage sportsPage;
	
	@Before
    public void setup() {
		chromeDriverProvider = new ChromeDriverProvider();
    }
	
	@Given("^the device \"([^\"]*)\"$")
	public void the_device(String deviceName, DataTable deviceSpecs) {
	    
		//you can pass the specs data table values for a specific device 
		//calling the overloaded method setupDeviceSecifications(int width, int height, float pixelRatio)
		chromeDriverProvider.setupDeviceSecifications(deviceName);
		
		assertNotNull(chromeDriverProvider.getDriver());
		
	}

	@When("^open BBC Home Page \"([^\"]*)\"$")
	public void open_BBC_Home_Page(String bbcHomePageUrl) {
		 
		homePage = new HomePage(chromeDriverProvider.getDriver(), bbcHomePageUrl);
		   
		assertEquals(ApplicationConstants.HOME_PAGE_TITLE.value(), homePage.getTitle());//We also can validate the page url
		
	}

	@When("^navigate to \"([^\"]*)\"$")
	public void navigate_to(String sportNavMenuItem) {
		
		//generic navigate method to allow navigate to all pages on the navigator
		sportPage = homePage.navigateTo(SportPage.class, sportNavMenuItem);
		
		assertEquals(ApplicationConstants.SPORT_PAGE_TITLE.value(), sportPage.getTitle());
		
	}

	@When("^choose a specific sport \"([^\"]*)\"$")
	public void choose_a_specific_sport(String sportLinkText) {
		
		//generic sport navigate method to allow navigate to all sport pages on the sport navigator
		sportsPage = sportPage.navigateToSport(SportsPage.class, sportLinkText);
		
		//all sport page title use the same prefix sportPagetitle
		assertEquals( sportLinkText.toUpperCase() + ApplicationConstants.SPORTS_PAGE_TITLE.value().toUpperCase(), sportsPage.getTitle().toUpperCase());
		
	}

	@Then("^a sports news story at the first headline can be read \"([^\"]*)\"$")
	public void a_sports_news_story_at_the_first_headline_can_be_read(String currentSportStoryTitle) {
	    
		//Note that the validation is based on the sport story title. 
		//It need to be changed when the bbc sport story title changes 
		//(see on bbc.com the story name and change on gherkin scenario according)
		sportsPage.moveToSportStory(ApplicationConstants.FIRST_SPORT_STORY.valueInteger());
		
		//I put to assertions just to check that there is a story and is the right one
		assertNotNull(sportsPage.getSelectedSportStory());//confirm that there is a story
		
		assertTrue(sportsPage.getSportStoryTitle().contains(currentSportStoryTitle));//confirm that is the first one
	}

	@After
	public void clean() {
		chromeDriverProvider.closeDriver();
		chromeDriverProvider = null;
		homePage = null;
		sportPage = null;
		sportsPage = null; 
	}

}
