package testNGFeatures;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will
	 * call this method every time a test fails. So we can put some code in here
	 * to decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and
	 * false it not.
	 *
	 */
	@Override
	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {

			System.out.println("before (skipped): " + result.getTestContext().getSkippedTests().size());
			System.out.println("before (failed): " + result.getTestContext().getFailedTests().size());
			System.out.println("before (passped): " + result.getTestContext().getPassedTests().size());
			result.getTestContext().getFailedTests().removeResult(result);
			counter++;


			return true;
		} else {
			System.out.println("before (skipped): " + result.getTestContext().getSkippedTests().size());
			System.out.println("before (failed): " + result.getTestContext().getFailedTests().size());
			System.out.println("before (passped): " + result.getTestContext().getPassedTests().size());

			return false;
		}


//		if (result.getStatus() == 2 & counter < retryLimit) {
//			System.out.println("before (skipped): " + result.getTestContext().getSkippedTests().size());
//			System.out.println("before (failed): " + result.getTestContext().getFailedTests().size());
//			System.out.println("before (passped): " + result.getTestContext().getPassedTests().size());
//
//			result.getTestContext().getFailedTests().removeResult(result);
//
//			System.out.println("afetr (skipped): " + result.getTestContext().getSkippedTests().size());
//			System.out.println("after (failed): " + result.getTestContext().getFailedTests().size());
//			System.out.println("after (passped): " + result.getTestContext().getPassedTests().size());
//
//			counter++;
//			return true;
//
//		} else {
//			return false;
//		}
	}

}
