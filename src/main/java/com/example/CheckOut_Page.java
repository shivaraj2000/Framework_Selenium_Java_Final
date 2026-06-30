package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut_Page extends Abstarct_Component {

    WebDriver driver;
    public CheckOut_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //Locators
    @FindBy(css=".user__address .input")
    WebElement enterCountry;

    @FindBy(css=".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(css=".action__submit")
    WebElement submit;

    public void selectCountry(String CountryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(enterCountry,CountryName).build().perform();
        visibilityOfElementsAppear(By.cssSelector(".ta-results"));
        selectCountry.click();
    }

    public void submitOrder()
    {
        submit.click();
    }
}
