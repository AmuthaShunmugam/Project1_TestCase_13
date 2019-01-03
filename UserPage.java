package com.ibm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage 
{
	//To click on 'Login'
	@FindBy(xpath="//a[text()='Login']")
	WebElement LoginEle;
	
	//Enter the Login phone number
	@FindBy(xpath="//input[@id='pnum2']")
	WebElement PhoneEle;
	
	//Enter the password
	@FindBy(xpath="//input[@id='pword2']")
	WebElement PasswordEle;
	
	//Click on Login button
	@FindBy(xpath="//button[text()='Login']")
	WebElement LoginButtonEle;

	//To click on 'Shop by Category'
	@FindBy(xpath="//*[@id='categories-menu']/ul/li/span")
	WebElement ShopCategoryEle;
	
	//To click on 'Search for product'
	@FindBy(xpath="//input[@id='search-box']")
	WebElement SearchBoxEle;
	
	//To enter value on 'Search for Product'
	@FindBy(xpath="//input[@type='text']")
	WebElement SearchBoxValueEle;
	
	//To select from dropdown 'NewBadhamProduct'
	@FindBy(xpath="//div[@class='modal-body']/div/a[3]")
	WebElement BadhamEle;
	
	//To click on the 'Add to Cart'
	@FindBy(xpath="//a[text()='Add To Cart']")
	WebElement AddToCartEle;
	
	//To Click on 'Cart' on top right 
	@FindBy(xpath="//div[@class='cart-menu']")
	WebElement CartIconEle;
	
	//To click on Checkout option
	@FindBy(xpath="//a[text()='Check Out']")
	WebElement CheckOutEle;
	
	//TO click on 'Continue Payment'
	@FindBy(xpath="//a[text()='Continue to payment']")
	WebElement ContinuePaymentEle;
	
	//To click on the check box
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement CheckBoxEle;
	
	//To click on Confirm Order
	@FindBy(xpath="//a[text()='Confirm Order']")
	WebElement ConfirmOrderELe;
	
	//To Click on MyAccount
	@FindBy(xpath="//a[@onclick='myAccount()']")
	WebElement MyAccountEle;
	
	//To click on My Orders
	@FindBy(xpath="//a[text()='My Orders']")
	WebElement MyOrderEle;
	
	//To Click On Order Placed
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[7]")
	WebElement OrderPlacedEle;
	
	
	
	WebDriverWait wait;
	WebDriver driver;
	
	public UserPage(WebDriver driver, WebDriverWait wait)  
	{
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickonShopByCategory()
	{
		ShopCategoryEle.click();
	}
	
	public void ClickonSearchbox()
	{
		SearchBoxEle.click();
	}
	
	public void ClickonSearchElement()
	{
		BadhamEle.sendKeys("Badam-p01");
	}
	
	public void ClickonLogin()
	{
		LoginEle.click();
	}
	
	public void EnterPhoneNumber()
	{
		PhoneEle.sendKeys("9489160855");
	}
	
	public void EnterPassword()
	{
		PasswordEle.click();
		PasswordEle.sendKeys("jenisedeepa20");
	}
	
    public void ClickonLoginButton()
    {
    	LoginButtonEle.click();
    }
	
	public void ClickOnAddToCart()
	{
		AddToCartEle.click();
	}
	
	public void ClickOnCartIcon()
	{
		CartIconEle.click();
	}
	
	public void ClicOnCheckOut()
	{
		CheckOutEle.click();
	}
	
	public void ScrollUpDownInPage()
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	public void ScrollUpDownInPage1()
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
	}
	
	public void ClickOnContinuePayment()
	{
		ContinuePaymentEle.click();
	}
	
	public void ClickOnCheckBox()
	{
		CheckBoxEle.click();
	}
	
	public void ClickOnConfirmOrder()
	{
		ConfirmOrderELe.click();
	}
	
	public void ClickOnMyAccount()
	{
		MyAccountEle.click();
	}
	
	public void ClickOnMyOrder()
	{
		MyOrderEle.click();
	}
	
	public void CheckOrderPlaced()
	{
		OrderPlacedEle.click();
	}

}
