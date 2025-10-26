package automation.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.pageObjects.LandingPageSignInFlow;
import automation.pageObjects.LandingPageSignupFlow;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EndToEnd {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();

		// Disable Save Password popup

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false); // disable Chrome credentials service
		prefs.put("profile.password_manager_enabled", false); // disable password manager popup

		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);

		driver.get("https://automationexercise.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@href='/login']")).click();
				

		// SignUp Flow
		driver.findElement(By.cssSelector("input[data-qa='signup-name']")).sendKeys("test1");
		driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("rao15@yopmail.com");
		driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[id='uniform-id_gender2']")).click();
		driver.findElement(By.id("password")).sendKeys("Hello@1234");

		//Sign in flow
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("rao15@yopmail.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Hello@1234");
		driver.findElement(By.xpath("//input[@data-qa='login-button']")).click();
		
		//Account data add flow
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);"); // scrolls down 500px
		Thread.sleep(2000);
		new Select(driver.findElement(By.id("days"))).selectByIndex(22); // Select 23rd day
		new Select(driver.findElement(By.id("months"))).selectByVisibleText("March"); // Select March
		new Select(driver.findElement(By.id("years"))).selectByValue("2021");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.cssSelector("#first_name")).sendKeys("dummy3");
		driver.findElement(By.cssSelector("#last_name")).sendKeys("dummy3");
		driver.findElement(By.cssSelector("#company")).sendKeys("dummy3");
		driver.findElement(By.cssSelector("#address1")).sendKeys("dummy3");
		driver.findElement(By.cssSelector("#address2")).sendKeys("dummy3");
		new Select(driver.findElement(By.cssSelector("#country"))).selectByVisibleText("India");
		driver.findElement(By.cssSelector("#state")).sendKeys("dummy2");
		driver.findElement(By.cssSelector("#city")).sendKeys("dummy2");
		driver.findElement(By.cssSelector("#zipcode")).sendKeys("dummy2");
		driver.findElement(By.cssSelector("#mobile_number")).sendKeys("1234567");

		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();

		String expectedText = "ACCOUNT CREATED!";
		WebElement element = driver.findElement(By.xpath("//*[text()='Account Created!']"));
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText, "Account creation message mismatch!");

		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		driver.findElement(By.xpath("//a[@href= '/products']")).click();
		js.executeScript("window.scrollBy(0, 500);");

		// PRoduct Page flow from Clicking button
		
		String pname = "Blue Top";

		driver.findElement(By.id("search_product")).sendKeys("pname");
		driver.findElement(By.id("submit_search")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".features_items"));

		boolean productfound = false;
		Actions actions = new Actions(driver);

		for (WebElement product : products) {
			String productname = product
					.findElement(By.xpath("//div[@class='productinfo text-center']//p[contains(text(),'Blue Top')]"))
					.getText();
			if (productname.equalsIgnoreCase(pname)) {
				productfound = true;

				actions.moveToElement(product).perform();

				WebElement cartButton = product.findElement(By.cssSelector(".btn.btn-default.add-to-cart"));
				cartButton.click();
				System.out.println("Clicked Add to Cart for : " + productname);
				break;
			} else {
				System.out.println("Nothing to display");
			}

		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title.w-100")));

		boolean popup = driver.findElement(By.cssSelector(".modal-title.w-100")).isDisplayed();

		System.out.println("Modal is displayed " + popup);

		driver.findElement(By.xpath("//p[@class='text-center']//a[@href ='/view_cart']")).click();
		
		List<WebElement> cartProduct =driver.findElements(By.xpath("//td[@class='cart_description']"));
		
		Boolean match=cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(pname));
		Assert.assertTrue(match);
		
		
		
		
		driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();
		
		driver.findElement(By.xpath("//a[@href='/payment']")).click();
		
		
		
		//Payment
		driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@name='card_number']")).sendKeys("12345667");
		driver.findElement(By.xpath("//input[@name ='cvc']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@name ='expiry_month']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@name ='expiry_year']")).sendKeys("2030");
		driver.findElement(By.id("submit")).click();
		
		
		WebElement element1 = driver.findElement(By.cssSelector("h2[class='title text-center'] b"));
		String actualname = element1.getText();
		Assert.assertTrue(actualname.equalsIgnoreCase("Order Placed!"));
		
		driver.close();
		
		
		
		
		
		

	}

}
