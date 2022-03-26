package com.sreepriyavinod.learningSelenium2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsPractice {

	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();

	}

	@Test
	public void acceptSimpleAlert() {

		WebElement simpleAlertButton = driver.findElement(By.id("alertButton"));
		simpleAlertButton.click();

		// accepting alert
		driver.switchTo().alert().accept();
	}

	@Test
	public void acceptOrDismissConfirmationAlert() {

		WebElement confirmButton = driver.findElement(By.id("confirmButton"));
		confirmButton.click();
		driver.switchTo().alert().accept();

		// dismissing alert
		confirmButton.click();
		driver.switchTo().alert().dismiss();
	}

	@Test
	public void promptAlert() {

		WebElement promptButton = driver.findElement(By.id("promtButton"));
		promptButton.click();

		// Declare Alert (interface) type reference to perform multiple action with
		// alerts
		Alert alertBox = driver.switchTo().alert();
		System.out.println("Alert box text : " + alertBox.getText()); // to get the text from the alert box
		
		alertBox.sendKeys("Priya");
		alertBox.accept();
		
		//Assert.assertEquals(promptButton.getText(), "You entered Priya");
	}

}
