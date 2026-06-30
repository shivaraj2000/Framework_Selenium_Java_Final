package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Abstarct_Component {

    WebDriver driver;
    public Abstarct_Component(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    //Locators
    @FindBy(css="[routerlink*='cart']")
    WebElement cart;


    public void visibilityOfElementsAppear(By product)
    {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(product));
    }

    public void visibilityOfWebElementsAppear(WebElement product)
    {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(product));
    }

    public void visibilityOfElementsDisapper(WebElement ele)
    {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void goToCart()
    {
        cart.click();
    }
}
