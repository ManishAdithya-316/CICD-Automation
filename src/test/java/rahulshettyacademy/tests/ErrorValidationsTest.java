package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
														//import current packages (rahulshettyAcademy.com/testComponents/Retry class)
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)  //What method will fail due to flaky/inconsitent add retryAnalyzeer=Retry.class to @Test
	public void enterInvalidEmail() {
		
		landingPage.login("man123@yopmail.com","Invalid password");
		//landingPage.getErrorMessage();
		//Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage() );
		Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage() );//Intentionally failing to get Extent Report + Screenshot
		}
	
	@Test
	public void SubmitOrder() throws InterruptedException, IOException
	{
		
		
		String productName="ZARA COAT 3";
		
		
		
		
	
	    //LandingPage landingPage=launchApplication();
	    //Here we can directly access landingPage object by extending parentClass BaseTest */
		ProductCatalogue productCatalogue=landingPage.login("man316@yopmail.com","CMpunk@316"); //Product Catalogue object is returned 
		
		//ProductCatalogue productCatalogue=new ProductCatalogue(driver);-->Handle Object Creation inside page objects for encapsulation
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();//CartPage Object is returned
		
		//CartPage cartPage=new CartPage(driver);-->Handle Object Creation inside page objects for encapsulation
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");//Checking whether this product does not exist
		//Assert.assertFalse(False) = Pass bcoz ZARA COAT 33 does not exist
		Assert.assertFalse(match);//Check if any Match found  //Do all Assertions in Main class dont do assertions in page objects as they are reserved for actions only Page Objects=Action Classes?
		
	}
	
	
}
