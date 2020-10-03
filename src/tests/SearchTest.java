package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.LocationPopupPage;
import pages.SerchResultPage;

public class SearchTest extends BasicTest{
	
	@Test
	public void serachResult() throws IOException, InterruptedException {
		LocationPopupPage pp = new LocationPopupPage(this.driver, this.waiter, this.js);
		SerchResultPage  sp = new SerchResultPage(this.driver, this.waiter, this.js);
		
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		FileOutputStream fos = new FileOutputStream(file);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		SoftAssert sa = new SoftAssert();
		
		
		this.driver.navigate().to(baseUrl + "/meals");
		
		pp.setLocation("City Center - Albany");
		
		Thread.sleep(2000);
		
		for(int i = 1; i<=6; i++) {
			XSSFRow row = sheet1.getRow(i);
			
			String url = row.getCell(1).getStringCellValue();
			
			String location = row .getCell(0).getStringCellValue();
			
			double num = row.getCell(2).getNumericCellValue();
			
			List<String> productN = new ArrayList<String>();
			for(int j = 3; j<3 + num; j++) {
				String fileName = row.getCell(j).getStringCellValue();
				productN.add(fileName);
			}
			pp.openSelectLocation();
			pp.setLocation(location);
			Thread.sleep(2000);
			
			this.driver.navigate().to(url);
			Thread.sleep(2000);
			
			sa.assertEquals(sp.numberOfResult(), num);
			
			for(int j = 0; j<sp.numberOfResult(); j++) {
				boolean equals = false;
				String acctual = sp.productName().get(j);
				String expected = productN.get(j);
				if(acctual.contains(expected)) {
					equals = true;
				}
				sa.assertTrue(equals);
			}
			sa.assertAll();
		}
		
		wb.close();
		fis.close();
		
	}

      
 }
		
