package StepDefinition;

import TestComponents.BaseTest;
import com.example.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class stepDefImplementaion extends BaseTest {
public Landing_Page landingPage;
public Product_Catalog pc;

      @Given("I landed on Ecommerce page")
      public void I_landed_on_Ecommerce_page() throws IOException
      {
          landingPage=launchApp();
      }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String userName, String Password)
    {
        pc=lp.loginToApplication(userName,Password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products=pc.getProductList();
        pc.addToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public  void checout_submit_order(String productName)
    {
        pc.goToCart();

        //Cart Page
        Cart_Page cp=new Cart_Page(driver);
        boolean bool=cp.verifyProductDisplay(productName);
        Assert.assertTrue(bool);
        cp.clickOnCheckOutPage();

        //Checkout
        CheckOut_Page checkout = new CheckOut_Page(driver);
        checkout.selectCountry("india");
        checkout.submitOrder();
    }

    @Then("{string} message is dispalyed on confirmation page")
    public  void message_disaplyed_confirmationpage(String string)
    {
        Confirmation_Page cpp= new Confirmation_Page(driver);
        String confirmmsg= cpp.getConfirmationmsg();
        Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is dispalyed")
    public void message_is_displayed(String string)
    {
        Assert.assertEquals(string,lp.getErrorMsg());
        driver.close();
    }
}