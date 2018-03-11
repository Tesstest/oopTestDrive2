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

		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		currentTitle = getScreenTitle(locatorTitle, 90);

		exTest.log(LogStatus.INFO, "Verify title...");
		return currentTitle;
	}

	public PageChat findAndTapOnUser(String serviceUserName) {

		boolean userIsFounded = false;
		By serviceUser = new By.ByXPath("//android.widget.TextView[contains(@text, 'Dina')]");
		try {
			WebElement elUser = waitForElement(serviceUser, 10);
			elUser.click();
			userIsFounded = true;

		} catch (Exception e) {
			System.out.println("will swipe...");
		};

		if (!userIsFounded){
			for (int i = 0; i < 3; i++){
				swipeDown(60, -40);
				try {
					WebElement elUser = waitForElement(serviceUser, 10);
					elUser.click();
					userIsFounded = true;

					break;
				} catch (Exception e) {
					System.out.println("swipe # " + i);
				}
			}
		}
		if (userIsFounded){
			exTest.log(LogStatus.INFO, "Tap on dialog with service-user...");

		} else {
			exTest.log(LogStatus.ERROR, "Service-user is not found!");

		}

		return this;
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

	public PageChat sendPhoto(File file) {

		try {



			By locatorMessage = new By.ById("com.drive2:id/chat_message");
			//waitForElement(locatorMessage, 20).sendKeys(file);

			exTest.log(LogStatus.INFO, "Type text-message...");

		} catch (Exception e) {
			String textError = "Could not type a message ";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);
		}

		return this;
	}

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

    public Gallery tapOnButtonPhoto() {
		Gallery gallery = null;
		By btnPhoto = new By.ById("com.drive2:id/chat_attachment_button");

		try {
			waitForElement(btnPhoto, 30);
			gallery = new Gallery(driver, exTest);
			exTest.log(LogStatus.INFO, "Tap on btn Photo...");

		} catch (Exception e) {
			String textError = "Could not find btn Photo!";
			System.out.println(textError);
			exTest.log(LogStatus.ERROR, textError);

		}
		return gallery;
	}
}