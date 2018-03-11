package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageMore extends BasePage {

	public PageMore(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text='Выход']")
	private WebElement exit;

	@FindBy(xpath = "//android.widget.TextView[@text='Личный блог']")
	private WebElement blog;

	@FindBy(xpath = "//android.widget.TextView[@text='Мои машины']")
	private WebElement myCars;

	@FindBy(xpath = "//android.widget.TextView[@text='Гости']")
	private WebElement guests;

	@FindBy(xpath = "//android.widget.TextView[@text='Закладки']")
	private WebElement bookmarks;
	
	public LoginPage exit() {
		// new implementation
		// TouchAction action = new TouchAction(driver);
		// action.moveTo(el)

		LoginPage loginPage = null;
		Dimension screenSize;
		screenSize = driver.manage().window().getSize();

		WebElement elExit = null;

		int startx = screenSize.width / 2;
		int starty = (int) (screenSize.height * 0.80);
		int endy = (int) (screenSize.height * 0.20);

		for (int i = 0; i < 10; i++) {
			try {
				elExit = waitForVisibilityOfElement(exit, 60);
				break;

			} catch (Exception e) {
				// driver.swipe(startx, starty, startx, endy, 5000);
			}
		}

		try {
			elExit.click();
			loginPage = new LoginPage(driver, exTest);

			exTest.log(LogStatus.INFO, "Tapped on button Exit...");

		} catch (Exception e) {
			String textError = "Could not find button Exit";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return loginPage;
	}

	public PagePersonalBlog tapOnPersonalBlog() {

		PagePersonalBlog pagePersonalBlog = null;
		WebElement elPersonalBlog = null;

		Dimension screenSize;
		screenSize = driver.manage().window().getSize();

		int startx = screenSize.width / 2;
		int starty = (int) (screenSize.height * 0.60);
		int endy = (int) (screenSize.height * 0.40);

		for (int i = 0; i < 5; i++) {
			try {
				elPersonalBlog = waitForVisibilityOfElement(blog, 20);
				if (elPersonalBlog.isDisplayed()) {
					// have to swipe because Android considers this element as
					// visible and it is not.
					// driver.swipe(startx, starty, startx, endy, 2000);

					break;
				}

			} catch (Exception e) {
				// driver.swipe(startx, starty, startx, endy, 2000);
			}
		}

		// now if element is found we tap on it
		try {
			elPersonalBlog.click();

			pagePersonalBlog = new PagePersonalBlog(driver, exTest);
			exTest.log(LogStatus.INFO, "Tapped on 'Personal Blog'...");

		} catch (Exception e2) {
			String textError = "Could not find item 'Personal Blog'";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);

		}

		return pagePersonalBlog;
	}

}
