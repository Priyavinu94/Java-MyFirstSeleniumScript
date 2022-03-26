package websiteSeleniumPractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWaitPractice {

	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html");
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, 15);
	}

	@Test
	public void useExplicitWait() {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Click me')]")));
		WebElement clickMeButton = driver.findElement(By.xpath("//button[contains(text(),'Click me')]"));
		clickMeButton.click();

		// 1. condition passed - to locate element when the text present is "Selenium"
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("demo"), "Selenium"));
		String text = driver.findElement(By.id("demo")).getText(); // getting text from the element

		// using Assertion : Assert.assertEquals(boolean actual, boolean expected)
		Assert.assertEquals(text.equals("Selenium"), true);

		// 2. condition passed - visibility of Element located
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
		String text2 = driver.findElement(By.xpath("//p[text()='WebDriver']")).getText();
		Assert.assertEquals(text2, "WebDriver", "Expected text to be displayed is WebDriver");
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
