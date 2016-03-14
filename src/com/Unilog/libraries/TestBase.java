package com.Unilog.libraries;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//opening browser and connecting url for every scripts which are going to run


public class TestBase extends Setup {

	
	@BeforeMethod
	//@Parameters("browser")
	public void Start(){
		
		launchApplication();
		 
	}
	
	@AfterMethod
	public void quit(){
		quit1();
		
		
	}
}
