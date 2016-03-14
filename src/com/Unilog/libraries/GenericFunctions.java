package com.Unilog.libraries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericFunctions  {

	 
	ResultSummary rs = new ResultSummary();
	ExcellLibraries lib = new ExcellLibraries(); 
	int scriptcnt = 0;

	/*String Groupname 	= lib.getExcelData("Groupdata", 0, 1);
	String GroupDescription = lib.getExcelData("Groupdata", 1, 1);
	String FormTitle = lib.getExcelData("Captureformpage", 0, 1);
	String Descriptionmessage = lib.getExcelData("Captureformpage", 1, 1);
	String fieldname = lib.getExcelData("Captureformpage", 3, 1);*/
	
	public void click(WebElement element,String scriptname,String desc) throws Exception{
		try{
			WebDriverWait wait = new WebDriverWait(Setup.driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			rs.writePassLog(scriptname, desc,"");
		}catch(Exception e){
			rs.writeFailLog(scriptname, "Failed to "+desc,"");

		}
	}
	public void doubleClick(WebElement element,String scriptname,String desc) throws Exception{
		Actions action = new Actions(Setup.driver);
		 //Double click
		action.doubleClick(element).perform();
		try{
			WebDriverWait wait = new WebDriverWait(Setup.driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			action.doubleClick(element).perform();
			rs.writePassLog(scriptname, desc,"");
		}catch(Exception e){
			rs.writeFailLog(scriptname, "Failed to "+desc,"");

		}
	}
	
	

	public void typeText(WebElement element,String scriptname,String desc,String enterText) throws Exception{
		try{
			WebDriverWait wait = new WebDriverWait(Setup.driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(enterText);
			rs.writePassLog(scriptname, desc,"");
		}catch(Exception e){
			rs.writeFailLog(scriptname, "Failed to "+desc,"");
		}
	}

	public  void checkbox_Checking(WebElement element,String scriptname,String desc) throws Exception {
		boolean checkstatus;
		try{
			checkstatus = element.isSelected();
			if (checkstatus == true) {
				System.out.println("Checkbox is already checked");
			} else {
				element.click();
				rs.writePassLog(scriptname, desc,"");
				System.out.println("Checked the checkbox");
			}}catch(Exception e){
				rs.writeFailLog(scriptname, "Failed to "+desc,"");
			}
	}


	
	public   void selectElementBy(WebElement element, String GroupName,String scriptname) {
		Select selectitem = new Select(element);
		 List<WebElement> allOptions = selectitem.getOptions();
		    for (WebElement webElement : allOptions)
		    {
		    	System.out.println("Element are "+webElement.getText());
		    	if(webElement.getText().contains(GroupName)){
		    		 
		    		selectitem.selectByVisibleText(GroupName);
		    	}
		    	 
		     
		    }
		}

	public void verifyTitle(String actual, String expected, String scriptname, String desc, String TCID)
			throws Exception {
		try {
			if (actual.equals(expected)) {

				rs.writePassLog(scriptname, "title matches", TCID);



			} else {
				rs.writeFailLog(scriptname, "title does not match", TCID);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	} 


	public void verifyTitle1(String actual, String expected, String scriptname, String desc, String TCID)
			throws Exception {
		try {
			if (actual.equals(expected)) {

				rs.writePassLog(scriptname, "Saved", TCID);
 
			} else {
				rs.writeFailLog(scriptname, "With this name already form exists", TCID);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	} 
	
	public   void radiobutton_Select(WebElement Radio,String scriptname, String desc, String TCID) throws Exception {
		boolean checkstatus;
		try{
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			rs.writePassLog(scriptname, desc, TCID);
			System.out.println("RadioButton is already checked");
		} else {
			Radio.click();
			rs.writeFailLog(scriptname, desc, TCID);
			System.out.println("Selected the Radiobutton");
		}
		}catch (Exception e) {
			e.printStackTrace();
	}

	}
	
	public void verifyingStatus2(String str, List<WebElement> all, String scriptname, String TCID) throws Exception{ 
        int count=1;
        try{
            for(WebElement element: all)
            {
                
                 System.out.println("elements are "+element);
                if(element.getText().contains(str)){
                     Setup.driver.findElement(By.xpath(".//*[@id='ui_dvData']/div["+count+"]/div[1]/input")).click();
                     
                     break;
                     
                }count= count+2;
                
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	public void ExpandDisplayRules( String scriptname  ) throws Exception{

		Iterator<WebElement> expand=Setup.driver.findElements(By.xpath("//div[@id='dvRules']//label[contains(text(),'By')]")).iterator();
		expand.next();
		while(expand.hasNext())
		{
			expand.next().click();
		}
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)Setup.driver;
		jse.executeScript("window.scrollBy(0,-1050)", "");
		Thread.sleep(3000);}

	public   void compareElements(String str, List<WebElement> all, String scriptname, String TCID) throws Exception {
		//for (WebElement ele:all){
		List<String> all1 = new ArrayList<String>();
		try {
			for (WebElement ele:all){
				all1.add(ele.getText());
				 System.out.println("Allforms  "+ all1.add(ele.getText()));
			}
			System.out.println("Come");
			if (all1.contains(str)){
				 System.out.println("str is " + str);
				rs.writePassLog(scriptname, "The form is already existed ", TCID);

				//break;
				}
			else {
				rs.writeFailLog(scriptname, "The form doesn't exist", TCID);
			}

			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//}


	public void verifyingStatus(String str, List<WebElement> all, String scriptname, String TCID) throws Exception{ 
		int count=0;
		try{
			for(WebElement element: all)
			{
				count++;
				 System.out.println("elements are "+element);
				if(element.getText().contains(str)){
					String status = Setup.driver.findElement(By.xpath("//div[@id='ui_dvData']/div["+count+"]/div[3]/img")).getAttribute("title");
					System.out.println("status is  " +  status);
					if(status.contains("InActive")){
						Setup.driver.findElement(By.xpath("//div[@id='ui_dvData']/div["+count+"]/div[3]/img")).click();
						rs.writePassLog(scriptname, "The form status is  Activated ", TCID);
						break;
					}
					else {
						rs.writePassLog(scriptname, "The form status is already Active", TCID);
					}
				}}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switcingToFrame(String str, String scriptname){

		Setup.driver.switchTo().frame(str);

	}

 
}
































































































































/////////////////////////////////////////////////////////////////////////

/*public void  logout(String scriptname, String desc) throws Exception{

 
try {
	WebElement SignoutButton =new Myaccountspage().getMyaccountspage().getSignoutButton();
	click(SignoutButton, scriptname, "Clicking on SignoutButton ");
	WebElement SignOut =new Myaccountspage().getMyaccountspage().getSignOut();
	click(SignOut, scriptname, "Clicking on SignOut ");
	rs.writePassLog(scriptname, desc,"");
} catch (Exception e) {
	rs.writeFailLog(scriptname, "Failed to "+desc,"");
}



}*/


/////////////////////////////////////////////////////
/*public void createform(String scriptname ) throws Exception{
WebElement plumb5io = new Myaccountspage().getMyaccountspage().getNavigateToActiveAccountsPage();
click(plumb5io, scriptname, "Clicking on plumb5io button in Myaccountspage");
WebElement Lead = new Activeaccountspage().getActiveaccountspage().getLeadtButton();
click(Lead, scriptname, "Clicking on Lead button in Activeaccountspage");
WebElement CreatenNewLink = new Formdashboardpage().getFormdashboardpage().getCreatenNewLink();
click(CreatenNewLink, scriptname, "Clicking on CreatenNewLink in Formdashboardpage");
WebElement CreatLink = new Newpage().getNewpage().getCraetelink1();
click(CreatLink, scriptname, "Clicking on CreatLink in Newpage");

WebElement FormTitle1 = new Captureformpage().getCaptureformpage().getFormTitleTextbox();
typeText(FormTitle1, scriptname, "Entering FormTitle in  Captureformpage ", FormTitle);
Thread.sleep(4000);
WebElement Descriptionmessage1 = new Captureformpage().getCaptureformpage().getFormDescriptionTextbox();
typeText(Descriptionmessage1, scriptname, "Entering Descriptionmessage in  Captureformpage ", Descriptionmessage);
Thread.sleep(4000);
Iterator<WebElement> expand=Setup.driver.findElements(By.xpath("//div[@id='dvRules']//label[contains(text(),'By')]")).iterator();
expand.next();
while(expand.hasNext())
{
	expand.next().click();
}
Thread.sleep(3000);
JavascriptExecutor jse = (JavascriptExecutor)Setup.driver;
jse.executeScript("window.scrollBy(0,-1050)", "");
Thread.sleep(5000);
Setup.driver.findElement(By.xpath("//input[@id='ui_chkVisitorType']")).click();

//String visitortype ="unknown";
String visitortype =lib.getExcelData("Captureformpage", 2, 1);
switch(visitortype){

case"unknown":{	    	  
	Setup.driver.findElement(By.xpath("//div[@id='ui_chkVisitorTypeTd']/label[contains(text(),'Unknown')]")).click();
	WebElement Unknown = new Captureformpage().getCaptureformpage().getUnknown();
	click(Unknown, scriptname, "Clicking on Unknown label in Captureformpage");
	WebElement saveButton = new Captureformpage().getCaptureformpage().getSavebutton();
	click(saveButton, scriptname, "Clicking on Savebutton in Captureformpage");
	String save=Setup.driver.findElement(By.xpath("html/body/div[8]")).getText().replace("\n", "").replace("X", "");
	System.out.println("The status:"+save.replace("\n", "").replace("X", ""));
	verifyTitle1(save, "Saved",  scriptname, "Form Successfully saved", "");
	Setup.driver.navigate().to("http://plumb5.io/");
		Thread.sleep(5000);
		allform.switchToFrame("Plumb5FromCampaign", scriptname, "switching into new frame");
		Thread.sleep(5000);
		WebElement name = new Poppage().gePoppage().getNametextbox();
		asser.typeText(name, scriptname, "Entering name", popname);

	break;
}

case"Lead":{	    	  
	//Setup.driver.findElement(By.xpath("//div[@id='ui_chkVisitorTypeTd']/label[contains(text(),'Lead / Prospect')]")).click();
	WebElement Lead1 = new Captureformpage().getCaptureformpage().getLead();
	click(Lead1, scriptname, "Clicking on Lead / Prospect label in Captureformpage");
	WebElement saveButton = new Captureformpage().getCaptureformpage().getSavebutton();
	click(saveButton, scriptname, "Clicking on Savebutton in Captureformpage");
	String save=Setup.driver.findElement(By.xpath("html/body/div[8]")).getText().replace("\n", "").replace("X", "");
	System.out.println("The status:"+save.replace("\n", "").replace("X", ""));
	verifyTitle1(save, "Saved",  scriptname, "Form Successfully saved", "");
	break;
}

case"Customer":{	    	  
	//Setup.driver.findElement(By.xpath("//div[@id='ui_chkVisitorTypeTd']/label[contains(text(),'Customer')]")).click();
	WebElement Customer = new Captureformpage().getCaptureformpage().getCustomer();
	click(Customer, scriptname, "Clicking on Customer label in Newpage");
	WebElement saveButton = new Captureformpage().getCaptureformpage().getSavebutton();
	click(saveButton, scriptname, "Clicking on Savebutton in Captureformpage");
	String save=Setup.driver.findElement(By.xpath("html/body/div[8]")).getText().replace("\n", "").replace("X", "");
	System.out.println("The status:"+save.replace("\n", "").replace("X", ""));
	verifyTitle1(save, "Saved",  scriptname, "Form Successfully saved", "");
	break;
}
}




}*/
////////////////////////////////////////////////////////////
/*public void creatAndDeleteGroups( String scriptname) throws Exception{
WebElement plumb5io = new Myaccountspage().getMyaccountspage().getNavigateToActiveAccountsPage();
click(plumb5io, scriptname, "Clicking on plumb5io button in Myaccountspage");
WebElement Lead = new Activeaccountspage().getActiveaccountspage().getLeadtButton();
click(Lead, scriptname, "Clicking on Lead button in Activeaccountspage");
WebElement mailmarketing = new Formdashboardpage().getFormdashboardpage().getMailMarketing();
click(mailmarketing, scriptname, "Clicking on mailmarketing in Formdashboardpage");
WebElement Groups = new Formdashboardpage().getFormdashboardpage().getGroupslink();
click(Groups, scriptname, "Clicking on Groups in Formdashboardpage");
WebElement Addnew = new Groupspage().getGroupspage().getAddNewlink();
click(Addnew, scriptname, "Clicking on AddNewlink in Groupspage");
WebElement Groupsname = new Groupspage().getGroupspage().getGroupnameTextbox();
typeText(Groupsname, scriptname,"Entering Groupsname in textbox of Groupspage", Groupname);
WebElement Description  = new Groupspage().getGroupspage().getDescriptionTextbox();
typeText(Description, scriptname,"Entering Description in textbox of Groupspage", GroupDescription);
WebElement Addgroup = new Groupspage().getGroupspage().getAddGroupLink();
click(Addgroup, scriptname, "Clicking on Addgroup button in Groupspage");
Thread.sleep(3000);
String actual=Setup.driver.findElement(By.xpath("html/body/div[9]")).getText().replace("\n", "").replace("X", "");
verifyTitle(actual, "Added succussfully", scriptname, "Group Created Successfully", "");
//	asr.verifyText(actual, "Added succussfully", scriptname , " Group successfully added");
System.out.println("The status:"+actual.replace("\n", "").replace("X", ""));
WebElement DeleteGroup = new Groupspage().getGroupspage().getDeleteGroup();
click(DeleteGroup, scriptname, "Clicking on DeleteGroup in Groupspage");
Utils.checkAlert_Accept();

}*/
////////////////////////////////////////////////////////////////////////////

/*public  void  callingLogin(String scriptname) throws Exception{

WebElement email1=new Loginpage().getLoginpage().getEmailTextbox();
typeText(email1, scriptname,"Enter Username", email);
WebElement password1 =new Loginpage().getLoginpage().getPasswordTextbox();
typeText(password1, scriptname, "Enter password", password);
Thread.sleep(3000);
WebElement registerButton =new Loginpage().getLoginpage().getRegisterButton();
click(registerButton, scriptname, "Clicking on Login button ");

}
*/
////////////////////////////////////////////////////////////////////////////////////////////
/*public  void callingLogout(String scriptname) throws Exception{
WebElement SignoutButton= new Myaccountspage().getMyaccountspage().getSignoutButton();
click(SignoutButton, scriptname, "Clicking on Signoutimage in Myactiveaccountspage");
WebElement SignOut=  new Myaccountspage().getMyaccountspage().getSignOut();
click(SignOut, scriptname, "Clicking on SignOut button in Myactiveaccountspage");
String actual = Setup.driver.getTitle();
System.out.println("actual title  " + actual);
verifyTitle(actual, "Plumb5 - About the company", scriptname, "Matched title", "");

}
*/
///////////////////////////////////////////////////////////////////////////////////////
/*public void navigateToFormdashboardpage(String scriptname) throws Exception{
WebElement plumb5io = new Myaccountspage().getMyaccountspage().getNavigateToActiveAccountsPage();
click(plumb5io, scriptname, "Clicking on plumb5io button in Myaccountspage");
WebElement Lead = new Activeaccountspage().getActiveaccountspage().getLeadtButton();
click(Lead, scriptname, "Clicking on Lead button in Activeaccountspage");
WebElement viewallforms = new  Formdashboardpage().getFormdashboardpage().getAllFormsLink();
click(viewallforms, scriptname, "Clicking on viewallforms in Formdashboardpage");
WebElement Allforms = new Formdashboardpage().getFormdashboardpage().getViewAllLink();
click(Allforms, scriptname, "Clicking on Allforms in Formdashboardpage");
}
*/

/////////////////////////////////////////////////////////////////////////////////////////////////
/*public void navigateToCaptureFormpage(String scriptname) throws Exception{
WebElement navigateToActiveAccountsPage =new Myaccountspage().getMyaccountspage().getNavigateToActiveAccountsPage();
click(navigateToActiveAccountsPage, scriptname, "Clicking on Plumb5 Link");
WebElement LeadtButton=new Activeaccountspage().getActiveaccountspage().getLeadtButton();
click(LeadtButton, scriptname, "Clicking on Lead button ");
WebElement createnewlink=new Formdashboardpage().getFormdashboardpage().getCreatenNewLink();
click(createnewlink, scriptname, "Clicking on createnewlink ");
WebElement Craetelink1=new Newpage().getNewpage().getCraetelink1();
click(Craetelink1, scriptname, "Clicking on Craetelink1 button ");
}
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////
/*public void editingFormfieldList(String scriptname) throws Exception{
 
WebElement editButton=new Captureformpage().getCaptureformpage().getEditButton();
click(editButton, scriptname, "Clicking on Edit button ");
WebElement fieldtextbox=new Captureformpage().getCaptureformpage().getFieldNameTextbox();
typeText(fieldtextbox, scriptname,"Entering name in  fieldtextbox", fieldname);
WebElement fieldtypedropdown=new Captureformpage().getCaptureformpage().getEditButton();
Select dropdwon = new Select(fieldtypedropdown);
List<WebElement> allOptions = dropdwon.getOptions();
for (WebElement webElement : allOptions)
{
	
	if(webElement.getText().contains("Radio Button")){
		dropdwon.selectByVisibleText("Radio Button");
	}
	System.out.println(webElement.getText());
 
}

WebElement RadioButton=new Captureformpage().getCaptureformpage().getRadioButton();
radiobutton_Select(RadioButton, scriptname, "Checking Radio button", "") ;
WebElement Updatebutton=new Captureformpage().getCaptureformpage().getRadioButton();
click(Updatebutton, scriptname, "Clicking on Updatebutton button ");
}
*/