package com.sreepriyavinod.learningSelenium2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class ExplicitWaitPractice {
	
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
		
		wait = new WebDriverWait(driver, 10);
	}
	


}
