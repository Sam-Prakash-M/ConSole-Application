package com.samprakash.onlinebookshopapplication.view.userValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.viewmodel.uservalidation.UserValidationViewModel;

public class UserValidationView extends Colors {
  
	private UserValidationViewModel userValidatiobViewModel;
	private Scanner scanner = new Scanner(System.in);
	public UserValidationView() {
		userValidatiobViewModel = new UserValidationViewModel(this);
	}
	public void userVerify() {
		
		
	}
	public void showUserUI() {
		int choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
					+"\n|   "+ANSI_ITALIC+ANSI_YELLOW+"  1) Sign up"+ANSI_RESET+ANSI_GREEN+"                                      |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     2) Sign in"+ANSI_RESET+ANSI_GREEN+"                                       |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     3) Exit                                       "+ANSI_RESET+ANSI_GREEN+"|\n"
					+"+---------------------------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				switch(choice) {
				case 1 : {
					userValidatiobViewModel.createUserNameAndPassword();
					break;
				}
				case 2 : {
					userValidatiobViewModel.userVerify();
					break;
				}
				case 3 : {
					break;
				}
				}
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input !!!");
				scanner.next();
				continue;
			}
		}
		while(true);
		
		
		
	}
	public void showError(String error) {
		
		System.err.println(error);
	}
	public String[] getUserNameAndPassWord() {
		String userName , passWord;
		boolean isCorrect;
		do {
			System.out.println(ANSI_CYAN+"Enter a UserName --> "+ANSI_RESET);
			userName = scanner.next();
			System.out.println(ANSI_CYAN+"Enter a PassWord --> "+ANSI_RESET);
			scanner.nextLine();
			passWord = scanner.nextLine();
			isCorrect = userValidatiobViewModel.UserNameAndPasswordValidation(userName,passWord);
		}
		while(true);
		//return null;
	}

}
