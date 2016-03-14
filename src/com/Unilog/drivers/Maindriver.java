package com.Unilog.drivers;

import java.util.ArrayList;

import org.testng.TestNG;

import com.Unilog.libraries.Browser;
import com.Unilog.libraries.ResultSummary;
import com.Unilog.libraries.SendMailForMultipleReceipient;
import com.Unilog.libraries.ZipResults;

public class Maindriver     {

	public static int pass = 0;
	public static int fail = 0;
	public static int rowcount;
	public static String Exectime;
	public static long Extime;
	public static long endTime;
	public static long startTime;
	
	public static String Exectime1;
	public static long Extime1;
	public static long endTime1;
	public static long startTime1;
	public static void main(String[] args) throws Exception {
		
		 
		@SuppressWarnings("unused")
		String s2="";
	
		
		//for(int j=0;j<=2;j++){
		System.out.println("Framework Starts");
		Maindriver.startTime1 = System.currentTimeMillis();
		Browser br = new Browser();
		 		
		 //runScripts stores all the script names that has to be executed for this run
		String runScripts = br.testConfigFile.getProperty("runScripts");
		
		System.out.println(runScripts);
		
		ResultSummary rs = new ResultSummary();
		rs.createSummaryFile();
		Scripts s = new Scripts();
		ArrayList<String> scripexec = s.scr();
		TestNG testNGInstance = new TestNG();
		
		
		// using java reflections
		int n = scripexec.size();
		System.out.println("exec count " + n);
		@SuppressWarnings("rawtypes")
		Class[] testSuite = new Class[scripexec.size()];
		for (int i = 0; i < scripexec.size(); i++) {
			testSuite[i] = Class.forName("com.Unilog.scripts."+scripexec.get(i));
					

		}
		
		//using TestNGsuite
		testNGInstance.setTestClasses(testSuite);
		testNGInstance.run();
		
		//Zipping Results
		ZipResults z = new ZipResults();
		z.zipRes();
		
		SendMailForMultipleReceipient send = new SendMailForMultipleReceipient();
		send.sendMail("Shivaraj", SendMailForMultipleReceipient.Esplit,
				"Unilog Execution Report");
		System.out.println("framework end");
		
		
				
	}
	 

	}
	

		
		
		
		
		
		

