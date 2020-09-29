package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String baseUrl = "http://demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String paseword = "12345678a";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.waiter = new WebDriverWait(driver, 30);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {

			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File SrcFile=ts.getScreenshotAs(OutputType.FILE);
			String fileSuffix = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

			
			FileHandler.copy(SrcFile, new File("Screenshots/" + fileSuffix + ".png"));
		}
		this.driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}

}
