package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainMenu extends BasePage{

	public MainMenu(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//android.widget.ImageView)[1]")
	@CacheLookup
	private WebElement btnHome;

	@FindBy(xpath = "(//android.widget.ImageView)[2]")
	@CacheLookup
	private WebElement btnNewsFeed;

	@FindBy(xpath = "(//android.widget.ImageView)[3]")
	@CacheLookup
	private WebElement btnChat;

	@FindBy(xpath = "(//android.widget.ImageView)[4]")
	@CacheLookup
	private WebElement btnBell;

	@FindBy(xpath = "(//android.widget.ImageView)[5]")
	@CacheLookup
	private WebElement btnSandwich;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	@CacheLookup
	private WebElement ArrowBack;

	@FindBy (xpath="//android.widget.TextView[contains(@content-desc, '']")
	@CacheLookup
	private WebElement btnPlus;

	public HomePage clickOnBtnHome() {
		// Have to tap twice to make sure that upload an initial page (cleaning
		// from previous action)

		HomePage homePage = null;
		try {
			getContext("NATIVE");
			WebElement elBtnHome = waitForVisibilityOfElement(btnHome, 20);
			elBtnHome.click();

			// Have to tap twice to make sure that upload an initial page
			// (cleaning from previous action)
			elBtnHome.click();

			homePage = new HomePage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Home");

		} catch (Exception e) {
			System.out.println("Button Home is not found!");
			exTest.log(LogStatus.ERROR, "Button Home is not found!");
		}
		return homePage;
	}

	public PageChat clickOnBtnChat() {

		PageChat pageChat = null;

		try {
			WebElement elBtnChat = waitForVisibilityOfElement(btnChat, 60);
			elBtnChat.click();
			pageChat = new PageChat(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Chat");

		} catch (Exception e) {
			System.out.println("Button Chat is not found!");

			exTest.log(LogStatus.ERROR, "Button Chat is not found!");
		}
		return pageChat;
	}
//
//	public PageNewsFeed clickOnBtnNewsFeed() {
//
//		PageNewsFeed pageNewsFeed = null;
//
//		try {
//			WebElement elBtnNewsFeed = waitForVisibilityOfElement(btnNewsFeed, 20);
//			elBtnNewsFeed.click();
//			pageNewsFeed = new PageNewsFeed(driver, exTest);
//
//			exTest.log(LogStatus.INFO, "Tapped on button News Feed...");
//
//		} catch (Exception e) {
//			System.out.println("Button News Feed is not found!");
//
//			exTest.log(LogStatus.ERROR, "Button News Feed is not found!");
//		}
//
//		return pageNewsFeed;
//
//	}
//
//	public PageNotifications clickOnBtnBell() {
//
//		PageNotifications pageNotifications = null;
//
//		try {
//			WebElement elBtnBell = waitForVisibilityOfElement(btnBell, 20);
//			elBtnBell.click();
//			pageNotifications = new PageNotifications(driver, exTest);
//
//			exTest.log(LogStatus.INFO, "Tapped on button Bell...");
//
//		} catch (Exception e) {
//			System.out.println("Button Bell is not found!");
//
//			exTest.log(LogStatus.ERROR, "Button Bell is not found!");
//		}
//		return pageNotifications;
//	}
//
//	public PageMore clickOnBtnSandwich() {
//		PageMore pageMore = null;
//		try {
//			WebElement elBtnSandwich = waitForVisibilityOfElement(btnSandwich, 60);
//			elBtnSandwich.click();
//			pageMore = new PageMore(driver, exTest);
//
//			exTest.log(LogStatus.INFO, "Tapped on button Sandwich");
//
//		} catch (Exception e) {
//
//			System.out.println("Button Sandwich is not found!");
//			exTest.log(LogStatus.ERROR, "Button Sandwich is not found!");
//		}
//		return pageMore;
//	}

	public void clickOnArrowBack() {

		try {
			WebElement elArrowBack = waitForVisibilityOfElement(ArrowBack, 20);
			elArrowBack.click();

			exTest.log(LogStatus.INFO, "Tapped on Arrow back...");

		} catch (Exception e) {
			System.out.println("Button Arrow Back is not found!");

			exTest.log(LogStatus.ERROR, "Button Arrow Back is not found!");
		}
	}
}
