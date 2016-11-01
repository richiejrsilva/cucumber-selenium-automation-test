package pt.richiesilva.browser.automation.scenario;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
				 strict = false, 
				 features = "classpath:cucumber/features/readSportsStoriesMultiDevice.feature",
				 plugin = {"pretty", "json:target/cucumber/cucumber.json"},
				 tags = { "~@ignore" })
public class BBCSportsStoriesTest {}

//if you want to get the html default report replace the cucumber options above for the options bellow

//@CucumberOptions(monochrome = true, plugin = {"pretty", "html:target/cucumber"}, 
//				features = "classpath:cucumber/features/readSportsStoriesMultiDevice.feature")

//I'm using java 8 to be available run selenium last version. In order to avoid the excpetion "Unsupported major.minor version 52.0"
//But my cucumber implementation follow the standard previous to java 8 (new lambda implementation).
//I'm using jdk 7 on my job