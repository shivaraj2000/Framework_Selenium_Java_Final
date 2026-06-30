package com.example;

import AbstractComponents.Abstarct_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation_Page  extends Abstarct_Component {

    WebDriver driver;
    public Confirmation_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName = "h1")
    WebElement confirmationMsg;

    public String getConfirmationmsg()
    {
        return confirmationMsg.getText();
    }
}
