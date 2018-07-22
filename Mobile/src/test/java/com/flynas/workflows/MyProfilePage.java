package com.flynas.workflows;

import java.util.concurrent.TimeUnit;

import com.ctaf.support.ExcelReader;
import com.ctaf.utilities.Reporter;

import com.flynas.testObjects.*;

public class MyProfilePage extends ProfilePageLocators{
	
	public void waitforpageload() throws InterruptedException{	
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		}
	
	public void updatePswd(String crntPswd, String newPswd) throws Throwable {
		ExcelReader xls = new ExcelReader(configProps.getProperty("Credentialsdata"),"ChangepswdUser");

		waitforpageload();
		try{
			type(ProfilePageLocators.crntpswd, crntPswd, "Current Password");
			type(ProfilePageLocators.crntpswd, newPswd," New Password");
			click(ProfilePageLocators.prflupdtbtn,"Profile Update Btn");
			xls.setCellData("ChangepswdUser", "password", 1, newPswd);
			Reporter.SuccessReport("Modifying mobile number :", "Mobile number updated Successfully");
		}catch(Exception e){
			Reporter.failureReport("Modifying mobile number :", "Mobile number update failed");
		}
		
	}
	
	public void updateMobilenum() throws Throwable {
		waitforpageload();
		try{
			type(ProfilePageLocators.prflmobile, randomNumericString(10), "MobileNumber");
			click(ProfilePageLocators.cntctupdtbtn,"Mobile Update Btn");
			Reporter.SuccessReport("Modifying mobile number :", "Mobile number updated Successfully");
		}catch(Exception e){
			Reporter.failureReport("Modifying mobile number :", "Mobile number update failed");
		}
		
	}
	
	
}
