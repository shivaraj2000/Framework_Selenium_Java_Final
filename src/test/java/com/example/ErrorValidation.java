package com.example;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"ErrorValidation"})
    public void LoginerrorValidations()
    {
        lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@77");
        Assert.assertEquals("Incorrect email  password.",lp.getErrorMsg());
    }

    @Test
    public void ProdcterrorValidations()
    {
        String productName="ZARA COAT 3";
        Product_Catalog pc=lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@7");
        List<WebElement> products=pc.getProductList();
        pc.addToCart(productName);
        pc.goToCart();
        Cart_Page cp=new Cart_Page(driver);
        boolean bool=cp.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(bool);
    }
}
