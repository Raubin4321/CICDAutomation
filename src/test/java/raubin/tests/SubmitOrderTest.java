package raubin.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import raubin.TestComponents.BaseTest;
import raubin.pageobjects.CartPage;
import raubin.pageobjects.CheckoutPage;
import raubin.pageobjects.ConfirmationPage;
import raubin.pageobjects.OrderPage;
import raubin.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	//To Verify "ZARA COAT 3" is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("robinkr67@gmail.com", "Munna@4457");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		orderPage.VerifyOrderDisplay(productName );
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\raubinkumar\\data\\PurchaseOrder.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	
	}
	
	/*@DataProvider
	public Object[][] getData() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "robinkr67@gmail.com");
		map.put("password", "Munna@4457");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "kumarraubin05@gmail.com");
		map1.put("password", "Raubin@4457");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] { {map}, {map1} };
		
	}*/
	
	/*@DataProvider
	public Object[][] getData() {
		
		return new Object[][] { {"robinkr67@gmail.com","Munna@4457","ZARA COAT 3"}, 
		{"kumarraubin05@gmail.com","Raubin@4457","ADIDAS ORIGINAL"} };
		
	}*/

}
