package automation.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import automation.pageObjects.*;
import automation.testcomponents.Base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderFlow extends Base {

	@Test(dataProvider ="getData", priority = 1,groups={"smoke"})
	public void login(HashMap <String,String> input) throws IOException {
		LandingPageSignInFlow signIn = launchWebsite();
        signIn.openLoginPage();
        signIn.signIn(input.get("email"), input.get("password"));
	}
	
	@Test(dependsOnMethods = "login", groups={"smoke"}, dataProvider ="getCardData")
	public void submitOrder(HashMap <String,String> card) throws IOException {

        CartPage cart = new CartPage(driver);
        cart.openProductPage();
        cart.searchProduct("Blue Top");
	    
	    boolean added = cart.addProductToCart("Blue Top");
        Assert.assertTrue(added, "Product was not added to cart.");
        boolean presentInCart = cart.verifyProductInCart("Blue Top");
        Assert.assertTrue(presentInCart, "Product not found in cart.");

        PaymentPage payment = cart.proceedToCheckout();
        payment.enterPaymentDetails(
        	    card.get("nameOnCard"),
        	    card.get("cardNumber"),
        	    card.get("cvc"),
        	    card.get("expiryMonth"),
        	    card.get("expiryYear")
        	);
        	payment.submitOrder();

        String successMsg = payment.getOrderSuccessMessage();
        Assert.assertEquals(successMsg, "ORDER PLACED!", "Order placement failed.");
    }
	
	@DataProvider
	public Object[] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>(); 
		map.put("email", "rao15@yopmail.com");
		map.put("password", "Hello@1234");
		return new Object[] {map};
		
	}
	
	@DataProvider
	public Object[] getCardData() throws IOException {
	    List<HashMap<String, String>> data = getJsonData(
	        System.getProperty("user.dir") + "/src/test/java/automation/data/Carddetails.json"
	    );
	    return new Object[] { data.get(0) };  // Correct syntax
	}
	
	
	
	
	
	
}
	
