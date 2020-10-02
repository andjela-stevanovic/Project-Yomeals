package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.CartSummeryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest{
	
	
	@Test (priority = 0)
	public void editProfile() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(this.driver, this.waiter, this.js);
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSystemPage np = new NotificationSystemPage(this.driver, this.waiter, this.js);
		ProfilePage profilep = new ProfilePage(this.driver, this.waiter, this.js);
		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		
		pp.closePopup();
		
		lp.logIn(this.email, this.paseword);
		
		Assert.assertEquals(np.messageText(), "Login Successfull", "ERROR expected to logi in");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		
		profilep.changeProfileInformation("Ronald", "W.", "Adur", "099871", "1324", "India", "Kerala", "Adur");
		
		Assert.assertEquals(np.messageText(), "Setup Successful", "ERROR chenges don't saved");
		
		np.waitDisplayNon();
		
		ap.logout();
		
		Assert.assertEquals(np.messageText(), "Logout Successfull!", "ERROR expected to logout");
		
	}
	
	@Test (priority = 5)
	public void changeProfileImage() throws IOException {
		LoginPage lp = new LoginPage(this.driver, this.waiter, this.js);
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		NotificationSystemPage np = new NotificationSystemPage(this.driver, this.waiter, this.js);
		ProfilePage profilep = new ProfilePage(this.driver, this.waiter, this.js);
		AuthPage ap = new AuthPage(this.driver, this.waiter, this.js);

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		
		pp.closePopup();
		
		lp.logIn(this.email, this.paseword);
		
		Assert.assertEquals(np.messageText(), "Login Successfull", "ERROR expected to logi in");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");

		String imgPath = new File("Images/2020-09-29-18-46-53.png").getCanonicalPath();
		
		profilep.uploadProfileImage(imgPath);
		
		Assert.assertEquals(np.messageText(), "Profile Image Uploaded Successfully",
				"ERROR expected to be uplad");
		
		np.waitDisplayNon();
		
		profilep.deleteProfileImage();
		
		Assert.assertEquals(np.messageText(), "Profile Image Deleted Successfully",
				"ERROR expected to be deleted");
		
		np.waitDisplayNon();
		
		ap.logout();
		
		Assert.assertEquals(np.messageText(), "Logout Successfull!", "ERROR expected to logout");
		
		
	}

}
