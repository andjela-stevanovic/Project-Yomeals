package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SerchResultPage extends BasicPage{

	public SerchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public List<WebElement> results(){
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}
	
	public List<String> productName(){
		List<String> names = new ArrayList<String>();
		for(int i = 0; i<this.results().size(); i++) {
			String name = this.results().get(i).getText();
			names.add(name);
		}
		return names;
	}
	
	public double numberOfResult() {
		return this.results().size();
	}

}
