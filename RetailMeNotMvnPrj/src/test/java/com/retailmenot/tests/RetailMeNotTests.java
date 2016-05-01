package com.retailmenot.tests;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.retailmenot.util.Utility;
import com.retialmenot.pages.DealsPage;
import com.retialmenot.pages.HomePage;

public class RetailMeNotTests {
	WebDriver driver;
	HomePage hPage;
	DealsPage dealsPage;
	 Logger logger;
	@Parameters({"url","browserType"})
	@BeforeTest
	public void invokeBrowser(String url,String browserType) throws IOException
	{
		if(browserType.equals("FF"))
		{
			driver =  new FirefoxDriver();
		}
		else if(browserType.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		
		driver.get(url);
		
		Utility.loadProperties("Object.properties");
        
		logger=Logger.getLogger("RetailMeNotTests");        
        
		PropertyConfigurator.configure("log4j.properties");

		logger.info("Browser Opened Successfully");
		
	}
	 
	@DataProvider(name="DP")
	public String[][] feedDP() throws BiffException, IOException
	{
		String data[][] =Utility.readXlsFile("DealsInputData.xls","sanity");
		return data;
	}
	@Test
	public void verifyDealsPageTitle()
	{
		 hPage = new HomePage(driver);		
		 dealsPage = hPage.browseProductDeals();
		 boolean result = dealsPage.verifyDealsPageTitle();
		 Assert.assertTrue(result);
		 logger.info("Verify Deals Page Tilte is passed");
		
	}
	//@Parameters({"productCategory","dealsCount"})
	@Test(dependsOnMethods={"verifyDealsPageTitle"},dataProvider="DP")
	public void verifyDealsCount(String productCategory,String dealsCount)
	{
		int actualCount = dealsPage.getCategoryItemsCount(productCategory);
		
		logger.info("The category items count " + actualCount);
		
		int expectedCount = Integer.parseInt(dealsCount);
		
		Assert.assertEquals(actualCount, expectedCount);
		
				
	}
	@AfterTest
	public void shutdownBrowser()
	{
		driver.quit();
	}
}


















