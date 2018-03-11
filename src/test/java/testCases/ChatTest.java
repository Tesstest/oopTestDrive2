package testCases;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import common.ExtentFactory;
import common.SetupConnection;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pages.*;

public class ChatTest extends SetupConnection {

    ExtentTest exTestParent;
    ExtentReports exReport;
    String serviceUserName = "Dina";
    //PageChat pageChat = new PageChat(driver, exTest);

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        exReport.endTest(exTestParent);
        exReport.flush();
    }

    @BeforeClass

    public void beforeTest ()throws InterruptedException {

        exReport = ExtentFactory.getInstance();
        exTestParent = exReport.startTest("Chat");

        // For debugging
        exTest = exReport.startTest("Login by Email");
        exTestParent.appendChild(exTest);

        //test
        LoginByEmailPage loginByEmail = new LoginPage(driver, exTest).clickOnBtnEmail();

        loginByEmail.typeEmail("dforsp@gmail.com")
                .typePassword("qAlityAssurance").clickOnButtonEnter(false);
        //end for debugging

        //preconditions
        String chatTitle;
        String dialogTitle;

        boolean chatOpened = false;
        exTest = exReport.startTest("Testing of chat ");
        exTestParent.appendChild(exTest);

        MainMenu mainMenu = new MainMenu(driver, exTest);

        for (int i=0; i < 2; i++){
            chatTitle = mainMenu.clickOnBtnChat().getTheTitle();
            if (chatTitle.equals("Сообщения")) {
                chatOpened = true;
                break;
            }
        }
        PageChat pageChat = new PageChat(driver, exTest);

        if (chatOpened){
            dialogTitle = pageChat.findAndTapOnUser(serviceUserName).getTheTitle();
            if (dialogTitle.equals(serviceUserName)){
                exTest.log(LogStatus.INFO, "Dialog is opened");
            } else {
                System.out.println("Dialog is not opened!");
                exTest.log(LogStatus.ERROR, "Dialog is not opened!");
                throw new SkipException("Skip tests");
            }
        } else {
           System.out.println("Chat is not opened!");
           throw new SkipException("Skip tests");
        }
    }

/*
    @Test(groups = {"Chat"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatChatIsOpened() {

        exTest = exReport.startTest("Chat Screen Is Opened");
        exTestParent.appendChild(exTest);

        String ExpectedTitle = "Сообщения";

        MainMenu mainMenu = new MainMenu(driver, exTest);
        pageChat = mainMenu.clickOnBtnChat();

        String currentTitle = pageChat.swipe().getTheTitle();

        Assert.assertEquals(ExpectedTitle, currentTitle);
        exTest.log(LogStatus.PASS, "Verifed That Chat is opened");
    }

    @Test(groups = {"Chat"}, dependsOnMethods = {"verifyThatChatIsOpened"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatDialogWithUserIsOpened() {

        exTest = exReport.startTest("Dialog With User Is Opened");
        exTestParent.appendChild(exTest);

        String serviceUserName = "Dina";
        String currentTitle = pageChat.tapOnUser(serviceUserName).getTheTitle();

        Assert.assertEquals(serviceUserName, currentTitle);
        exTest.log(LogStatus.PASS, "Verify That Dialog With User is opened");
    }
*/

    @Test(groups = {"Chat"}, enabled = true)
    public void verifyThatTextMessageIsSent() {

        exTest = exReport.startTest("Text Message Is Sent");
        exTestParent.appendChild(exTest);

        DateFormat dateFormat = new SimpleDateFormat();
        Date currentDate = new Date();
        String textMessage = "Test message: " + dateFormat.format(currentDate);
        PageChat pageChat = new PageChat(driver, exTest);
        // PageChat pageChat = new PageChat(driver, exTest);
        String sentMessage = pageChat.typeTextMessage(textMessage).tapOnButtonSendMessage()
                .findSentMessage(textMessage);

        Assert.assertEquals(textMessage, sentMessage);
        exTest.log(LogStatus.PASS, "Verify sending message");
    }
    @Test(groups = {"Chat"}, enabled = true)
    public void verifyThatPhotoIsSent() throws IOException {

        String expectedTitle = serviceUserName;
        String currentTitle = "";
        Object [] pages;

        exTest = exReport.startTest("Photo Is Sent");
        exTestParent.appendChild(exTest);

        PageChat pageChat = new PageChat(driver, exTest);
        Gallery gallery = pageChat.tapOnButtonPhoto();
        if (gallery!= null){
            pages = gallery.selectPhotos(1).SendPhotos("PageChat");

        }

        Assert.assertEquals(currentTitle, expectedTitle);
        exTest.log(LogStatus.PASS, "Verify sending photo");


        /*
      File filePhoto = new File("C:\\Work\\Intellij\\oopTestDrive2\\photo2.jpg");
        PageChat pageChat = new PageChat(driver, exTest);

        if (filePhoto.exists()){
            try {
                driver.pushFile("/mnt/sdcard/Pictures/2.jpg", filePhoto);
                System.out.println("File exists");
            } catch (Exception e) {
                System.out.println("!!");
            }
        }
*/

/*
        if (filePhoto.exists()){
            try {
                Path path = filePhoto.toPath();
                byte [] data = Files.readAllBytes(path);
                driver.pushFile("/mnt/sdcard", filePhoto);
                System.out.println("File exists");
            } catch (Exception e) {
                System.out.println("!!");
            }


        }
*/
    }

    @Test(groups = {"Chat"}, enabled = false)
    public void verifyThatDialogIsClosed() throws InterruptedException {

        String ExpectedTitle = "Сообщения";

        exTest = exReport.startTest("DialogIsClosed");
        exTestParent.appendChild(exTest);

        //swipe needed for invoke screen after tapping on arrow back
       // String currentTitle = pageChat.tapOnArrowBack().swipe().getTheTitle();

       // Assert.assertEquals(ExpectedTitle, currentTitle);
       // exTest.log(LogStatus.PASS, "Verifed That Dialog is closed");
    }
}
