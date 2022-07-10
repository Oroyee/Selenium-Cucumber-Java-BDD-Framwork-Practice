@restfulapi
Feature: RESTful API testing on REQRES

  @registration_1
  Scenario: user POST on register API with valid email and password
    When user POST 1 valid email and 1 valid password on register
    Then server response 200 along with token
  @registration_2  
  Scenario: user POST on register API only with valid email
    When user POST only 1 valid email on register
    Then server response 400 along with an error Get User List
  @listusers
  Scenario: user GET on users API
    When user GET on users
    Then server response 200 along with token
    
