package com.flynas.scripts.android.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;


import com.flynas.workflows.*;

public class TC01_a_memberRegistration extends BookingPageFlow{
	
	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_01_a_memberRegistration(String Description) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			//instantiating page objects
			
			Homepage homepage = new Homepage();
			
			homepage.select_TittleMenu();
			homepage.Click_Register();
			RegistrationPage RegisterPg = new RegistrationPage();
			String username = RegisterPg.register("","Adult"); // Registering a new Adult member
			
			//Loging out
			homepage.select_TittleMenu();
  			homepage.Click_logout();
			
			//Verifying member registration by loging in.
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username, "Test@123");		
						
			Reporter.SuccessReport("TC01_a_memberRegistration", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC01_a_memberRegistration", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{"Validate Member Login"}};
	}

}
