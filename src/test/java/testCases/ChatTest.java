package testCases;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import common.ExtentFactory;
import common.SetupConnection;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pages.LoginByEmailPage;
import pages.LoginPage;
import pages.MainMenu;
import pages.PageChat;
import testNGFeatures.RetryAnalyzer;

import static common.SetupConnection.driver;

public class ChatTest extends SetupConnection {

    ExtentTest exTestParent;
    ExtentReports exReport;

    PageChat pageChat = new PageChat(driver, exTest);

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws InterruptedException {
        exReport = ExtentFactory.getInstance();
        exTestParent = exReport.startTest("Chat");

        // For debugging
/*
        exTest = exReport.startTest("Login by Email");
        exTestParent.appendChild(exTest);

        LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

        loginByEmail.typeEmail("dforsp@gmail.com")
                .typePassword("qAlityAssurance").clickOnButtonEnter(false);
*/
        //end for debugging
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        exReport.endTest(exTestParent);
        exReport.flush();
    }

     @Test(groups = {"Chat"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatChatScreenIsOpened() {

        exTest = exReport.startTest("Chat Screen Is Opened");
        exTestParent.appendChild(exTest);

        String ExpectedTitle = "Сообщения";

        MainMenu mainMenu = new MainMenu(driver, exTest);
        pageChat = mainMenu.clickOnBtnChat();

        String currentTitle = pageChat.swipe().getTheTitle();

        Assert.assertEquals(ExpectedTitle, currentTitle);
        exTest.log(LogStatus.PASS, "Verifed That Chat is opened");
    }

    @Test(groups = {"Chat"}, dependsOnMethods = {
            "verifyThatChatScreenIsOpened"}, retryAnalyzer = RetryAnalyzer.class) // ,
    // priority
    // =
    // 2)
    public void verifyThatDialogWithUserIsOpened() {

        exTest = exReport.startTest("Dialog With User Is Opened");
        exTestParent.appendChild(exTest);

        String serviceUserName = "Dina";
        String currentTitle = pageChat.tapOnUser(serviceUserName).getTheTitle();

        Assert.assertEquals(serviceUserName, currentTitle);
        exTest.log(LogStatus.PASS, "Verify That Dialog With User is opened");
    }

    @Test(groups = {"Chat"}, dependsOnMethods = {"verifyThatDialogWithUserIsOpened"},
            retryAnalyzer = RetryAnalyzer.class, enabled = true)
    public void verifyThatTextMessageIsSent() {

        exTest = exReport.startTest("Text Message Is Sent");
        exTestParent.appendChild(exTest);

        DateFormat dateFormat = new SimpleDateFormat();
        Date currentDate = new Date();
        String textMessage = "Test message: " + dateFormat.format(currentDate);

        // PageChat pageChat = new PageChat(driver, exTest);
        String sentMessage = pageChat.typeTextMessage(textMessage).tapOnButtonSendMessage()
                .findSentMessage(textMessage);

        Assert.assertEquals(textMessage, sentMessage);
        exTest.log(LogStatus.PASS, "Verify sending message");
    }
    @Test(groups = {"Chat"}, dependsOnMethods = {"verifyThatDialogWithUserIsOpened"},
            retryAnalyzer = RetryAnalyzer.class)

   public void verifyThatPhotoIsSent() {

    }

    @Test(groups = {"Chat"}, dependsOnMethods = {"verifyThatTextMessageIsSent"})
    public void verifyThatDialogIsClosed() throws InterruptedException {

        String ExpectedTitle = "Сообщения";

        exTest = exReport.startTest("DialogIsClosed");
        exTestParent.appendChild(exTest);

        //swipe needed for invoke screen after tapping on arrow back
        String currentTitle = pageChat.tapOnArrowBack().swipe().getTheTitle();

        Assert.assertEquals(ExpectedTitle, currentTitle);
        exTest.log(LogStatus.PASS, "Verifed That Dialog is closed");
    }
}
