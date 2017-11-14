package testNGFeatures;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
	/*
	 * This interface is used to programmatically add annotation to the test
	 * methods during run time. Transform method is called for every test during
	 * test run.
	 */
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		/*
		 * annotation.setRetryAnalyzer(testClass); testMethod.getName();
		 */ }

}
