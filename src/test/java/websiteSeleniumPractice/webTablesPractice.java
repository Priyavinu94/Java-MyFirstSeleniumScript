package websiteSeleniumPractice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class webTablesPractice {

	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seleniumpractise.blogspot.com/2021/08/webtable-in-html.html");
		driver.manage().window().maximize();
	}

	@Test
	public void switchToIframe() {

		// xpath : //table[@id='customers']//tbody//tr[1]//th[1]

		List<WebElement> rowsList = driver.findElements(By.cssSelector("#customers tr"));
		// locator using xpath : //table[@id='customers']//tbody//tr

		List<WebElement> columnsList = driver.findElements(By.cssSelector("#customers tr th"));
		// locator using xpath : //table[@id='customers']//tbody//tr//th

		String beforeIndex = "//table[@id='customers']//tbody//tr[";
		String afterIndex = "]//td[";

		// iterating through table elements and printing
		for (int i = 2; i <= rowsList.size(); i++) {
			for (int j = 1; j <= columnsList.size(); j++) {
				WebElement tableElement = driver.findElement(By.xpath(beforeIndex + i + afterIndex + j + "]"));
				if (!tableElement.getText().equals("")) {
					System.out.println(tableElement.getText());
				} else {
					System.out.println("------------ ");
				}
			}
		}

	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
