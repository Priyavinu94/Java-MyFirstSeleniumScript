package com.sreepriyavinod.learningSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationAssignment {

	WebDriver wd;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");

		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		wd.manage().window().maximize();
	}

	@Test
	public void createAccount() {

		
		WebElement emailInput = wd.findElement(By.id("email_create"));
		WebElement createAccButton = wd.findElement(By.id("SubmitCreate"));

		emailInput.sendKeys("priya12345@gmail.com");
		createAccButton.submit();

		//finding Elements to input the required field
		WebElement genderInput = wd.findElement(By.id("id_gender2"));
		WebElement firstNameInput = wd.findElement(By.id("customer_firstname"));
		WebElement lastNameInput = wd.findElement(By.id("customer_lastname"));
		WebElement passwordInput = wd.findElement(By.id("passwd"));
		WebElement dateInput = wd.findElement(By.id("days"));
		WebElement monthInput = wd.findElement(By.id("months"));
		WebElement yearInput = wd.findElement(By.id("years"));
		WebElement newsletterCheckbox = wd.findElement(By.id("newsletter"));
		WebElement receiveOfferCheckbox = wd.findElement(By.id("optin"));
		WebElement addressLine1Input = wd.findElement(By.id("address1"));
		WebElement addressCityInput = wd.findElement(By.id("city"));
		WebElement addressStateInput = wd.findElement(By.id("id_state"));
		WebElement addressZipcodeInput = wd.findElement(By.id("postcode"));
		WebElement addressCountryInput = wd.findElement(By.id("id_country"));
		WebElement additionalInfoInput = wd.findElement(By.id("other"));
		WebElement phoneNumInput = wd.findElement(By.id("phone_mobile"));
		WebElement aliasAddressInput = wd.findElement(By.id("alias"));
		WebElement submitAccButton = wd.findElement(By.id("submitAccount"));

		//provide all the input
		genderInput.click();
		firstNameInput.sendKeys("Sree");
		lastNameInput.sendKeys("priya");
		passwordInput.sendKeys("priya12345");

		Select selection;
		selection = new Select(dateInput);
		selection.selectByValue("24");
		selection = new Select(monthInput);
		selection.selectByValue("10");
		selection = new Select(yearInput);
		selection.selectByValue("1994");

		newsletterCheckbox.click();
		receiveOfferCheckbox.click();

		addressLine1Input.sendKeys("#567, XYZ Street");
		addressCityInput.sendKeys("Toronto ON Canada");
		selection = new Select(addressStateInput);
		selection.selectByValue("32");
		addressZipcodeInput.sendKeys("A1B2C3");
		selection = new Select(addressCountryInput);
		selection.selectByValue("21");
		additionalInfoInput.sendKeys("Not a US Resident. Complete Residential Address provided in Alias address field");
		phoneNumInput.sendKeys("41613215664");

		aliasAddressInput.clear();
		aliasAddressInput.sendKeys("#567, XYZ Street, Toronto ON, A1B2C3, Canada");
		submitAccButton.submit();

		Assert.assertEquals(wd.getCurrentUrl(), "http://automationpractice.com/index.php?controller=authentication",
				"Not redirected to authentication page");

	}

	@AfterMethod
	public void closeBrowser() {
		wd.quit();
	}

}
