package websiteSeleniumPractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFramesPractice {
	
	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seleniumpractise.blogspot.com/2017/01/frames-in-selenium-webdriver.html");
		driver.manage().window().maximize();
	}
	
	@Test
	public void switchToIframe() {
		
		//Finding iframe element
		WebElement iFrameElement = driver.findElement(By.id("google"));
		
		//switch to iframe
		driver.switchTo().frame(iFrameElement);
		
		//finding an element inside iframe and clicking on it
		WebElement handlePopupLink = driver.findElement(By.cssSelector("article.post-5276 a.entry-title-link"));
		handlePopupLink.click();
		
		String text = driver.findElement(By.cssSelector("#genesis-content div.breadcrumb")).getText();
		System.out.println(text);
		//Assertion
		Assert.assertEquals(text.contains("Handle Authentication Pop Up"), true);
		
		//switch to parent page and assert
		driver.switchTo().defaultContent();

		WebElement headingElement = driver.findElement(By.xpath("//h3[@itemprop='name']"));
		Assert.assertEquals(headingElement.getText(), "Frames in Selenium WebDriver");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
