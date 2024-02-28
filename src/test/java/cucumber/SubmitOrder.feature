#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

#//Use Tidy gherkin plugin to automatically generate methods

@tag
Feature: Purchase the order from from Ecommerce website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce Page

 
  @Regression
  Scenario Outline: Positive test of  submitting the order
   Given Logged in with username <name> and password <password>
   When I add product <productname> to Cart
   And Checkout <productName> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
   
    Examples: 
      | name  						 | password 	| productname  |
      | man316@yopmail.com | CMpunk@316 | ZARA COAT 3	 |
     
