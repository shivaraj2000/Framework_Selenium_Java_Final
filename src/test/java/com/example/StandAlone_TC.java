package com.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class StandAlone_TC
{
    public static void main(String[] args)
   {
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://rahulshettyacademy.com/client");

       String productName="ZARA COAT 3";

       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       driver.findElement(By.id("userEmail")).sendKeys("Shivaraj@gmail.com");
       driver.findElement(By.id("userPassword")).sendKeys("Shivaraj@7");
       driver.findElement(By.id("login")).click();

       WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

       List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

       WebElement prod=products.stream().filter(product ->
                       product.findElement(By.cssSelector(".card b")).getText().equals(productName)).
               findFirst().orElse(null);
       prod.findElement(By.cssSelector(".card button:last-of-type")).click();


       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));


        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean bool= cartProducts.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
       Assert.assertTrue(bool);

       driver.findElement(By.cssSelector(".totalRow button")).click();

       Actions a = new Actions(driver);
       a.sendKeys(driver.findElement(By.cssSelector(".user__address .input")),"india").build().perform();

       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-results")));

       driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
       driver.findElement(By.cssSelector(".action__submit")).click();

       String conformmsg= driver.findElement(By.tagName("h1")).getText();
       boolean flag=conformmsg.equalsIgnoreCase("Thankyou for the order.");
       Assert.assertTrue(flag);
       driver.close();


   }

}
