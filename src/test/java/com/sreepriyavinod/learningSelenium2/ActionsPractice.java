package com.sreepriyavinod.learningSelenium2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsPractice {

	WebDriver driver;
	Actions action; // declaring Actions class object reference

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/buttons");

		action = new Actions(driver); // instantiating Actions class
	}

	@Test // method to test click function
	public void testClickFunctionality() {

		WebElement clickMeButton = driver.findElement(By.cssSelector("div.col-12.mt-4.col-md-6 div.mt-4:nth-of-type(3) button"));
		action.click(clickMeButton).perform();

		WebElement clickSuccessMsg = driver.findElement(By.id("dynamicClickMessage"));
		Assert.assertEquals(clickSuccessMsg.getText(), "You have done a dynamic click");
	}

	@Test
	public void testDoubleClickFunctionality() {

		WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
		action.doubleClick(doubleClickButton).perform();

		WebElement doubleClickSuccessMsg = driver.findElement(By.id("doubleClickMessage"));
		Assert.assertEquals(doubleClickSuccessMsg.getText(), "You have done a double click");
	}

	@Test
	public void testRightClickFunctionality() {

		WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
		action.contextClick(rightClickButton).perform();

		WebElement rightClickSuccessMsg = driver.findElement(By.id("rightClickMessage"));
		Assert.assertEquals(rightClickSuccessMsg.getText(), "You have done a right click");
	}

	@Test
	public void testDragFunctionality() {

		driver.navigate().to("https://demoqa.com/dragabble");
		WebElement mainHeader = driver.findElement(By.className("main-header"));
		Assert.assertEquals(mainHeader.getText(), "Dragabble");

		WebElement dragMeBox = driver.findElement(By.id("dragBox"));
		action.clickAndHold(dragMeBox).moveByOffset(1, 1).perform();
		String getAttribute = dragMeBox.getAttribute("class");
		Assert.assertEquals(getAttribute, "drag-box ui-draggable ui-draggable-handle ui-draggable-dragging");
	
	}

	@Test
	public void testDragAndDropFunctionality() {
		
		driver.navigate().to("https://demoqa.com/droppable");
		WebElement mainHeader = driver.findElement(By.className("main-header"));
		Assert.assertEquals(mainHeader.getText(), "Droppable");
		
		WebElement sourceElement = driver.findElement(By.id("draggable"));
		WebElement targetElement = driver.findElement(By.id("droppable"));
		action.dragAndDrop(sourceElement, targetElement).perform();
		Assert.assertEquals(targetElement.getText(), "Dropped!");
		
	}
	
	@Test
	public void testMouseHoverFunctionality() {
		
		WebElement mouseHoverElement = driver.findElement(By.id("toolTipButton"));
		action.moveToElement(mouseHoverElement).perform();
		
		String attributeValue = mouseHoverElement.getAttribute("aria-describedby");
		Assert.assertEquals(attributeValue, "buttonToolTip");
		
	}

	@AfterMethod
	public void closeBrowser() {
		// driver.quit();
	}
}
