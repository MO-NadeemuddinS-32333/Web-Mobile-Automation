@tag
Feature: Launch and login app
  I want to use this template for my feature file

  @tag1
  Scenario: sanity test of application
    Given Application is launch
    And user login
    When user clicks on global search
    And enter input as Yes Bank
    And clicks on result
    Then Yesbank get quote page is displayed
    
  @tag2
  Scenario: Regression test of application
  Given order placement
    And place f&O order
    When price input
    And click on place order
    Then order book updated
   
