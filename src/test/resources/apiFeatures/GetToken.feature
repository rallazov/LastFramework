Feature: Token API

@TokenAPI  @APIRegression  @APISmoke
Scenario Outline: Post call - Positive scenario
	Given User create request body with "<usernameOrEmailAddress>" , "<password>" 
	When User sumbits post request and gets response
	And User validates if response status code is 200
	Then User retrives token from response

Examples:
	|usernameOrEmailAddress   |password  |
	|test                     |Test123!  |




@TokenAPI  @APIRegression  
Scenario Outline: Negative scenario: User name is missing
	Given User create request body with "<usernameOrEmailAddress>" , "<password>" 
	When User sumbits post request and gets response
	And User validates if response status code is 400
	Then Validate if user gets "<errorMessage>"

Examples:
|usernameOrEmailAddress   |password  |errorMessage								   |
|	                      |Test123!  |The UserNameOrEmailAddress field is required.|




@TokenAPI  @APIRegression 
Scenario Outline: Negative scenario: Password is missing
	Given User create request body with "<usernameOrEmailAddress>" , "<password>" 
	When User sumbits post request and gets response
	And User validates if response status code is 400
	Then Validate if user gets "<errorMessage>"

Examples:
	|usernameOrEmailAddress   |password  |errorMessage	                 |
	|test                     |	         |The Password field is required.|
