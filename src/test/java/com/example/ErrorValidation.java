package com.example;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class)
    public void LoginerrorValidations()
    {
        lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@77");
        Assert.assertEquals("Incorrect email or password.",lp.getErrorMsg());
    }


    @Test
    public void ProdcterrorValidations() throws InterruptedException {
        String productName="ZARA COAT 3";
        Product_Catalog pc=lp.loginToApplication("Shivaraj@gmail.com","Shivaraj@7");
        List<WebElement> products=pc.getProductList();
        pc.addToCart(productName);
        pc.goToCart();
        Cart_Page cp=new Cart_Page(driver);
        boolean bool=cp.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(bool);
        //System.out.println("Testing the pull request from git pull from inteelij");
         //System.out.println("Testing the pull request from git pull from cmd");
        //System.out.println("Added new develop branch");
        //System.out.println("from develop branch we are pushih code to git");
    }
}
