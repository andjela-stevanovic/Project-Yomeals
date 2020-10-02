package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummeryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemClass extends BasicTest{

	
	@Test (priority = 0)
	public void addMealToCart() throws InterruptedException {
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSystemPage np = new NotificationSystemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		pp.closePopup();
		
		mp.addToCart("22");
		
		Assert.assertEquals(np.messageText(), "The Following Errors Occurred:" + "\n" + "Please Select Location"
		 ,"ERROR added to cart");
		
		np.waitDisplayNon();
		
		pp.openSelectLocation();
		pp.setLocation("City Center - Albany");
		
		this.waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class = 'location-popup-content']")));
		
		mp.addToCart("14");
		Assert.assertEquals(np.messageText(), "Meal Added To Cart");
		
	}
	
	@Test (priority = 5)
	public void addMealToFavorite() throws InterruptedException {
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSystemPage np = new NotificationSystemPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
     	LoginPage lp = new LoginPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		pp.closePopup();
		
		mp.addToFavorite();
		Thread.sleep(1000);
		
		Assert.assertEquals(np.messageText(), "Please login first!", "ERROR add to favorite");
		Thread.sleep(1000);
		
		this.driver.navigate().to(this.baseUrl +"/guest-user/login-form");
		lp.logIn(this.email, this.paseword);
		
		np.waitDisplayNon();
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mp.addToFavorite();
		
		Assert.assertEquals(np.messageText(), "Product has been added to your favorites.",
				"ERROR expected to add to favorites");
	}
	
	@Test (priority = 10)
	public void clearCart() throws IOException, InterruptedException {
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		MealPage mp = new MealPage(this.driver, this.waiter, this.js);
		NotificationSystemPage np = new NotificationSystemPage(this.driver, this.waiter, this.js);
		CartSummeryPage cs = new CartSummeryPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(this.baseUrl +"/meals");
		pp.setLocation("City Center - Albany");
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		FileOutputStream fos = new FileOutputStream(file);
		XSSFSheet sheet1 = wb.getSheetAt(1);
		
		SoftAssert sa = new SoftAssert();
		
		for(int i = 1; i<=sheet1.getLastRowNum(); i++) {
			XSSFRow row = sheet1.getRow(i);
			
			String url = row.getCell(0).getStringCellValue();
			double qty = row.getCell(1).getNumericCellValue();
			String quantity = "" + qty;
			
			
			this.driver.navigate().to(url);
			mp.addToCart(quantity);
			
			sa.assertEquals(np.messageText(), "Meal Added To Cart", "ERROR not added");
			
			sa.assertAll();
			
		}
		cs.clarAll();
		Thread.sleep(2000);
		Assert.assertEquals(np.messageText(), "All meals removed from Cart successfully");
		
		wb.close();
		fis.close();

	}
	


}
