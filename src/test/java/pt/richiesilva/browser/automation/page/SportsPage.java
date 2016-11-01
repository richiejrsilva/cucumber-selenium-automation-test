package pt.richiesilva.browser.automation.page;

import org.openqa.selenium.WebDriver;

public class SportsPage extends SportPage {
	
	private String selectedSport;
	
	public SportsPage(WebDriver driver, String url, String selectedSport) {
		super(driver, url);
		this.selectedSport = selectedSport;
	}

	public String getSportSelected() {
		return selectedSport;
	}

}