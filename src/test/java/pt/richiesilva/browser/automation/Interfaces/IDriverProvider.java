package pt.richiesilva.browser.automation.Interfaces;

import org.openqa.selenium.WebDriver;

/**
 * Each different driver can implements this interface. Firefox, IE, Android etc. 
 * This is only an implementation idea to share generic drivers methods for all driver implementations
 * In a real case a service driver pattern could be implemented
 *
 */
public interface IDriverProvider {

	void closeDriver();
	/**
	 * @param width
	 * @param height
	 */
	void resizeWindow(int width, int height);
	
	WebDriver getDriver();
	/**
	 * @param deviceName
	 */
	void setupDeviceSecifications(String deviceName);
	/**
	 * @param width
	 * @param height
	 * @param pixelRatio
	 */
	void setupDeviceSecifications(int width, int height, float pixelRatio);
}
