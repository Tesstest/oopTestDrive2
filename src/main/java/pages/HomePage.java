package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private WebElement elCarOfTheDay;

    public HomePage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
        super(driver, exTest);
    }

    public WebElement getCarOfTheDay() {
        By carOfTheDay = new By.ByXPath("//div[@class='c-cotd']//a[1]");

        try {
            elCarOfTheDay = waitForElement(carOfTheDay, 60);
            exTest.log(LogStatus.INFO, "Car of the day is found...");

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Car of the day is NOT found!");
            System.out.println("Car of the day is NOT found!");
        }
        return elCarOfTheDay;
    }

    public boolean carOfTheDayIsPresent() {
        boolean carOfTheDayIsPresent = false;

        try {
           getContext("WEBVIEW");
           String textCarOfTheDay = getCarOfTheDay().getAttribute("innerHTML");

            if (textCarOfTheDay.contains("Машина дня")) {
                carOfTheDayIsPresent = true;
                exTest.log(LogStatus.INFO, "Verified text 'Машина дня'...");
            } else {
                exTest.log(LogStatus.ERROR, "Text 'Машина дня' is not found...");
            }
        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Element Car Of The Day is not found...");
        }

        return carOfTheDayIsPresent;
    }

    public String getNameCarOfTheDay(WebElement elCar) {
        String nameCarOfTheDay = "";

        try {
            // String wholeTextCarOfTheDay =
            // getCarOfTheDay().getAttribute("innerHTML");
            String wholeTextCarOfTheDay = elCar.getAttribute("innerHTML");
            char ch = '>';
            int beginIndex = wholeTextCarOfTheDay.lastIndexOf(ch) + 1;
            nameCarOfTheDay = wholeTextCarOfTheDay.substring(beginIndex).trim();

            exTest.log(LogStatus.INFO, "Name of the car is " + nameCarOfTheDay);
            System.out.println(nameCarOfTheDay);

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Couldn't get name of the car!");
            System.out.println("Couldn't get name of the car");
        }
        return nameCarOfTheDay;
    }

    public PageCar clickOnCarOfTheDay(WebElement elCar) throws InterruptedException {
        PageCar carPage = null;

        try {
            elCar.click();
            carPage = new PageCar(driver, exTest);

            exTest.log(LogStatus.INFO, "Tapped on the car of the day...");

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Couldn't click on the car of the day!");
            System.out.println("Couldn't click on the car of the day!");
        }
        return carPage;
    }

    public String getTheTitle() {
        String className = this.getClass().getName();
        String currentTitle = "";

        By locatorTitle = new By.ByXPath("//android.widget.TextView[@index='0']");
        currentTitle = getScreenTitle(locatorTitle, 90);

        exTest.log(LogStatus.INFO, "Verified title of the page. Title is " + currentTitle);
        return currentTitle;
    }
}
