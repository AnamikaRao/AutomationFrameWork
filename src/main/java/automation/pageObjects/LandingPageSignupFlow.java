package automation.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.abstactComponent.AbstractComponent;


public class LandingPageSignupFlow extends AbstractComponent {

	WebDriver driver;
	//WebDriverWait wait;

	public LandingPageSignupFlow(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		PageFactory.initElements(driver, this);

	}
	@FindBy(css=".btn.btn-primary")
	WebElement buttonclick;

	@FindBy(css = "input[data-qa='signup-name']")
	WebElement signupName;

	@FindBy(css = "input[data-qa='signup-email']")
	WebElement signupEmail;

	@FindBy(css = "button[data-qa='signup-button']")
	WebElement signupButton;

	@FindBy(css = "div[id='uniform-id_gender2']")
	WebElement radiobutton;

	@FindBy(id = "password")
	WebElement signupPassword;

	@FindBy(id = "days")
	WebElement signupDays;

	@FindBy(id = "months")
	WebElement signupMonths;

	@FindBy(id = "years")
	WebElement signupYears;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	WebElement signupCheckbox;

	@FindBy(css = "#first_name")
	WebElement signupFirstName;

	@FindBy(css = "#last_name")
	WebElement signupLastName;

	@FindBy(css = "#company")
	WebElement signupCompany;

	@FindBy(css = "#address1")
	WebElement signupAddress1;

	@FindBy(css = "#address2")
	WebElement signupAddress2;

	@FindBy(css = "#country")
	WebElement signupCountry;

	@FindBy(css = "#state")
	WebElement signupState;

	@FindBy(css = "#city")
	WebElement signupCity;

	@FindBy(css = "#zipcode")
	WebElement signupZip;

	@FindBy(css = "#mobile_number")
	WebElement signupmobile;

	@FindBy(xpath = "(//button[@type='submit'])[1]")
	WebElement SignupButton;

	@FindBy(xpath = "//*[text()='Account Created!']")
	WebElement accountText;

	By radiobtn = By.cssSelector("div[id='uniform-id_gender2']");

	public void SignupForm(String name, String email) {
		signupName.sendKeys(name);
		signupEmail.sendKeys(email);
		signupButton.click();
	}

	public void SignupDetails(String password) {
		waitForElementToBeVisible(radiobtn);
		radiobutton.click();
		signupPassword.sendKeys(password);

		pageScroll();
	
		new Select(signupDays).selectByIndex(22);
		new Select(signupMonths).selectByVisibleText("March");
		new Select(signupYears).selectByValue("2021");
		signupCheckbox.click();
		signupFirstName.sendKeys("dummy3");
		signupLastName.sendKeys("dummy3");
		signupCompany.sendKeys("dummy3");
		signupAddress1.sendKeys("dummy3");
		signupAddress2.sendKeys("dummy3");
		new Select(signupCountry).selectByVisibleText("India");
		signupState.sendKeys("dummy3");
		signupCity.sendKeys("dummy3");
		signupZip.sendKeys("123");
		signupmobile.sendKeys("1234567");
		SignupButton.click();

	}

	public void accountCreated() {
		String expectedText = "ACCOUNT CREATED!";
		WebElement element = accountText;
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText, "Account creation message mismatch!");
	}
	
	public void btnClick()
	{
		buttonclick.click();
	}
}
