package com.flynas.workflows;

import org.apache.commons.lang3.RandomStringUtils;

import com.ctaf.utilities.Reporter;

import com.flynas.testObjects.*;

public class Homepage extends HomePageLocators{
	
	public void select_Bookflights(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnBF);
			click(HomePageLocators.rgstrdUsrIcnBF, "Book flights");
		}
		else if (userType.equalsIgnoreCase("Anonymous")){
			waitforElement(HomePageLocators.bookFlights);
			click(HomePageLocators.bookFlights, "Book flights");
		}
		
	}
	public void select_Managebooking(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnMMB);
			click(HomePageLocators.rgstrdUsrIcnMMB, "Book flights");
		}else if(userType.equalsIgnoreCase("Anonymous")){
		waitForElementPresent(HomePageLocators.manageBookings, "Book flights");
		click(HomePageLocators.manageBookings, "Manage Bookings");
		}
	}
	public void select_OnlineCheckIn(String userType) throws Throwable
	{
		if(userType.equalsIgnoreCase("registered")){
			waitforElement(HomePageLocators.rgstrdUsrIcnCI);
			click(HomePageLocators.rgstrdUsrIcnCI, "Online CheckIn");
		}else if(userType.equalsIgnoreCase("Anonymous")){
			waitForElementPresent(HomePageLocators.onlineCheckin, "Online CheckIn");
			click(HomePageLocators.onlineCheckin, "Online Check-in");
		}
	}
	public void select_TittleMenu() throws Throwable
	{
//		BookingPageFlow BF = new BookingPageFlow();
//		BF.Handlepopup();
		waitForElementPresent(HomePageLocators.tittleMenu, "Menu Icon");
		click(HomePageLocators.tittleMenu, "TittleMenu");
	}
	public void Click_login() throws Throwable
	{
		waitForElementPresent(HomePageLocators.login, "Login");
		click(HomePageLocators.login, "Login");
	}
	public void Login(String username,String pwd) throws Throwable
	{try{
		System.out.println("User name : "+username);
		System.out.println("password : "+pwd);
		type(HomePageLocators.email, username, "Email");
		type(HomePageLocators.pasword, pwd, "Password");
		click(HomePageLocators.Login_btn, "Login");
		waitforElement(HomePageLocators.rgstrdUsrIcnBF);
		Reporter.SuccessReport("Login", " login Successfull");
		
	}catch(Exception e){
		Reporter.failureReport("Login failure", "Unable to login");
		}
	}
	
	public void verifyLoginSuccess(boolean expected) throws Throwable{
		if(isElementPresent(HomePageLocators.rgstrdUsrIcnBF)==expected)
		Reporter.SuccessReport("verify Login", " login "+expected+" Successfull");
		else
			Reporter.failureReport("verify Login", " login "+expected+" failed");
	}
	
	public void Click_myProfile() throws Throwable
	{
		waitForElementPresent(HomePageLocators.myProfile, "My Profile link");
		click(HomePageLocators.myProfile, "My Profile link");
	}
	
	public void Click_logout() throws Throwable
	{
		Thread.sleep(1000);
		scrollToText("Log Out");
		click(HomePageLocators.logout_btn, "Logout");
	}
	
	public void click_Settings() throws Throwable {
		waitForElementPresent(HomePageLocators.settingsIcn, "Login");
		click(HomePageLocators.settingsIcn, "Settings Icon");
	}
	
	public void  lockAccount(String username) throws Throwable
	{
		try{
		String password = RandomStringUtils.random(8, true, true);
	
		for(int i=0;i<=10;i++){
			type(HomePageLocators.email, username, "Email");
			type(HomePageLocators.pasword, password, "Password");
			click(HomePageLocators.Login_btn, "Login");
			//if(i!=10)
			//verifyAlertPopup();
			}
			Reporter.SuccessReport("Lock account", "Lock account Successful");
		}catch(Exception e){
			Reporter.SuccessReport("Lock account", "Lock account Unsuccessful");
		}
					
	}
	public void Click_Register() throws Throwable {
		waitForElementPresent(HomePageLocators.register, "Register");
		click(HomePageLocators.register, "Register");
	}

}
