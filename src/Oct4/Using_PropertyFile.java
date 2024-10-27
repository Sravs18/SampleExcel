package Oct4;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Using_PropertyFile {
	WebDriver driver;
	Properties conpro;
		
	 @BeforeTest
	  public void beforeTest() throws Throwable
	 {
		 	conpro = new Properties();
			//load property file
			conpro.load(new FileInputStream("Login.properties"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	  }
	
	@Test
  public void adminLogin() throws Throwable {
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(conpro.getProperty("Enteruser"));
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(conpro.getProperty("EnterPass"));
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.endsWith(Expected))
		{
		Reporter.log("Valid Credentails::"+Expected+"       "+Actual,true);	
		}
		else
		{
			//capture error message
			String Message = driver.findElement(By.xpath(conpro.getProperty("ObjErrormessage"))).getText();
			Reporter.log(Message+"     "+Expected+"        "+Actual);
		}
  }
 

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
