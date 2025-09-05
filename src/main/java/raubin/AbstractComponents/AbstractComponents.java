package raubin.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import raubin.pageobjects.CartPage;
import raubin.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findby) {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	
	}
	
	public void waitForWebElementToAppear(WebElement findby) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
		
		}
	
	public void waitForElementDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	public CartPage goToCartPage() {
		cartHeader.click();
		
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
