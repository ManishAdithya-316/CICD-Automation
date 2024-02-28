package rahulshettyacademy.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{  //What method will fail due to flaky/inconsitent add retryAnalyzeer=Retry.class to @Test
	
	int count=0;
	int maxTry=1;//maxTry=  how many number of times you want to rerun failed tests again

	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry) {
			count++;
			return true;//If true is returned failed test is again rerun
		}
		
		return false;
	}

}
