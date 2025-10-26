package automation.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.abstactComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='nav navbar-nav']//li//a[@href='/products']")
	WebElement productMenu;

	@FindBy(id = "search_product")
	WebElement searchBox;

	@FindBy(id = "submit_search")
	WebElement searchButton;

	@FindBy(css = ".features_items")
	List<WebElement> productList;

	@FindBy(css = ".modal-title.w-100")
	WebElement modalTitle;
	
	By productNameLocator = By.xpath("//div[@class='productinfo text-center']//p[contains(text(),'Blue Top')]");
	By addToCartButton = By.cssSelector(".btn.btn-default.add-to-cart");

	@FindBy(xpath = "//p[@class='text-center']//a[@href ='/view_cart']")
	WebElement viewCartLink;

	@FindBy(xpath = "//a[normalize-space()='Blue Top']")
	List<WebElement> cartProduct;

	@FindBy(css = ".btn.btn-default.check_out")
	WebElement checkoutButton;

	@FindBy(xpath = "//a[@href='/payment']")
	WebElement paymentLink;

	


public void openProductPage() {
    waitForElementToBeVisible(productMenu);
    productMenu.click();
}

public void searchProduct(String pname) {
    waitForElementToBeVisible(searchBox);
    searchBox.clear();
    searchBox.sendKeys(pname);
    searchButton.click();
}

public boolean addProductToCart(String pname) {
    boolean productFound = false;
    for (WebElement product : productList) {
        String productName = product.findElement(productNameLocator).getText();
        if (productName.equalsIgnoreCase(pname)) {
            productFound = true;
            moveToElement(product);
            WebElement addButton = product.findElement(addToCartButton);
            addButton.click();
            break;
        }
    }
    return productFound;
}

public boolean verifyProductInCart(String productName) {
    waitForElementToBeVisible(modalTitle);
    viewCartLink.click();
    return cartProduct.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
}

public PaymentPage proceedToCheckout() {
    waitForElementToBeVisible(checkoutButton);
    checkoutButton.click();
    paymentLink.click();
    return new PaymentPage(driver);
}
}
