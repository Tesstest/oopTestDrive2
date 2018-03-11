package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginByVKontaktePage extends BasePage {

    AndroidDriver<WebElement> driver;

    public LoginByVKontaktePage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
        super(driver, exTest);
    }

    public LoginByVKontaktePage typeEmail(String email) {

        try {
            By elementEmail = By.className("android.widget.EditText");////////////////////
            waitForElements(elementEmail, 30).get(0).sendKeys(email);
            exTest.log(LogStatus.INFO, "Typed email...");

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Couldn't type email!");
        }

        return this;
    }

    public LoginByVKontaktePage typePassword(String password) {

        try {
            By elementPassword = By.className("android.widget.EditText");
            exTest.log(LogStatus.INFO, "Typed password...");
            waitForElements(elementPassword, 30).get(1).sendKeys(password);

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Couldn't type password!");
        }

        return this;
    }

    public HomePage clickOnButtonLogin() throws InterruptedException {
        HomePage homePage = null;

        try {
            By btnLogIn = new By.ByXPath("//android.widget.Button[@content-desc ='Log in']");
            waitForElement(btnLogIn, 30).click();
            homePage = new HomePage(driver, exTest);

        } catch (Exception e) {
            exTest.log(LogStatus.ERROR, "Button LogIn on page VKontacte is not found!");
        }

        return homePage;
    }

    public LoginByVKontaktePage clickOnForgotPassword() {
        return this;
    }

    public LoginPage clickOnClose() {
        WebElement btnClose = driver.findElement(By.id("com.drive2:id/startup_web_close_button"));
        btnClose.click();
        LoginPage loginPage = new LoginPage(driver, exTest);

        return loginPage;
    }
}
