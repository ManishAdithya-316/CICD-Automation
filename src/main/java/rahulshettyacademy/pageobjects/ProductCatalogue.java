package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public  ProductCatalogue(WebDriver driver) {
		
		super(driver);//Send driver to parent class(AbstractComponent)
		this.driver=driver;
		
		PageFactory.initElements(driver, this);//Initializing passed driver object to be used in this class
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;//Line 20+21=List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector("button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	

	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod=products.stream().filter(product->              //b is tagname                      //.findFirst()_>returns only 1st result out of many orElse(returns null value)
		 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);	  //Note use Java compiler ver 1.8/above for this code to work
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissapear(spinner);
		
	}
	
}
