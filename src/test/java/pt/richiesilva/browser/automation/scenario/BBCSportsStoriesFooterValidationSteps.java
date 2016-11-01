package pt.richiesilva.browser.automation.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import pt.richiesilva.browser.automation.page.HomePage;
import pt.richiesilva.browser.automation.page.SportsPage;
import pt.richiesilva.browser.automation.page.SportPage;
import pt.richiesilva.browser.automation.util.ChromeDriverProvider;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BBCSportsStoriesFooterValidationSteps {
	
	ChromeDriverProvider chromeDriverProvider;
	
	private SportsPage sportsPage;
	
	@Before
    public void setup() {
		chromeDriverProvider = new ChromeDriverProvider();
    }
	
	@Given("^my device \"([^\"]*)\"$")
	public void my_device(String deviceName, DataTable deviceSpecs) {
	    
		//you can pass the specs data table values for a specific device 
		//calling the overloaded method setupDeviceSecifications(int width, int height, float pixelRatio)
		chromeDriverProvider.setupDeviceSecifications(deviceName);
		
		assertNotNull(chromeDriverProvider.getDriver());
		
	}

	@When("^open BBC \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" web page$")
	public void open_BBC_web_page(String bbcHomePage, String sports, String sport) {
	    
		sportsPage = new HomePage(chromeDriverProvider.getDriver(), bbcHomePage)
		.navigateTo(SportPage.class, sports)
		.navigateToSport(SportsPage.class, sport);

		//all sport page title use the same prefix sportPagetitle
		assertEquals(sport.toUpperCase() + ApplicationConstants.SPORTS_PAGE_TITLE.value().toUpperCase(), sportsPage.getTitle().toUpperCase());
		
	}

	@Then("^all page footer link are working and labels are according$")
	public void all_page_footer_link_are_working_and_labels_are_according(List<List<String>> footerComponents) {
	    //The labels and links are given by an arguments table
		//With this approach functional people can update the scenario values
		//Note that I'm validating all links and labels in one time.
		//It can be separated into several methods and/or actions to be more precise
		assertTrue(sportsPage.isFooterValid(footerComponents));
	}

	@And("^page footer title is \"([^\"]*)\"$")
	public void page_footer_title_is(String footerTitle) throws Throwable {
		assertEquals(footerTitle, sportsPage.getFooterTitle(footerTitle));
	}	

	@And("^there is a footer rights reserved label \"([^\"]*)\"$")
	public void there_is_a_footer_rights_reserved_label(String rightsReservedLabel) {
		assertEquals(rightsReservedLabel, sportsPage.getRightsReservedLabel(rightsReservedLabel));
	}
	
	@After
	public void clean() {
		chromeDriverProvider.closeDriver();
		chromeDriverProvider = null;
		sportsPage = null; 
	}

}
