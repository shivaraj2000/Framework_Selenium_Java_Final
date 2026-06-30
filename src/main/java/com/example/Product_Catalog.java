package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Product_Catalog extends Abstarct_Component
{
    WebDriver driver;
    public Product_Catalog(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //Using PageFactory
    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By productsBy=By.cssSelector(".mb-3");
    By productName= By.cssSelector(".card b");
    By toastMsg= By.cssSelector("#toast-container");


    public List<WebElement> getProductList()
    {
        visibilityOfElementsAppear(productsBy);
        return products;
    }

    public WebElement getProductName(String productName)
    {
        WebElement prod= getProductList().stream().filter(product ->
                        product.findElement(By.cssSelector(".card b")).getText().equals(productName)).
                        findFirst().orElse(null);
        return prod;
    }

    public void addToCart(String productName)
    {
        getProductName(productName).findElement(By.cssSelector(".card button:last-of-type")).click();
        visibilityOfElementsAppear(toastMsg);
        visibilityOfElementsDisapper(spinner);
    }
}
