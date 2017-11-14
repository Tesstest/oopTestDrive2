package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "com.drive2:id/startup_authorization_email_button")
	WebElement btnEmail;

	public LoginPage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	              /*searchContext is a WebDriver or WebElement
	              instance *///an instance of PageObject.class);
	}

	public boolean btnEmailIsPresent() {

		boolean elBtnEmailIsPresent = false;

		try {
			WebElement elBtnEmail = waitForVisibilityOfElement(btnEmail, 60);
			elBtnEmailIsPresent = elBtnEmail.isDisplayed();
			exTest.log(LogStatus.INFO, "Found button Email...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Button 'Почта' is not found...");
			System.out.println("Button Email is not found");
		}

		return elBtnEmailIsPresent;
	}

	public LoginByEmailPage clickOnBtnEmail() {

		btnEmail.click();
		exTest.log(LogStatus.INFO, "Tapped on button Email...");

		LoginByEmailPage loginByEmail = new LoginByEmailPage(driver, exTest);

		return loginByEmail;

	}

	public LoginByFacebookPage clickOnBtnFB() {
		LoginByFacebookPage loginByFB = null;

		try {

			By btnFB = new By.ById("com.drive2:id/startup_authorization_facebook_button");
			waitForElement(btnFB, 60).click();
			loginByFB = new LoginByFacebookPage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Facebook...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Button Facebook is not found...");
			System.out.println("Button Facebook is not found");

		}

		return loginByFB;
	}

	public LoginByVKontaktePage clickOnBtnVK() {
		// driver.findElement(By.id("com.drive2:id/startup_authorization_vk_button")).click();
		LoginByVKontaktePage loginByVK = null;

		try {
			By btnVK = new By.ById("com.drive2:id/startup_authorization_vk_button");
			waitForElement(btnVK, 60).click();
			loginByVK = new LoginByVKontaktePage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tap on button VKontakte...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Button VKontakte is not found...");
			System.out.println("Button VK is not found");

		}

		return loginByVK;
	}

	// this method belongs pageFB and should move out
	public boolean CheckPageFBisOpened() {
		boolean faceBookPageIsDesplayed = false;

		By pageFB = new By.ByXPath("//android.widget.Button[contains(@content-desc, 'Log In')]");
		WebElement elPageFB = waitForElement(pageFB, 60);
		faceBookPageIsDesplayed = elPageFB.isDisplayed();

		return faceBookPageIsDesplayed;

	}

	// this method belongs pageFB and should move out
	public LoginPage closePageFB() {
		By closePageFB = new By.ByXPath("(//android.widget.ImageView)[1]");
		WebElement elClosePageFB = waitForElement(closePageFB, 60);
		elClosePageFB.click();

		return this;
	}

	// this method belongs pageFB and should move out
	public LoginPage closePageVK() {
		By closePageVK = new By.ById("com.drive2:id/startup_web_close_button");
		WebElement elClosePageVK = waitForElement(closePageVK, 60);
		elClosePageVK.click();

		return this;
	}

	public void clickOnSignUp() {
		driver.findElement(By.id("com.drive2:id/startup_authorization_sign_up_button")).click();

	}

}
