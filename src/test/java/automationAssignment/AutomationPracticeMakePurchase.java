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
import org.testng.annotations.AfterMethod;
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

		// input email and password,  click login
		emailInput.sendKeys("sree1234@gmail.com");
		passwordInput.sendKeys("sree1234");
		signInButton.click();

		// 2. Assert User name *****************************
		WebElement currentUserName = driver.findElement(By.className("account"));
		Assert.assertEquals(currentUserName.getText(), "Sree Vin");

		// 3. Click on "WOMEN" *****************************
		WebElement womenButton = driver
				.findElement(By.cssSelector("#block_top_menu>ul:nth-of-type(1)>li:nth-of-type(1)"));
		womenButton.click();

		WebElement navigationPageTitle = driver.findElement(By.className("navigation_page"));
		Assert.assertEquals(navigationPageTitle.getText(), "Women");

		// 4. Click on Quick view of first product 'Faded Short Sleeve T-shirts'
		WebElement productView = driver
				.findElement(By.xpath("//a[@class='product_img_link' and @title='Faded Short Sleeve T-shirts']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productView);
		productView.click();

		// 5. Switch to Iframe -- Update quantity of the selected product
		driver.switchTo().frame(0); // passing id/name gives exception --- NoSuchFrameFound
		WebElement quantityInput = driver.findElement(By.cssSelector("#quantity_wanted_p input.text"));
		quantityInput.clear();
		quantityInput.sendKeys("2"); // Input quantity '2'

		// 6. Updating size
		WebElement sizeSelectionBox = driver.findElement(By.id("group_1"));
		Select selectSize = new Select(sizeSelectionBox);
		selectSize.selectByValue("3"); // Select size 'L'

		String productNameDisplay = driver.findElement(By.cssSelector("div.pb-center-column h1")).getText();
		WebElement size = driver.findElement(By.cssSelector("#uniform-group_1>span"));
		String sizeSelected = size.getText();
		WebElement colour = driver.findElement(By.cssSelector("#color_to_pick_list a.color_pick.selected"));
		String colourSelected = colour.getAttribute("title");

		// 7. Click on Add To Cart
		WebElement addToCartButton = driver.findElement(By.id("add_to_cart"));
		addToCartButton.click(); // submit() directly goes to Summary page skipping the confirmation part.

		// 8. Asserting product added to cart message
		driver.switchTo().defaultContent();
		WebElement successMessage = driver.findElement(By.cssSelector("#layer_cart div.layer_cart_product>h2"));
		WebElement productName = driver.findElement(By.id("layer_cart_product_title"));
		WebElement productAttributes = driver.findElement(By.id("layer_cart_product_attributes"));
		WebElement quantity = driver.findElement(By.id("layer_cart_product_quantity"));
		
		try {
			Thread.sleep(2000); // Thread to pause until all texts are displayed in the browser
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(successMessage.getText(), "Product successfully added to your shopping cart");

		// 9. Asserting Product and quantity
		Assert.assertEquals(productName.getText(), productNameDisplay);
		Assert.assertEquals(productAttributes.getText(), colourSelected + ", " + sizeSelected);
		Assert.assertEquals(quantity.getText(), "2");

		WebElement totalPriceElement = driver
				.findElement(By.cssSelector("div.layer_cart_cart span.ajax_block_cart_total"));
		String totalPrice = totalPriceElement.getText();

		// 10. Click on 'Proceed to CheckOut'
		WebElement proceedToCheckOutButton = driver
				.findElement(By.cssSelector("div.layer_cart_cart>div.button-container>a"));
		proceedToCheckOutButton.click();

		// 11. Summary Page : Asserting total price
		WebElement totalPriceSummary = driver.findElement(By.id("total_price"));
		Assert.assertEquals(totalPriceSummary.getText(), totalPrice);

		// 12. Proceed to Checkout
		WebElement proceedToCheckOutButton2 = driver.findElement(By.cssSelector("p.cart_navigation a:first-child"));
		proceedToCheckOutButton2.click();

		// 13. Enter Order Message
		WebElement orderMessageBox = driver.findElement(By.cssSelector("#ordermsg textarea"));
		orderMessageBox.sendKeys(
				"Product Ordered: Faded Short Sleeve T-shirts\r\n" + "Quantity : #2, Color : Orange, Size : L");

		// 14. Proceed to CheckOut
		WebElement proceedToCheckOutButton3 = driver.findElement(By.cssSelector("p.cart_navigation button"));
		proceedToCheckOutButton3.submit();

		// 15. Click check box to agree terms and conditions
		WebElement checkBox = driver.findElement(By.id("cgv"));
		if (!checkBox.isSelected()) {
			checkBox.click();
		}

		// 16. Proceed to CheckOut
		WebElement proceedToCheckOutButton4 = driver.findElement(By.cssSelector("p.cart_navigation button"));
		proceedToCheckOutButton4.click(); // submit does not navigates to next page

		// 17. Pay by bank
		WebElement payByBankOption = driver.findElement(By.className("bankwire"));
		payByBankOption.click();

		// 18. Asserting payment method
		WebElement paymentMethodConfirmation = driver.findElement(By.className("page-subheading"));
		Assert.assertEquals(paymentMethodConfirmation.getText(), "BANK-WIRE PAYMENT.");

		// 19. Final CheckOut
		WebElement finalCheckOutButton = driver.findElement(By.cssSelector("p.cart_navigation button"));
		finalCheckOutButton.submit();

		// 20. Asserting Order confirmation
		WebElement orderConfirmationMessage = driver.findElement(By.className("cheque-indent"));
		Assert.assertEquals(orderConfirmationMessage.getText(), "Your order on My Store is complete.");

	}
	
	@AfterMethod
	public void closeBrowser() {
		// driver.quit();
	}

}
