# ConSole-Application Laptop list

Target Audience : Customer ( can be any human being)

Member : 1

### Features :
 1. List of Laptops Shown
 2. list of user selected laptops Processor's varient
 3. list of user selected laptops  equilant ram varient
 4. shown list of laptops according to User given choices 
 5. show the selected Laptops price
 6. If User don't need this Laptop can Leave also additionaly
 7. User can change or go back to previous menu with a help stack operation 
	    implemented in the Code.

Model Classes :
	
LaptopList :
		
	1) + ourLaptops : String [] 
	2) - stage	: int
	3) - option 	: String
	4) - LaptopName : String

SamLaptopShowroom :

	1) + SHOP_NAME	        : String
	2) + jsonRetreiver	: JSONObject
	3) + brandCollection	: JSONArray
	4) + processorsVarient  : JSONArray
	5) + ramVarient		: JSONArray
	6) + stage		: int
	7) + traverse		: Stack <LaptopList>
 
	
