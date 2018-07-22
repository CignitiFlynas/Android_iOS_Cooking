package com.flynas.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class SettingPageLocators extends ActionEngine{
	
	public static By options = By.xpath("//*[resource-id='com.flynas.android.app:id/title']");
	public static By option(String option) {
		return By.xpath("//*[@text='"+option+"']");
	}
	public static By menuitem = By.xpath("//*[@class='android.widget.TextView']");
	public static By backIcn = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleBack']");
	
}
