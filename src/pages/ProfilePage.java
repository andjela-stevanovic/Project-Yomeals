package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage{

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[1]/fieldset/input"));
	}
	
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[2]/fieldset/input"));
	}
	
	public WebElement getAddress() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[2]/div[2]/fieldset/input"));
	}
	
	public WebElement getPhone() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[1]/fieldset/input"));
	}
	
	public WebElement getZipCode() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[2]/fieldset/input"));
	}
	
	public Select getCountry() {
		WebElement country = this.driver.findElement(By.id("user_country_id"));
		Select s = new Select(country);
		return s;
	}
	
	public Select getState() {
		WebElement state = this.driver.findElement(By.id("user_state_id"));
		Select s = new Select(state);
		return s;
	}
	
	public Select getCity() {
		WebElement state = this.driver.findElement(By.id("user_city"));
		Select s = new Select(state);
		return s;
	}
	
	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}
	
	public WebElement getUploadImage() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[1]"));
	}
	
	public WebElement getRemoveImage() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
	}
	
	public void deleteProfileImage() {
		js.executeScript("arguments[0].click();", this.getRemoveImage());
	}
	
	public void uploadProfileImage(String fileName) {
		js.executeScript("arguments[0].click();", this.getUploadImage());
		this.driver.findElement(By.xpath("//*[@id=\"form-upload\"]/input")).sendKeys(fileName);
		
	}
	
	public void changeProfileInformation(String fName, String lName, String address, 
			String phone, String zip, String country, String state, String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(fName);
		this.getLastName().clear();
		this.getLastName().sendKeys(lName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zip);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(1000);
		this.getState().selectByVisibleText(state);
		this.getCity().selectByVisibleText(city);
		this.getSaveButton().click();
	}

}
