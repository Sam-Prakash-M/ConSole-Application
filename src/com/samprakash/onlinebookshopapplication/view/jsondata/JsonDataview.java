package com.samprakash.onlinebookshopapplication.view.jsondata;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.dto.Admin;
import com.samprakash.onlinebookshopapplication.view.availablebook.AvailableBooksView;
import com.samprakash.onlinebookshopapplication.view.modifybooks.ModifyBooksView;
import com.samprakash.onlinebookshopapplication.view.purchasebook.PurchaseBookView;
import com.samprakash.onlinebookshopapplication.view.userValidation.UserValidationView;
import com.samprakash.onlinebookshopapplication.viewmodel.jsondata.JsonDataViewModel;

public class JsonDataview extends Colors {
	private   JsonDataViewModel jsonDataViewModel ;
	private AvailableBooksView availableBooksView;
	private PurchaseBookView purchaseBookView;
	private UserValidationView userValidationView;
	private  Scanner scanner =  new Scanner(System.in);
	public JsonDataview() {
		jsonDataViewModel = new JsonDataViewModel(this);
	}
	private ModifyBooksView modifyBooksView;
	
	
	public void startTheApp() {
		jsonDataViewModel.assignJsonobject();
		showMenu();
		
	}
	private void showMenu() {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
				+"\n|   "+ANSI_ITALIC+ANSI_BG_PURPLE+"Welcome to  "+Admin.OWNER
				+"'s Online Shopping App"+ANSI_RESET+ANSI_GREEN+"   |"
				+"\n+---------------------------------------------------+\n"+ANSI_RESET);
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
					+"\n|   "+ANSI_ITALIC+ANSI_YELLOW+"  1) Admin"+ANSI_RESET+ANSI_GREEN+"                                      |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     2) User"+ANSI_RESET+ANSI_GREEN+"                                       |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     3) Exit                                       "+ANSI_RESET+ANSI_GREEN+"|\n"
					+"+---------------------------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				switch(choice) {
				            
				case 1 : {
					modifyBooksView = new ModifyBooksView();
					jsonDataViewModel.adminVerify();
					modifyBooksView.showAdminAccess();
					break;
				}
				case 2 : {
					//userValidationView = new UserValidationView();
					//userValidationView.showUserUI();
					ShowUserMenu();
				}
				case 3 : {
					break;
				}
				default  : {
					showError("Enter a Number Between 1 to 3");
				}
				}
				
			}
			catch(InputMismatchException ime ) {
				showError("Enter a Valid Input <--");
				scanner.next();
				continue;
			}
				
		}
		while(choice != 3);
		
	}
	private void ShowUserMenu() {
		availableBooksView = new AvailableBooksView();
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
					+"\n|   "+ANSI_ITALIC+ANSI_YELLOW+"  1) Search Book"+ANSI_RESET+ANSI_GREEN+"                                |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     2) Purchase Book"+ANSI_RESET+ANSI_GREEN+"                              |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     3) Exit                                       "+ANSI_RESET+ANSI_GREEN+"|\n"
					+"+---------------------------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				switch(choice) {
				
				case 1 : {
					availableBooksView.searchBook();
					break;
				}
				case 2 : {
					purchaseBookView = new PurchaseBookView();
					purchaseBookView.purchaseBook();
					break;
				}
				case 3 : {
					break;
				}
				default : {
					showError("Enter a Number Between 1 to 3");
				}
				}
			}
			catch(InputMismatchException ime) {
				showError("Enter a valid Input : ");
				scanner.next();
				continue;
			}
		}
		while(choice != 3);
		
	}
	public void showError(String error) {
		
		System.err.println(error);
	}
	public void getAdminCredential() {
		
		String userName , passWord;
		boolean isCorrect;
		do {
			System.out.println(ANSI_CYAN+"Enter a UserName --> "+ANSI_RESET);
			userName = scanner.next();
			System.out.println(ANSI_CYAN+"Enter a PassWord --> "+ANSI_RESET);
			scanner.nextLine();
			passWord = scanner.nextLine();
			isCorrect = jsonDataViewModel.UserNameAndPaswordValidation(userName,passWord);
			
		}
		while(!isCorrect);
		System.out.println(ANSI_BG_WHITE+ANSI_PURPLE+"Password Verified Successfully "+ANSI_RESET);
		
		
	}
	

}
