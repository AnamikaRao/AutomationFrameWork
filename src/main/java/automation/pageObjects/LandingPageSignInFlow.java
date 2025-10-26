package automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstactComponent.AbstractComponent;

public class LandingPageSignInFlow extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPageSignInFlow(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//PageFactory
	
	@FindBy(xpath="//input[@data-qa='login-email']")
	WebElement loginEmail;
	
	
	@FindBy(xpath="//input[@data-qa='login-password']")
	WebElement loginPassword;
	
	
	@FindBy(xpath="//button[@data-qa='login-button']")
	WebElement loginButton;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement loginPage;
	
	@FindBy(xpath ="//p[normalize-space()='Your email or password is incorrect!']")
	WebElement incorrectErrorText;
	
	
	public void loginPage()
	{
		loginPage.click();
	}
	
	// ---------- Actions ----------
    public void openLoginPage() {
        waitForElementToBeVisible(loginPage);
        loginPage.click();
    }

    public void signIn(String email, String password) {
        waitForElementToBeVisible(loginEmail);
        loginEmail.clear();
        loginEmail.sendKeys(email);
        loginPassword.clear();
        loginPassword.sendKeys(password);
        loginButton.click();
    }
    
    public String signInErrorCode() {
        waitForElementToBeVisible(incorrectErrorText);
        return incorrectErrorText.getText().trim();
    }
	
	
	
	
	

}
