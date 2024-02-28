@tag
Feature: Feature for error validation
  I want to use this template for my feature file

  @ErrorValidation00
  Scenario Outline: Title of your scenario
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password> 
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name               | password            |
      | man316@yopmail.com | CMpunk@316Incorrect |
