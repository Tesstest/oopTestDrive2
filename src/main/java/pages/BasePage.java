package pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public abstract class BasePage {

    protected AndroidDriver<WebElement> driver;
    protected ExtentTest exTest;

    public BasePage(AndroidDriver<WebElement> driver, ExtentTest exTest) {
        this.driver = driver;
        this.exTest = exTest;
    }

    public WebElement waitForElement(By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        return element;
    }

    public WebElement waitForVisibilityOfElement(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }

    public List<WebElement> waitForElements(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        List<WebElement> listOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

        return listOfElements;
    }

    public String getScreenTitle(By locator, int timeout) {
        String theTitle = "";

        try {
            List<WebElement> ls = waitForElements(locator, timeout);
            WebElement elTitle = ls.get(0);
            theTitle = elTitle.getText();

        } catch (Exception e) {
            System.out.println("Couldn't get the title!");
            throw e; // if delete throw program completed and couldn't continue
            // execute other tests
        }
        return theTitle;
    }

    public void getContext(String context) {
        boolean findContext = false;
        Set<String> availableContexts = driver.getContextHandles();
        for (String contextName : availableContexts) {

            //todo dell after
            System.out.println(contextName);
            //
            if (contextName.contains(context)) {
                driver.context(contextName);
                findContext = true;
                break;
            }
        }
        if (!findContext){
            exTest.log(LogStatus.ERROR, "Could not get context: " + context);
            System.out.println("Could not get context: " + context);
        }
    }
}