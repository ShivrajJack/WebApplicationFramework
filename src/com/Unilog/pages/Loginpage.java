package com.Unilog.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Unilog.libraries.ExcellLibraries;
import com.Unilog.libraries.GenericFunctions;
import com.Unilog.libraries.Setup;

public class Loginpage {
	ExcellLibraries lib = new ExcellLibraries(); 
	GenericFunctions asser = new GenericFunctions();
	/*String email = lib.getExcelData("TC001_P5_LoginLogout", 0, 1);
	String password = lib.getExcelData("TC001_P5_LoginLogout", 1, 1);*/
	
	public Loginpage getLoginpage() 
	{
		return PageFactory.initElements(Setup.driver, Loginpage.class);
		
	}

	@FindBy(id="username")
	private WebElement EmailTextbox;
	
	 	public WebElement getEmailTextbox() {
		return EmailTextbox;
	}


	public WebElement getPasswordTextbox() {
		return PasswordTextbox;
	}


	public WebElement getRegisterButton() {
		return LoginButton;
	}

	@FindBy(id="password")
	private WebElement PasswordTextbox;
	
	@FindBy(id="Login")
	private WebElement LoginButton;
	
	 	
	/*public void login(String scriptname) throws Exception{
		WebElement EmailTextField=new Loginpage().getLoginpage().getEmailTextbox();
		asser.typeText(EmailTextField, scriptname,"Enter Username in EmailTextField", email);
		WebElement PasswordTextField =new Loginpage().getLoginpage().getPasswordTextbox();
		asser.typeText(PasswordTextField, scriptname, "Enter password PasswordTextField", password);
		Thread.sleep(3000);
		WebElement registerButton =new Loginpage().getLoginpage().getRegisterButton();
		asser.click(registerButton, scriptname, "Clicking on Login button "); 
		 
		 
 	 }*/
	 

}
