package com.flynas.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class RegistrationPageLocators extends ActionEngine{
	
	//Menu locators
	public static By register_tittle = By.xpath("//*[@resource-id='com.flynas.android.app:id/titleTitle']");
	public static By userId = By.id("com.flynas.android.app:id/registerDetailsEmail");
	public static By pswd = By.id("com.flynas.android.app:id/registerDetailsPassword");
	public static By tittle = By.xpath("//*[@resource-id='com.flynas.android.app:id/registerContactTitle']");
	public static By membertittle = By.xpath("//*[@resource-id='com.flynas.android.app:id/textLabel']");
	public static By firstname = By.id("com.flynas.android.app:id/registerContactFirstName");
	public static By lastname = By.id("com.flynas.android.app:id/registerContactLastName");
	public static By Mobilenumber = By.id("com.flynas.android.app:id/registerContactMobile");
	public static By dateofbirth = By.xpath("//*[@resource-id='com.flynas.android.app:id/registerContactDateOfBirth']");	
	public static By datepicker = By.xpath("//*[@resource-id='android:id/numberpicker_input']");
	public static By selectdate = By.xpath("//*[@resource-id='com.flynas.android.app:id/buttonDefaultPositive']");
	public static By doctypefdl= By.xpath("//*[@resource-id='com.flynas.android.app:id/registerDocumentsType']");
	public static By doctype= By.xpath("//*[@resource-id='com.flynas.android.app:id/textLabel']");
	public static By documentNmbr = By.id("com.flynas.android.app:id/registerDocumentsIDNumber");
	public static By docExpDt = By.xpath("//*[@resource-id='com.flynas.android.app:id/registerDocumentsIDExpiration']");
	public static By registerbtn = By.xpath("//*[@resource-id='com.flynas.android.app:id/profilePassengerUpdateButton']");
	
	
}
