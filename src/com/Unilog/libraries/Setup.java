package com.Unilog.libraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Setup {
	
	public static WebDriver driver;
	ExcellLibraries xllib;
	
	 
	
	public void launchApplication(){
	
		xllib = new ExcellLibraries();
		
		 String browser =xllib.getExcelData("Config", 1, 0);
	//	String browser ="chrome";
		 String url =xllib.getExcelData("Config", 1, 1);
	//	String url ="https://unilog.force.com/Community/login";
		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver","E:\\unilogworkspace\\Unilog\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")){
			driver = new FirefoxDriver();
	}
		else if(browser.equals("ie")){
			System.setProperty("webdriver.ie.driver","E:\\unilogworkspace\\Unilog\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	 
	public void quit1(){
		driver.quit();
	}

	}

