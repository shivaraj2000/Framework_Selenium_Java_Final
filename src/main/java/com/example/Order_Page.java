package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Order_Page extends Abstarct_Component {
    WebDriver driver;
    public Order_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> productName;

    public Boolean verifyOrderDispaly(String productNam)
    {
        Boolean bool= productName.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productNam));
        return bool;
    }
}
