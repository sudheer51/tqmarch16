package com.retialmenot.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DealsPage {
	WebDriver driver;
	public DealsPage(WebDriver driver) {
		this.driver = driver;
 
	}
	public boolean verifyDealsPageTitle()
	{
		boolean result = false;
		// Check that we're on the right page.
		if ("Product Deals From RetailMeNot".equals(driver.getTitle())) {
			// Alternatively, we could navigate to the login page, perhaps logging out first
			result = true;
		}
		return result;
	}
	public int getCategoryItemsCount(String productCategory)
	{
		driver.findElement(By.partialLinkText(productCategory)).click();
																		//  "+   value         +"
		List<WebElement> itemsList = driver.findElements(By.cssSelector("#"+productCategory +">div>div"));
		return itemsList.size();
		
	}
}
