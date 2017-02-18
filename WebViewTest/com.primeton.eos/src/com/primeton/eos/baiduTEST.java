package com.primeton.eos;

import java.net.URL;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class baiduTEST {
	
	AppiumDriver driver;
	public AppiumDriver SetUp() throws Exception{
	
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("deviceName", "d1e5e469");//021YHB2133070943
		androidCapabilities.setCapability("platformVersion", "5.1.1");
//		androidCapabilities.setCapability("app", "/Users/test/AppiumTest/CITEST/download/Android/emp-core.apk");
		androidCapabilities.setCapability("appPackage", "com.primeton.emp.client.core");
		androidCapabilities.setCapability("appActivity", "com.primeton.emp.client.core.StartUpActivity");	
		androidCapabilities.setCapability("unicodeKeyboard", "true");
		androidCapabilities.setCapability("resetKeyboard", "true");
		androidCapabilities.setCapability("noReset", "true");
		AppiumDriver driver = new AndroidDriver(new URL("http://192.168.8.230:4723/wd/hub"),androidCapabilities);

		return driver;
		
	}
	
	@Test
	public void searchTest() throws Exception{
		
		driver = SetUp();
		Thread.sleep(6000);
		driver.findElementByXPath("//android.widget.TextView[@text='ddd']").click();
		
		
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(":"+contextName);
			if (contextName.contains("WEBVIEW")){
				driver.context(contextName);
			}else
			{
				System.out.println("no WEBVIEW"); 
			}
		}
//		driver.findElementByClassName("android.widget.EditText").sendKeys("puyuan");
		driver.findElementByCssSelector("#index-kw").sendKeys("普元");
		driver.findElementByCssSelector("#index-bn").click();
//		Assert.assertEquals("I am a div", div.getText()); //验证得到的文本是否正确。


		//离开 webview，回到原生应用。
		driver.context("NATIVE_APP");

		//关闭应用。
		driver.quit();
	}
}
