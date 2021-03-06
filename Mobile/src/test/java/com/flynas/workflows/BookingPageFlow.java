package com.flynas.workflows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.openqa.selenium.Alert;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import com.flynas.testObjects.BookingPageLocators;




public class BookingPageFlow extends BookingPageLocators{
	
	public void inputBookingDetails(String tripType, String origin, String dest, String deptDate,
			String origin2, String departure2, String retDate, String adults, String child, String infant, String promo,String Currency) throws Throwable{
		
		
		//Select Trip Mode
		waitForVisibilityOfElement(BookingPageLocators.oneWay, "One Way");
		if(tripType.equalsIgnoreCase("Round Trip")){
			click(BookingPageLocators.roundTrip, "Round Trip");
		} else if(tripType.equalsIgnoreCase("One Way")){
			click(BookingPageLocators.oneWay, "One Way");
		} else if(tripType.equalsIgnoreCase("Multi City")){
			click(BookingPageLocators.multiCity, "Multi City");
		}
		
		click(BookingPageLocators.origin, "Origin");
		
		click(BookingPageLocators.search, "Search");
		type(BookingPageLocators.searchEdit, origin, "Origin");
		click(BookingPageLocators.searched_city, "Origin");
		type(BookingPageLocators.searchEdit, dest, "Destination");
		click(BookingPageLocators.searched_city, "Destination");
	
		if(tripType.equalsIgnoreCase("One Way"))
		{
			
			click(BookingPageLocators.Departuredate, "Departure Date");
			select_date(deptDate);
			click(BookingPageLocators.Select_date, "Select Date");
			Thread.sleep(1000);
		}
		
		if(tripType.equalsIgnoreCase("Round Trip"))
		{
			click(BookingPageLocators.Departuredate, "Departure Date");
			select_date(deptDate);
		//	click(BookingPageLocators.Select_date, "Select Date");
		//	click(BookingPageLocators.Returndate_rtp, "Return Date");
			select_date(retDate);
			click(BookingPageLocators.Select_date, "Select Date");
		}
		if(tripType.equalsIgnoreCase("Multi City")){
			
			click(BookingPageLocators.Departuredate_multicity, "DepartureDate");
			select_date(deptDate);
			select_date(retDate);
			click(BookingPageLocators.Select_date, "Select Date");
			click(BookingPageLocators.origin_multicity, "Origin2");
			Thread.sleep(2000);
			click(BookingPageLocators.search, "Search");
			type(BookingPageLocators.searchEdit, origin2, "Origin");
			click(BookingPageLocators.searched_city, "Origin");
			type(BookingPageLocators.searchEdit, departure2, "Destination");
			click(BookingPageLocators.searched_city, "Destination");
		//	click(BookingPageLocators.Returndate_multicity, "ReturnDate");
			
		//	click(BookingPageLocators.Select_date, "Select Date");
		//	scrollToElement(BookingPageLocators.findFlights);
		
			
		}
		int psngrcount = Integer.valueOf(adults)+Integer.valueOf(child)+Integer.valueOf(infant);
		if(psngrcount>1)
		{
			click(BookingPageLocators.Passengercount, "Clicked on passengers");
			{
				if(Integer.valueOf(adults)>1){
					for(int i=1;i<Integer.valueOf(adults);i++)
					{
						click(BookingPageLocators.Audaltplusbutton, "Adult");
						System.out.println("Adults: "+adults);
					}
				}
				if(Integer.valueOf(child)>0){
					for(int i=0;i<Integer.valueOf(child);i++)
					{
						click(BookingPageLocators.childplusbutton, "Child");
						System.out.println("Child: "+child);
					}
				}
				if(Integer.valueOf(infant)>0){
					swipeAndroid(0.50f);
					for(int i=0;i<Integer.valueOf(infant);i++)
					{
						click(BookingPageLocators.infantplusbutton, "Infant");
						System.out.println("Infant: "+infant);
					}
				}
				click(BookingPageLocators.done, "Passenger count Entered");
			}
		}	
				
		if(promo!="")
		{
				click(BookingPageLocators.promo, "promo");
				type(BookingPageLocators.promo,promo,"Entered promo code");
		}
		
	}

	
	public void select_dates(String deptDate, String retDate) throws Throwable{
		click(BookingPageLocators.Departuredate, "Departure Date");
		select_date(deptDate);
		if(retDate!="") 
		select_date(retDate);
		click(BookingPageLocators.Select_date, "Select Date");
	}
	
	public void selectPayWithSmilePoint() throws Throwable{
		scrollToElement(BookingPageLocators.smilePointsbtn);
		click(BookingPageLocators.smilePointsbtn,"Smile points");
	}
	
	public void clickFindFlightsBtn() throws Throwable{
		scrollToText("Find Flights");
		click(BookingPageLocators.findFlights, "Find Flights");
	}

	public void select_date(String deptdate) throws Throwable
	{
		
		String dd = deptdate.split("-")[0];
		System.out.println("dd : "+dd);
		//trim leading zeros in the date
		String date = Integer.valueOf(dd).toString();
		System.out.println("date : "+date);
		String mmYY = deptdate.split("-")[1];
		System.out.println("MMYY : "+mmYY);
		
		  boolean blnFlag =true;
		  while (blnFlag) 
		  {
			//  WebElement ele_CalendarView = driver.findElement(By.xpath("//*[@resource-id='com.flynas.android.app:id/calendar_view']/following::[@class='android.widget.LinearLayout']"));
			  List<WebElement> lst_MonthandYear = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/title']"));
			  if(lst_MonthandYear.get(0).getText().equalsIgnoreCase(mmYY))
			  {
				 System.out.println("Current month : "+lst_MonthandYear.get(0).getText());
			  }
			  else
			  {
				  
				  Dimension   size = driver.manage().window().getSize();
				  System.out.println(size);					   
				  //Find swipe start and end point from screen's with and height.
				  //Find starty point which is at bottom side of screen.
				  int starty = (int) (size.height * 0.80);
				  //Find endy point which is at top side of screen.
				  int endy = (int) (size.height * 0.30);
				  //Find horizontal point where you wants to swipe. It is in middle of screen width.
				  int startx = size.width / 2;
				  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

				  //Swipe from Bottom to Top.
				  AndroidDriver2.swipe(startx, starty, startx, endy, 3000);
				  Thread.sleep(2000);
			  }
			  
			  List<WebElement> ddd = driver.findElements(By.xpath("//*[@text='"+date+"']"));
				 
			  for(int i=0;i<ddd.size();i++)
			  {
				  System.out.print("");
				  if(ddd.get(i).getAttribute("enabled").contains("true")){
					  ddd.get(i).click();
					  break;
				  }
			  }
			blnFlag = false;  			  		  
		  }
	}
		
			
			
	public boolean selectClass(String bookingClass, String tripType) throws Throwable{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		List<WebElement> flights = driver.findElements(BookingPageLocators.flights);
		List<WebElement> ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
		while(ClassArrow.size()==0){
			click(BookingPageLocators.rightarrow, "Calander rightarrow in select class");
			waitforElement(BookingPageLocators.nextFlight);
			click(BookingPageLocators.nextFlight, "Next Available Flight");
			ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
		}
		for(int k=0;k<ClassArrow.size();k++){
			if(bookingClass.contains("Business")){
				if(isElementDisplayedTemp(BookingPageLocators.busOW)==true){
					click(BookingPageLocators.busOW, "Business");
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			if(bookingClass.contains("Simple")){
				if(isElementDisplayedTemp(BookingPageLocators.economyOW)==true){
					click(BookingPageLocators.economyOW, "Economy");
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			if(bookingClass.contains("Extra")){
				if(isElementDisplayedTemp(BookingPageLocators.flexOW)==true){
					click(BookingPageLocators.flexOW, "Flex");
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			}
		if(!tripType.equalsIgnoreCase("One Way")){
			List<WebElement> rtflights = driver.findElements(BookingPageLocators.flights);
			List<WebElement> rtClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
			//rtClassArrow.get(0).click();
			while(rtClassArrow.size()==0){
				click(BookingPageLocators.rightarrow, "Calander rightarrow in select class");
				waitforElement(BookingPageLocators.nextFlight);
				click(BookingPageLocators.nextFlight, "Next Available Flight");
				rtClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
			}
			for(int k=0;k<rtClassArrow.size();k++){
				if(bookingClass.contains("Business")){
					if(isElementDisplayedTemp(BookingPageLocators.busOW)==true){
						click(BookingPageLocators.busOW, "Business");
						break;
					}else{
						rtClassArrow.get(k+1).click();
					}
				}
				if(bookingClass.contains("Simple")){
					if(isElementDisplayedTemp(BookingPageLocators.economyOW)==true){
						click(BookingPageLocators.economyOW, "Simple");
						break;
					}else{
						rtClassArrow.get(k+1).click();
					}
				}
				if(bookingClass.contains("Extra")){
					if(isElementDisplayedTemp(BookingPageLocators.flexOW)==true){
						click(BookingPageLocators.flexOW, "Extra");
						break;
					}else{
						rtClassArrow.get(k+1).click();
					}
				}
				}
		}	
		return true;
	}
	
	public void selectClassForEmployee(String bookingClass) throws Throwable{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		while(isElementPresent(BookingPageLocators.title)==false){
			List<WebElement> ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
			while(ClassArrow.size()==0){
				click(BookingPageLocators.rightarrow, "Calander rightarrow in select class");
				waitforElement(BookingPageLocators.nextFlight);
				click(BookingPageLocators.nextFlight, "Next Available Flight");
				ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
			}
		
			for(int k=0;k<ClassArrow.size();k++){
				if(bookingClass.contains("Simple")){
					if(isElementDisplayedTemp(BookingPageLocators.economyOW)==true){
						click(BookingPageLocators.economyOW, "Economy");
						break;
					}else{
						ClassArrow.get(k+1).click();
					}
				}
				if(bookingClass.contains("Staff Stand By")){
					if(isElementDisplayedTemp(BookingPageLocators.flexOW)==true){
						click(BookingPageLocators.flexOW, "Flex");
						break;
					}else{
						ClassArrow.get(k+1).click();
					}
				}
				if(bookingClass.contains("Staff Confirmed")){
					if(isElementDisplayedTemp(BookingPageLocators.busOW)==true){
						click(BookingPageLocators.busOW, "Business");
						break;
					}else{
						ClassArrow.get(k+1).click();
					}
				}
			}
		}
	}
	
	public String generateString(int size){
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	//public boolean inputPassengerDetails(String domOrInt, String totalPass, String nationality,
	public String[] inputPassengerDetails(String domOrInt, String totalPass, String nationality, 
			String travelDoc, String docNum, String naSmiles, String mobileNum, String emailId,String fname,String lname,String payment2) 
					throws Throwable{
		waitforElement(BookingPageLocators.title);
		String lastName=null,firstname=null;
		String[] FirstLastName = null;
		
		try{
			
			for(Integer count =1; count<=Integer.valueOf(totalPass); count++)
			{
				List<WebElement> Passengernames = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailsRowName']"));
				List<WebElement> titlefields = driver.findElements(BookingPageLocators.title);
				
				String Passenger=Passengernames.get(count-1).getText();
				System.out.println(Passenger);
				titlefields.get(0).click();
				List<WebElement> passengertitle = driver.findElements(BookingPageLocators.passengertitle);
				if(Passenger.contains("Adult"))	
					passengertitle.get(0).click();
				else 
					passengertitle.get(1).click();
				
				
				if(payment2.equalsIgnoreCase("Nas"))
				{
					type(BookingPageLocators.fName,fname , "firstName");
					type(BookingPageLocators.lName, lname, "LastName");

				}	
				else
				{	
					String[] Fnames = {"Zenia","Brielle","Alec","Grady","Mikayla","Kalia","Jared","Mallory","Moana","Clinton","Renee","Griffin","Merritt","Jenna","Zoe","Carla","Amber","Ayanna","Elvis","Camilla","Scarlet","Andrew","Joel","Timon","Thor","Shad","Simone","Dexter","Tana","Helen","Robert","Veda","Kirby","Molly"};
					String[] Lnames = {"Jones","Williams","Bond","Dawney","Stathom","Stevens","Mccall","Bernard","Sanford","Matthews","Collier","Hooper","Clemons","Graham","Richmond","Richard","Morton","Watts","Bryan","Woods"};
					int Fnameindex = (int) (Math.random() * Fnames.length);    		
		        	firstname =  Fnames[Fnameindex];
		        	int index = (int) (Math.random() * Lnames.length);
		    		lastName = Lnames[index];
		    		type(BookingPageLocators.fName,firstname , "firstName");
					type(BookingPageLocators.lName, lastName, "LastName");
				}
				
				click(BookingPageLocators.dateofbirth, "Date Of Birth");
				scrollKeysForAndroid(new String[] { }, "Date Of Birth",Passenger);
				
				click(BookingPageLocators.selectdate, "Select");
							
			//	scrollToElement(BookingPageLocators.documenttype);
				
				if(isElementPresent(BookingPageLocators.documenttype)==false)
				{
					driver.navigate().back();
					click(BookingPageLocators.documenttype, "Document Type");
				}
				else
				{
					click(BookingPageLocators.documenttype, "Document Type");
				}
				driver.findElement(By.xpath("//*[@text='"+travelDoc+"']")).click();
			//	Thread.sleep(3000);
				
			
				
				if(travelDoc.equalsIgnoreCase("National ID Card"))
				{
					docNum = generateString(7);
					List<WebElement> docNumfld = driver.findElements(BookingPageLocators.idnumber);
					docNumfld.get(0).sendKeys(docNum);
					try{
						AndroidDriver2.hideKeyboard();
					}catch(Exception e){
						System.out.println("No keyboard");
					}
					
												
				}
				else
				{
					docNum = generateString(10);
					type(BookingPageLocators.idnumber, docNum, "ID Number");
					click(BookingPageLocators.idexpdate, "Document Expiry date");
					scrollKeysForAndroid(new String[] {}, "Document Expiry Date","");
					click(BookingPageLocators.selectdate, "Select");
				//	Thread.sleep(3000);
									
				}
				swipeAndroid(0.40f);
				
				if(!naSmiles.equalsIgnoreCase(""))
				{
					if(isElementPresent(BookingPageLocators.smily)==true)
					{
						type(BookingPageLocators.smily, naSmiles, "naSmilyNumber");
						//Thread.sleep(3000);
					}
					else
					{
						System.out.println("No Smily");
					}
				}
				
				if(Integer.parseInt(totalPass) > 1 && count < Integer.parseInt(totalPass))
				{
//					List<WebElement> Passengernames = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/passengerDetailsRowName']"));
//					Passenger=Passengernames.get(0).getText();
//					System.out.println(Passenger);
					List<WebElement> plus = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/passenger_details_list_item_main']"));
					plus.get(0).click();
					scrollToText("Title");
//					waitForElementPresent(BookingPageLocators.title, "Tittle");
//					waitforElement(BookingPageLocators.title);
					
				}
			} 
			
			scrollToText("Email Address*");
			click(BookingPageLocators.mobilenum, "Mobile Number");
			type(BookingPageLocators.mobilenum, mobileNum, "MobileNumber");
			click(BookingPageLocators.email, "Email");
			type(BookingPageLocators.email, emailId, "Email Address");
			scrollToElement(BookingPageLocators.continuebtn);
			click(BookingPageLocators.continuebtn, "Continue");
			 FirstLastName = new String[2];
			 FirstLastName[0] =firstname;
			 FirstLastName[1] =lastName;
			 return FirstLastName;
			
		}catch(Exception e){
			e.printStackTrace();
			return FirstLastName;
			
		}
		
		
	
	}
	
	public static boolean scrollKeysForAndroid(String[] list, String locatorname,String Passenger) throws Throwable {
		boolean flag = false;
		Boolean b=false;
		try { 
			if((locatorname.equalsIgnoreCase("Date Of Birth")))
			{		
					WebElement Year = driver.findElementByXPath("//android.widget.NumberPicker[@index='2']//android.widget.EditText");
					WebElement Yearminus = driver.findElementByXPath("//android.widget.NumberPicker[@index='2']//android.widget.Button");
					Point Currentyear = Year.getLocation();
					Point downbtn = Yearminus.getLocation();
					int currentselection = Integer.parseInt(Year.getText());
					int Yeartoselect;
					if(Passenger.contains("Infant"))
					{ 
						Yeartoselect = currentselection-1;
						while(!Year.getText().equalsIgnoreCase(Integer.toString(Yeartoselect))){
								AndroidDriver2.swipe(downbtn.getX(), downbtn.getY(), Currentyear.getX(),Currentyear.getY(), 1000);
	                        }
					}
					else if(Passenger.contains("Child"))
						{
						Yeartoselect = currentselection-5;
						while(!Year.getText().equalsIgnoreCase(Integer.toString(Yeartoselect))){
								AndroidDriver2.swipe(downbtn.getX(), downbtn.getY(), Currentyear.getX(),Currentyear.getY(), 1000);
	                        }
						}
					else
						{
						Yeartoselect = currentselection-13;
						while(!Year.getText().equalsIgnoreCase(Integer.toString(Yeartoselect))){
								AndroidDriver2.swipe(downbtn.getX(), downbtn.getY(), Currentyear.getX(),Currentyear.getY(), 1000);
	                        }
						}
					
				
			}
			else if((locatorname.equalsIgnoreCase("Credit Card Expiry Date")))
				{
					//Selecting month
					WebElement mnth = driver.findElementByXPath("//android.widget.NumberPicker[@index='0']//android.widget.EditText");
					WebElement mnthminus = driver.findElementByXPath("//android.widget.NumberPicker[@index='0']//android.widget.Button[1]");
					while(!mnth.getText().equalsIgnoreCase(list[0])){
						Point Currentmnth = mnth.getLocation();
						Point Mdownbtn = mnthminus.getLocation();
						AndroidDriver2.swipe(Mdownbtn.getX(), Mdownbtn.getY(), Currentmnth.getX(),Currentmnth.getY(), 1000);
						 }
					
					//Selecting Year
					WebElement Year = driver.findElementByXPath("//android.widget.NumberPicker[@index='1']//android.widget.EditText");
					WebElement Yearplus = driver.findElementByXPath("//android.widget.NumberPicker[@index='1']//android.widget.Button[1]");
					WebElement Yearminus = driver.findElementByXPath("//android.widget.NumberPicker[@index='1']//android.widget.Button[2]");
					
					int Selecttbd = Integer.parseInt(list[1]);
					while(!Year.getText().equalsIgnoreCase(list[1])){
						int currentselection = Integer.parseInt(Year.getText());
						if( currentselection > Selecttbd)
						{
							Point Currentyear = Year.getLocation();
							Point downbtn = Yearminus.getLocation();
							AndroidDriver2.swipe(Currentyear.getX(), Currentyear.getY(), downbtn.getX(),downbtn.getY(), 1000);
                        }else if (currentselection < Selecttbd){
                        	Point Currentyear = Year.getLocation();
							Point Upbtn = Yearplus.getLocation();
							AndroidDriver2.swipe(Currentyear.getX(), Currentyear.getY(), Upbtn.getX(),Upbtn.getY(), 1000);
							}
						}
					}
			
			else if((locatorname.equalsIgnoreCase("Document Expiry Date")))
				{	
					WebElement Year = driver.findElementByXPath("//android.widget.NumberPicker[3]//android.widget.EditText");
					WebElement Yearminus = driver.findElementByXPath("//android.widget.NumberPicker[3]//android.widget.Button");
					int currentselection = Integer.parseInt(Year.getText());
					int Selecttbd = currentselection+1;
					while(!Year.getText().equalsIgnoreCase(Integer.toString(Selecttbd))){
							Point Currentyear = Year.getLocation();
							Point btnbelow = Yearminus.getLocation();
							AndroidDriver2.swipe(btnbelow.getX(), btnbelow.getY(), Currentyear.getX(),Currentyear.getY()-20, 1000);
						}
				}
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Scroll",
						"<b>" + locatorname + "</b>" + " was " + "<b>" + "NOT" + "</b>" + " select");
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("Scroll", "Scroll to " + "<b>" + locatorname + "</b>" + "and selected");
			}
		}
		return flag;
	}
	
	public boolean inputExtras(String charity) throws Throwable{
		//click(BookingPageLocators.continueBtn, "Continue");
		
				
		if (Integer.parseInt(charity)>1){
			
			String chariity = getAttribute(BookingPageLocators.charitydisable, "disabled","Charity");
			if(chariity.equalsIgnoreCase("true"))
			{
				System.out.println("Charity is disabled");
				click(BookingPageLocators.continuebtn, "Continue");

			}
			else
			{
			
			click(BookingPageLocators.selectCharity,"Charity");
			Thread.sleep(2000);
			click(By.xpath("//div[contains(text(),'"+charity+"')]"), "Charity Amount");
			Thread.sleep(3000);
			click(BookingPageLocators.continuebtn, "Continue");
			}
		}
		else
		{
			click(BookingPageLocators.continuebtn, "Continue");
		}
		return true;
	}

	public boolean selectallSeats(String seatSelect,String totalpass,String tripType) throws Throwable {
		waitforElement(BookingPageLocators.seatSelecttionTittle);
		if(isElementDisplayedTemp(BookingPageLocators.seatSelecttionTittle)==true){
			waitforElement(BookingPageLocators.seatplusbutton);
			List<WebElement> Seats = driver.findElements(BookingPageLocators.seatplusbutton);
			if(Seats.size()==0){
				click(BookingPageLocators.continuebtn, "Continue");
			}else{
				swipeAndroid(0.45f);
				List<WebElement> ele = driver.findElements(BookingPageLocators.seatplusbutton); 
				if(ele.size()==0){
					click(BookingPageLocators.continuebtn, "Continue");
				}				
				if(seatSelect.equalsIgnoreCase("Extra Leg Room")){								
					for(int i=0;i<ele.size();i++) {
					 	ele.get(i).click();
					 	driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
					 	List<WebElement> seats = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/seat_row_list']//android.widget.LinearLayout[@index='1']//android.widget.ImageView[@index='0']"));
					 	seats.get(i).click();
							if(i!=ele.size()-1){
								click(BookingPageLocators.seatSelectLeftButton, "Left Button To Go back");
							}
					}
				}else if(seatSelect.equalsIgnoreCase("Premium"))
					for(int i=0;i<ele.size();i++) {
						ele.get(i).click();
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
							if(i>=6){
								List<WebElement> seats2 = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/seat_row_list']//android.widget.LinearLayout[@index='4']//android.widget.ImageView[@index='0']"));
								seats2.get(i-6).click();
							}else{
								List<WebElement> seats = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/seat_row_list']//android.widget.LinearLayout[@index='3']//android.widget.ImageView[@index='0']"));
								seats.get(i).click();
							}
							if(i!=ele.size()-1){
								click(BookingPageLocators.seatSelectLeftButton, "Left Button To Go back");
							}
			}else if(seatSelect.equalsIgnoreCase("Upfront"))
					click(BookingPageLocators.selUpfrontSeat,"Upfront");
			else if(seatSelect.equalsIgnoreCase("Extra Leg Room2"))
				click(BookingPageLocators.selExLeg2Seat,"Extra Leg Room2");
			else if(seatSelect.equalsIgnoreCase("Standard"))
				click(BookingPageLocators.selStdSeat,"Standard");
			else if(seatSelect.equalsIgnoreCase("Business")){
				for(int i=0;i<ele.size();i++) {
					ele.get(i).click();
					driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
					List<WebElement> BusinesSeats = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/seat_row_list']//android.widget.LinearLayout[@index='1']//android.widget.ImageView[@index='0']"));
					BusinesSeats.get(i).click();
					if(i!=ele.size()-1){
						click(BookingPageLocators.seatSelectLeftButton, "Left Button To Go back");
					}
					
					}
			}
		click(BookingPageLocators.continuebtn, "Continue");
		}
		}else{
			System.out.println("No Seats Available");
		}
		return true;
	}
		
	public boolean selectSeat(String seatSelect, String bookingtype,String triptype) throws Throwable {
		System.out.println("Seat tyoe "+seatSelect);
		driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
		if(isElementDisplayed(BookingPageLocators.seatSelecttionTittle)==true){
			List<WebElement> Seatplsbtn = driver.findElements(BookingPageLocators.seatplusbutton);
			if(Seatplsbtn.size()==0 || bookingtype.equalsIgnoreCase("CodeShare") || bookingtype.equalsIgnoreCase("PartcodeShare")){
				click(BookingPageLocators.continuebtn, "Continue");
			}else{
				waitForElementPresent(BookingPageLocators.seatplusbutton, "Seat Adding Button");
				click(BookingPageLocators.seatplusbutton, "Seat Adding Button");
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
				boolean selectseat = (isElementDisplayed(BookingPageLocators.elmWithText("Spacious")) || isElementDisplayed(BookingPageLocators.elmWithText("Business")));
				while(selectseat==true){
					scrollToElement(BookingPageLocators.elmWithText(seatSelect));
					List<WebElement> Seats = driver.findElements(BookingPageLocators.seatsofType(seatSelect));
					int seatno = (int) (Math.random() * (Seats.size()-1));
					System.out.println("Selecting seat "+seatno);
					Seats.get(seatno).click();
					driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
					selectseat = (isElementDisplayed(BookingPageLocators.elmWithText("Spacious")) || isElementDisplayed(BookingPageLocators.elmWithText("Business")));
					}
			}
			click(BookingPageLocators.continuebtn, "Continue");
		}
		else
		{
			System.out.println("No Seats Available");
		}
		return true;
		
}
	
	
	public boolean payment(String paymentType,String pnr) throws Throwable {		
		waitforElement(BookingPageLocators.Payment_title);
		System.out.println(paymentType);
		scrollToElement(BookingPageLocators.continuebtn);
			
		if(paymentType.equalsIgnoreCase("Credit Card")){
			type(BookingPageLocators.cardNumber,configProps.getProperty("cardNumber"),"Card Number");
			type(BookingPageLocators.cardName,configProps.getProperty("cardHolderName"),"Card Holder Name");
		
			click(BookingPageLocators.expdate,"Expiry Date");
			//Thread.sleep(2000);
			scrollKeysForAndroid(new String[] {configProps.getProperty("expMM"),configProps.getProperty("expYYYY") }, "Credit Card Expiry Date","");
			click(BookingPageLocators.Select_CC_Expdate, "Select");
			
			type(BookingPageLocators.cvvNum,configProps.getProperty("cvv"),"cvv");
		} 
		else if(paymentType.equalsIgnoreCase("Voucher")){
			click(BookingPageLocators.tabVoucher,"Sadad");
			type(BookingPageLocators.voucherNum,configProps.getProperty("voucher"),"Voucher");
			click(BookingPageLocators.retrieveVoucher, "Retrieve Voucher");
		} 
		else if(paymentType.equalsIgnoreCase("SADAD")){
			click(BookingPageLocators.tabSadad,"Sadad");
		//	type(BookingPageLocators.sadadOP,configProps.getProperty("sadadOLPID"),"SADAD OLP ID");
		//	click(BookingPageLocators.terms, "Terms And Conditions");
			scrollToElement(BookingPageLocators.continuebtn);
			click(BookingPageLocators.continuebtn, "Purchase");
		}
		else if(paymentType.equalsIgnoreCase("Nas"))
			{
				click(BookingPageLocators.tabNasCredit, "NAS CREDIT");
				String totat = getText(BookingPageLocators.totalSAR, "Total");
				String[] totalsar = totat.split("SAR");
				
				type(BookingPageLocators.creditShellAmount, totalsar[1], "Amount");

			}

		waitforElement(BookingPageLocators.continuebtn);
		scrollToElement(BookingPageLocators.continuebtn);
		click(BookingPageLocators.continuebtn, "Purchase");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		//Clicking continue on 3D secure page in case of credit card payment
		if(paymentType.equalsIgnoreCase("Credit Card")){
				waitforElement(BookingPageLocators.Submit);
						if(isElementDisplayedTemp(BookingPageLocators.Submit)==true){
							click(BookingPageLocators.Submit, "Submit in Securepage");
						}else{
								System.out.println("No secure Page");
						}
			}else{
				System.out.println("Payment type is not CreditCard");
			}
			
		return true;
	}
	
	public boolean nasmilespayment(String Username,String Password) throws Throwable {
		boolean flag =false;
		waitforElement(BookingPageLocators.Payment_title);
		System.out.println(paymentType);
		scrollToElement(BookingPageLocators.continuebtn);
		click(BookingPageLocators.tabNaSmiles,"Sadad");
		
		type(BookingPageLocators.naSmileId,Username, "naSmileID");
		type(BookingPageLocators.naSmilepwd,Password, "naSmilePwd");
		click(BookingPageLocators.naSmileslogin, "Log-in");
		Thread.sleep(2000);
		if(isElementDisplayedTemp(BookingPageLocators.redeem)==false)
		{
			System.out.println("NO Sufficient points");
			flag= false;
		}
		else
		{
		click(BookingPageLocators.redeem, "Redeem");
		Thread.sleep(2000);
		payment("Credit Card", "");			
		flag = true;
		}
		return flag;
	}
	
	
	public String getReferenceNumber() throws Throwable{
		waitForElementPresent(BookingPageLocators.summaryRefNumber, "Reference Number");
		return getText(BookingPageLocators.summaryRefNumber, "Reference Number");
	//	ExcelReader xls = new ExcelReader(configProps.getProperty("OutputPnrs"),"Android");
	//	String pnr = xls.getCellData("Android", "PNR",xls.getRowCount("PNR"));
	//	return pnr;
	}
	
	
	public void Baggage(String bookingtype,String totalpass) throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
		if(isElementDisplayedTemp(BookingPageLocators.baggagetittle))
		{
			driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
			List<WebElement> passenger = driver.findElements(BookingPageLocators.psngrBaggageExpntnBtn);
			System.out.println(passenger.size());
			//for(int i=0;i<passenger.size();i++)
				//{
					passenger.get(0).click();
					List<WebElement> baggages = driver.findElements(BookingPageLocators.addbaggagebtn);
					baggages.get(0).click();
					//Capturing the expansion button count after expansion
					//swipeAndroid(0.40f);
					//passenger = driver.findElements(BookingPageLocators.psngrBaggageExpntnBtn);
					//passenger.get(i).click();
					//Capturing the expansion button count after closing the currently open section
					//passenger = driver.findElements(BookingPageLocators.psngrBaggageExpntnBtn);
				//}
			}
		else
		{
			System.out.println("No Baggage available");
		}
		
	}	
	
	public void validateCheckin() throws Throwable
	{	
		waitforElement(BookingPageLocators.checkinConformation);
		if(isElementPresent(BookingPageLocators.checkinConformation,"Check-in Conformation"))
			{
			Reporter.SuccessReport("Validating check in", "Checkin is Done");
			//waitforElement(BookingPageLocators.continuebtn);
			}
		else{
			Reporter.failureReport("Validating check in", "Check in is Not Done");
			}
		click(BookingPageLocators.continuebtn, "View My BoardingPasses");
		waitforElement(BookingPageLocators.checkinBarCode);
		if(isElementDisplayedTemp(BookingPageLocators.checkinBarCode)){
			Reporter.SuccessReport("Validating Boarding Passes", "Boarding Passes Are Available");
			}else{
				Reporter.failureReport("Validating Boarding Passes", "Boarding Passes Are not Available");
			}
	}
	
	public void validate_ticketStatus() throws Throwable
	{
		waitforElement(BookingPageLocators.summaryRefNumber);
		System.out.println(getText(BookingPageLocators.summaryStatus,"Status"));
		if(getText(BookingPageLocators.summaryStatus,"Status").equalsIgnoreCase("Confirmed"))
		{		
			System.out.println("Ticket has booked");
			Reporter.SuccessReport("Ticket Confirmation", "Ticket has booked with PNR:" + getText(BookingPageLocators.summaryRefNumber, "PNR"));
			writingPNR("Android",getText(BookingPageLocators.summaryRefNumber, "PNR"));			
		}
		else if(getText(BookingPageLocators.summaryStatus,"Status").equalsIgnoreCase("Hold"))
			{
				Reporter.failureReport("Ticket Confirmation", "Ticket is in hold with PNR:" + getText(BookingPageLocators.summaryRefNumber, "PNR"));
				writingPNR("Android","Hold");	
			}
		else{
				Reporter.failureReport("Ticket Conformation", "Ticket has not Booked");
				writingPNR("Android","Fail");	
			}
	}
	
	public void verifyPNRforSadad() throws Throwable{
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS );
		String PNR = getText(BookingPageLocators.confirmation_PNR, "PNR For SADAD");
		 System.out.println(PNR);
		 String status = getText(BookingPageLocators.confirmation_Status, "Status");
		 System.out.println(status);
		 if(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Fail"))
		 {
			 Reporter.SuccessReport("Sadad Ticket Confiramation", "Ticket has  booked,PNR :"+PNR+" With Status "+status);
			
		 }
			 
		else
		{
			 Reporter.failureReport("Verifing PNR Status", "Ticket is not Booked");
		}	
	}
	
	public static String pickDate(String xlsDate){
		
		String[] depdate = xlsDate.split("\\^");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, Integer.parseInt(depdate[1])); // Adding 1 days
		String newDeptDate = sdf.format(c.getTime());
		System.out.println(newDeptDate);
		return newDeptDate;
		
	}
	
	public void selctClasswithCodeshare(String bookingtype,String bookingClass,String tripType) throws Throwable
	{
		int count=0;
		while(isElementPresent(BookingPageLocators.title)==false)	
		{
			List<WebElement> RightArrow = driver.findElements(BookingPageLocators.rightarrow);
			List<WebElement> ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
			int j=0;
			while(ClassArrow.size()==0 && j<7){
				System.out.println("No Flights");
				RightArrow.get(0).click();
				Thread.sleep(5000);//Wait for next date flights to load. Do not remove this wait.
				ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
				j++;
			}
		
			for(int i=0;i<ClassArrow.size();i++)
				{
					System.out.println("Total Flights : "+ClassArrow.size());
					
					count=0;
					if(isElementPresent(BookingPageLocators.bookingclass(bookingClass))==true)
					{
						String FlightNum = getText(BookingPageLocators.flightNumber, "Flight");
						System.out.println(FlightNum);
						for(int k=0;k<FlightNum.length();k++)
						{
							char result = FlightNum.charAt(k);
						 	if(Character.isDigit(result)){
					 		count++;}
						}
						
						if((count==4||count==8) && bookingtype.equalsIgnoreCase("CodeShare"))
							{
								click(BookingPageLocators.bookingclass(bookingClass), bookingClass);
								Thread.sleep(2000);
								break;
							}
						else if((4 < count && count < 8)  && bookingtype.equalsIgnoreCase("PartcodeShare"))
								{
								click(BookingPageLocators.bookingclass(bookingClass), bookingClass);
								Thread.sleep(2000);
								break;							
								}
						else{	
								ClassArrow.get(i+1).click();
								}
							
					}
					else
						{
							System.out.println(bookingClass+" class not available in flight no "+i );
							ClassArrow.get(i+1).click();
						}
				}	
			
		}
			
	}
	
	public void Select_A_Meal() throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
		if(isElementDisplayedTemp(BookingPageLocators.mealTitle)){
		swipeAndroid(0.30f);
		List<WebElement>  MealExpButtons = driver.findElements(BookingPageLocators.mealExpansionButton);
		List<WebElement> MealsNames = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/mealName']"));
		for(int i=0;i<MealExpButtons.size();i++)
		{
			if(!MealsNames.get(i).getText().equalsIgnoreCase("No Meal")){
				MealExpButtons.get(i).click();
			}
			MealExpButtons.get(i).click();
			List<WebElement> AvailableMeal = driver.findElements(BookingPageLocators.availableMeal);
			AvailableMeal.get(i).click();
			Thread.sleep(1000);
		//	MealExpButtons.get(i).click();
			swipeAndroid(0.50f);
			Thread.sleep(5000);
			MealExpButtons = driver.findElements(BookingPageLocators.mealExpansionButton);
			MealsNames = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/mealName']"));
		}
	}else{
		System.out.println("Meal Not Available");
	}
	}
	public void Select_Meal() throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
		if(isElementPresent(BookingPageLocators.mealTitle)){
			List<WebElement>  MealExpButtons = driver.findElements(BookingPageLocators.mealExpansionButton);
			List<WebElement> MealsNames = driver.findElements(By.xpath("//*[@resource-id='com.flynas.android.app:id/mealName']"));
			for(int i=0;i<=MealExpButtons.size();i++)
			{
				if(!MealsNames.get(0).getText().equalsIgnoreCase("No Meal")){
				MealExpButtons.get(0).click();
			}
			
			MealExpButtons.get(0).click();
			List<WebElement> AvailableMeal = driver.findElements(BookingPageLocators.availableMeal);
			AvailableMeal.get(0).click();
		}
		click(BookingPageLocators.continuebtn, "Continue");		
		
	}else{
		System.out.println("No meal Available");
	}}
	
	public void Select_lounge() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.Loung)==true)
		{
		List<WebElement> Loung = driver.findElements(BookingPageLocators.Loung);
		
			for(int i=1 ;i<=Loung.size()-2;i++)
		{
			if(Loung.get(i).findElement(By.tagName("input")).getAttribute("value")=="true")
			{
				Loung.get(i).click();
				break;
			}
			else
			{
				Loung.get(i+1).click();
				break;
			}
		}
		}
		
		else
		{
			System.out.println("No Loung");
		}
		//click(BookingPageLocators.continueBtn, "Continue");
	}
	
	public void Baggage_Extra()
	{
		List<WebElement>  passengers = driver.findElements(BookingPageLocators.Adult_baggagae);
		for(int i=0;i<passengers.size();i++)
		{
			String value=passengers.get(i).getText();
			if(value.equalsIgnoreCase("Adult 1"))
			{
				passengers.get(i).click();
				ImplicitWait();
				List<WebElement>  Baggage_weight = driver.findElements(BookingPageLocators.Baggage_weight);
				for(int j=1;j<=Baggage_weight.size();j++)
				{
					Baggage_weight.get(j).click();
					ImplicitWait();
					break;

				}
				break;

			}
			else
				if(value.equalsIgnoreCase("Child 1"))
				{
					passengers.get(i).click();
					ImplicitWait();
					List<WebElement>  Baggage_weight = driver.findElements(BookingPageLocators.Baggage_weight);
					for(int j=1;j<=Baggage_weight.size();j++)
					{
						Baggage_weight.get(j).click();
						ImplicitWait();
						break;
					}
				}
		}

	}
	
	public void Handlepopup() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.Deny)==true)
		{
			click(BookingPageLocators.Deny, "DENY");
		}
	}
	
	public void writingPNR(String sheetname,String value)
	{
		ExcelReader xls = new ExcelReader(configProps.getProperty("OutputPnrs"),sheetname);
		int rownum = xls.getRowCount(sheetname);
		if(xls.getCellData(sheetname, "PNR", rownum)==null){
			xls.setCellData(sheetname, "PNR", rownum, value);
			xls.setCellData(sheetname, "TestCaseNum", rownum,Integer.toString(rownum));
			
		}else{
			xls.setCellData(sheetname, "PNR", rownum+1, value);
			xls.setCellData(sheetname, "TestCaseNum", rownum+1,testName);
		}
	}
	
	public static void writecredentials(String userID,String password,String firstname,String lastname, String doctype, String docnumber )
	{
		ExcelReader xls = new ExcelReader(configProps.getProperty("Credentialsdata"),"UserCredentials");
		int rownum = xls.getRowCount("UserCredentials");
		if(xls.getCellData("UserCredentials", "userid", rownum)==null){
			xls.setCellData("UserCredentials", "key", rownum, "credentials"+(rownum-1));
			xls.setCellData("UserCredentials", "userid", rownum, userID);
			xls.setCellData("UserCredentials", "password", rownum, password);
			xls.setCellData("UserCredentials", "firstname", rownum, firstname);
			xls.setCellData("UserCredentials", "lastname", rownum, lastname);
			xls.setCellData("UserCredentials", "Document Type", rownum, doctype);
			xls.setCellData("UserCredentials", "Document Number", rownum, docnumber);
		}else{
			xls.setCellData("UserCredentials", "key", rownum+1, "credentials"+(rownum));
			xls.setCellData("UserCredentials", "userid", rownum+1, userID);
			xls.setCellData("UserCredentials", "password", rownum+1, password);
			xls.setCellData("UserCredentials", "firstname", rownum+1, firstname);
			xls.setCellData("UserCredentials", "lastname", rownum+1, lastname);
			xls.setCellData("UserCredentials", "Document Type", rownum+1, doctype);
			xls.setCellData("UserCredentials", "Document Number", rownum+1, docnumber);
		}
	}
	
	public void verifingEcofarePrice(String bookingClass) throws Throwable{
		Thread.sleep(3000);
		List<WebElement> ClassArrow = driver.findElements(BookingPageLocators.selectFlightUpDownArrow);
		
		for(int k=0;k<ClassArrow.size();k++){
			
			if(bookingClass.contains("Business")){
				if(isElementDisplayedTemp(BookingPageLocators.busOW)==true){
					String fare= getText(BookingPageLocators.bussPrice, "Business Price");
					click(BookingPageLocators.busOW, "Business");
					waitforElement(BookingPageLocators.cartSummaryBalance);
					String summaryfare= getText(BookingPageLocators.cartSummaryBalance, "Cart Summary Balance");
					String totalfare = summaryfare.split("\\s+")[1];
					if(fare.equals(totalfare)){
						Reporter.SuccessReport("Verifing Business Fare", "Successfully Verified");
					}else{
						Reporter.failureReport("Verifing Business Fare", "Fares Are Not same");
					}
					click(BookingPageLocators.tittleBack, "Tittle Back Button");
					
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			if(bookingClass.contains("Economy")){
				if(isElementDisplayedTemp(BookingPageLocators.economyOW)==true){
					String fare= getText(BookingPageLocators.econnmyPrice, "Economy Price");
					click(BookingPageLocators.economyOW, "Economy");
					waitforElement(BookingPageLocators.cartSummaryBalance);
					String summaryfare= getText(BookingPageLocators.cartSummaryBalance, "Cart Summary Balance");
					String totalfare = summaryfare.split("\\s+")[1];
					if(fare.equals(totalfare)){
						Reporter.SuccessReport("Verifing Economy Fare", "Successfully Verified");
					}else{
						Reporter.failureReport("Verifing Economy Fare", "Fares Are Not same");
					}
					click(BookingPageLocators.tittleBack, "Tittle Back Button");
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			if(bookingClass.contains("Extra")){
				if(isElementDisplayedTemp(BookingPageLocators.flexOW)==true){
					String fare= getText(BookingPageLocators.flexPrice, "Flex Price");
					click(BookingPageLocators.flexOW, "Extra");
					waitforElement(BookingPageLocators.cartSummaryBalance);
					String summaryfare= getText(BookingPageLocators.cartSummaryBalance, "Cart Summary Balance");
					String totalfare = summaryfare.split("\\s+")[1];
					if(fare.equals(totalfare)){
						Reporter.SuccessReport("Verifing Flex Fare", "Successfully Verified");
					}else{
						Reporter.failureReport("Verifing Flex Fare", "Fares Are Not same");
					}
					click(BookingPageLocators.tittleBack, "Tittle Back Button");
					break;
				}else{
					ClassArrow.get(k+1).click();
				}
			}
			}
	}
	
	//***************Homepage actions
	
	public void handleRatingRequest() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.loveFlynasApp)==true)
		{
			click(BookingPageLocators.noThanks, "No Thanks");
		}
		else
		{
			System.out.println("No Rating Request");
		}
	}
	
	//***************ManageBookingPage**************
	
	public void searchFlightMMB(String referenceNum, String Lastname) throws Throwable{
		// add validation
		//driver.get(configProps.getProperty("URL_Search"));
		waitForElementPresent(BookingPageLocators.bookingReference, "Booking Reference");
		type(BookingPageLocators.bookingReference, referenceNum, "Reference Number");
		//type(BookingPageLocators.email_mb, email, "Email");
		//Thread.sleep(5000);
		//type(BookingPageLocators.sfpMoblie, mobile, "Mobile");
		type(BookingPageLocators.lastName_mb, Lastname, "Last Name");
		click(BookingPageLocators.findBooking_btn,"Find Booking");	
		
	}
	

	public void searchFlightCheckin(String referenceNum, String Lastname) throws Throwable{
		waitForElementPresent(BookingPageLocators.checkInReference, "CheckIn Reference");
		type(BookingPageLocators.checkInReference, referenceNum, "Reference Number");
		type(BookingPageLocators.lastname_incheckin, Lastname, "Lastname");
		click(BookingPageLocators.checkin_btn, "Check-in");	
	}
	
	
	public String changeDate(String referenceNum, String email, String mobile, String lastName, String newDate, 
			String selectSeat,String totalpassString ,String bookingclass,String tripType) throws Throwable{
		Thread.sleep(10000);//pageload is taking time don't remove this thread
		waitForElementPresent(BookingPageLocators.btnchngFlight, "Change Flight");
		click(BookingPageLocators.btnchngFlight, "Change Flight");
		waitForElementPresent(BookingPageLocators.flightToChange, "Select Flight");
		click(BookingPageLocators.flightToChange, "Select Flight");
		click(BookingPageLocators.continuebtn, "Continue");
		select_date(newDate);
		click(BookingPageLocators.selectDateButton_mb, "Select");
		selectClass(bookingclass, tripType);
		Thread.sleep(3000);
		String newDateto=driver.findElement(By.xpath("//*[@resource-id='com.flynas.android.app:id/extraFlightDate']")).getText();
		String Date[] = newDateto.split(",");
		String Datesplit[] = Date[1].split(" ");
		String newdate[] = newDate.split("-");
		if(Datesplit[1].contains(newdate[0]))
		{
			scrollToElement(BookingPageLocators.cnfmChanges);
			click(BookingPageLocators.cnfmChanges, "Conform Changes");
			if(isElementDisplayedTemp(BookingPageLocators.Payment_title)==true)
			{
				payment("Credit Card", "");
			}
			Reporter.SuccessReport("Verifing Change Date", "Successfully Changed");
		}
		else
		{
			Reporter.failureReport("Verifing Change Date", "Failed To Change Date");
		}
		return getReferenceNumber();
	}	
	

	public void performCheckin() throws Throwable
	{
		waitForElementPresent(BookingPageLocators.checkInFlight, "Flight To checkIn");
		click(BookingPageLocators.checkInFlight, "Flight CheckIn");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		//swipeAndroid(0.50f);
		List<WebElement> passengers_incheckin = driver.findElements(BookingPageLocators.passengers_incheckin);
		for(int  i=0;i<passengers_incheckin.size();i++)
		{
			passengers_incheckin.get(i).click();
		}
		scrollToElement(BookingPageLocators.terms);
		//click(BookingPageLocators.terms, "Terms And Conditions");
		click(BookingPageLocators.continuebtn, "Continue");
		
	}
	
	//*************************************************************************************
	public void navigatetoHmPg() throws Throwable{
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		click(BookingPageLocators.tittleHome, "Home Img");
		}	
	
	public void continueOnPsngrDtls() throws Throwable{
		waitforElement(BookingPageLocators.title);
		if(isElementDisplayedTemp(BookingPageLocators.continuebtn)==false)
		{
			scrollToElement(BookingPageLocators.continuebtn);
		}
		click(BookingPageLocators.continuebtn, "Continue");
	}
	
	public void continueOnExtras() throws Throwable{
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		if(isElementDisplayed(BookingPageLocators.baggagetittle)){
		click(BookingPageLocators.continuebtn, "Continue");
		}else{
			System.out.println("No Baggage is Available");				
		}
	}
	
	public void continueOnSeatSelection() throws Throwable{
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		if(isElementDisplayed(BookingPageLocators.seatSelecttionTittle)==true)
		{
			click(BookingPageLocators.continuebtn, "Continue");
		}
		else
		{
			System.out.println("No Seat Page");
		}
	}
	
	public void cntinueOnTravelDocument() throws Throwable
		{
		waitForElementPresent(BookingPageLocators.travelDocuments, "Travel Documents");
		click(BookingPageLocators.continuebtn, "Continue");
		}
	
	public void cntinueRandomSeatSelection() throws Throwable
		{
		if(isElementDisplayedTemp(BookingPageLocators.seatSelecttionTittle)){
			waitForElementPresent(BookingPageLocators.seatSelecttionTittle, "Seat Selection");
			click(BookingPageLocators.continuebtn, "Continue");
		}
		}
	
	public void confirmRandomSeatSelection() throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		if(isElementDisplayedTemp(BookingPageLocators.ok)){
			click(BookingPageLocators.ok, "OK");}
	}
	
	public void verifyPNRinMMBList(String PNRnumber, boolean expected) throws Throwable{
		if(isElementPresent(BookingPageLocators.MMB(PNRnumber))==expected){
			Reporter.SuccessReport("Verifying PNR in chekin List", "Pnr");
		}else{
			Reporter.failureReport("Verifying PNR in chekin List", "Pnr");
		}
	}
	
	public  void registeredUsrManageFlight(String PNRnumber) throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		scroll(PNRnumber);
		click(BookingPageLocators.manage(PNRnumber), "Manage");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	
	public void verifyPNRinCheckinList(String PNRnumber, boolean expected) throws Throwable{
		if(isElementPresent(BookingPageLocators.checkin(PNRnumber))==expected){
			Reporter.SuccessReport("Verifying PNR in chekin List", "Pnr");
		}else{
			Reporter.failureReport("Verifying PNR in chekin List", "Pnr");
		}
	}
	
	public  void registeredUsrCheckin(String PNRnumber) throws Throwable
	{
		
		waitForElementPresent(BookingPageLocators.checkin_title, "Check-in title");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		if(isElementPresent(BookingPageLocators.checkin(PNRnumber))==true){
			click(BookingPageLocators.checkin(PNRnumber), "Checkin");
		}else{
			Reporter.failureReport("Click Check in", "PNR visibility");
		}
		
	}
	
	
	public void clickExtrasbtn(){
		
	}
	
	public void seatslctnBtn(){
		
	}
	
	public  void cancelFlights(String Flightways) throws Throwable
	{ try{
		waitForElementPresent(BookingPageLocators.cancelflightBtn, "Cancel Flight");		
		click(BookingPageLocators.cancelflightBtn, "Cancel Flight");
		waitForElementPresent(BookingPageLocators.flightToChange, "Select Flight");
		List<WebElement> Flightstocancel = driver.findElements(BookingPageLocators.flightToChange);
		if (Flightways.equalsIgnoreCase("All"))
			for(int i=0;i<Flightstocancel.size();i++){
				Flightstocancel.get(i).click();
			}
		else if (Flightways.equalsIgnoreCase("Departing"))
			Flightstocancel.get(0).click();
		else if (Flightways.equalsIgnoreCase("Returning"))
			Flightstocancel.get(1).click();
		//click(BookingPageLocators.flightToChange, "Select Flight");
		
		click(BookingPageLocators.continuebtn, "Continue");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		click(BookingPageLocators.ok, "ok");	
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		Reporter.SuccessReport("Cancelling flight", "Flight cancelled");
		}catch (Exception e){
		Reporter.failureReport("Cancelling Flight", "Flight cancellation failed");
		}
	}
	
	public  void confirmChanges() throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		scrollToElement(BookingPageLocators.cnfmChanges);
		click(BookingPageLocators.cnfmChanges, "Confirm");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		if(isElementPresent(BookingPageLocators.ok)==true)
			{
			click(BookingPageLocators.ok, "Creditsheel popup");
			Reporter.SuccessReport("Credit shell pop-up handled", "Crdit shell repayment popup ok button");
			}		
	}
	
	public void verifyConfirmchanges() throws Throwable
	{
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		if(isElementPresent(BookingPageLocators.summaryStatus)==true)
		{
			Reporter.SuccessReport("Verifing status after clicking confirm changes", "Status: Confirmed");
		}
		else
		{
			Reporter.failureReport("Verifing staus after clicking confirm changes", "Staus : not confirmed");
		}
	}
	
	public void verifyConfirmcharges() throws Throwable
	{
		if(isElementPresent(BookingPageLocators.conformedAftercharges)==true)
		{
			Reporter.SuccessReport("Verifing Confirmed after clicking conform charges", "Successfully Verified");
		}
		else
		{
			Reporter.failureReport("Verifing Confirmed after clicking conform charges", "fail to verify");
		}
	}
	
	//***************Utilities
	
	public String[] pickCredentials(String Sheetname){
		ExcelReader xls = new ExcelReader(configProps.getProperty("Credentialsdata"),Sheetname);
		int count = xls.getRowCount(Sheetname);
		int index = ((int)(Math.random() * count-1)) + 1;;
		String[] credentials =new String[5];
		credentials[0] = xls.getCellValue("credentials"+index, "userid");
		credentials[1] = xls.getCellValue("credentials"+index, "password");
		credentials[2] = xls.getCellValue("credentials"+index, "firstname");
		credentials[3] = xls.getCellValue("credentials"+index, "lastname");
		credentials[4] = xls.getCellValue("credentials"+index, "nasmiles");
		return credentials;
	} 
	
	public void verifyAnonymousCheckinFail() throws Throwable{
		if(isElementPresent(BookingPageLocators.checkInFlight)==false){
			Reporter.SuccessReport("Verifing anonymous checkin failure", "Failure message");
		}else{
			Reporter.failureReport("Verifing anonymous checkin failure", "Failure message");
		}
	}
	
}

