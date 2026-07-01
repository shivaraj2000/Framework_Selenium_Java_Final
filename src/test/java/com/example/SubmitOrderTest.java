package com.example;


import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class SubmitOrderTest extends BaseTest
{
   String productName="ZARA COAT 3";
    @Test(dataProvider = "getData", groups = "Purchase")
    public  void submitOrder(HashMap<String,String> input) throws IOException {
       //Login
       Product_Catalog pc=lp.loginToApplication(input.get("email"),input.get("password"));

       //Product Page
       //Product_Catalog pc=new Product_Catalog(driver);
       List<WebElement> products=pc.getProductList();
       pc.addToCart(input.get("product"));
       pc.goToCart();

       //Cart Page
       Cart_Page cp=new Cart_Page(driver);
       boolean bool=cp.verifyProductDisplay(input.get("product"));
       Assert.assertTrue(bool);
       cp.clickOnCheckOutPage();

       //Checkout
       CheckOut_Page checkout = new CheckOut_Page(driver);
       checkout.selectCountry("india");
       checkout.submitOrder();

       //Confirmation Page
       Confirmation_Page cpp= new Confirmation_Page(driver);
       String confirmmsg= cpp.getConfirmationmsg();
       Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
   }

   @Test(dependsOnMethods = {"submitOrder"})
   public void orderHistoryTest()
   {
      Product_Catalog pc=lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@7");
      Order_Page op=pc.goToOrderPage();
      Assert.assertTrue(op.verifyOrderDispaly(productName));
   }

   @DataProvider
   public Object[][] getData() throws IOException {
      List<HashMap<String, String>> data= getJsonDataToMap
              (System.getProperty("user.dir") + "/src/test/java/Data/PurchaseOrder.json");
      return new Object[][] {{data.get(0)},{data.get(1)}};
   }

//   @DataProvider
//   public Object[][] getData()
//   {
//      HashMap<String,String> map1=new HashMap<>();
//      HashMap<String,String> map2=new HashMap<>();
//
//      map1.put("email","Shivaraj@gmail.com");
//      map1.put("password","Shivaraj@7");
//      map1.put("product","ZARA COAT 3");
//
//      map2.put("email","Shivaraj@gmail.com");
//      map2.put("password","Shivaraj@7");
//      map2.put("product","ADIDAS ORIGINAL");
//
//      return new Object[][] {{map1},{map2}};
//   }

//   @DataProvider
//   public Object[][] getData()
//   {
//      return new Object[][]
//              {{"Shivaraj@gmail.com","Shivaraj@7","ZARA COAT 3"},{"Shivaraj@gmail.com","Shivaraj@7","ADIDAS ORIGINAL"}};
//   }




}
