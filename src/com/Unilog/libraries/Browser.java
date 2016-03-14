package com.Unilog.libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	// controlling browser and url
//	public static WebSetup.driver Setup.driver = null;
	private FileInputStream configFile = null;
	public Properties testConfigFile = null;
	String chromedrvpath = null;
	String iedrvpath = null;

	// choosing browser
	public Browser() {
		try {
			configFile = new FileInputStream(".\\Config.properties");
			testConfigFile = new Properties();
			testConfigFile.load(configFile);
		 //	System.out.println("I have loaded the configuration file N times");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void open() {
		String browserType = null;
		String Env = null;

		browserType = testConfigFile.getProperty("browserType");
		Env = testConfigFile.getProperty("Env");
		if (browserType.equalsIgnoreCase("firefox")) {

			@SuppressWarnings("unused")
			ProfilesIni profilesIni = new ProfilesIni();

			Setup.driver = new FirefoxDriver();
			if (Env.equalsIgnoreCase("production")) {

				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("qa")) {
				this.RunAutoit();
				Setup.driver = new FirefoxDriver();
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("stage")) {
				this.RunAutoit();
				Setup.driver = new FirefoxDriver();
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			}

		} else if (browserType.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--start-maximized");

			if (Env.equalsIgnoreCase("production")) {
				String chromedrvpath = testConfigFile.getProperty("chromepath");
				System.setProperty("webSetup.driver.chrome.Setup.driver", chromedrvpath);

				Setup.driver = new ChromeDriver();
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("qa")) {
				this.RunAutoit();

				System.setProperty("webSetup.driver.chrome.Setup.driver", chromedrvpath);
				Setup.driver = new ChromeDriver(options);
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("stage")) {
				this.RunAutoit();

				System.setProperty("Setup.driver.chrome.Setup.driver", chromedrvpath);
				Setup.driver = new ChromeDriver(options);
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			}

			else if (Env.equalsIgnoreCase("New Stage")) {
				System.setProperty("Setup.driver.chrome.Setup.driver", chromedrvpath);
				Setup.driver = new ChromeDriver(options);
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			}
		}

		else if (browserType.equalsIgnoreCase("ie")) {
			if (Env.equalsIgnoreCase("production")) {
				String iedrvpath = testConfigFile.getProperty("iedrvpath");
				System.setProperty("webSetup.driver.ie.Setup.driver", iedrvpath);

				Setup.driver = new InternetExplorerDriver();

				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("qa")) {
				this.RunAutoit();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
						true);

				System.setProperty("Setup.driver.ie.Setup.driver", iedrvpath);
				//Setup.driver = new InternetExplorerDriver.driver(capabilities);
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (Env.equalsIgnoreCase("stage")) {
				this.RunAutoit();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
						true);

				System.setProperty("Setup.driver.ie.Setup.driver", iedrvpath);
				Setup.driver = new InternetExplorerDriver(capabilities);
				Setup.driver.manage().window().maximize();
				Setup.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			}
		}
}
	

	// choosing url
	public void ConnectToUrl() {

		String testUrl = testConfigFile.getProperty("testurl");
		Setup.driver.get(testUrl);
	}
	
	//Clearing cache
	public void ClearCache(){
		
		
		
	}
	// closing the browsers
	public void close() {
		Setup.driver.quit();

	}

	// global wait
	public void globalWait(long time) {
		Setup.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void globalWait() {
		String str = testConfigFile.getProperty("globaltime");
		long time = Long.parseLong(str);
		Setup.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	// Run the windows based scenarios using AutoIt
	public void RunAutoit() {
		try {
			Runtime.getRuntime().exec(System.getProperty(""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}