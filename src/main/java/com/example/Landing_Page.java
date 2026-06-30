package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Hello world!
 *
 */
public class Landing_Page extends Abstarct_Component
{
    WebDriver driver;
    public Landing_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //Normal Way
    //WebElement userName= driver.findElement(By.id("userEmail"));

    //Using PageFactory
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorToastMsg;


    public Product_Catalog loginToApplication(String Email, String Password)
    {
        userEmail.sendKeys(Email);
        userPassword.sendKeys(Password);
        login.click();
        Product_Catalog pc=new Product_Catalog(driver);
        return pc;
    }

    public String getErrorMsg()
    {
        visibilityOfWebElementsAppear(errorToastMsg);
        return errorToastMsg.getText();
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
