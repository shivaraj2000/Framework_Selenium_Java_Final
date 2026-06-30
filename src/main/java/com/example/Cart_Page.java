package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cart_Page extends Abstarct_Component {

    WebDriver driver;
    public Cart_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //Locators
    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    WebElement checkout;

    public Boolean verifyProductDisplay(String productName)
    {
        Boolean bool= cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
        return bool;
    }



    public void clickOnCheckOutPage()
    {
        checkout.click();
    }
}
