package com.flynas.workflows;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;


import com.flynas.testObjects.*;
import com.flynas.workflows.*;

public class RegistrationPage extends HomePageLocators{
	
	public String register(String userID, String Agegroup) throws Throwable
	{
		
		String[] Fnames = {"Zenia","Brielle","Alec","Grady","Mikayla","Kalia","Jared","Mallory","Moana","Clinton","Renee","Griffin","Merritt","Jenna","Zoe","Carla","Amber","Ayanna","Elvis","Camilla","Scarlet","Andrew","Joel","Timon","Thor","Shad","Simone","Dexter","Tana","Helen","Robert","Veda","Kirby","Molly"};
		String[] Lnames = {"Jones","Williams","Bond","Dawney","Stathom","Stevens","Mccall","Bernard","Sanford","Matthews","Collier","Hooper","Clemons","Graham","Richmond","Richard","Morton","Watts","Bryan","Woods"};
		int Fnameindex = (int) (Math.random() * Fnames.length);    		
    	String firstname =  Fnames[Fnameindex];
    	int Lnameindex = (int) (Math.random() * Lnames.length);
		String lastName = Lnames[Lnameindex];
		
		String  username;
		String  email;
		if(userID!= ""){
			username = userID;
			email = userID;
		}else{		
			username = firstname+lastName+randomNumericString(4);
			email = username+"@cigniti.com";
		}
		String password = "Test@123";
		final String[] Doctype = {"Passport", "National ID Card", "Iqama"};
		Random random = new Random();
		int index = random.nextInt(Doctype.length);
		String doctyp = Doctype[index];
		String docNum = randomString(10);
		
		
		waitForElementPresent(RegistrationPageLocators.tittle, "Register");
		type(RegistrationPageLocators.userId, email, "UserEmail");
		type(RegistrationPageLocators.pswd,password, "Password");
		click(RegistrationPageLocators.tittle, "Title");
		
		List<WebElement> membertitle = driver.findElements(RegistrationPageLocators.membertittle);
		if(Agegroup.contains("Adult"))	
			membertitle.get(0).click();
		else if(Agegroup.contains("Child"))
			membertitle.get(3).click();
		else membertitle.get(4).click();
		
		
		type(RegistrationPageLocators.firstname, firstname , "firstName");
		type(RegistrationPageLocators.lastname, lastName, "LastName");
		
		type(RegistrationPageLocators.Mobilenumber, randomNumericString(10), "MobileNumber");
		click(RegistrationPageLocators.dateofbirth,"DOB");
		BookingPageFlow.scrollKeysForAndroid(new String[] { }, "Date Of Birth",Agegroup);
		click(BookingPageLocators.selectdate, "Select");
		click(RegistrationPageLocators.doctypefdl,"Document Type");
		List<WebElement> doctype = driver.findElements(RegistrationPageLocators.doctype);
		if(doctyp.equalsIgnoreCase("Passport"))
			doctype.get(0).click();
		else if (doctyp.equalsIgnoreCase("National ID Card"))
			doctype.get(1).click();
		else doctype.get(2).click();
		type(RegistrationPageLocators.documentNmbr,docNum, "Document Number");
		click(RegistrationPageLocators.docExpDt,"ID Expiry date");
		BookingPageFlow.scrollKeysForAndroid(new String[] { }, "Document Expiry Date","");
		click(BookingPageLocators.selectdate, "Select");
		click(RegistrationPageLocators.registerbtn,"Register Button");
		if(isElementDisplayedTemp(HomePageLocators.settingsIcn)==true)
		{
		BookingPageFlow.writecredentials(email,password,firstname,lastName,doctyp,docNum);
		return email;
		}
		else return null;
	}
	
	
	
	
	
	
	
	
	

}
