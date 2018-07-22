package com.flynas.workflows;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import com.ctaf.utilities.Reporter;

import com.flynas.testObjects.*;


public class SettingsPage extends SettingPageLocators{
	
	public void set(String category,String option ) throws Throwable
	{	try{driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		List<WebElement> menuItems = driver.findElements(SettingPageLocators.menuitem);
		if(category.equalsIgnoreCase("Language"))
			menuItems.get(2).click();
		else if(category.equalsIgnoreCase("Currency"))
			menuItems.get(3).click();
		else if(category.equalsIgnoreCase("Contact us"))
			menuItems.get(4).click();
		else if(category.equalsIgnoreCase("App Feedback"))
			menuItems.get(5).click();
		else if(category.equalsIgnoreCase("Rate Flynas app"))
			menuItems.get(6).click();
		else if(category.equalsIgnoreCase("Terms & Conditions"))
			menuItems.get(7).click();
		else if(category.equalsIgnoreCase("Privacy Policy"))
			menuItems.get(8).click();
		
		Thread.sleep(2000);
		click(SettingPageLocators.option(option), option);
		
		Reporter.SuccessReport("Changing "+category+" in Settings", category+" changed to "+option);
		}catch (Exception e){
			Reporter.failureReport("Changing "+category+" in Settings", category+" could not be changed to "+option);
		}
	}
	public void click_BackIcn() throws Throwable{
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		click(SettingPageLocators.backIcn, "Back Icon");
	}
	
	
}
