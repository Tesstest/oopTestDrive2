package testNGFeatures;

import com.relevantcodes.extentreports.LogStatus;
import common.SetupConnection;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.net.MalformedURLException;
import java.util.Arrays;

public class TestListeners extends SetupConnection implements ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {

		String[] groups = result.getMethod().getGroups();

		if (Arrays.asList(groups).contains("NeededLogout")) {
			exTest.log(LogStatus.INFO, "Logout...");
			driver.quit();
			try {
				CreateCapabilities();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		/*
		 * Works too slow because swiping a lot of times, trying to find item
		 * Exit at the end of the screen try { PageMore pageMore = new
		 * MainMenu(driver, exTest).clickOnBtnSandwich(); pageMore.exit();
		 * 
		 * exTest.log(LogStatus.INFO, "Logout..."); } catch (Exception e) { //if
		 * couldn't do logout - restart app driver.quit(); try {
		 * CreateCapabilities(); } catch (Exception e2) { e2.printStackTrace();
		 * } e.printStackTrace(); }
		 */
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String[] groups = result.getMethod().getGroups();

		if (Arrays.asList(groups).contains("RestartApp")) {
			driver.quit();
			try {
				CreateCapabilities();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		String[] groups = result.getMethod().getGroups();

		if (Arrays.asList(groups).contains("RestartApp")) {
			driver.quit();
			try {
				CreateCapabilities();
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
