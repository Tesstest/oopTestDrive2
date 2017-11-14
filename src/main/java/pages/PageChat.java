package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.io.File;

public class PageChat extends BasePage {

	public PageChat(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
	}

	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'Сообщения')]")
	WebElement title;

	@FindBy(id = "com.drive2:id/item_chat_title")
	WebElement userDialog;


	public String getTheTitle() {
		String currentTitle = "";

		//System.out.println( driver.getContext());
		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		if (driver.findElement(locatorTitle).isDisplayed()){

		}
		currentTitle = getScreenTitle(locatorTitle, 90);

		exTest.log(LogStatus.INFO, "Verify title...");

		return currentTitle;
	}

	public PageChat tapOnUser(String serviceUserName) {

		try {
			WebElement elUser = driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + serviceUserName + "\"));");
			elUser.click();

			exTest.log(LogStatus.INFO, "Tap on dialog with service-user...");

		} catch (Exception e) {
			String textError = "Could not find service-user " + serviceUserName;
			System.out.println(textError);

			exTest.log(LogStatus.ERROR, textError);
		}

		return this;

		/*
		 * ("new UiScrollable(new UiSelector()"
		 * +"resourceID(\"com.drive2:id/item_chat_title\")).scrollIntoView("
		 * +"new UiSelector().text(\"BusinessSupport\"));").click();
		 * 
		 */

		/*
		 * WebElement elUser = method.waitForElements(locator, timeout)
		 * 
		 * TouchAction action = new TouchAction(driver); action.moveTo(elUser);
		 */

		/*
		 * By userLocator = By.xpath("android.widget.TextView");
		 * System.out.println(".. PageChat.tapOnUser .... try to find");
		 * List<WebElement> users = method.waitForElements(userLocator, 90);
		 * WebElement serviceUser = null;
		 * 
		 * System.out.println("length is " + users.size());
		 * 
		 * for (WebElement user : users){ String userName = user.getText();
		 * 
		 * System.out.println("Usr name is " + userName);
		 * 
		 * if( userName == serviceUserName){ serviceUser = user; break; } else{
		 * exTest.log(LogStatus.ERROR, "Service user is not found!");
		 * System.out.println("Service user is not found!"); }
		 * 
		 * }
		 */

		// String stringXpath = "//android.widget.TextView and @text = " +
		// userName;
		// By dialogWithUser = By.xpath(stringXpath);
		// WebElement elDialogWithUser = method.waitForElement(dialogWithUser,
		// 60);
		// elDialogWithUser.click();

		// WebElement elUserDialog =
		// method.waitForVisibilityOfElement(userDialog, 60);
		// elUserDialog.click();

	}

	public PageChat tapOnButtonSendMessage() {

		try {
			By btnSend = new By.ById("com.drive2:id/chat_send_button");
			waitForElement(btnSend, 20).click();

			exTest.log(LogStatus.INFO, "Tap on button Send message...");

		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		return this;
	}

	public PageChat typeTextMessage(String text) {

		try {
			By locatorMessage = new By.ById("com.drive2:id/chat_message");
			waitForElement(locatorMessage, 20).sendKeys(text);

			exTest.log(LogStatus.INFO, "Type text-message...");

		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}

		return this;

	}

//	public PageChat sendPhoto(File file) {

//		try {
//			By locatorMessage = new By.ById("com.drive2:id/chat_message");
//			waitForElement(locatorMessage, 20).sendKeys(file);
//
//			exTest.log(LogStatus.INFO, "Type text-message...");
//
//		} catch (Exception e) {
//			String textError = "Could not type a message ";
//			System.out.println(textError);
//			exTest.log(LogStatus.ERROR, textError);
//		}
//
//		return this;
//	}

	public String findSentMessage(String text) {

		String sentMessage = "";
		try {
			By locatorSentMessage = new By.ByXPath(
					"//android.widget.TextView[@resource-id = 'com.drive2:id/chat_message_text' " + "and @text = '"
							+ text + "']");
			sentMessage = waitForElement(locatorSentMessage, 60).getText();

			exTest.log(LogStatus.INFO, "Find text-message in sent messages...");

		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		return sentMessage;
	}

	public PageChat tapOnArrowBack() throws InterruptedException {

		try {
			By btnSend = new By.ByXPath("//android.widget.ImageButton[@content-desc = 'Navigate up']");
			waitForElement(btnSend, 20).click();
			System.out.println("Find arrow");
			exTest.log(LogStatus.INFO, "Tap on arrow back...");

		} catch (Exception e) {
			String textError = "Could not tap on arrow back!";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}
		Thread.sleep(9000);
		return this;
	}
	public PageChat swipe() {
		Dimension screenSize;
		screenSize = driver.manage().window().getSize();

		int startx = screenSize.width / 2;
		int starty = (int) (screenSize.height * 0.80);
		int endy = (int) (screenSize.height * 0.20);

		TouchAction action = new TouchAction(driver);

		action.press(startx, starty).moveTo(0, -1000).release().perform();

		return this;
	}

}
