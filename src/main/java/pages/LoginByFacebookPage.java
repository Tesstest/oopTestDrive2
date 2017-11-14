package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginByFacebookPage extends BasePage{

	AndroidDriver<WebElement> driver;

	public LoginByFacebookPage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
	}

	public LoginByFacebookPage typeEmail(String email) {

		try {
			By elementEmail = By.xpath("//android.widget.EditText[contains(@text, 'Email')]");
			waitForElement(elementEmail, 30).sendKeys(email);
			driver.hideKeyboard();

			exTest.log(LogStatus.INFO, "Typed email...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't type email!");
		}

		return this;
	}

	public LoginByFacebookPage typePassword(String password) {

		try {
			By elementPassword = By.xpath("//android.widget.EditText[contains(@text, 'Password')]");
			waitForElement(elementPassword, 30).sendKeys(password);
			driver.hideKeyboard();

			exTest.log(LogStatus.INFO, "Typed password...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't type password!");
		}

		return this;
	}

	public HomePage clickOnButtonLogin() throws InterruptedException {
		boolean foundbtnLogin = true;
		HomePage homePage = null;
		// method.getContext("WEBVIEW");
		try {

			// By elButtonLogIn = new By.ByXPath("//button[@type='button' and
			// @value='Log In']"); //@type='submit' and
			By elButtonLogIn = new By.ByXPath("//android.widget.Button[contains(@content-desc,'Log')]");
			waitForElement(elButtonLogIn, 60).click();
			exTest.log(LogStatus.INFO, "Tapped on button Log In on Facebook Page...");

			homePage = new HomePage(driver, exTest);
		} catch (Exception e) {
			foundbtnLogin = false;
			exTest.log(LogStatus.ERROR, "Button 'logIn' on Facebook Page is not found!");
		}

		/*
		 * Sometimes we need additional action - tap on button Continue (depends
		 * on FB settings)
		 */
		if (foundbtnLogin) {
			try {
				// method.getContext("WEBVIEW");
				// By elButtonContinue = new
				// By.ByXPath("//button[@value='Continue']");

				By elButtonContinue = new By.ByXPath("//android.widget.Button");
				waitForElement(elButtonContinue, 60).click();
				exTest.log(LogStatus.INFO, "Tapped on button Continue on Facebook Page...");

			} catch (Exception e) {
				exTest.log(LogStatus.INFO, "Button 'Continue' on Facebook Page is not found!");
			}
		}
		return homePage;
	}

	public LoginByFacebookPage clickOnForgotPassword() {
		return this;

	}

}
