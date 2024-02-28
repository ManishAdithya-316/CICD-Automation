package cucumber;
//Cucumber needs either TestNG/Junit to run its code it cant run on its own it provides many plugins
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//features is location of feature files	//glue is location of stepdefenition  //monochrome makes cucumber reports readable
@CucumberOptions(features="src/test/java/cucumber/", glue="rahulshettyacademy.stepDefenitions", monochrome=true,
				tags="@ErrorValidation",
				plugin= {"html:tatget/cucumber.html"} //key value pair cucumber has many plugins which is/are pulled to generate html/json reports
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ //Extrend Abstract TestNGCucumber class

}
