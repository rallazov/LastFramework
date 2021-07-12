Feature: Get Personal Info API

@PersonalInfoAPI  @APIRegression  @APISmoke
Scenario Outline: Post Once  validate multiple times 
	
	Given User gets token for "company" account when flag is "<flags>"
	When User submit GET request to "PersonalInfo_URL"
	And User validate if status code is 200
	Then User validates if "<elements>" exists in response 
	
	Examples:
		|flags         |elements           |        
		|true          |name               |
		|false         |surname 		   |
		|false         |middleName         |
		|false         |companyName        |
		|false         |userName           |
		|false         |emailAddress       | 
		|false         |phoneNumber        | 
		|false         |address 		   |
		|false         |address2           |
		|false         |countryState       |
		|false         |city               |
		|false         |postalCode         | 
		|false         |tenantName         | 
		|false         |accountType        | 
		|false         |birthDate          | 
		|false         |businessType       |
		|false         |profileImageUrl    |
		|false         |numberOfEmployees  |
		|false         |registrationNumber |
		|false         |approximateAGI     | 
		|false         |businessTypeId     | 
		|false         |countryStateId     | 
   
       