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
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ChromeTest {
	
	AppiumDriver driver;
	public AppiumDriver SetUp() throws Exception{
	
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("deviceName", "d1e5e469");//021YHB2133070943
		androidCapabilities.setCapability("platformVersion", "5.1.1");
		androidCapabilities.setCapability("browserName", "Chrome");		
		androidCapabilities.setCapability("unicodeKeyboard", "true");
		androidCapabilities.setCapability("resetKeyboard", "true");
		androidCapabilities.setCapability("appPackage", "com.android.browser");
		androidCapabilities.setCapability("appActivity", ".BrowserActivity");
		AppiumDriver driver = new AndroidDriver(new URL("http://192.168.6.200:4723/wd/hub"),androidCapabilities);

		return driver;
		
	}
	
	@Test
	public void searchTest() throws Exception{
		driver = SetUp();
		driver.get("https://epass.icbc.com.cn/regist/regist_index.jsp");
		driver.findElementByCssSelector("#CNcustName").sendKeys("普元信息");
	    Select credType = new Select(driver.findElementByXPath("//select[@name='credType']"));
	    credType.selectByValue("2");
	    Thread.sleep(1000);
	    credType.selectByValue("0");
	    driver.findElementByCssSelector("#credNum").sendKeys("370281182738372837");
	    driver.findElementByCssSelector("#phoneNum").sendKeys("13748374847");
	    driver.findElementByXPath("//div[@class='n_btn']").click();
	    String result = driver.switchTo().alert().getText();
	    System.out.println(result);
	    Assert.assertEquals("输入的身份证号码不正确，请重新输入！"
	    		+"\n"
	    		+"\n"
	    		+"\n"
	    		+"\n"
	    		+"提示：为了您的正常体验请勿勾选“禁止此页再显示对话框”",result );
	    Thread.sleep(2000);
	    driver.switchTo().alert().accept();
	    Thread.sleep(4000);
		//关闭应用。
		driver.quit();
	}
}
