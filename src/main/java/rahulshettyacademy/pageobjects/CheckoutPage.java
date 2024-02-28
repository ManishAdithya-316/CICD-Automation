package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country; //Use private to use encapsulation -> hiding webelements and showing methods which use them inside class
	
	@FindBy(css=".ta-results")
	private WebElement results;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry; //this is webelement not by element
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country,countryName ).build().perform();//instead of using driver.findElement.sendKeys()
		waitForElementToAppear(By.cssSelector(".ta-results")); //Waiting for suggestions to load //here argument is by element so we cant use WebElement results;
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() { //returns ConfirmationPage object
		submit.click();
		return new ConfirmationPage(driver);//After clicking submit button , order confirmation page is displayed so we return it
	}
	
	
	
	

}
