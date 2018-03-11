package testCases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.ExtentFactory;
import common.SetupConnection;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import testNGFeatures.RetryAnalyzer;

//@Listeners(MethodListeners.class)
public class LoginTest extends SetupConnection {

	// private AndroidDriver<WebElement> driver;
	ExtentTest exTestParent;
	ExtentReports exReport;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		exReport = ExtentFactory.getInstance();
		exTestParent = exReport.startTest("Login/Reject login/Logout");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		exReport.endTest(exTestParent);
		exReport.flush();
	}

	@Test(priority = 1)
	public void verifyRejectLoginByEmailIsPerformed() {

		exTest = exReport.startTest("Reject Login");
		exTestParent.appendChild(exTest);

		boolean btnEmailIsPresent = false;

		LoginPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail().clickOnReturn();

		btnEmailIsPresent = loginByEmail.btnEmailIsPresent();
		if (btnEmailIsPresent) {
			exTest.log(LogStatus.PASS, "Verifed reject Login");

		} else {
			exTest.log(LogStatus.FAIL, "Verifed reject Login");
		}

		Assert.assertEquals(btnEmailIsPresent, true);
	}

	@Test(groups = { "RestartApp" }, priority = 2, enabled = true)
	public void verifyThatSystemRejectsWrongCredentials() throws InterruptedException {

		exTest = exReport.startTest("Reject Wrong Credentials");
		exTestParent.appendChild(exTest);

		boolean wrongCredentials = true;
		String expectedTitle = "Вход";

		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

		loginByEmail.typeEmail("d@g.com").typePassword("1").clickOnButtonEnter(wrongCredentials); // homePage

		String currentTitle = loginByEmail.getTheTitle();

		if (currentTitle.equals(expectedTitle)) {
			exTest.log(LogStatus.PASS, "Verified System Rejected Wrong Credentials");

		} else {
			exTest.log(LogStatus.ERROR, "Found title does not matches to the expected");
			exTest.log(LogStatus.FAIL, "Verified System Rejected Wrong Credentials");
		}

		Assert.assertEquals("Вход", currentTitle);
		loginByEmail.clickOnReturn();

	}

	@Test(groups = { "RestartApp", "NeededLogout" }, priority = 3, enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void verifyLoginByVKontakteIsPerformed() throws InterruptedException {

		exTest = exReport.startTest("Login By VKontakte");
		exTestParent.appendChild(exTest);

		String expectedTitle = "DRIVE2.RU";

		LoginByVKontaktePage loginByVK = new LoginPage(driver, exTest).clickOnBtnVK();

		HomePage homePage = loginByVK.typeEmail("dforshop@gmail.com").typePassword("qAlityAssurance17")
				.clickOnButtonLogin();

		Thread.sleep(2000);
		homePage = new HomePage(driver, exTest);
		boolean carOfTheDayIsPresent = homePage.carOfTheDayIsPresent();
		if (carOfTheDayIsPresent){
			exTest.log(LogStatus.PASS, "Verify Login By VKontakte Is Performed");
		} else {
			exTest.log(LogStatus.ERROR,
				"Car of the day is not found");
			exTest.log(LogStatus.FAIL, "Verify Login By VKontakte Is Performed");
		}
		Assert.assertTrue(carOfTheDayIsPresent);
		//		String currentTitle = homePage.getTheTitle();
//
//		if (currentTitle.equals(expectedTitle)) {
//			exTest.log(LogStatus.PASS, "Verify Login By VKontakte Is Performed");
//
//		} else {
//			exTest.log(LogStatus.ERROR,
//					"Found title does not matches to the expected...Current title is " + currentTitle);
//			exTest.log(LogStatus.FAIL, "Verify Login By VKontakte Is Performed");
//		}
//		Assert.assertEquals(currentTitle, "DRIVE2.RU");
	}

	@Test(groups = { "RestartApp", "NeededLogout" }, priority = 4, enabled = false, retryAnalyzer = RetryAnalyzer.class)
	public void verifyLoginByFacebookIsPerformed() throws InterruptedException {

		exTest = exReport.startTest("Login By Facebook");
		exTestParent.appendChild(exTest);

		String expectedTitle = "DRIVE2.RU";

		LoginByFacebookPage loginByFB = new LoginPage(driver, exTest).clickOnBtnFB();

		HomePage homePage = loginByFB.typeEmail("dforshop@gmail.com").typePassword("qAlityAssurance17")
				.clickOnButtonLogin();

		Thread.sleep(2000);
		String currentTitle = homePage.getTheTitle();

		if (currentTitle.equals(expectedTitle)) {
			exTest.log(LogStatus.PASS, "Verified Login By Facebook Is Performed");

		} else {
			exTest.log(LogStatus.ERROR, "Found title does not matches to the expected");
			exTest.log(LogStatus.FAIL, "Verified Login By Facebook Is Performed");

		}
		Assert.assertEquals(currentTitle, "DRIVE2.RU");
	}

	@Test(groups = { "Critical" }, priority = 5, enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void verifyLoginByEmailIsPerformed() throws InterruptedException {

		exTest = exReport.startTest("Login By Email");
		exTestParent.appendChild(exTest);

		boolean wrongCredentials = false;
		String expectedTitle = "DRIVE2.RU";

		LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

		HomePage homePage = loginByEmail.typeEmail("dforsp@gmail.com").typePassword("qAlityAssurance")
				.clickOnButtonEnter(wrongCredentials);

		Thread.sleep(2000);

		String currentTitle = homePage.getTheTitle();
		if (currentTitle.equals(expectedTitle)) {
			exTest.log(LogStatus.PASS, "Verified Login By Email Is Performed");

		} else {
			exTest.log(LogStatus.ERROR, "Found title does not matches to the expected");
			exTest.log(LogStatus.FAIL, "Verified Login By Email Is Performed");
		}
		Assert.assertEquals(currentTitle, "DRIVE2.RU");
	}
}
