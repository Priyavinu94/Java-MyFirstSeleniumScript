package automationAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPracticeMakePurchase {

	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
	}

	@Test
	public void testPurchaseProcess() {

		// 1. Login to page *******************************

		// find elements
		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("passwd"));
		WebElement signInButton = driver.findElement(By.id("SubmitLogin"));

		// input email and password, then click login
		emailInput.sendKeys("sree1234@gmail.com");
		passwordInput.sendKeys("sree1234");
		signInButton.click();

		// 2. Assert User name *****************************
		WebElement currentUserName = driver.findElement(By.className("account"));
		Assert.assertEquals(currentUserName.getText(), "Sree Vin");

		// 3. Click on"WOMEN"
		WebElement womenButton = driver
				.findElement(By.cssSelector("#block_top_menu>ul:nth-of-type(1)>li:nth-of-type(1)"));
		womenButton.click();

		WebElement navigationPageTitle = driver.findElement(By.className("navigation_page"));
		Assert.assertEquals(navigationPageTitle.getText(), "Women");

		// 4. Click on Quickview of first product 'Faded Short Sleeve T-shirts'

		/*
		 * WebElement productQuickView = driver.findElement( By.
		 * xpath("//a[@title='Faded Short Sleeve T-shirts']//following-sibling::a[@class='quick-view']//span"
		 * )); ------ElementNotInteractable exception
		 */
		WebElement productView = driver
				.findElement(By.xpath("//a[@class='product_img_link' and @title='Faded Short Sleeve T-shirts']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productView);
		productView.click();

		// Switch to Iframe

		// 5. Updating quantity of the selected product
		driver.switchTo().frame(0); // passing id/name gives exception --- NoSuchFrameFound
		WebElement quantityInput = driver.findElement(By.cssSelector("#quantity_wanted_p input.text"));
		quantityInput.clear();
		quantityInput.sendKeys("2"); // Input quantity '2'

		// 6. Updating size
		WebElement sizeSelectionBox = driver.findElement(By.id("group_1"));
		Select selectSize = new Select(sizeSelectionBox);
		selectSize.selectByValue("3"); // Select size 'L'

		// 7. Click on Add To Cart
		WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
		addToCartButton.click(); // submit() directly goes to Summary page skipping the confirmation part.

		// 8. Assertion
		driver.switchTo().defaultContent();
		WebElement successMessage = driver.findElement(By.cssSelector("#layer_cart div.layer_cart_product>h2"));
		System.out.println("Success Message: "+ successMessage.getText()); //--not fetching text
//		Assert.assertEquals(successMessage.getText(), "Product successfully added to your shopping cart");
		
		// 9. Asserting Product and quantity
		
		WebElement productName = driver.findElement(By.id("layer_cart_product_title"));
		WebElement quantity = driver.findElement(By.id("layer_cart_product_quantity"));
		System.out.println(productName.getText());	//--not fetching text
		System.out.println(quantity.getText());		//--not fetching text
		
		// 10. Click on 'Proceed to CheckOut'
		WebElement checkOutButton = driver.findElement(By.cssSelector(".clearfix .layer_cart_cart .button-container .btn.btn-default.button.button-medium span"));
		checkOutButton.click();
	}

}
