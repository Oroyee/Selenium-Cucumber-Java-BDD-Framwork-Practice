@swaglabslogin
Feature: Test login error message on SwagLabs

  Scenario Outline: 
    Given browser is open for SwagLabs
    And user is on SwagLabs login page
    When user sign in with <action>
    Then Show error message: <message>

    Examples: 
      | action                   | message                                                                   |
      | no credentilas           | Epic sadface: Username is required                                        |
      | email and empty password | Epic sadface: Password is required                                        |
      | password and empty email | Epic sadface: Username is required                                        |
      | incorrect credentilas    | Epic sadface: Username and password do not match any user in this service |