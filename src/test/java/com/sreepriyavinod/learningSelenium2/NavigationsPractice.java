package com.sreepriyavinod.learningSelenium2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationsPractice {

	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://demoqa.com/");

	}
	
	@Test
	public void practiceNavigations() {
		
		//navigating to another web page ---using navigate().to(String URL) 
		driver.navigate().to("http://automationpractice.com/");
		
		//using navigate().back() --- navigating back to https://demoqa.com/
		driver.navigate().back();
		
		//using navigate().forward() --- navigating again to http://automationpractice.com/
		driver.navigate().forward();
		
		//refresh/reload --- using navigate().refresh()
		driver.navigate().refresh();
	}
	
	@AfterMethod
	public void closeBrowser() {
		// driver.quit();
	}
}
