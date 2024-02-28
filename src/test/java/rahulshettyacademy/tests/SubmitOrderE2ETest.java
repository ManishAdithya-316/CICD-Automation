package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class SubmitOrderE2ETest extends BaseTest{
	
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData" , groups={"Purchase"}) //This test runs twice bcoz it receives 2 sets of data from dataprovider
	
	//public void SubmitOrder(String email, String password, String productName) throws InterruptedException, IOException -->Type 1
	
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{
		
		
		//LandingPage landingPage=launchApplication();
	    //Here we can directly access landingPage object by extending parentClass BaseTest */
		ProductCatalogue productCatalogue=landingPage.login(input.get("email"),input.get("password")); //Product Catalogue object is returned 
		
		//ProductCatalogue productCatalogue=new ProductCatalogue(driver);-->Handle Object Creation inside page objects for encapsulation
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();//CartPage Object is returned
		
		//CartPage cartPage=new CartPage(driver);-->Handle Object Creation inside page objects for encapsulation
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);//Check if any Match found  //Do all Assertions in Main class dont do assertions in page objects as they are reserved for actions only Page Objects=Action Classes?
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		
		String confirmMsg=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); //Screen text is different from html text(capitals,small letter,etc) so we use equalsIgnoreCase
		//driver.close();
		
	}
	
	@Test(dependsOnMethods={"SubmitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalogue productCatalogue=landingPage.login("man316@yopmail.com","CMpunk@316");
		
		OrdersPage ordersPage=productCatalogue.goToOrdersPage();//All pages extend Abstract Component
		Assert.assertTrue(ordersPage.verifyProductDisplay(productName)); //Checks whether match flag is returning "true"
		
	}
	
	
	
	
	//1st way using Object[][]
	/*@DataProvider
	public Object[][] getData() {
		
		return new Object[][] { {"man316@yopmail.com","CMpunk@316","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"} };//email1 password1 productname1 , email2 password2 productname2
	}*/
	
	//2nd way using HashMap
	/*@DataProvider
	public Object[][] getData() {
		
		//Create Hashmap for 1st set of data HashMap=Key value pairs
		HashMap<String,String> map=new HashMap();
		map.put("email", "man316@yopmail.com");
		map.put("password", "CMpunk@316");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] { {map},{map1} }; //return Object array containing 2 maps
		
		
	}*/
	
	//3rd way using JSON data
	@DataProvider
	public Object[][] getData() throws IOException{
		
	List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");//Either Create DataProvider class object & call this method/ put the method in BaseTest & inherit this method
	return new Object[][] { {data.get(0)},{data.get(1)} };   //==return new Object[][] { {Hashmap1},{Hashmap2} };
		
		
	}
	

}
