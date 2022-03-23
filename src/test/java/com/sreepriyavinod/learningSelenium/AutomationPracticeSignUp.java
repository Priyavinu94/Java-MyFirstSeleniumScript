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

public class AutomationPracticeSignUp {

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
	public void verifySignUpFunctionality() {

		WebElement emailInput = wd.findElement(By.id("email_create"));
		WebElement createAccButton = wd.findElement(By.id("SubmitCreate"));
		
		String emailID = createRandomString("alphaNumeric", 8) + "@gmail.com";
		emailInput.sendKeys(emailID);
		createAccButton.submit();

		// finding Elements to input the required field
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

		// provide all the input
		genderInput.click();
		String firstName = createRandomString("regular", 8).toUpperCase();
		firstNameInput.sendKeys(firstName);
		String lastName = createRandomString("regular", 7).toUpperCase();
		lastNameInput.sendKeys(lastName);
		String password = createRandomString("alphaNumeric", 9);
		passwordInput.sendKeys(password);

		Select selection;
		selection = new Select(dateInput);
		selection.selectByValue(String.valueOf(createRandomNumber(1, 28)));
		selection = new Select(monthInput);
		selection.selectByValue(String.valueOf(createRandomNumber(1, 12)));
		selection = new Select(yearInput);
		selection.selectByValue(String.valueOf(createRandomNumber(1900, 2022)));

		newsletterCheckbox.click();
		receiveOfferCheckbox.click();

		addressLine1Input.sendKeys("#567, XYZ Street");
		addressCityInput.sendKeys("Toronto ON Canada");
		selection = new Select(addressStateInput);
		selection.selectByValue("32");
		addressZipcodeInput.sendKeys("12345");
		selection = new Select(addressCountryInput);
		selection.selectByValue("21");
		additionalInfoInput.sendKeys("Not a US Resident. Complete Residential Address provided in Alias address field");
		phoneNumInput.sendKeys(createRandomString("numeric", 10));

		aliasAddressInput.clear();
		aliasAddressInput.sendKeys("#567,XYZ St,Toronto ON,A1B2C3,CA");
		submitAccButton.click();

		WebElement currentUserName = wd.findElement(By.className("account"));
		Assert.assertEquals(currentUserName.getText(), firstName + " " + lastName, "Login not successful");

	}

	@AfterMethod
	public void closeBrowser() {
		// wd.quit();
	}

	// method to create Random String of length n
	public String createRandomString(String stringType, int n) {

		String str;
		if (stringType.equals("alphaNumeric")) {
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		} else if (stringType.equals("regular")) {
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
		} else { // stringType is numeric
			str = "0123456789";
		}

		StringBuilder randomString = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			// Math.random generates a random value between 0.0 and 1.0 ---
			// this value times str.length() gives a random double value,
			// explicitly type casting to integer type
			int index = (int) (str.length() * Math.random());
			randomString.append(str.charAt(index));
		}
		return randomString.toString();
	}

	// method to create a random integer within a given range
	public int createRandomNumber(int minOfRange, int maxOfRange) {
		
		// Math.Random() returns any random value between 0.0 and 1.0
		// this value * (maxOfRange - minOfRange) rounded to nearest integer gives a
		// number b/w minOfRange and maxOfRange
		
		return (int) (Math.round(Math.random() * (maxOfRange - minOfRange))) + minOfRange;
	}

}
