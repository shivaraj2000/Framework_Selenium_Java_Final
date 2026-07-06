Feature: Error Validation

  @ErrorValidations
  Scenario Outline: Positive test for submitting the order
    Given I landed on Ecommerce page
    When Logged in with username <userName> and password <password>
    Then "Incorrect email or password." message is dispalyed

    Examples:
      | userName          |   password  |
      |Shivaraj@gmail.com | Shivaraj@71 |


