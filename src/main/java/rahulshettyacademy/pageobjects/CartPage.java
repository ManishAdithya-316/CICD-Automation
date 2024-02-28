package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;//Multiple Elements
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	
	
	public boolean verifyProductDisplay(String productName) {
		
		boolean match=productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
	public CheckoutPage goToCheckout() { //Returns CheckoutPage
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	

	
	
	

}
