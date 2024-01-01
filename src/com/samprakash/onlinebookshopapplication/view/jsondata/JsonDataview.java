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
		userValidationView = new UserValidationView();
		String userName;
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
				scanner.nextLine();
				switch(choice) {
				            
				case 1 : {
					modifyBooksView = new ModifyBooksView();
					 userName = userValidationView.showAdminUI();
					 if(userName != null) {
						 modifyBooksView.showAdminAccess(userName);
					 }
					break;
				}
				case 2 : {
					
					userName = userValidationView.showUserUI();
					if(userName != null) {
						ShowUserMenu(userName);
					}
					break;
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
				scanner.nextLine();
				continue;
			}
				
		}
		while(choice != 3);
		
	}
	private void ShowUserMenu(String userName) {
		availableBooksView = new AvailableBooksView();
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
					+"\n|   "+ANSI_ITALIC+ANSI_YELLOW+"  1) Search Book"+ANSI_RESET+ANSI_GREEN+"                                |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW+"     2) Purchase Book"+ANSI_RESET+ANSI_GREEN+"                              |\n"
					+"|"+ANSI_ITALIC+ANSI_YELLOW+"     3) Show History"+ANSI_RESET+ANSI_GREEN+"                               |\n"+ANSI_RESET+ANSI_GREEN+"|"+ANSI_ITALIC+ANSI_YELLOW
					+"     4) Logout                                     "
							+ANSI_RESET+ANSI_GREEN+"|\n"
					+"+---------------------------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch(choice) {
				
				case 1 : {
					availableBooksView.searchBook();
					break;
				}
				case 2 : {
					purchaseBookView = new PurchaseBookView();
					purchaseBookView.purchaseBook(userName);
					break;
				}
				case 3 : {
					userValidationView.showHistoryOfUser(userName);
					break;
				}
				case 4 : {
					break;
				}
				default : {
					showError("Enter a Number Between 1 to 3");
				}
				}
			}
			catch(InputMismatchException ime) {
				showError("Enter a valid Input : ");
				scanner.nextLine();
				continue;
			}
		}
		while(choice != 4);
		
	}
	public void showError(String error) {
		
		System.err.println(error);
	}
	
	

}
