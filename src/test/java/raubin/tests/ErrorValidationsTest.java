package raubin.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import raubin.TestComponents.BaseTest;
import raubin.TestComponents.Retry;
import raubin.pageobjects.CartPage;
import raubin.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		
		landingPage.loginApplication("robinkr670@gmail.com", "Munna@44");
		//landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//div[aria-label='Incorrect email or password.']	

	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("robinkr67@gmail.com", "Munna@4457");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	}

}
