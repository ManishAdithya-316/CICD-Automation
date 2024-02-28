package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrdersPage;

public class AbstractComponent { //extend all child classes/pages
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);//Initialize Page Factrory to use @FindBy(css="[routerlink*='cart']")
		WebElement cartHeader;
	}

	public void waitForElementToAppear(By findBy) { //Same method name different constructor(By element)
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
		}
	
	public void waitForWebElementToAppear(WebElement findBy) {  //Same method name different constructor(WebElement)
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
		}
	

	public void waitForElementToDissapear(WebElement ele) throws InterruptedException {
		//Spinner code istaking time.. instead use Thread.sleep(1000) bcoz of application having 1 more hidden spinner to handle load
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
		Thread.sleep(1000);	
		}
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']"))
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	public CartPage goToCartPage() {//Return type CartPageObject
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);//After clicking Go to Cart button Cart page is displayed so we create object here
		return cartPage;//Returning CartPage object
		
	}
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public OrdersPage goToOrdersPage() {//Return type CartPageObject
		ordersHeader.click();
		OrdersPage ordersPage=new OrdersPage(driver);//After clicking Go to Cart button Cart page is displayed so we create object here
		return ordersPage;//Returning CartPage object
		
	}
	
}
