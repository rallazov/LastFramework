Feature: Login

  @MB-001 @Smoke @Regression
  Scenario: Login Successfully
    Given Im on MealB login page
    And I enter valid MealB username
    And I enter valid MealB password
    When I click MealB log in button
    Then I land in the MealB homepage
