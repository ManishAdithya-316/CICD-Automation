package rahulshettyacademy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;//Declare driver globally
	public LandingPage landingPage;//Landing page is also common so we can make this a variable which can be used in other classes

	public WebDriver initializeDriver() throws IOException {
		
		//Properties class->java.util class
		
		Properties prop=new Properties();       //C:\\Users\\User\\eclipse-workspace\\SeleniumFrameworkDesign = System.getProperty("user.dir")
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//System.getProperty("gets property bf browser from maven command line") if(this property is not null then use it from command prompt : if it is null/no input given from user in cmd prompt use GlobalData.properties
		//Ternary operator	//mvn test -d browser="chrome" ->command line where key=browser and value=chrome/edge/firefox
		String browserName= System.getProperty("browser")!=null ?System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName=prop.getProperty("browser"); //here browser is key, prop.getProperty() gets value
		
		//System.out.println(browserName);
		
		if(browserName.contains("chrome")) {
		ChromeOptions options=new ChromeOptions();
		WebDriverManager.chromedriver().setup(); //Download webdriver manager dependecy
		
		if(browserName.contains("headless")) {
			options.addArguments("headless");//Headless mode executes test in invisible browser, it will be faster
		}
		
		driver=new ChromeDriver(options);//if headless=true, options will contain headless else it will be empty
		driver.manage().window().setSize(new Dimension(1440,900));//If browser is running headless/invisible you want to run it fullscreen to avoid element not visible errors
		}
		
		if(browserName.equalsIgnoreCase("firefox")) {
			//Write code to setup firefox browser dummy code below
			//WebDriverManager.geckodriver().setup(); //Download webdriver manager dependecy
			 driver=new FirefoxDriver();
			
			}
		
		if(browserName.equalsIgnoreCase("edge")) {
			//Write code to setup Edge browser dummy code below
			System.setProperty("webdriver.edge.driver","C:\\Users\\User\\Desktop\\Eclipse\\msedgedriver.exe");;
			EdgeOptions options=new EdgeOptions();
			options.addArguments("inprivate");
			driver=new EdgeDriver(options);
			
			}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Give global timeouts to avoid sync issues
		
		return driver;//ERetrun driver for use of other methods in this/other classes
		
	}
	
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {  //FileUtils need commons.io dependency
		
		//Step 1:Read JSON file to a Single String                                                                                                      //Standard String Encoding Type
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Step 2:Convert String to HashMap using Jackson Databind dependency import itfrom mvn repository and add it to pom.xml
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){}); //Syntax Returns List<HashMap1,HashMap2>
		
		return data;//Returns List containing HashMaps
		
	}

public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	
	TakesScreenshot ts=	(TakesScreenshot) driver; //cast driver to (TakesScreenshot)
	File source=ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");  //UserDirectory/Reports-Folder/TestcaseName(Which Got Failed).png
	FileUtils.copyFile(source, file);
	return (System.getProperty("user.dir")+"//reports//"+testCaseName+".png"); //Returns path of created/copied file

//Extent Reports
}
	
	
	
	
	
	
	
	
	
	
	
	
	@BeforeMethod(alwaysRun=true) //always Run for all groups
	public LandingPage launchApplication() throws IOException { //Return LandingPage Object
		
		
		driver=initializeDriver();//get driver from initializeDriver to prevent null errors
		landingPage=new LandingPage(driver);//pass driver
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true) //always Run for all groups
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);//Not required...
		//driver.close();
		driver.quit(); //Use driver.quit instead of close to avoid socket errors
	}
	
	
	
}
