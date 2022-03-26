package websiteSeleniumPractice;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MulitpleWindowPractice {

	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
		driver.manage().window().maximize();
	}

	@Test
	public void multipleWindows() {

		String parentHandle = driver.getWindowHandle();
		System.out.println("Parent Window ID : " + parentHandle + " loads " + driver.getTitle());

		// click on the first link to start new Tab
		WebElement startNewTabLink1 = driver.findElement(By.xpath("//div[@class='post-body entry-content']//a[1]"));
		startNewTabLink1.click(); // opens new tab

		String childHandle1 = "";
		Set<String> handlesSet1 = driver.getWindowHandles(); // Set of 2 strings - {parentHandle, childHandle1}
		for (String handle : handlesSet1) {
			if (!handle.equalsIgnoreCase(parentHandle)) {
				childHandle1 = handle; // gets the window handle for new tab
				break;
			}
		}
		driver.switchTo().window(childHandle1); // switching to new tab opened
		System.out.println("First child Window ID : " + childHandle1 + " loads " + driver.getTitle());
		//Assertion
		Assert.assertEquals(driver.getTitle(), "Google");	

		// switching back to the parent window
		driver.switchTo().window(parentHandle);

		// click on second link to start new tab
		WebElement startNewTabLink2 = driver.findElement(By.xpath("//div[@class='post-body entry-content']//a[2]"));
		startNewTabLink2.click();

		String childHandle2 = "";
		Set<String> handlesSet2 = driver.getWindowHandles(); // Set of 3 strings - {parentHandle, childHandle1, childHandle2}
		for (String handle : handlesSet2) {
			if (!handlesSet1.contains(handle)) {
				childHandle2 = handle;	// gets the window handle for new tab
				break;
			}
		}
		driver.switchTo().window(childHandle2);	// switching to new tab opened
		System.out.println("Second child Window ID : " + childHandle2 + " loads " + driver.getTitle());
		//Assertion
		Assert.assertEquals(driver.getTitle().contains("Facebook"), true);
		
		// switching back to the parent window
		driver.switchTo().window(parentHandle);

		
		// click and open 3rd link in new tab
		WebElement startNewTabLink3 = driver.findElement(By.xpath("//div[@class='post-body entry-content']//a[3]"));
		startNewTabLink3.click();

		String childHandle3 = "";
		Set<String> handlesSet3 = driver.getWindowHandles();	// Set of 4 strings - parentHandle and 3 childHandles
		for (String handle : handlesSet3) {
			if (!handlesSet2.contains(handle)) {
				childHandle3 = handle;
				break;
			}
		}
		driver.switchTo().window(childHandle3);	//switching to new tab
		System.out.println("Third child Window ID : " + childHandle3 + " loads " + driver.getTitle());
		//Assertion
		Assert.assertEquals(driver.getTitle().contains("Yahoo"), true);
		
		// switching back to the parent window
		driver.switchTo().window(parentHandle);
		//Assertion
		Assert.assertEquals(driver.getTitle().contains("Selenium Practise"), true);
	}

}
