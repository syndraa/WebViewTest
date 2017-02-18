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

public class WebViewTest {
	
	AppiumDriver driver;
	public AppiumDriver SetUp() throws Exception{
	
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("deviceName", "d1e5e469");//021YHB2133070943 d1e5e469
		androidCapabilities.setCapability("platformVersion", "5.1.1");
		androidCapabilities.setCapability("app", "/Users/test/AppiumTest/CITEST/download/Android/CrossWalk.apk");
//		androidCapabilities.setCapability("appPackage", "com.mogoweb.chrome.shell");
//		androidCapabilities.setCapability("appActivity", "com.mogoweb.chrome.shell.ShellActivity");	
		androidCapabilities.setCapability("unicodeKeyboard", "true");
		androidCapabilities.setCapability("resetKeyboard", "true");
		androidCapabilities.setCapability("noReset", "true");
		
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("androidProcess", "com.mogoweb.chrome.shell");
//		androidCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
	
		AppiumDriver driver = new AndroidDriver(new URL("http://192.168.6.200:4723/wd/hub"),androidCapabilities);

		return driver;
		
	}
	
	@Test
	public void searchTest() throws Exception{
		
			driver = SetUp();
	//		driver.findElementByXPath("//android.widget.Button[@text='basic page test']").click();
			
			Thread.sleep(6000);
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println(contextName);
				if (contextName.contains("WEBVIEW")){
					driver.context(contextName);
				}else
				{
					System.out.println("no WEBVIEW"); 
				}
			}

		
//		driver.context("WEBVIEW_com.mogoweb.chrome.shell");
//		driver.switchTo().window("WEBVIEW");
//	    driver.findElementByCssSelector("#index-kw").sendKeys("suzhou");

		//在 guinea-pig 页面用 id 和 元素交互。
//		WebElement searchText = driver.findElementByName("Select Element");
//		searchText.click();
//		driver.findElementByXPath("//android.widget.TextView[@text='Select Element']").click();
	
		//		searchText.sendKeys("普元");
//		driver.findElementByAccessibilityId("su").click();
//		Assert.assertEquals("I am a div", div.getText()); //验证得到的文本是否正确。


		//离开 webview，回到原生应用。
		driver.context("NATIVE_APP");

		//关闭应用。
		driver.quit();
	}
}
