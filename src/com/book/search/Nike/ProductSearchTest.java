package com.book.search.Nike;

import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
 
public class ProductSearchTest {
	    static WebDriver driver;
	 	WebElement element, product_name,bookname;
	 	static String cart_sub_total, hard_cover_price;
    	static String[]  csub_total;
		static Select selection;
	 
	@BeforeClass
     public static void openWebBrowser(){

            System.setProperty("webdriver.chrome.driver", "<chrome driver path>");
            driver = new ChromeDriver();     
		} 
 
	 @Test
     public void searchProductTest(){
 
	        driver.get("http://www.amazon.com");
	  	    try
	  	    {
			Select selection = new Select(driver.findElement(By.id("searchDropdownBox")));
			 
			selection.selectByVisibleText("Books");
	 
	    	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nike");
	    	driver.findElement(By.className("nav-input")).click();

            bookname= driver.findElement(By.partialLinkText("Shoe Dog"));
	    	driver.findElement(By.linkText("Phil Knight"));	
	    	bookname.click();    
		 }catch (Exception e){
			}
	     Assert.assertNotNull(selection);
	     System.out.println("Unable to find Books from dropdown box " );
     }
 
	 @Test
     public void subTotalTest()
     {
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  	    driver.findElement(By.xpath("//span[text()='Hardcover']")).click();

  	        hard_cover_price = driver.findElement(By.xpath("//*[@id='a-autoid-6-announce']")).getText();

	  	    driver.findElement(By.id("add-to-cart-button")).click();
	        driver.findElement(By.xpath("//span[text()='Cart']")).click();

	   	    try{
	   	        	cart_sub_total = driver.findElement(By.xpath("//*[@id='sc-subtotal-amount-activecart']/span")).getText();
	   	    	   	System.out.println(cart_sub_total);
	   	    	    csub_total = cart_sub_total.split(" ");
    
          }catch (Exception e){
	        	  assert(hard_cover_price.equals(csub_total));
   	             System.out.println("Unable to match subtotal price with Product price from product paga ");
	          
	          }
}
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
}