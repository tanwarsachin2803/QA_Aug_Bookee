Feature: Homepage

  Background:
    Given Open Browser
    And Open Facebook Url

  Scenario: Verifying Facebook logo
    Then Verify Logo
    Then Quit Browser

  Scenario: Verifying login with invalid credentials
    And Enter username "pragra123@yopmail.com"
    And Enter password "Pragra@12345"
    And Click login button
    Then Verify you are not login
    Then Quit Browser

  Scenario: Verifying login with valid credentials
    And Enter username "pragra123@yopmail.com"
    And Enter password "Pragra@123"
    And Click login button
    Then Verify you are login
    Then Quit Browser




