package pages;

import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;

public class Gallery extends BasePage {

    public Gallery(AndroidDriver<WebElement> driver, ExtentTest exTest) {
        super(driver, exTest);
    }

    public Gallery selectPhotos(int quantity){
        return this;
    }

    public Object [] SendPhotos(String className){
        Object [] objects = new Array[1];
        if (className.equals("PageChat")){

            objects[0] = new PageChat(driver, exTest);
        }
        return objects;
    }
}
