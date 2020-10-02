package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getAddToCart() {
		return this.driver.findElement
				(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}
	
	public WebElement getFavorite() {
		return this.driver.findElement(By.xpath("//*[@id=\"item_119\"]"));
	}
	
	public WebElement getQty() {
		return this.driver.findElement
		(By.name("product_qty"));
	}
	
	public void addToCart(String qty) throws InterruptedException {
		this.getQty().sendKeys(qty);
		this.getQty().sendKeys(Keys.ARROW_RIGHT);
		this.getQty().sendKeys(Keys.BACK_SPACE);
		this.getAddToCart().click();
	}
	
	public void addToFavorite() {
		this.getFavorite().click();
	}

}
