@Smoke
Feature: Purchase the order from Ecommerce Website

  Background:
  Given I landed on Ecommerce page

    @Regression
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <userName> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is dispalyed on confirmation page
    Examples:
      | userName          |   password | productName |
      |Shivaraj@gmail.com | Shivaraj@7 | ZARA COAT 3 |


