package com.retialmenot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.retailmenot.util.Utility;

public class HomePage {
	WebDriver driver;
	
//	// The element is now looked up using the name attribute
//    @FindBy(how = How.LINK_TEXT, using = "Coupons")
//    private WebElement coupounsLink;
//    
// // The element is now looked up using the name attribute
//    @FindBy(how = How.XPATH, using = "Product Deals")
//    private WebElement productDealsLink;
    
    
	 public HomePage(WebDriver driver) {
	        this.driver = driver;
	        
	        //PageFactory.initElements(driver, HomePage.class);

	        // Check that we're on the right page.
	        if (!"RetailMeNot Coupons, Promo Codes and Mobile App".equals(driver.getTitle())) {
	            // Alternatively, we could navigate to the login page, perhaps logging out first
	            throw new IllegalStateException("This is not the Home page");
	        }
	    }
	 public DealsPage browseProductDeals()
	 {
		 driver.findElement(By.linkText(Utility.pro.getProperty("CouponsLinkText"))).click();
		 driver.findElement(By.linkText("Product Deals")).click();
//		 coupounsLink.click();
		// productDealsLink.click();
		 return new DealsPage(driver);
	 }

}
