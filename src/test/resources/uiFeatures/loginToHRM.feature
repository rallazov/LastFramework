Feature: Login functionality test cases verification
  Scenario: User should be able to successfully login to HRM website
    Given I navigate to HRM webpage
    Then I enter valid username
    Then I enter valid password
    When I click on login button
    Then I should be able to successfully login to HRM homapage
  Scenario: User should not be able to login to HRM with invalid password
    Given I navigate to HRM webpage
    Then I enter valid username
    Then I enter invalid password
    When I click on login button
    Then I should not be able to successfully login to HRM homapage
  Scenario: User should not be able to login to HRM with invalid username
    Given I navigate to HRM webpage
    Then I enter invalid username
    Then I enter valid password
    When I click on login button
    Then I should not be able to successfully login to HRM homapage
  Scenario: User should not be able to login to HRM with invalid credentials
    Given I navigate to HRM webpage
    Then I enter invalid username
    Then I enter invalid password
    When I click on login button
    Then I should not be able to successfully login to HRM homapage
  Scenario: User should not be able to login to HRM with invalid username format
    Given I navigate to HRM webpage
    Then I enter invalid username format
    Then I enter valid password
    When I click on login button
    Then I should not be able to successfully login to HRM homapage