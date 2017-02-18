package com.primeton.eos;

import java.net.URL;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class X5WebDemo {
	
	AppiumDriver driver;
	public AppiumDriver SetUp() throws Exception{
	
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("deviceName", "d1e5e469");//021YHB2133070943 d1e5e469
		androidCapabilities.setCapability("platformVersion", "5.1.1");
		androidCapabilities.setCapability("app", "/Users/test/AppiumTest/CITEST/download/Android/X5WebDemo.apk");
	//	androidCapabilities.setCapability("appPackage", "com.example.test_webview_demo");
	//	androidCapabilities.setCapability("appActivity", "com.example.test_webview_demo.MainActivity");	
		androidCapabilities.setCapability("unicodeKeyboard", "true");
		androidCapabilities.setCapability("resetKeyboard", "true");
		androidCapabilities.setCapability("noReset", "true");
		
/*		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidProcess", "com.mogoweb.chrome.shell");
		androidCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
	*/
		AppiumDriver driver = new AndroidDriver(new URL("http://192.168.6.200:4723/wd/hub"),androidCapabilities);

		return driver;
		
	}
	
	@Test
	public void searchTest() throws Exception{
		
		driver = SetUp();
		
		Thread.sleep(3000);
		driver.findElementByXPath("//android.widget.EditText[@text='百度一下']").sendKeys("https://epass.icbc.com.cn/regist/regist_index.jsp");
		Thread.sleep(1000);
		driver.findElementByXPath("//android.widget.Button[@text='进入']").click();
		Thread.sleep(1000);
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
		
	    driver.findElementByCssSelector("#CNcustName").sendKeys("普元信息");
	    Select credType = new Select(driver.findElementByXPath("//select[@name='credType']"));
	    credType.selectByValue("2");
	    Thread.sleep(1000);
	    credType.selectByValue("0");
	    driver.findElementByCssSelector("#credNum").sendKeys("370281182738372837");
	    driver.findElementByCssSelector("#phoneNum").sendKeys("13748374847");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByClassName('n_btn')[0].click()");

	    //System.out.println(driver.findElementByXPath("//div[@class='n_btn']").getAttribute("width"));
	    //driver.findElementByXPath("//nobr[text()='下一步']").click();
	    //driver.findElementByXPath("//div[@class='n_btn']").click();
	    //driver.findElement(By.id("link4Verifyimage2Name")).click();
//	    driver.findElementByXPath("//div/nobr").click();
	    Thread.sleep(1000);
	    String result = driver.switchTo().alert().getText();
	    System.out.println(result);
	    Assert.assertEquals("输入的身份证号码不正确，请重新输入！"
	    		+"\n"
	    		+"\n"
	    		+"\n"
	    		+"\n"
	    		+"提示：为了您的正常体验请勿勾选“禁止此页再显示对话框”",result );
	    Thread.sleep(2000);
	    driver.context("NATIVE_APP");	   
	    ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
	    Thread.sleep(4000);

		//关闭应用。
		driver.quit();
	}
}
