package rahulshettyacademy.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {  //Right click on the class --> select source --> choose override/implement methods --> then pick the methods you'd like to implement.

	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReporterObject(); //Here we are not creating object, we are directly using class ExtentReporterNG to call method getReporterObject() which returns an object which is stored in extent
	
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal(); //Thread Safe conceppt- Thread local assigns unique id to test/method threads which are accessing same variable to avoid concurrency issues 
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test=extent.createTest(result.getMethod().getMethodName());//Creates Method Name test of which method is starting to execute
		extentTest.set(test); //Setting test variable inside ThreadLocal so that whenever this variable is accessed parallely, those threads get unique id 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.PASS, "Test Passed"); replace test variable/object as extentTest.get();-->Making it threadsafe to avoid concurrency/parallel execution issues
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub //Write screenshot code
		
		test.fail(result.getThrowable());//Result prints exception/error
		
		try {						//getsTestClass from TestNG.xml & then gets the JavaRealClass	
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filePath = null;
		
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());  //path of screenshot , title/name of test which failed
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); //replace test variable/object as extentTest.get();-->Making it threadsafe to avoid concurrency/parallel execution issues
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();//Import method which generates extent report.html
	}

}
