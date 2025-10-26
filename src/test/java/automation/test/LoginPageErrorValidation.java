package automation.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.pageObjects.LandingPageSignInFlow;
import automation.testcomponents.Base;

public class LoginPageErrorValidation extends Base{
	
	String expected = "Your email or password is incorrect!";
	
	@Test
	public void LoginInvalidUserNameInvalidPassword() throws IOException {
		LandingPageSignInFlow signIn = launchWebsite();
		
        signIn.openLoginPage();
        signIn.signIn("rao151@yopmail.com", "Hello@12345");
        System.out.println("Tried login with Invalid Username & Invalid Password");
        String actual = signIn.signInErrorCode();
        
        Assert.assertEquals(actual, expected,"Error message mismatch!");

        
	}
	@Test
	public void LoginInvalidUserNameValidPassword() throws IOException {
		LandingPageSignInFlow signIn = launchWebsite();
		
        signIn.openLoginPage();
        signIn.signIn("r@yopmail.com", "Hello@1234");
        System.out.println("Tried login with Invalid Username & valid Password");
        String actual = signIn.signInErrorCode();
        Assert.assertEquals(actual, expected,"Error message mismatch!");
	}
	@Test
	public void LoginValidUserNameInvalidPassword() throws IOException {
		LandingPageSignInFlow signIn = launchWebsite();
		
        signIn.openLoginPage();
        signIn.signIn("r@yopmail.com", "Hello234");
        System.out.println("Tried login with valid Username & Invalid Password");
        String actual = signIn.signInErrorCode();
        Assert.assertEquals(actual, expected,"Error message mismatch!");
	}

}
