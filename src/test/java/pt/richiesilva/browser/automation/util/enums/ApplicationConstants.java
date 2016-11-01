package pt.richiesilva.browser.automation.util.enums;

public enum ApplicationConstants {//the idea is to put all text together separated from pages code
	
	/************ DRIVER SETTING SPECS **********/
	
	WITH("width"),
	HEIGHT("height"),
	PIXEL_RATIO("pixelRatio"),
    DEVICE_NAME("deviceName"),
    DEVICE_METRICS("deviceMetrics"),
	MOBILE_EMULATION("mobileEmulation"),
	
	/****************** BBCPAGE CONSTANTS ***************************/
	
	RELATIVE_XPATH_FOR_FOOTER_TITLE("//h2[contains(@class,'orb-footer-lead')]"),
	PAGE_HEADER_ID_XPATH("orb-header"),
	RELATIVE_XPATH_FOR_FOOTER_RIGTHS_RESERVED_LABEL("//span[contains(@class,'orb-hilight')]"),
	
	TITLE_INDEX(2),
	LABEL_INDEX(0),
	
	/**************************** SPORTPAGE CONSTANTS *****************************************/
	
	SPORTS_NAV_RELATIVE_XPATH(".//span[contains(@class,'link-text') and contains(@class ,'primary-nav-flyout')]"),
	
	/************************************** TEST STEPS LABELS ****************/
	
	HOME_PAGE_TITLE("BBC - Homepage"),
	SPORT_PAGE_TITLE("Home - BBC Sport"),
	SPORTS_PAGE_TITLE(" - BBC Sport"),//sport page title sufix. the prefix is dynamic based on the sport passed on gherkin scenario
	
	FIRST_SPORT_STORY(0),	
	
	/********************************** OTHER CONSTANTS ****************************/
	
	EXPECTED_TITLE_LABEL(" Expected Title: "),
	FOUND_LABEL(" Found: "),
	ERROR_OCCURS_AT("An Error Occurs at: ");
	
	
	/************************************** END OF CONSTANTS ********************/
	
    private String value;
    
    private Integer valueAsInt;
    
    ApplicationConstants(Integer valueAsInt) {
        this.valueAsInt = valueAsInt;
    }

    ApplicationConstants(String url) {
        this.value = url;
    }
    
    public String value() {
        return value;
    }
    
    public Integer valueInteger() {
        return valueAsInt;
    }
}
