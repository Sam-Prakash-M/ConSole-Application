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
	  
	public String createUserNameAndPasswordForUser(int choice) {
		 
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

	
	public boolean UserNameAndPasswordValidation(String userName, String passWord) {
		
		if(userName.matches(PATTERN_FOR_USERNAME) 
				&& passWord.matches(PATTERN_FOR_PASSWORD)) {
			return true;
		}
		userValidationView.showError("Your UserName or PassWord is not valid\n"
				+ "Rules For UserName  : -->\n"
				+ "Starts with either a lowercase or uppercase alphabet. Can contain any of the\r\n"
				+ " characters '@', '#', '-', or '_'. Can include digits. The length must be\r\n"
				+ " between 3 and 15 characters.\n\n"
				+ "Rules For PassWord : -->\n"
				+ "Starts with the specified conditions (at least one digit, one lowercase\r\n"
				+ "letter, one uppercase letter, and one of the specified special characters).\r\n"
				+ "Does not contain any whitespace characters. Has a total length between 8 and\r\n"
				+ "15 characters.");
		return false;
	}



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


	public void deteteAdminAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					deleteAdminInFile(userNameAndPassword.getUserName());
					break;
				}
				else {
					userValidationView.showError("Admin is not Present ");
				}
		}
		while(true);
		userValidationView.showSuccess("Admin Deleted SuccessFully");
		
	}


	public void deleteUserAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isUserAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					deleteUserInFile(userNameAndPassword.getUserName());
					break;
				}
				else {
					userValidationView.showError("User is not Present ");
				}
		}
		while(true);
		userValidationView.showSuccess("User Deleted SuccessFully");
		
	}


	public void recoverDeletedUserAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isUserAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					recoverUserInFile(userNameAndPassword.getUserName());
					break;
				}
				else {
					userValidationView.showError("User is not Present ");
				}
		}
		while(true);
		userValidationView.showSuccess("User Account Recoverd SuccessFully");
		
	}


	public void recoverDeletedAdminAccount(int choice) {
		do {
			Persons userNameAndPassword = userValidationView.getUserNameAndPassWord();
				if(isAdminAlreadyPresent(userNameAndPassword.getUserName(),choice)) {
					recoverAdminInFile(userNameAndPassword.getUserName());
					break;
				}
				else {
					userValidationView.showError("User is not Present ");
				}
		}
		while(true);
		userValidationView.showSuccess("Admin Account Recoverd SuccessFully");
		
	}


	public JSONArray getUsersPersonalJsonArray() {
		
              return (JSONArray) OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver().get("Users");
	}


	public JSONObject getUserStatsJsonObject() {
		
		return (JSONObject) OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver().get("Users");
	}


	public void findAllHistories(JSONArray userJSONArray, JSONObject userAndAdminPeronsalDetails,String userName) {
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
