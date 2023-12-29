package com.samprakash.onlinebookshopapplication.view.modifybooks;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.viewmodel.modifybooks.ModifyBooksViewModel;

public class ModifyBooksView extends Colors{
	private Scanner scanner = new Scanner(System.in);
	ModifyBooksViewModel modifyBooksViewModel;
	  
	public ModifyBooksView() {
		modifyBooksViewModel = new ModifyBooksViewModel(this);
	}
	
	public void showAdminAccess() {
		
		ModifyBooksView modifyBooksView = new ModifyBooksView();
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN + "+---------------------------------------------------+" +
	                   "\n|   " + "  " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "1) Modify Books Stocks" + ANSI_RESET + ANSI_GREEN + "                           |" +
	                   "\n" + ANSI_RESET + ANSI_GREEN + "|" + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "2) Modify Books Price" + ANSI_RESET + ANSI_GREEN + "                       |" +
	                   "\n" + ANSI_RESET + ANSI_GREEN + "|" + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "3) Exit" + ANSI_RESET + ANSI_GREEN + "                                       |" +
	                   "\n+---------------------------------------------------+" + ANSI_RESET);


			try {
				choice = scanner.nextInt();
				switch(choice) {
				case 1 : {
					modifyBooksViewModel.modifyBooksInShop();
					break;
				}
				case 2 : {
					modifyBooksViewModel.modifyBooksPrice();
					break;
				}
				case 3 : {
					break;
				}
				default : {
					showError("Enter a number Between 1 - 3");
				}
				}
				
			}
			catch(InputMismatchException ime) {
				showError("Enter a valid input");
				scanner.next();
				continue;
			}
			
			
		}
		while(choice != 3);
		
		
	}
	private void showError(String error) {
		
		System.err.println(error);
	}

	public void showAllBooksToAddStockCount(JSONArray allBooks) {
		
		int choice ;
		do {
		System.out.println("+---------------------------------------------------+");
		for(int i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		System.out.println(ANSI_RESET);
		System.out.println("+---------------------------------------------------+");	
			 try {
				 choice = scanner.nextInt();
				 if(choice >= 1 && choice <= 20) {
					 modifyBooksViewModel.modifyTheCurrentBookStock((JSONObject)allBooks.get(choice-1));
					 break;
				 }
				 else {
					 showError("Enter a Number Between 1 to 20");
				 }
			 }
		     catch(InputMismatchException ime) {
		    	 showError("Enter a Valid Input");
		    	 scanner.next();
		    	 continue;
		     }
	
			  } while(true);
			 
		
	}

	public void showStocks(JSONObject currBook) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
				+ANSI_BLUE+currBook.get("title")+" Book Current Stock Count is : "
				+currBook.get("stock")
				+ANSI_BLUE+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
		
	}

	public int getnewBookCount() {
		int choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
					+ANSI_BLUE+"\n Enter a Book Count  -> "
							+ANSI_RESET+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
			try {
				choice = scanner.nextInt();
				    break;
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.next();
				continue;
			}
			
		}
		while(true);
		return choice;
	}

	public void showAllBooksToChangePrice(JSONArray allBooks) {

		int choice ;
		do {
		System.out.println("+---------------------------------------------------+");
		for(int i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		System.out.println(ANSI_RESET);
		System.out.println("+---------------------------------------------------+");	
			 try {
				 choice = scanner.nextInt();
				 if(choice >= 1 && choice <= 20) {
					 modifyBooksViewModel.modifyTheCurrentBookPrice((JSONObject)allBooks.get(choice-1));
					 break;
				 }
				 else {
					 showError("Enter a Number Between 1 to 20");
				 }
			 }
		     catch(InputMismatchException ime) {
		    	 showError("Enter a Valid Input");
		    	 scanner.next();
		    	 continue;
		     }
	
			  } while(true);
			 
		
		
	}

	public void showPrice(JSONObject currBook) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
				+ANSI_BLUE+currBook.get("title")+" Book Current Price is : "
				+currBook.get("price")
				+ANSI_BLUE+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
		
		
	}

	public long getNewPrice() {
		long choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
					+ANSI_BLUE+"\n Enter a New Price  -> "
							+ANSI_RESET+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
			try {
				choice = scanner.nextLong();
				return choice;
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.next();
				continue;
			}
			
		}
		while(true);
		
	}

}
