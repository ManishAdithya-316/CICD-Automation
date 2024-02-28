package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup(); //Download webdriver manager dependecy
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Give global timeouts to avoid sync issues
		
		//LandingPage landingPage=new LandingPage(driver);//pass driver  step moved to SubmitOrderE2ETest
		
		driver.findElement(By.id("userEmail")).sendKeys("man316@yopmail.com");//anshika@gmail.com
		driver.findElement(By.id("userPassword")).sendKeys("CMpunk@316");//iamking@000
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));//Defining Explicit wait to handle sync issues
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));//Wait until products in page load
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		//Returns Zara product webelement
		WebElement prod=products.stream().filter(product->              //b is tagname                      //.findFirst()_>returns only 1st result out of many orElse(returns null value)
								 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);	  //Note use Java compiler ver 1.8/above for this code to work
		
		prod.findElement(By.cssSelector("button:last-of-type")).click();  //button is tagname which returns 2 buttons view & add to cart, last of type is css selector feature which returns last of tag type button which is add to cart
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //Wait till "Product Added to Cart" toaster message is displayed AND
		//ng-animating->is a class used for displaying loading icon ->know by devs
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));//waitUntilExpectedConditions.invisibilityof(whole web element(driver.findElement(By.xxx)) //Here we are waiting for pg to load affter product has been added to cart
		//invisiblityOf() method is used instead of visibilityOfElementLocated(By.cssSelector(xxx) to save time
		Thread.sleep(2000);//:)
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();//attribute*(contains)value->css selectors
		
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));//List<datatype=WebElement(singular)>
		
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));//anyMatch checks if any match is there and returns true/false
		Assert.assertTrue(match);//Check if any Match found
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india" ).build().perform();//instead of using driver.findElement.sendKeys()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); //Waiting for suggestions to load
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); //Screen text is different from html text(capitals,small letter,etc) so we use equalsIgnoreCase
		//driver.close();
		
		
		
		
		
	}

}
