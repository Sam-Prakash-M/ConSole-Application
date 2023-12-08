# Console Application: Laptop List

## Target Audience
Customer (can be any human being)

## Team Members
1

## Features
- List of laptops shown
- List of user-selected laptops' processor variants
- List of user-selected laptops' equivalent RAM variants
- Show a list of laptops according to user-given choices
- Show the selected laptop's price
- Option for the user to leave if they don't need the selected laptop
- Ability for the user to change or go back to the previous menu using a stack operation

## Model Classes

### 1. StackImplementation
- This class acts as a view.
- It is used to get input from the user.

### 2. LaptopViewModel
- `gettingJSONObject()`: Method to get the JSON Object.
- `listOutUserChoices()`: Method to observe the input from the user and give a response based on it.
- `getInputFromUser(int minimum, int maximum)`: Method used to go through each menu listed in the project.

### 3. LaptopModel
- `brandNames()`: Method to show the list of laptops from LaptopRepository Singleton class.
- `processorType()`: Method to show the list of processors available for the user-selected laptop from LaptopRepository Singleton class.
- `ramType()`: Method to show the list of RAM variants available for the user-selected laptop from LaptopRepository Singleton class.
- `listOfLaptops()`: Method to show the list of available laptops based on the user selection features.

### 4. LaptopList
- This class acts as a Data Transfer Object.
- `ourLaptops`: String[]
- `stage`: int
- `option`: String
- `name`: String

### 5. LaptopRepository
- This class is a Singleton class.
- It is used to access JSON data and also saves the user-given option by stack.
- `LAPTOP_OBJECT`: LaptopRepository
- `jsonRetriever`: JSONObject
- `brandCollection`: JSONArray
- `processorsVariant`: JSONArray
- `ramVariant`: JSONArray
- `stage`: int
- `traverse`: Stack

