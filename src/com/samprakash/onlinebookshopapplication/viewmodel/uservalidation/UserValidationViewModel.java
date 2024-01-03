package com.samprakash.onlinebookshopapplication.viewmodel.uservalidation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.dto.Persons;
import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.util.FileHandling;
import com.samprakash.onlinebookshopapplication.view.userValidation.UserValidationView;

public class UserValidationViewModel extends FileHandling {

	private UserValidationView userValidationView;
	
	public UserValidationViewModel(UserValidationView userValidationView) {
		this.userValidationView = userValidationView;
	}
	
	private static final String PATTERN_FOR_USERNAME = "^[a-zA-Z][a-zA-Z0-9@#\\-_]{2,14}$";
	/*
	 * Starts with either a lowercase or uppercase alphabet. Can contain any of the
	 * characters '@', '#', '-', or '_'. Can include digits. The length must be
	 * between 3 and 15 characters.
	 */
	private static final String PATTERN_FOR_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
	/*
	 * Starts with the specified conditions (at least one digit, one lowercase
	 * letter, one uppercase letter, and one of the specified special characters).
	 * Does not contain any whitespace characters. Has a total length between 8 and
	 * 15 characters.
	 */
	  
	/*
	 * this method get input from getUserNameAndPassWord() and checks both admin and
	 * user are not already in the current userName once its verified this two
	 * conditions next the details are written in User and Admin's Personal
	 * Details.json
	 */	public String createUserNameAndPasswordForUser(int choice) {
		 
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(!isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice) && !isUserAlreadyPresent(userNameAndPassword.getUserName(), choice)) {
					writeNewUserInFile(userNameAndPassword);
					userValidationView.showSuccess("New User Added SuccessFully");
					return userNameAndPassword.getUserName();
				}
				else {
					userValidationView.showError("UserName is Already Exist");
				}
		}
		while(true);
		
	}

	
	/* This Method gets userName and Password from getUserNameAndPassWord() and 
	 * Validate those userName and Password if it matches return true or false and
	 * also tells the Error Message */
	public boolean UserNameAndPasswordValidation(String userName, String passWord) {
		
		if(userName.matches(PATTERN_FOR_USERNAME) 
				&& passWord.matches(PATTERN_FOR_PASSWORD)) {
			return true;
		}
		userValidationView.showError("Your UserName or PassWord is not valid");
		return false;
	}

     
	/*
	 * this method verify the current user is already present if not it shows an
	 * error like user is not exist
	 */

	public String userVerify(int choice) {
		
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isUserAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					userValidationView.showSuccess("Sign in SuccessFully");
					return userNameAndPassword.getUserName();
				}
				else {
					userValidationView.showError("User is not Exist");
				}
		}
		while(true);
		
	}

             
	/*
	 * Its Creates the username and password and also it gets validate and write the
	 * current Admin UserName and Password in file . This method returns the Current Admin UserName
	 * Whenever the Admin Username is already exist it throws an error
	 */
	public String createUserNameAndPasswordForAdmin(int choice) {
		 
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(!isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice) && !isUserAlreadyPresent(userNameAndPassword.getUserName(), choice)) {
					writeNewAdminInFile(userNameAndPassword);
					userValidationView.showSuccess("New Admin Added SuccessFully");
					return userNameAndPassword.getUserName();
					
				}
				else {
					userValidationView.showError("UserName is Already Exist");
				}
		}
		while(true);
	
	}
    
	
	/*
	 * Its get userName and PassWord if and only if admin presents then shows
	 * message sign in successfully and returns the userName
	 */

	public String adminVerify(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					userValidationView.showSuccess("Sign in SuccessFully");
					return userNameAndPassword.getUserName();
				}
				else {
					userValidationView.showError("Admin is not Exist");
				}
		}
		while(true);
		
	}
    
	/*
	 * this methods deletes the current admin Account only when the admin userName
	 * is already present in database or its throws an Error admin is not present
	 */
	public void deteteAdminAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					deleteAdminInFile(userNameAndPassword.getUserName());
					userValidationView.showSuccess("Admin Deleted SuccessFully");
					break;
				}
				else {
					userValidationView.showError("Admin is not Present ");
				}
		}
		while(true);
		
		
	}
	/*
	 * this methods deletes the current User Account only when the User userName
	 * is already present in database or its throws an Error User is not present
	 */

	public void deleteUserAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isUserAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					deleteUserInFile(userNameAndPassword.getUserName());
					userValidationView.showSuccess("User Deleted SuccessFully");
					break;
				}
				else {
					userValidationView.showError("User is not Present ");
				}
		}
		while(true);
		
		
	}
	/*
	 * this methods recovers the  deleted current User Account only when the User userName
	 * is already present in database or its throws an Error User is not present
	 */

	public void recoverDeletedUserAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isUserAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					recoverUserInFile(userNameAndPassword.getUserName());
					userValidationView.showSuccess("User Account Recoverd SuccessFully");
					break;
				}
				else {
					userValidationView.showError("User is not Present ");
				}
		}
		while(true);
		
		
	}
	/*
	 * this methods recovers the  deleted current Admin Account only when the Admin userName
	 * is already present in database or its throws an Error Admin is not present
	 */

	public void recoverDeletedAdminAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					recoverAdminInFile(userNameAndPassword.getUserName());
					userValidationView.showSuccess("Admin Account Recoverd SuccessFully");
					break;
				}
				else {
					userValidationView.showError("Admin is not Present ");
				}
		}
		while(true);
		
		
	}

	/*
	 * this method returns the JSONArray Object of Users in User and Admin's
	 * Personal Details.json file
	 */
	public JSONArray getUsersPersonalJsonArray() {
		
              return (JSONArray) OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver().get("Users");
	}

	/*
	 * this method returns the JSONObject of Users in User and Admin's
	 * User and Admin Usage stats.json file
	 */
	public JSONObject getUserStatsJsonObject() {
		
		return (JSONObject) OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver().get("Users");
	}
        
	/*
	 * this method first find the current user user id and find whether the current
	 * user has any buying history if the user has histories it gives to view or its
	 * shows an error message
	 */

	public void findAllHistoriesOfCurrentUser(JSONArray userJSONArray, JSONObject userAndAdminPeronsalDetails,String userName) {
		if (userJSONArray.isEmpty()) {
			userValidationView.showError("There is no history Was Saved as of Now!!!");
			return;
		}
		long userID = 0;
		for (int i = 0; i < userJSONArray.size(); i++) {
			JSONObject currUser = (JSONObject) userJSONArray.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				userID = (long) currUser.get("ID");
				break;
			}
		}
		String id = String.valueOf(userID);
		if(userAndAdminPeronsalDetails.containsKey(id)) {
			
			JSONArray currUserHistory = (JSONArray) userAndAdminPeronsalDetails.get(id);
			
			for(int i = 0 ; i < currUserHistory.size() ; i++) {
				
				userValidationView.prinTheCurrentUserHistory((JSONObject)currUserHistory.get(i));
				
			}
			
		}
		else {
			userValidationView.showError("There is no history For the Current User !!!");
		}
		
		
	}
}
