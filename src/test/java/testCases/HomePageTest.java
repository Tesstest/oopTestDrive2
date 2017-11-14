package testCases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.ExtentFactory;
import common.SetupConnection;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginByEmailPage;
import pages.LoginPage;
import pages.MainMenu;

public class HomePageTest extends SetupConnection {
    ExtentTest exTestParent;
    ExtentReports exReport;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws InterruptedException {
        exReport = ExtentFactory.getInstance();
        exTestParent = exReport.startTest("Home Page");

        // For debugging
        exTest = exReport.startTest("Login by Email");
        exTestParent.appendChild(exTest);

        LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();
        loginByEmail
                .typeEmail("dforsp@gmail.com")
                .typePassword("qAlityAssurance")
                .clickOnButtonEnter(false);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        exReport.endTest(exTestParent);
        exReport.flush();
    }

    @Test(groups = { "HomePage" }, priority = 1)//, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatHomeScreenIsOpened() throws InterruptedException {
        boolean carOfTheDayIsPresent = false;
        exTest = exReport.startTest("Home Page Is Opened");
        exTestParent.appendChild(exTest);

        //MainMenu mainMenu = new MainMenu(driver, exTest);
        //HomePage homePage = mainMenu.clickOnBtnHome();
        HomePage homePage = new HomePage(driver, exTest);
        carOfTheDayIsPresent = homePage.carOfTheDayIsPresent();

        Assert.assertTrue(carOfTheDayIsPresent);
        exTest.log(LogStatus.PASS, "Verified That Home Page is opened");
    }

    @Test(groups = { "HomePage" }, priority = 2, enabled = false) // , dependsOnMethods = {
    // "verifyThatHomeScreenIsOpened"
    // })
    public void verifyThatCarOfTheDayIsOpened() throws InterruptedException {

        String nameOfCarOnPageHome = "Empty car name on Home Page";
        String nameOfCarOnPageCar = "Empty car name";

        exTest = exReport.startTest("Page Of The Car Of The Day Is Opened");
        exTestParent.appendChild(exTest);

        HomePage homePage = new HomePage(driver, exTest);
        WebElement car    = homePage.getCarOfTheDay();

        nameOfCarOnPageHome = homePage.getNameCarOfTheDay(car);
        nameOfCarOnPageCar  = homePage.clickOnCarOfTheDay(car).getNameOfTheCar();

        Assert.assertEquals(nameOfCarOnPageCar, nameOfCarOnPageHome);
        exTest.log(LogStatus.PASS, "Verified That page of the car of the day is opened");
    }

}
