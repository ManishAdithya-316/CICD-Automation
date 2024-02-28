package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	//Initialization code
	public LandingPage(WebDriver driver) {
		
		super(driver);//Send driver to parent class(AbstractComponent)
		this.driver=driver;
		PageFactory.initElements(driver, this);//Page Factory model/design pattern code for initializing all elements
		
		}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//The above step is written in Pagefactory model by using below 2 steps
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;//Avoid passing duplicate names in methods Eg:passwordEle,password
	
	@FindBy(id="login")
	WebElement submit;
	
	//div[@aria-label='Incorrect email or password.']
	//.ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue login(String username,String password)//Avoid passing duplicate names in methods Eg:passwordEle,password  //Return type ProductCatalogueObject
	{
		userEmail.sendKeys(username);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);//After clicking login button Product Catalgoue page is displayed so we create object here
		return productCatalogue;//Returning productCatalogue object
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	

}
