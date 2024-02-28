package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;//Multiple Elements
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	
	
	public boolean verifyProductDisplay(String productName) {
		boolean match=productNames.stream().anyMatch(tableproduct->tableproduct.getText().equalsIgnoreCase(productName)); //for each table product get that name
		return match;
	}
	
	
	

	
	
	

}
