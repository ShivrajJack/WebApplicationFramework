package com.Unilog.scripts;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Unilog.drivers.Maindriver;
import com.Unilog.libraries.GenericFunctions;
import com.Unilog.libraries.ResultSummary;
import com.Unilog.libraries.Setup;
import com.Unilog.libraries.TestBase;
import com.Unilog.pages.Loginpage;
import com.google.common.base.Verify;

import com.Unilog.libraries.ExcelUtils;

public class TC001_Unilog_LoginLogout extends TestBase {
	ResultSummary rs = new ResultSummary();
	String scriptname = getClass().getSimpleName();
	//GenericFunctions asser = new GenericFunctions();
 	int scriptcnt = 0;
	 
 	@Test(dataProvider="Logindata")
	
	public void LoginlogOutTset(String username, String password) throws Exception{
 		Maindriver.startTime = System.currentTimeMillis();
		rs.createLogFile(scriptname); 
//		, String name, String surname
		WebElement EmailTextField=new Loginpage().getLoginpage().getEmailTextbox();
		System.out.println("username "+username);
		EmailTextField.sendKeys(username);
		//asser.typeText(EmailTextField, scriptname,"Enter Username in EmailTextField", username);
		WebElement PasswordTextField =new Loginpage().getLoginpage().getPasswordTextbox();
		PasswordTextField.sendKeys(password);
		System.out.println("password "+password);
//		System.out.println("name "+name);
//		System.out.println("name "+surname);
		//asser.typeText(PasswordTextField, scriptname, "Enter password PasswordTextField", password);
		//Thread.sleep(3000);
		WebElement registerButton =new Loginpage().getLoginpage().getRegisterButton();
		//asser.click(registerButton, scriptname, "Clicking on Login button "); 
		registerButton.click();
		Thread.sleep(2000);
		rs.writePassLog(scriptname, "Entered usn is: "+ username+ "Entered psw is :"+password,  "");
		String title =Setup.driver.getTitle();
		System.out.println("title "+title);
		if(title.equalsIgnoreCase("Login | Unilog Customer Community")){
		rs.writePassLog(scriptname, "The title of the Product is matched", "");
		}else{ 
			rs.writeFailLog(scriptname, "The title of the Product is not matched", "");
			rs.writeFailLog(scriptname, "Entered usn is: "+ username+ "Entered psw is :"+password,  "");
		}
		//EmailTextField.clear();
		//PasswordTextField.clear();
		
 	
 	}

 	
 	@DataProvider 

 	public Object[][] Authentication(Method m) throws Exception{
 		if(m.getName().equalsIgnoreCase("LoginlogOutTset")){
 			Object[][] testObjArray = ExcelUtils.getTableArray("E:\\unilogworkspace\\Unilog\\Unilog.xlsx.xlsx","Logindata");

 			return (testObjArray);
 		}

 		else{

 			Object[][] testObjArray = ExcelUtils.getTableArray("E:\\unilogworkspace\\Unilog\\Unilog.xlsx.xlsx","Logindata");

 			return (testObjArray);
 		}
 	 	    /* return new Object[][] { 
					{  "Canada", "Canada","Canada","Canada" }, 
					{  "Russia","Canada","Canada","Canada" }, 
					{  "Japan","Canada","Canada","Canada" } 
				};*/}


 	/*@DataProvider(name= "Logindata")
 	
 	public Object[][] getData(){
		return new Object[][]{
			{"sivubj", "gurukulaa"},
			{"sivubj1", "gurukulaa1"},
			{"sivubj2", "gurukulaa2"},
		};*/
 	 

 	
 }