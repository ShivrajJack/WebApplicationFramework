package com.Unilog.libraries;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Sending Execution report email for multiple recepiants 
public class SendMailForMultipleReceipient {
		Browser br=new Browser();
	 public static String[] Esplit;
	  public static String html_Text;
	  Properties emailProperties;
	  Session mailSession;
	  MimeMessage emailMessage;
	
	  public void sendMail(String userName, String toaddress[], String msg) throws AddressException, MessagingException, IOException
	  {  
	 
		  SendMailForMultipleReceipient javaEmail = new SendMailForMultipleReceipient();
		 
		 
	    javaEmail.setMailServerProperties();
	    javaEmail.createEmailMessage();
	    javaEmail.sendEmail();
	    
	  }
	 
	  public void setMailServerProperties() {
	 
	    String emailPort = "587";//gmail's smtp port//587
	 
	    emailProperties = System.getProperties();
	  //  emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    emailProperties.put("mail.smtp.port", emailPort);
	    emailProperties.put("mail.smtp.auth", "true");
	    emailProperties.put("mail.smtp.starttls.enable", "true");
	 
	  }
	 
	  public void createEmailMessage() throws AddressException,
	      MessagingException, IOException {
	    
	    
	    String emailSubject = "Unilog Automation Execution Result";
	    
	    InetAddress ownIP=InetAddress.getLocalHost();
	    System.out.println("IP of my system is := "+ownIP.getHostAddress());
		String path = br.testConfigFile.getProperty("toaddress");
	    Esplit = path.split(",");
		
		
	      Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		  String dateNow = formatter.format(currentDate.getTime());
		  System.out.println("Now the date is :=>  " + dateNow);
		  
	    URL url = new URL("file:///E:/unilogworkspace/Unilog/results/Summary.html");
		URLConnection conn = url.openConnection();
		DataInputStream in = new DataInputStream ( conn.getInputStream (  )  ) ;
		BufferedReader d = new BufferedReader(new InputStreamReader(in));
		String text = d.readLine();		
		//String Result_Pth = "https://www.dropbox.com/home/kepler_sports_fw/USATODAY_Sports_Automation/Automation_Test_Results";
		int html = text.indexOf("Selenium");		
		int index = html+22;
		String text3 = text.substring(index);
		html_Text = text3.replace("href", "");

		String emailBody = "</tr>"
			  	+ "<html><body>"
				+"<p><h1>Unilog Automation Execution Result Report</h1></p>"
				/*+"<p><h3>Date of Execution : "+dateNow+"</h3></p>"
				+ "<p><h3>Please find Complete Execution Result @ "+"<a href="+Result_Pth+">Deal Chicken</a>"+"\\Results*"
				+ "<p><h4>*Note: To access the detailed reports in Dropbox account, login with 'dealchicken@gmail.com/dcautomation' credentials</h3><p>"*/
				+ html_Text;
				
	    
	    mailSession = Session.getDefaultInstance(emailProperties, null);
	    emailMessage = new MimeMessage(mailSession);
	 
	    for (int i = 0; i < Esplit.length; i++) {
	      emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Esplit[i]));
	    }
	 
	    emailMessage.setSubject(emailSubject);
	    emailMessage.setContent(emailBody, "text/html");//for a html email
	    //emailMessage.setText(emailBody);// for a text email
	 
	  }
	 
	  public void sendEmail() throws AddressException, MessagingException {
	 
	    String emailHost = "smtp.gmail.com";
	    String fromUser = "plumb5.automation";//just the id alone without @gmail.com
	    String fromUserEmailPassword = "plumb5plumb5";
	 
	    Transport transport = mailSession.getTransport("smtp");
	 
	    transport.connect(emailHost, fromUser, fromUserEmailPassword);
	    transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
	    transport.close();
	    System.out.println("Email sent successfully.");
	  }
	
	
	  public String[] splitdatabycoma(String data)

	  {

	  

	  String[] splits = data.split(",");

	  return splits;

	  }

	
	
	
}
