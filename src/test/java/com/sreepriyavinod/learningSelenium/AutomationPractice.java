package com.sreepriyavinod.learningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPractice {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		// setting up the chrome driver path using setProperty()
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		
		//initialize interface reference with ChromeDriver object 
		driver = new ChromeDriver();
		
		//Get the URL of the Web page 
		driver.get("http://automationpractice.com/index.php?controller=contact");	
		
	}
	
	@Test
	public void chooseSubjectHeading() {
		
		//finding the elements
		WebElement subjectHeadingInput = driver.findElement(By.id("uniform-id_contact"));
		WebElement emailAddressInput = driver.findElement(By.id("email"));
		WebElement id_orderInput = driver.findElement(By.id("id_order"));
		WebElement sendQuery = driver.findElement(By.id("submitMessage"));
		WebElement messageInput = driver.findElement(By.id("message"));
		
		subjectHeadingInput.click();	//clicks on subjectHeading drop down
		
		WebElement selection = driver.findElement(By.id("id_contact"));
		Select select = new Select(selection);	//instantiating select tag
		
		select.selectByIndex(1); 	//--select by index
		//select.selectByValue("2"); 	//---select by value
		//select.selectByVisibleText("Customer service"); 	//---select by visible text
		
		emailAddressInput.sendKeys("sree@gmail.com");
		id_orderInput.sendKeys("#156123");
		messageInput.sendKeys("Practicing automation");
		//sendQuery.submit(); //----not working
		
		sendQuery.click(); 	//clicks Send button

	}
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
}
