package rahulshettyacademy.stepDefenitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;
//Use Tidy gherkin plugin to automatically generate methods
public class StepDefnImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		driver=initializeDriver();
		landingPage=launchApplication(); //calling launchApp() from BaseTest whic returns landing page obj
		
	}
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		driver=initializeDriver();
		landingPage=launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$") //start with cap^ and end with $ if data coming from .feature file replace <name>,<password> with regex (.+)
	public void logged_in_with_username_and_password(String username,String password) {
		productCatalogue=landingPage.login(username,password);
	}
	
	@When("^I add product (.+) to Cart$")//Whenever data is sent/regular expression(+) is used add ^ at start and $ at end to denote regex  
	public void i_add_prouct_to_cart(String productName) throws InterruptedException {
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
	}
	
	@When("^Checkout (.+) and submit the order$") //Can use and/when keyword
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage=checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")//{string}=THANKYOU FOR THE ORDER. use {string} when data is static in feature file and not coming from examples(outline data)
	public void message_displayed_Confirmation_Page(String string) throws InterruptedException { //string? use any method name
		String confirmMsg=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		Thread.sleep(3000);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string,landingPage.getErrorMessage() );
	}
	
	



}
	
	

