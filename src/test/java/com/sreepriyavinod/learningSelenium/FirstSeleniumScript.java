package com.sreepriyavinod.learningSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstSeleniumScript {
	
	WebDriver driver;	//WebDriver is Interface, declaring interface reference

	@BeforeMethod
	public void setUp() {
		
		// setting up the chrome driver path using setProperty()
		// method takes key-value pairs
		// key for chrome driver = "webdriver.chrome.driver",
		// value = the path of the location in which chrome driver is in the system
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");
		
		//initialize interface reference with ChromeDriver object (child class object - parent reference)
		driver = new ChromeDriver();
		
		//Get the URL of the Web page - which needs to be automated
		driver.get("https://demoqa.com/login");		
		
	}
	
	@Test
	public void testMethod() {
		System.out.println("My first test case");
	}

}
