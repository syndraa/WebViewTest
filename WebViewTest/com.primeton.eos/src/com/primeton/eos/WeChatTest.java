package com.primeton.eos;

import java.net.URL;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class WeChatTest
{
	
	AppiumDriver driver;
	public AppiumDriver SetUp() throws Exception{
	
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("deviceName", "d1e5e469");//021YHB2133070943 d1e5e469
		androidCapabilities.setCapability("platformVersion", "5.1.1");
		androidCapabilities.setCapability("appPackage", "com.tencent.mm");
		androidCapabilities.setCapability("appActivity", ".ui.LauncherUI");
		androidCapabilities.setCapability("unicodeKeyboard", "true");
		androidCapabilities.setCapability("resetKeyboard", "true");
		androidCapabilities.setCapability("noReset", "true");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
		androidCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
	
		AppiumDriver driver = new AndroidDriver(new URL("http://192.168.8.230:4723/wd/hub"),androidCapabilities);

		return driver;
		
	}
	
	@Test
	public void searchTest() throws Exception{
		
		driver = SetUp();
		Thread.sleep(20000);
		driver.findElementByXPath("//*[@text='我']").click();
		driver.findElementByXPath("//*[@text='收藏']").click();
		driver.findElementByXPath("//*[@text='不二大叔']").click();
		
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);
			if (contextName.contains("WEBVIEW")){
		//		driver.context(contextName);
			}else
			{
				System.out.println("no WEBVIEW"); 
			}
		}
		Thread.sleep(6000);
		System.out.println(driver.getPageSource());
		System.out.println(driver.getContextHandles());
		driver.context("WEBVIEW_com.tencent.mm:tools");
//	    driver.findElementByCssSelector("#index-kw").sendKeys("suzhou");

		driver.context("NATIVE_APP");

		//关闭应用。
		driver.quit();
	}
}
