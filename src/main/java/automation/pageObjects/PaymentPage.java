package automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import automation.abstactComponent.AbstractComponent;


public class PaymentPage extends AbstractComponent
{
WebDriver driver;
	
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//input[@name='name_on_card']")
	WebElement nameOnCard;
	@FindBy(xpath="//input[@name='card_number']")
	WebElement cardNumber;
	@FindBy(xpath="//input[@name ='cvc']")
	WebElement cvc;
	@FindBy(xpath="//input[@name ='expiry_month']")
	WebElement expiryMonth;
	@FindBy(xpath="//input[@name ='expiry_year']")
	WebElement expiryYear;
	@FindBy(id="submit")
	WebElement submitOrder;
	@FindBy(css="h2[class='title text-center'] b")
	WebElement orderSuccessTitle;
	
	
	// ---------- Actions ----------
    public void enterPaymentDetails(String name, String number, String cvcCode, String month, String year) 
    {
        waitForElementToBeVisible(nameOnCard);
        nameOnCard.sendKeys(name);
        cardNumber.sendKeys(number);
        cvc.sendKeys(cvcCode);
        expiryMonth.sendKeys(month);
        expiryYear.sendKeys(year);
    }

    public void submitOrder() {
        submitOrder.click();
    }

    public String getOrderSuccessMessage() {
        waitForElementToBeVisible(orderSuccessTitle);
        return orderSuccessTitle.getText().trim();
    }
    
}