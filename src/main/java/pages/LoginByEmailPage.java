package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginByEmailPage extends BasePage{

	public LoginByEmailPage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
	}

	public LoginByEmailPage typeEmail(String email) {
		// rewrite. It should be execute with creating object
		By elementEmail = By.id("com.drive2:id/email_authorization_email_edit_text");
		waitForElement(elementEmail, 5).sendKeys(email);

		exTest.log(LogStatus.INFO, "Type email...");
		// driver.findElement(elementEmail).sendKeys(email);

		return this;
	}

	public LoginByEmailPage typePassword(String password) {
		driver.findElement(By.id("com.drive2:id/email_authorization_password_edit_text")).sendKeys(password);
		exTest.log(LogStatus.INFO, "Type password...");

		return this;
	}

	public HomePage clickOnButtonEnter(boolean wrongCredentials) throws InterruptedException {
		HomePage homePage = null;

		try {
			By btnEnter = new By.ById("com.drive2:id/progress_button_text");
			waitForElement(btnEnter, 60).click();
			if (!wrongCredentials) {
				homePage = new HomePage(driver, exTest);
			}

			// Thread.sleep(6000);
			exTest.log(LogStatus.INFO, "Tapped on button Enter...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Button Enter is not found!");

		}

		return homePage;
	}

	public LoginByEmailPage clickOnForgotPassword() {
		return this;

	}

	public LoginPage clickOnReturn() {
		LoginPage loginPage = null;

		try {
			By arrowReturn = new By.ByXPath("//android.widget.ImageButton[@content-desc='Navigate up']");
			waitForElement(arrowReturn, 60).click();
			loginPage = new LoginPage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on arrow back...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Arrow back is not found...");
			System.out.println("The arrow back on page VK is not found");

		}

		// WebElement arrowReturn =
		// driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate
		// up']"));
		return loginPage;

	}

	public String getErrorMessage() {
		String currentMessage = "";

		try {
			By errorMessage = new By.ByXPath("//com.drive2:id/email_authorization_wrong_email_or_password");

			WebElement elMessage = waitForElement(errorMessage, 60);
			currentMessage = elMessage.getText();
		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Can't get message");
		}
		return currentMessage;
	}

	public String getTheTitle() {
//#attention - get the title
		String className = this.getClass().getName();
		String currentTitle = "";

		try {
			By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
			currentTitle = getScreenTitle(locatorTitle, 90);
			exTest.log(LogStatus.INFO, "Title of the page is " + currentTitle);

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't get the title on page " + className);

		}
		return currentTitle;
	}

}
