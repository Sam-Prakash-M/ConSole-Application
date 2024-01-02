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
	
	public void showAdminAccess(String userName) {
		
		ModifyBooksView modifyBooksView = new ModifyBooksView();
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN + "+---------------------------------------------------+" +
	                   "\n|   " + "  " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "1) Modify Books Stocks" + ANSI_RESET + ANSI_GREEN + "                        |" +
	                   "\n" + ANSI_RESET + ANSI_GREEN + "|" + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "2) Modify Books Price" + ANSI_RESET + ANSI_GREEN 
	                   + "                         |" +
	                   "\n" + ANSI_RESET + ANSI_GREEN +"|     "+ ANSI_ITALIC + ANSI_BG_BLUE+ ANSI_YELLOW +"3) Show History"+ANSI_RESET + ANSI_GREEN +"                               "
	                   		+ "|\n" + ANSI_RESET + ANSI_GREEN + "|" 
	                   + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "4) LogOut" + ANSI_RESET + ANSI_GREEN + "                                     |" +
	                   "\n+---------------------------------------------------+" + ANSI_RESET);


			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch(choice) {
				case 1 : {
					modifyBooksViewModel.modifyBooksInShop(userName);
					break;
				}
				case 2 : {
					modifyBooksViewModel.modifyBooksPrice(userName);
					break;
				}
				case 3 : {
					modifyBooksViewModel.showHistory(userName);
					break;
				}
				case 4 : {
					break;
				}
				default : {
					showError("Enter a number Between 1 - 4");
				}
				}
				
			}
			catch(InputMismatchException ime) {
				showError("Enter a valid input");
				scanner.nextLine();
				continue;
			}
			
			
		}
		while(choice != 4);
		
		
	}
	public void showError(String error) {
		
		System.out.println(ANSI_BG_RED+ANSI_WHITE+ANSI_BOLD+error+ANSI_RESET);
	}

	public void showAllBooksToAddStockCount(JSONArray allBooks ,String userName) {
		
		int choice ;
		do {
		System.out.println(ANSI_GREEN +"+---------------------------------------------------+");
		for(int i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		
		System.out.printf(ANSI_RESET+ANSI_BG_RED+ANSI_BOLD+"\n%3d Exit <-- ",0);
		System.out.println(ANSI_RESET+ANSI_GREEN +"\n+---------------------------------------------------+"+ANSI_RESET);	
			 try {
				 choice = scanner.nextInt();
				 scanner.nextLine();
				 if(choice >= 1 && choice <= 20) {
					 modifyBooksViewModel.modifyTheCurrentBookStock((JSONObject)allBooks.get(choice-1) , userName);
					 break;
				 }
				 else if(choice == 0) {
					 return;
				 }
				 else {
					 showError("Enter a Number Between 0 to 20");
				 }
			 }
		     catch(InputMismatchException ime) {
		    	 showError("Enter a Valid Input");
		    	 scanner.nextLine();
		    	 continue;
		     }
	
			  } while(true);
			 
		
	}

	public void showStocks(JSONObject currBook) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
				+ANSI_CYAN+currBook.get("title")+" Book Current Stock Count is : "
				+currBook.get("stock")
				+ANSI_BLUE+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
		
	}

	public int getnewBookCount() {
		int choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
					+ANSI_CYAN+"\n Enter a Book Count  -> "
							+ANSI_RESET+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				return choice;
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.nextLine();
				continue;
			}
			
		}
		while(true);
		
	}

	public void showAllBooksToChangePrice(JSONArray allBooks , String userName) {

		int choice ;
		do {
		System.out.println(ANSI_GREEN +"+---------------------------------------------------+");
		for(int i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		System.out.printf(ANSI_RESET+ANSI_BG_RED+ANSI_BOLD+"\n%3d Exit <-- ",0);

		System.out.println(ANSI_RESET+ANSI_GREEN +"\n+---------------------------------------------------+"+ANSI_RESET);	
			 try {
				 choice = scanner.nextInt();
				 scanner.nextLine();
				 if(choice >= 1 && choice <= 20) {
					 modifyBooksViewModel.modifyTheCurrentBookPrice((JSONObject)allBooks.get(choice-1) , userName);
					 break;
				 }
				 else if(choice == 0) {
					 return;
				 }
				 else {
					 showError("Enter a Number Between 0 to 20");
				 }
			 }
		     catch(InputMismatchException ime) {
		    	 showError("Enter a Valid Input");
		    	 scanner.nextLine();
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

	public int getNewPrice() {
		int choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
					+ANSI_BLUE+"\n Enter a New Price  -> "
							+ANSI_RESET+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				return choice;
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.nextLine();
				continue;
			}
			
		}
		while(true);
		
	}

	public void showSuccess(String success) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
				+"\n   "+ANSI_ITALIC+ANSI_BG_PURPLE+success+
				ANSI_RESET+ANSI_GREEN
				+"\n+---------------------------------------------------+\n\n"+ANSI_RESET);
		
		
	}

	public void prinTheCurrentUserHistory(JSONObject currDateHistory) {
		System.out.println(ANSI_GREEN+"-----------------------"
				+ "--------------------------------------+"+ANSI_RESET);
	
			System.out.print(ANSI_PURPLE);
			System.out.printf("%3s  -> Time of Change : %s"," "
					,(String) currDateHistory.get("modifiedTime") + "\n\n");
			System.out.printf("%3s  -> Book Name : %s"," "
					,(String) currDateHistory.get("BookName") + "\n\n");
			if(currDateHistory.containsKey("oldCount")) {
				System.out.printf("%3s  -> Old Books Count  : %s"," "
						,(String.valueOf(currDateHistory.get("oldCount")+"")) + "\n\n");
			}
			if(currDateHistory.containsKey("newCount")) {
				System.out.printf("%3s  -> New Books Count : %s"," "
						,(String.valueOf( currDateHistory.get("newCount")+"")) + "\n\n");
			}
			if(currDateHistory.containsKey("oldPrice")) {
				System.out.printf("%3s  -> Old Price  : %s"," "
						,(String.valueOf(currDateHistory.get("oldPrice")+"")) + "\n\n");
			}
			if(currDateHistory.containsKey("newPrice")) {
				System.out.printf("%3s  -> New Price : %s"," "
						,(String.valueOf(currDateHistory.get("newPrice")+"")) + "\n\n");
			}
			
			System.out.print(ANSI_RESET);
		
		System.out.println("\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		
		
	}

}
