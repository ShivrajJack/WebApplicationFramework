package com.Unilog.libraries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Unilog.drivers.Maindriver;

public class ResultSummary {
	public static String pth = "";
	public static int failcounter;
	public static int slno = 1;
	public String Snapshotpath;
	public static int screencapcounter = 1;
	Browser br = new Browser();

	/**
	 * <p>
	 * <b> Result summary function name : </b>createSummaryFile
	 * </p>
	 * <p>
	 * <b>Description : </b>Creating a Result Summary html Log file
	 * </p>
	 * 
	 * @throws IOException
	 */
	public void createSummaryFile () throws IOException {
		Browser br = new Browser();
		try {

			// String sfile= "D:\Plumb5PageObject\\results";
			String sfile = br.testConfigFile.getProperty("resultPath");
			System.out.println("sfile " + sfile);
			Calendar c = new GregorianCalendar();
			String currtime = c.getTime().toString();
			//Browser br1 = new Browser();

			String Browser = br.testConfigFile.getProperty("browserType");
			String Url = br.testConfigFile.getProperty("testurl");
			String Os = br.testConfigFile.getProperty("OS");
			String BrowserVersion = br.testConfigFile
					.getProperty("BrowserVersion");

			BufferedWriter bw1 = new BufferedWriter(new FileWriter(sfile
					+ "/Summary.html"));
			bw1.write("<html>");
			bw1.write("<head>");
			bw1.write("<title>Result Summary</title>");
			bw1.write("</head><body>");

			bw1.write("<table style=font-family:arial border =1 cellspacing=1 frame=Vsides bgcolor=#CC9999 Align=Center>");
			bw1.write("<tr><th width=775>Unilog:Automated Test Script Execution Summary Report</th></tr>");
			bw1.write("</table>");

			bw1.write("<table style=font-family:calibri border =1 cellspacing=1 frame=Vsides bgcolor=6699FF Align=Center>");
			bw1.write("<tr><td  width=500 ><B>Execution Started Time</B></td><td width=270><B>"
					+ currtime + "</B></td></tr>");
			bw1.write("<tr><td  width=500 ><B>Environment </B></td><td width=270><B>"
					+ Url + "</B></td></tr>");
			bw1.write("<tr><td  width=500 ><B>Browser </B></td><td width=270><B>"
					+ Browser + "</B></td></tr>");
			bw1.write("<tr><td  width=500 ><B>Browser Version </B></td><td width=270><B>"
					+ BrowserVersion + "</B></td></tr>");
			bw1.write("<tr><td  width=500 ><B>Operating System </B></td><td width=270><B>"
					+ Os + "</B></td></tr>");
			bw1.write("<tr><td  width=500 ><B>Automation Tool</B></td><td width=270><B>Selenium</B></td></tr></table><br/>");
			bw1.write("<table style=font-family:calibri border =2 cellspacing=1 frame=Vsides bgcolor=#E8E8E8 Align=Center><tr><td Align=Center width=50><B>SL NO</B></td><td Align=center width=632><B>TestScript Name</B></td><td width=80 Align=center><B>Result</B></td><td width=80 Align=center><B> TC No.</B></td><td width=80 Align=center><B>Execution Time</B></td></tr>");
			bw1.flush();
			bw1.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	 
	/**
	 * <p>
	 * <b>Result summary function name :</b> writePassSummary
	 * </p>
	 * <p>
	 * <b>Description :</b> Writing Pass Message to the html log file
	 * </p>
	 * 
	 * @param scriptname
	 * @param status
	 * @throws IOException
	 */
	 public void writePassSummary(String scriptname, String status,
			String TCNo) throws IOException {
		  
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			System.out.println("rpath    " + rpath);
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath
					+ "/summary.html", true));
			bw1.write("<tr><td Align=center>" + slno + "</td><td><a href="
					+ scriptname + ".html>" + scriptname
					+ "</a></td><td bgcolor=#00FF00 Align=center>" + status
					+ "</td><td bgcolor=#00FF00 Align=center>" +TCNo
					+ "</td><td bgcolor=#00FF00 Align=center>" + Maindriver.Exectime
					+ "</td></tr>");
			slno++;
			 
			bw1.close();
		} catch (Exception e) {
			System.out.println("Write PassSummary error = " + e);
		}
	}

	//**
	/* * <p>
	 * <b>Result summary function name :</b> writeFailSummary
	 * </p>
	 * <p>
	 * <b>Description :</b> Writing Fail Message to the html log file
	 * </p>
	 * 
	 * @param scriptname
	 * @param status
	 * @throws IOException
	* /*/
	public void writeFailSummary(String scriptname, String status,
			String TCNo) throws IOException {
		 
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath
					+ "/summary.html", true));
			bw1.write("<tr><td Align=center>" + slno + "</td><td><a href="
					+ scriptname + ".html>" + scriptname
					+ "</a></td><td bgcolor=#FF0000 Align=center>" + status
					+ "</td><td bgcolor=#00FF00 Align=center>" +TCNo
					+ "</td><td bgcolor=#00FF00 Align=center>" + Maindriver.Exectime
					+ "</td></tr>");
			slno++;

			bw1.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void createLogFile(String scriptname) throws IOException {
		 
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			Calendar c = new GregorianCalendar();
			String currtime = c.getTime().toString();
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath + "/"
					+ scriptname + ".html"));
			bw1.write("<html><head><title>"
					+ scriptname
					+ "</title></head><body><table style=font-family:calibri border =1 cellspacing=1 frame=Vsides  bgcolor=6699FF Align=Center><tr><td><B>Test Case Name</B></td><td><B>"
					+ scriptname + "</B></td></tr>");
			bw1.write("<tr><td width=500 ><B>Execution Start Time</B></td><td width=270><B>"
					+ currtime + "</B></td></tr></table><br/>");
			bw1.write("<table style=font-family:calibri border =1 cellspacing=1 frame=Vsides bgcolor=#E8E8E8 Align=Center><tr><td Align=center><B>Step Description</B></td><td Align=center><B>Testcase ID</B></td><td Align=center><B>Result</B></td></tr>");
			bw1.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/**
	 * <p>
	 * <b>Generic function name : </b>writePassLog
	 * </p>
	 * <p>
	 * <b>Description : </b>Writing Pass message to the HTML log file
	 * </p>
	 * 
	 * @param desc
	 * @throws IOException
	 */
	public void writePassLog(String scriptname, String desc,String TCID) throws IOException {
		 
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath + "/"
					+ scriptname + ".html", true));
			bw1.write("<tr><td width=690>"
					+ desc
					+ "</td><td width=80 bgcolor=#00FFD5 Align=center>"
					+ TCID
					+ "</td><td width=80 bgcolor=#00FF00 Align=center >PASS</td></tr>");
			bw1.close();
		} catch (Exception e) {
			System.out.println("Error = " + e);
		}
	}

	/**
	 * <p>
	 * <b>Generic function name :</b> writeFailLog
	 * </p>
	 * <p>
	 * <b>Description :</b> Writing Fail message to the HTML log file
	 * </p>
	 * 
	 * @param desc
	 * @throws Exception
	 */
	public void writeFailLog(String scriptname, String desc,String TCID) throws Exception {
		 
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			Snapshotpath = rpath;
			Capturescreenshot(scriptname);
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath + "/"
					+ scriptname + ".html", true));
			bw1.write("<tr><td width=690><a href="
					+Snapshotpath+">"+desc+"</a></td><td width=80 bgcolor=#00FFD5 Align=center>"
					+ TCID
					+ "</td><td width=80 bgcolor=#FF0000 Align=center >FAIL</td></tr>");
			/*
			 * bw1.write("<tr><td width=690><a href=" + "" + ">" + desc +
			 * "</a></td><td width=80 bgcolor=#FF0000 Align=center >FAIL</td></tr>"
			 * );
			 */
			bw1.close();
			failcounter++;
		} catch (Exception e) {
			System.out.println("Write Failed Log Exception = " + e);
		}
	}

/*	public void writePassLog(String scriptname, String desc) throws IOException {
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath + "/"
					+ scriptname + ".html", true));
			bw1.write("<tr><td width=690>"
					+ desc
					+ "</td><td width=80 bgcolor=#00FF00 Align=center >PASS</td></tr>");
			bw1.close();
		} catch (Exception e) {
			System.out.println("Error = " + e);
		}
	}
	
	public void writeFailLog(String scriptname, String desc) throws Exception {
		try {

			String rpath = br.testConfigFile.getProperty("resultPath");
			Capturescreenshot(scriptname);
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(rpath + "/"
					+ scriptname + ".html", true));
			// bw1.write("<tr><td width=690><a href="+ "Snapshotpath"+ ">" +
			// desc+
			// "</a></td><td width=80 bgcolor=#FF0000 Align=center >FAIL</td></tr>");
			bw1.write("<tr><td width=690><a href="
					+ ""
					+ ">"
					+ desc
					+ "</a></td><td width=80 bgcolor=#FF0000 Align=center >FAIL</td></tr>");
			bw1.close();
			failcounter++;
		} catch (Exception e) {
			System.out.println("Write Failed Log Exception = " + e);
		}
	}
	*/
	public void Capturescreenshot(String scriptname) throws Exception {
		 
		String rpath = br.testConfigFile.getProperty("resultPath");
		System.out.println("psth "+rpath);
		try {
			File srcFile = ((TakesScreenshot) Setup.driver)
					.getScreenshotAs(OutputType.FILE);
			// Once we have the screenshot in our file 'srcFile' you can use all
			// FileUtils methods like
			Snapshotpath = rpath + scriptname + screencapcounter + ".png";
			System.out.println("snapshot path " + Snapshotpath);
			srcFile.renameTo(new File(Snapshotpath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		screencapcounter++;

	}

}
