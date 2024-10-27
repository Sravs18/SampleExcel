package Oct4;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
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

public class LoginValidation {
	
	WebDriver driver ;
	Properties conpro;
	@BeforeTest
	public void beforeTest() throws Throwable{
		conpro = new Properties();
		conpro.load(new FileInputStream("Login.properties"));
		driver = new ChromeDriver();
	}
	@Test(dataProvider = "dp")
	public void adminLogin(String user,String pass) throws Throwable {
		driver.get(conpro.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
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
			Reporter.log(Message+"     "+Expected+"        "+Actual,true);
		}
	}

	@DataProvider
	public Object[][] dp() {
		Object login [][]= {{"Admin","Qedge123!@#"},{"","Qedge123!@#"},{"Admin",""},
				{"Admin","Qedge123!@#"},{"Test","Qedge123!@#"}};
		return login;
	}
	


	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
