package pt.richiesilva.browser.automation.util;

import java.util.HashMap;


import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import pt.richiesilva.browser.automation.Interfaces.IDriverProvider;
import pt.richiesilva.browser.automation.util.enums.ApplicationConstants;
/**
 * A simple implementation for test purpose. Service pattern could be implemented in a real case
 */
public class ChromeDriverProvider implements IDriverProvider {

	private WebDriver driver;
	
	private DesiredCapabilities capabilities;
	
	@Override
	public void resizeWindow(int width, int height) {
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);	
	}
	
	@Override
	public WebDriver getDriver() {
		
		if (driver != null) {
			return driver;
		}
		//You can get a driver with default chrome specs
		return (capabilities != null) ? (driver = new ChromeDriver(capabilities)) : (driver = new ChromeDriver());
	}
	
	@Override
	public void setupDeviceSecifications(String deviceName) {//just an example of driver configuration for a specific device
		
		//Some devices are not recognized. For example iPhone 6 Plus
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put(ApplicationConstants.DEVICE_NAME.value(), deviceName);
		
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put(ApplicationConstants.MOBILE_EMULATION.value(), mobileEmulation);
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
	}

	@Override
	public void setupDeviceSecifications(int width, int height, float pixelRatio) {
		//you also can specify the mobile metrics for a specific device
		
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put(ApplicationConstants.WITH.value(), width);
		deviceMetrics.put(ApplicationConstants.HEIGHT.value(), height);
		deviceMetrics.put(ApplicationConstants.PIXEL_RATIO.value(), pixelRatio);
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put(ApplicationConstants.DEVICE_METRICS.value(), deviceMetrics);
		// mobileEmulation.put("userAgent",
		// "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		
	} 
	
	@Override
	public void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
}
