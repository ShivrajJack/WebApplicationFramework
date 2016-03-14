package com.Unilog.assertion;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.Unilog.libraries.ResultSummary;

public class Assertion {
	ResultSummary rs = new ResultSummary();
	SoftAssert sa = new SoftAssert();

	public void assertString(String actual, String expected, String msg) {
		sa.assertEquals(actual, expected, msg);
	}

	/*public void assertWebelement(WebElement element, String message) {
		boolean elementIsPresent = false;
		if (element.isDisplayed()) {
			elementIsPresent = true;

		}
		sa.assertEquals(elementIsPresent, true, message);
	}*/

	public void assertAll() {
		sa.assertAll();
	}

	public void verifyTitle(String actual, String expected, String scriptname)
			throws Exception {
		try {
			if (actual.equals(expected)) {
				
				rs.writePassLog(scriptname, "title matches", "TCID");

				//rs.writePassLog(scriptname, "title matches" tcid);

			} else {
				rs.writeFailLog(scriptname, "title doesnot matches", "TCID");
				//rs.writeFailLog(scriptname, "title doesnot matches");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void assertWebelement(String scriptname,WebElement element,String message) throws Exception {
	try{
		if (element.isDisplayed()) {
			
			
			rs.writePassLog(scriptname, message, "TCID");
			

		}else{
			
			rs.writeFailLog(scriptname,"no"+ message, "TCID");
			
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}
		
		
		
}
	
public void verifyText(String actual,String expected,String scriptname,String message) throws Exception{
	
	try{
		
	
	if(actual.contains(expected)){
		rs.writePassLog(scriptname, message, "TCID");
	}else{
		
		rs.writeFailLog(scriptname,"not"+message, "TCID");
	}
	
}
	catch(Exception e){
		e.printStackTrace();
		rs.writeFailLog(scriptname,"not"+message, "TCID");
	}

}}