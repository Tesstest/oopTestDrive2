package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PagePersonalBlog extends BasePage {

	public PagePersonalBlog(AndroidDriver<WebElement> driver, ExtentTest exTest) {

		super(driver, exTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@itemprop = 'name']")
	WebElement userName;

	@FindBy(xpath = "//div[@class='js-entity-body']/a[@class='u-link-area']")
	WebElement post;

	@FindBy(xpath = "//div[@class = 'c-post-preview__title']/span/a")
	WebElement postName;

	/*
	 * @FindBy (xpath="//a[@class='c-button' and contains(text(), '��������')]")
	 * WebElement btnChange;
	 */
	@FindBy(xpath = "//span[@class='c-user-card__cars']")
	WebElement userCars;

	@FindBy(xpath = "//a[contains(@class,'c-button--solid') and contains(text(), '��������')]")
	WebElement btnWriteInBlog;

	@FindBy(id = "com.drive2:id/new_post_edit_title")
	WebElement titleOfPost;

	@FindBy(id = "com.drive2:id/new_post_edit_text")
	WebElement fieldtextOfPost;

	@FindBy(id = "com.drive2:id/new_post_info_allow_comments_switch")
	WebElement allowComments;

	@FindBy(id = "com.drive2:id/new_post_info_post_public_switch")
	WebElement showPost;

	@FindBy(xpath = "//android.widget.TextView[@text='�����']")
	WebElement btnMenuFurther;

	// @FindBy (xpath="//android.widget.TextView[@text='��������']")
	@FindBy(id = "com.drive2:id/toolbar_stages_action_next_stage")
	WebElement btnMenuWrite;


	public WebElement findUserName() {
		getContext("NATIVE");
		Dimension screenSize;
		screenSize = driver.manage().window().getSize();

		WebElement elUserName = null;

		int startx = screenSize.width / 2;
		int endy = (int) (screenSize.height * 0.80);
		int starty = (int) (screenSize.height * 0.20);

		for (int i = 0; i < 4; i++) {
			try {
				getContext("WEBVIEW");

				elUserName = waitForVisibilityOfElement(userName, 10);
				System.out.println(i);
				break;

			} catch (Exception e) {
				getContext("NATIVE");

				// driver.swipe(startx, starty, startx, endy, 2000);
			}
		}
		return elUserName;
	}

	public String getUserName() {
		String textUserName = "";

		WebElement userName = findUserName();

		getContext("WEBVIEW");

		try {
			textUserName = userName.getText();
			exTest.log(LogStatus.INFO, "Get user name of Personal Blog - " + textUserName);

		} catch (Exception e1) {
			String textError = "Could not find user of Personal Blog";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}

		return textUserName;
	}

	public PagePersonalBlog tapOnUserName() {
		WebElement userName = findUserName();

		try {
			userName.click();
			exTest.log(LogStatus.INFO, "Tapped on user name...");

		} catch (Exception e) {
			String textError = "Could not tap on user name...";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PagePersonalBlog openPost() {
		// getContext("WEBVIEW");

		try {
			waitForVisibilityOfElement(post, 20).click();
			exTest.log(LogStatus.INFO, "Post of Personal Blog is opened...");

		} catch (Exception e) {
			String textError = "Could not open post in Personal Blog!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);

		}
		return this;
	}

	public boolean findInfoUserCars() {
		boolean buttonIsfound = false;
		getContext("WEBVIEW");

		try {

			buttonIsfound = waitForVisibilityOfElement(userCars, 20).isDisplayed();
			exTest.log(LogStatus.INFO, "Found info about user's cars...");

		} catch (Exception e) {
			String textError = "Could not find info about user's cars! Post was not opened!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);

		}
		return buttonIsfound;
	}

	public PagePersonalBlog tapOnButtonWritePost() {
		getContext("NATIVE");
		Dimension screenSize;
		screenSize = driver.manage().window().getSize();

		WebElement elbtnWriteInBlog = null;

		int startx = screenSize.width / 2;
		int endy = (int) (screenSize.height * 0.80);
		int starty = (int) (screenSize.height * 0.20);

		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(i);
				getContext("WEBVIEW");
				elbtnWriteInBlog = waitForVisibilityOfElement(btnWriteInBlog, 30);

				break;

			} catch (Exception e) {
				getContext("NATIVE");
				// driver.swipe(startx, starty, startx, endy, 2000);
			}
		}

		getContext("WEBVIEW");
		try {
			elbtnWriteInBlog.click();
			exTest.log(LogStatus.INFO, "Tapped on button 'Write post'...");

			getContext("NATIVE");

		} catch (Exception e1) {
			String textError = "Could not find button 'Write post'!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PagePersonalBlog typeTitle(String textOfTitle) {
		try {
			waitForVisibilityOfElement(titleOfPost, 20).sendKeys(textOfTitle);
			exTest.log(LogStatus.INFO, "Title is written...");

		} catch (Exception e) {
			String textError = "Could not type title!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PagePersonalBlog typetext(String textOfPost) {
		try {
			waitForVisibilityOfElement(fieldtextOfPost, 20).sendKeys(textOfPost);
			exTest.log(LogStatus.INFO, "Text is written...");

		} catch (Exception e) {
			String textError = "Could not type text!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PagePersonalBlog setUpSwitchComments(boolean desiredState) {

		boolean currentState;
		try {

			WebElement elSwitch = waitForVisibilityOfElement(allowComments, 20);
			currentState = elSwitch.getText().equals("ON");
			exTest.log(LogStatus.INFO, "Setup switch 'Allow Comment'...");

			if (currentState != desiredState) {
				elSwitch.click();
			}

		} catch (Exception e) {
			String textError = "Could not find switch 'Allow Comment'";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}

		return this;
	}

	public PagePersonalBlog setUpSwitchShowPost(boolean desiredState) {

		boolean currentState;

		try {
			WebElement elSwitch = waitForVisibilityOfElement(showPost, 20);
			currentState = elSwitch.getText().equals("ON");
			exTest.log(LogStatus.INFO, "Setup switch 'Show post'...");

			if (currentState != desiredState) {
				elSwitch.click();
			}

		} catch (Exception e) {
			String textError = "Could not find switch 'Show post'";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}

		return this;
	}

	public PagePersonalBlog tapOnMenuButtonFurther() {
		try {
			waitForVisibilityOfElement(btnMenuFurther, 20).click();
			exTest.log(LogStatus.INFO, "Tapped on menu button 'Further'...");

		} catch (Exception e) {
			String textError = "Could not find menu button 'Further'!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public WebElement findMenuButtonWrite() {
		WebElement elBtnMenuWrite = null;

		try {
			elBtnMenuWrite = waitForVisibilityOfElement(btnMenuWrite, 20);
			exTest.log(LogStatus.INFO, "Found menu button 'Write'...");

		} catch (Exception e) {
			String textError = "Could not find menu button 'Write'!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return elBtnMenuWrite;
	}

	public void tapOnMenuButtonWrite() {
		// MainMenu mainMenu = null;
		WebElement elBtnMenuWrite = findMenuButtonWrite();

		try {
			elBtnMenuWrite.click();
			// mainMenu = new MainMenu(driver, exTest);
			exTest.log(LogStatus.INFO, "Tapped on menu button 'Write'...");

		} catch (Exception e) {
			String textError = "Could not tap menu button 'Write'!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		// return mainMenu;
	}

	public boolean findPost(String postTitle) {
		boolean postIsFound = false;
		getContext("WEBVIEW");

		try {
			By titlePublishedPost = new By.ByXPath("//a[@class = 'c-link' and contains(text(), " + postTitle + ")]");
			waitForElement(titlePublishedPost, 20).click();
			postIsFound = true;

			exTest.log(LogStatus.INFO, "Published post is found: " + postTitle);

		} catch (Exception e) {
			String textError = "Could not find published post!";
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}
		return postIsFound;
	}
}
