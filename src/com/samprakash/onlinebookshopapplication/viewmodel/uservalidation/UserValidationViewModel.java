package com.samprakash.onlinebookshopapplication.viewmodel.uservalidation;

import com.samprakash.onlinebookshopapplication.view.userValidation.UserValidationView;

public class UserValidationViewModel {

	private UserValidationView userValidationView;
	
	  
	public UserValidationViewModel(UserValidationView userValidationView) {
		this.userValidationView = userValidationView;
	}

	public void userVerify() {
		  
		userValidationView.showUserUI();
		
	}

	public void createUserNameAndPassword() {
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
		String[] userNameAndPassword = userValidationView.getUserNameAndPassWord();
		
	}

	public boolean UserNameAndPasswordValidation(String userName, String passWord) {
		String patternForUserName = "^[a-zA-Z0-9._-]{3,8}$";
		String patternforPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
		return false;
	}
}
