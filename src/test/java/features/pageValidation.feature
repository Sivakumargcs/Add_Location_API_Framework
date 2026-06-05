Feature: Validation place API's 

Scenario Outline:
Verify if the place is being sucessfully added using AddPlaceAPI 
	Given Add place payload with <Accuracy> "<Address>" "<Phone>" "<Language>" "<Name>" 
	When User calls "AddPlaceAPI" with Post http request 
	Then The API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	
	Examples: 
		|Accuracy |Address |Phone|Language|Name|
		|40|43,Erode|9994599945|English|Siva| 	
		|50|43,Gobi|9904599945|Tamil|Kumar| 	
		|60|43,Chennai|8888599945|Malayam|Raj| 
		
		
