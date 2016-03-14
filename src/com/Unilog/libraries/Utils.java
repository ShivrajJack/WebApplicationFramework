package com.Unilog.libraries;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	 
	public static void switchToNewWindow() {
		Set<String> s = Setup.driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		Setup.driver.switchTo().window(w2);
	}

	public static void switchToOldWindow() {
		Set<String> s = Setup.driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		Setup.driver.switchTo().window(w1);
	}

	public static void switchToParentWindow() {
		Setup.driver.switchTo().defaultContent();
	}


	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Setup.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitTillElementFound(WebElement ElementTobeFound,
			int seconds) {
		WebDriverWait wait = new WebDriverWait(Setup.driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(ElementTobeFound));
	}

	public static void takeScreenshotOfWebelement(WebDriver driver,
			WebElement element, String Destination) throws Exception {
		File v = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage bi = ImageIO.read(v);
		org.openqa.selenium.Point p = element.getLocation();
		int n = element.getSize().getWidth();
		int m = element.getSize().getHeight();
		BufferedImage d = bi.getSubimage(p.getX(), p.getY(), n, m);
		ImageIO.write(d, "png", v);

		FileUtils.copyFile(v, new File(Destination));
	}

	public static void setWindowSize(int Dimension1, int dimension2) {
		Setup.driver.manage().window().setSize(new Dimension(Dimension1, dimension2));

	}

	public static void takeScreenshotMethod(String Destination)
			throws Exception {
		File f = ((TakesScreenshot) Setup.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(Destination));
	}

	public static void pressKeyDown(WebElement element) {
		element.sendKeys(Keys.DOWN);
	}

	public void pressKeyEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public static void pressKeyUp(WebElement element) {
		element.sendKeys(Keys.UP);
	}

	public static void moveToTab(WebElement element) {
		element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}


	public static void handleHTTPS_Firefox() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(false);
		Setup.driver = new FirefoxDriver(profile);
	}

	public static void waitTillPageLoad(int i) {

		Setup.driver.manage().timeouts().pageLoadTimeout(i, TimeUnit.SECONDS);

	}

	public static void clickAllLinksInPage(String destinationOfScreenshot)
			throws Exception {

		List<WebElement> Links =Setup.driver.findElements(By.tagName("a"));
		System.out.println("Total number of links  :" + Links.size());

		for (int p = 0; p < Links.size(); p++) {
			System.out.println("Elements present the body :"
					+ Links.get(p).getText());
			Links.get(p).click();
			Thread.sleep(3000);
			System.out.println("Url of the page  " + p + ")"
					+ Setup.driver.getCurrentUrl());
			takeScreenshotMethod(destinationOfScreenshot + p);
			navigate_back();
			Thread.sleep(2000);
		}

	}

	public static void keyboardEvents(WebElement webelement, Keys key,
			String alphabet) {
		webelement.sendKeys(Keys.chord(key, alphabet));

	}

	public static void navigate_forward() {
		Setup.driver.navigate().forward();
	}

	public static void navigate_back() {
		Setup.driver.navigate().back();
	}

	public static void refresh() {
		Setup.driver.navigate().refresh();
	}

	public static void waitMyTime(int i) {
		Setup.driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);

	}

	public static void clearTextField(WebElement element) {
		element.clear();

	}

	public static void clickWebelement(WebElement element) {
		try {
			boolean elementIsClickable = element.isEnabled();
			while (elementIsClickable) {
				element.click();
			}

		} catch (Exception e) {
			System.out.println("Element is not enabled");
			e.printStackTrace();
		}
	}

	public static void clickMultipleElements(WebElement someElement,
			WebElement someOtherElement) {
		Actions builder = new Actions(Setup.driver);
		builder.keyDown(Keys.CONTROL).click(someElement)
		.click(someOtherElement).keyUp(Keys.CONTROL).build().perform();
	}

	public static void highlightelement(WebElement element) {
		for (int i = 0; i < 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor)Setup. driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: solid red; border: 6px solid yellow;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");

		}

	}

	public static boolean checkAlert_Accept() {
		try {
			Alert a =Setup. driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.accept();
			return true;

		} catch (Exception e) {

			System.out.println("no alert ");
			return false;

		}
	}

	public static boolean checkAlert_Dismiss() {
		try {
			Alert a = Setup.driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.dismiss();
			return true;

		} catch (Exception e) {

			System.out.println("no alert ");
			return false;

		}
	}

	/*public static void scrolltoElement(WebElement ScrolltoThisElement) {
		Coordinates coordinate = ((Locatable) ScrolltoThisElement)
				.getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}
*/
	public static void checkbox_Checking(WebElement checkbox) {
		boolean checkstatus;
		checkstatus = checkbox.isSelected();
		if (checkstatus == true) {
			System.out.println("Checkbox is already checked");
		} else {
			checkbox.click();
			System.out.println("Checked the checkbox");
		}
	}

	public static void radiobutton_Select(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			System.out.println("RadioButton is already checked");
		} else {
			Radio.click();
			System.out.println("Selected the Radiobutton");
		}
	}

	// Unchecking
	public static void checkbox_Unchecking(WebElement checkbox) {
		boolean checkstatus;
		checkstatus = checkbox.isSelected();
		if (checkstatus == true) {
			checkbox.click();
			System.out.println("Checkbox is unchecked");
		} else {
			System.out.println("Checkbox is already unchecked");
		}
	}

	public static void radioButton_Deselect(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			Radio.click();
			System.out.println("Radio Button is deselected");
		} else {
			System.out.println("Radio Button was already Deselected");
		}
	}

	public static void dragAndDrop(WebElement fromWebElement,
			WebElement toWebElement) {
		Actions builder = new Actions(Setup.driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
	}

	public static void dragAndDrop_Method2(WebElement fromWebElement,
			WebElement toWebElement) {
		Actions builder = new Actions(Setup.driver);
		Action dragAndDrop = builder.clickAndHold(fromWebElement)
				.moveToElement(toWebElement).release(toWebElement).build();
		dragAndDrop.perform();
	}

	public static void dragAndDrop_Method3(WebElement fromWebElement,
			WebElement toWebElement) throws InterruptedException {
		Actions builder = new Actions(Setup.driver);
		builder.clickAndHold(fromWebElement).moveToElement(toWebElement)
		.perform();
		Thread.sleep(2000);
		builder.release(toWebElement).build().perform();
	}

	public static void hoverWebelement(WebElement HovertoWebElement)
			throws InterruptedException {
		Actions builder = new Actions(Setup.driver);
		builder.moveToElement(HovertoWebElement).perform();
		Thread.sleep(2000);

	}

	public static void doubleClickWebelement(WebElement doubleclickonWebElement)
			throws InterruptedException {
		Actions builder = new Actions(Setup.driver);
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);

	}

	public static String getToolTip(WebElement toolTipofWebElement)
			throws InterruptedException {
		String tooltip = toolTipofWebElement.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}

	public static void selectElementByNameMethod(WebElement element, String Name) {
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(Name);
	}

	public static void selectElementByValueMethod(WebElement element,
			String value) {
		Select selectitem = new Select(element);
		selectitem.selectByValue(value);
	}

	public static void selectElementByIndexMethod(WebElement element, int index) {
		Select selectitem = new Select(element);
		selectitem.selectByIndex(index);
	}

	public static void clickCheckboxFromList(String xpathOfElement,
			String valueToSelect) {

		List<WebElement> lst =Setup. driver.findElements(By.xpath(xpathOfElement));
		for (int i = 0; i < lst.size(); i++) {
			List<WebElement> dr = lst.get(i).findElements(By.tagName("label"));
			for (WebElement f : dr) {
				System.out.println("value in the list : " + f.getText());
				if (valueToSelect.equals(f.getText())) {
					f.click();
					break;
				}
			}
		}
	}

	public static void downloadFile(String href, String fileName)
			throws Exception {
		URL url = null;
		URLConnection con = null;
		int i;
		url = new URL(href);
		con = url.openConnection();
		File file = new File(".//OutputData//" + fileName);
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		@SuppressWarnings("resource")
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
		while ((i = bis.read()) != -1) {
			bos.write(i);
		}
		bos.flush();
		bis.close();
	}

	public static void navigateToEveryLinkInPage() throws InterruptedException {

		List<WebElement> linksize = Setup.driver.findElements(By.tagName("a"));
		int linksCount = linksize.size();
		System.out.println("Total no of links Available: " + linksCount);
		String[] links = new String[linksCount];
		System.out.println("List of links Available: ");
		// print all the links from webpage
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
			System.out.println(linksize.get(i).getAttribute("href"));
		}
		// navigate to each Link on the webpage
		for (int i = 0; i < linksCount; i++) {
			Setup.driver.navigate().to(links[i]);
			Thread.sleep(3000);
			System.out.println(Setup.driver.getTitle());
		}
	}

	public static boolean compareElements(String a,List<WebElement> b) {
	    for (WebElement we:b) {
	        if (a.equalsIgnoreCase(we.getText())) {
	        	return true;
	        }
	    	}
	    return false;
	}
	
	public void switchToFrame(String ParentFrame, String ChildFrame) {
		try {
			Setup.driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " + ChildFrame
					+ "which is present on frame with id" + ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + ParentFrame
					+ " or " + ChildFrame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id "
					+ ChildFrame + "which is present on frame with id"
					+ ParentFrame + e.getStackTrace());
		}
	}

	
	public void switchtoDefaultFrame() {
		try {
			Setup.driver.switchTo().defaultContent();
			System.out.println("Navigated back to webpage from frame");
		} catch (Exception e) {
			System.out
					.println("unable to navigate back to main webpage from frame"
							+ e.getStackTrace());
		}
	}
	
	public void switchToFrame(WebElement frameElement) {
		try {
			if (isElementPresent(frameElement)) {
				Setup.driver.switchTo().frame(frameElement);
				System.out.println("Navigated to frame with element "+ frameElement);
			} else {
				System.out.println("Unable to navigate to frame with element "+ frameElement);
			}
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frameElement + e.getStackTrace());
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + frameElement + "is not attached to the page document" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to frame with element " + frameElement + e.getStackTrace());
		}
	}

	private boolean isElementPresent(WebElement frameElement) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
