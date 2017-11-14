package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageCar extends BasePage{

	public PageCar(AndroidDriver<WebElement> driver, ExtentTest exTest) {
		super(driver, exTest);
	}

	public String getTheTitle() {
		String currentTitle = "";

		By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
		currentTitle = getScreenTitle(locatorTitle, 90);

		exTest.log(LogStatus.INFO, "Verify title...");

		return currentTitle;
	}

	public String getNameOfTheCar() {
		String nameOfTheCar = "";

		try {
			getContext("NATIVE");
			getContext("WEBVIEW_chrome");

			By car = new By.ByXPath("//h1[@class = 'c-car-info__caption']");
			nameOfTheCar = waitForElement(car, 60).getText().trim();

			exTest.log(LogStatus.INFO, "Got name of the car...");

		} catch (Exception e) {
			exTest.log(LogStatus.ERROR, "Couldn't get name of the car!");
			System.out.println("Couldn't get name of the car!");
		}

		return nameOfTheCar;
	}
}