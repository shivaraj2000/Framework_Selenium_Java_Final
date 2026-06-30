package com.example;


import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class SubmitOrderTest extends BaseTest
{
    @Test
    public  void submitOrder() throws IOException {

       String productName="ZARA COAT 3";


       //Login
       Product_Catalog pc=lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@7");

       //Product Page
       //Product_Catalog pc=new Product_Catalog(driver);
       List<WebElement> products=pc.getProductList();
       pc.addToCart(productName);
       pc.goToCart();

       //Cart Page
       Cart_Page cp=new Cart_Page(driver);
       boolean bool=cp.verifyProductDisplay(productName);
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

}
