	package com.ibm.test;
	
	import java.io.File;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.AdminPage;
import com.ibm.pages.AdminPage1;
import com.ibm.pages.UserPage;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	PropertiesFileHandler propFIleHandler;
	HashMap<String, String> data;

	@BeforeSuite
	public void propertiesfile() throws IOException {
		String file = "./TestData/data.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		data = propFileHandler.getPropertiesAsMap(file);
	}

	@BeforeMethod
	public void BrowserInitialization() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void ReportStatistics() throws InterruptedException, SQLException {

		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
        driver.get(url);

		AdminPage1 home = new AdminPage1(driver, wait);
		home.EnetrEmailAddress(userName);
		home.EnetrPassword(password);
		home.ClickonLoginButton();
		home.ClickOnReports();
		Thread.sleep(3000);
		home.ClickOnStatistics();
		WebElement Product = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-striped table-hover']/tbody/tr[1]/td[2]"));
		String ProductValue = Product.getText().replace(",","").replace(".","").replaceAll("00","").trim();
		System.out.println("The total sales value before adding a product to cart is:" + ProductValue);
		Integer Value1=Integer.parseInt(ProductValue);

		UserPage User = new UserPage(driver, wait);
		driver.get("https://atozgroceries.com/");
		User.ClickonLogin();
		User.EnterPhoneNumber();
		User.EnterPassword();
		User.ClickonLoginButton();
		Thread.sleep(3000);
		User.ClickonSearchbox();
		User.ClickonSearchElement();
		User.ClickOnAddToCart();
		// Verifying the success message whether the product is added to the cart
		WebElement Msg = driver.findElement(By.xpath("//span[@id='toastmsg']"));
		String SuccessMsg = Msg.getText();
		System.out.println("The product added message:" + SuccessMsg);
		User.ClickOnCartIcon();
		User.ClicOnCheckOut();
		User.ScrollUpDownInPage();
		User.ClickOnContinuePayment();
		Thread.sleep(2000);
		User.ScrollUpDownInPage1();
		User.ClickOnCheckBox();
		User.ClickOnConfirmOrder();
		Thread.sleep(2000);
		User.ClickOnMyAccount();
		User.ClickOnMyOrder();
		// TO get the order id of newly placed product
		WebElement Order = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[1]"));
		String OrderId = Order.getText().replaceAll("#", "").trim();
		System.out.println("The order id is :" + OrderId);

		// To Validate the presence of added record in the db table
		Connection c = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries",
				"foodsonfinger_atoz", "welcome@123");
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from as_order where customer_id='9489160855' ");
		while (rs.next()) {
			System.out.println("The order added :" + rs.getString("order_id"));
			Assert.assertEquals(rs.getString("order_id"), OrderId);

		}
		// To check whether the total sales value has increased
		driver.get(url);
		AdminPage1 home1 = new AdminPage1(driver, wait);
		home1.EnetrEmailAddress(userName);
		home1.EnetrPassword(password);
		home1.ClickonLoginButton();
		home1.ClickOnReports();
		Thread.sleep(3000);
		home1.ClickOnStatistics();
		WebElement Product1 = driver.findElement(By.xpath("//table[@class='table table-bordered table-striped table-hover']/tbody/tr[1]/td[2]"));
		String ProductValue1 = Product1.getText().replace(",","").replace(".","").replaceAll("00","").trim();
		System.out.println("The total sales value before adding a product to cart is:" + ProductValue1);
		Integer Value2=Integer.parseInt(ProductValue1);
	    if((Value2 > Value1))
	    {
	    	System.out.println("The Total Sale value has increased");
	    }

	}
	
}

