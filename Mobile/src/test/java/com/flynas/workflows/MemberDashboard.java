package com.flynas.workflows;

import java.util.concurrent.TimeUnit;

import com.flynas.testObjects.BookingPageLocators;

public class MemberDashboard<RenderedWebElement> extends BookingPageLocators {
	
	public void waitforpageload() throws InterruptedException{	
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		}
	
	

}
