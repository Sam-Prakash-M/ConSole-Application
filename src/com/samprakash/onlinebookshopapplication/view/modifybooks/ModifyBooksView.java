package com.samprakash.onlinebookshopapplication.view.modifybooks;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
			System.out.println(ANSI_GREEN + "+---------------------------------------------------+" + "\n|   " + "  "
					+ ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "1) Add New Book" + ANSI_RESET + ANSI_GREEN
					+ "                               |" + "\n|   " + "  " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW
					+ "2) Delete a Book" + ANSI_RESET + ANSI_GREEN + "                              |" + "\n" + ANSI_RESET
					+ ANSI_GREEN + "|" + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "3) Modify Books Stocks"
					+ ANSI_RESET + ANSI_GREEN + "                        |" + "\n" + ANSI_RESET + ANSI_GREEN + "|" + "     "
					+ ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "4) Modify Books Price" + ANSI_RESET + ANSI_GREEN
					+ "                         |" + "\n" + ANSI_RESET + ANSI_GREEN + "|     " + ANSI_ITALIC + ANSI_BG_BLUE
					+ ANSI_YELLOW + "5) Show History" + ANSI_RESET + ANSI_GREEN + "                               " + "|\n"
					+ ANSI_RESET + ANSI_GREEN + "|" + "     " + ANSI_ITALIC + ANSI_BG_BLUE + ANSI_YELLOW + "6) LogOut"
					+ ANSI_RESET + ANSI_GREEN + "                                     |"
					+ "\n+---------------------------------------------------+" + ANSI_RESET);
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch(choice) {
				case 1 : {
					modifyBooksViewModel.addNewBook(userName);
					break;
				}
				case 2 : {
					modifyBooksViewModel.deleteABook(userName);
					break;
				}
				case 3 : {
					modifyBooksViewModel.modifyBooksStocks(userName);
					break;
				}
				case 4 : {
					modifyBooksViewModel.modifyBooksPrice(userName);
					break;
				}
				case 5 : {
					modifyBooksViewModel.showHistoryOfAdmin(userName);
					break;
				}
				case 6 : {
					break;
				}
				default : {
					showError("Enter a number Between 1 - 6");
				}
				}
				
			}
			catch(InputMismatchException ime) {
				showError("Enter a valid input");
				scanner.nextLine();
				continue;
			}
			
			
		}
		while(choice != 6);
		
		
	}
	public void showError(String error) {
		
		System.out.println(ANSI_BG_RED+ANSI_WHITE+ANSI_BOLD+error+ANSI_RESET);
	}

	public void showAllBooksToModifyStockCount(JSONArray allBooks ,String userName) {
		
		int choice ;
		do {
		System.out.println(ANSI_GREEN +"+---------------------------------------------------+");
		int i;
		for(i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		
		System.out.printf(ANSI_RESET+ANSI_BG_RED+ANSI_BOLD+"\n%3d Exit <-- ",0);
		System.out.println(ANSI_RESET+ANSI_GREEN +"\n+---------------------------------------------------+"+ANSI_RESET);	
			 try {
				 choice = scanner.nextInt();
				 scanner.nextLine();
				 if(choice >= 1 && choice <= i) {
					 if(!modifyBooksViewModel.modifyTheCurrentBookStock((JSONObject)allBooks.get(choice-1) , userName)) {
						 continue;
					 }
					 break;
				 }
				 else if(choice == 0) {
					 return;
				 }
				 else {
					 showError("Enter a Number Between 0 to "+i);
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
		int i;
		for(i = 0 ; i < allBooks.size() ; i++) {
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3d ) Book Name : %s "
					,(i+1),((JSONObject) allBooks.get(i)).get("title")+"\n\n");
			
		}
		System.out.printf(ANSI_RESET+ANSI_BG_RED+ANSI_BOLD+"\n%3d Exit <-- ",0);

		System.out.println(ANSI_RESET+ANSI_GREEN +"\n+---------------------------------------------------+"+ANSI_RESET);	
			 try {
				 choice = scanner.nextInt();
				 scanner.nextLine();
				 if(choice >= 1 && choice <= i) {
					 if(!modifyBooksViewModel.modifyTheCurrentBookPrice((JSONObject)allBooks.get(choice-1) , userName)) {
						 continue;
					 }
					 break;
				 }
				 else if(choice == 0) {
					 return;
				 }
				 else {
					 showError("Enter a Number Between 0 to "+i);
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
				+ANSI_CYAN+currBook.get("title")+" Book Current Price is : "
				+currBook.get("price")
				+ANSI_RESET+ANSI_GREEN+"\n+---------------------------------------------------+"+ANSI_RESET);	
		
		
	}

	public int getNewPrice() {
		int choice;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET
					+ANSI_CYAN+"\n Enter a New Price  -> "
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

	public void prinTheCurrentHistoryOfAdmin(JSONObject currDateHistory) {
		System.out.println(ANSI_GREEN+"-----------------------"
				+ "--------------------------------------+"+ANSI_RESET);
	        
			System.out.print(ANSI_PURPLE);
			 if(currDateHistory.containsKey("RemovedBookDate")) {
				 System.out.printf("%3s  -> Removed Book Date : %s"," "
							,(String) currDateHistory.get("RemovedBookDate") + "\n\n");
				}
			 if(currDateHistory.containsKey("RemovedBookTitle")) {
				 System.out.printf("%3s  -> Removed Book Name : %s"," "
							,(String) currDateHistory.get("RemovedBookTitle") + "\n\n");
				}
			 if(currDateHistory.containsKey("RemovedBookAuthor")) {
				 System.out.printf("%3s  -> Removed Book Author : %s"," "
							,(String) currDateHistory.get("RemovedBookAuthor") + "\n\n");
				}
			 if(currDateHistory.containsKey("RemovedBookGenre")) {
				 System.out.printf("%3s  -> Removed Book Genre : %s"," "
							,(String) currDateHistory.get("RemovedBookGenre") + "\n\n");
				}
			 if(currDateHistory.containsKey("RemovedBookPrice")) {
				 System.out.printf("%3s  -> Removed Book Price : %s"," "
							,String.valueOf(currDateHistory.get("RemovedBookPrice")) + "\n\n");
				}
			 if(currDateHistory.containsKey("RemovedBookStocksCount")) {
				 System.out.printf("%3s  -> Book Removed Stocks Count : %s"," "
							,String.valueOf(currDateHistory.get("RemovedBookStocksCount")) + "\n\n");
				}
			 if(currDateHistory.containsKey("BookAddedDate")) {
				 System.out.printf("%3s  -> Book Added Date : %s"," "
							,(String) currDateHistory.get("BookAddedDate") + "\n\n");
				}
			if(currDateHistory.containsKey("title")) {
				System.out.printf("%3s  -> Book Name : %s"," "
						,(String) currDateHistory.get("title") + "\n\n");
			}
            if(currDateHistory.containsKey("author")) {
            	System.out.printf("%3s  -> Book Author : %s"," "
						,(String) currDateHistory.get("author") + "\n\n");
			}
            if(currDateHistory.containsKey("genre")) {
            	System.out.printf("%3s  -> Book Genre : %s"," "
						,(String) currDateHistory.get("genre") + "\n\n");
			}
            if(currDateHistory.containsKey("price")) {
            	System.out.printf("%3s  -> Book Price : %s"," "
						,(String.valueOf(currDateHistory.get("price"))) + "\n\n");
             }
            if(currDateHistory.containsKey("stock")) {
            	System.out.printf("%3s  -> Book stock : %s"," "
						,(String.valueOf(currDateHistory.get("stock"))) + "\n\n");
            }
			if(currDateHistory.containsKey("modifiedTime")) {
				System.out.printf("%3s  -> Time of Change : %s"," "
						,(String) currDateHistory.get("modifiedTime") + "\n\n");
			}
			if(currDateHistory.containsKey("BookName")) {
				System.out.printf("%3s  -> Book Name : %s"," "
						,(String) currDateHistory.get("BookName") + "\n\n");
			}
			if(currDateHistory.containsKey("oldCount")) {
				System.out.printf("%3s  -> Old Books Count  : %s"," "
						,(String.valueOf(currDateHistory.get("oldCount"))) + "\n\n");
			}
			if(currDateHistory.containsKey("newCount")) {
				System.out.printf("%3s  -> New Books Count : %s"," "
						,(String.valueOf( currDateHistory.get("newCount"))) + "\n\n");
			}
			if(currDateHistory.containsKey("oldPrice")) {
				System.out.printf("%3s  -> Old Price  : %s"," "
						,(String.valueOf(currDateHistory.get("oldPrice"))) + "\n\n");
			}
			if(currDateHistory.containsKey("newPrice")) {
				System.out.printf("%3s  -> New Price : %s"," "
						,(String.valueOf(currDateHistory.get("newPrice"))) + "\n\n");
			}
			
			System.out.print(ANSI_RESET);
		
		System.out.println("\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		
		
	}

	public List getInputFromAdmin() {
		String bookTitle;
		System.out.println("\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		while(true) {
			System.out.println(ANSI_PURPLE+"Enter a Book Title Name  "+ANSI_RESET);
		       bookTitle = scanner.nextLine();
		       if(!modifyBooksViewModel.isBookPresence(bookTitle)) {
		    	   break;
		       }
		       else {
		    	   showError("Book is Already Present");
		    	   continue;
		       }
		      
		}
	
		System.out.println(ANSI_PURPLE+"Enter a Book Author Name  "+ANSI_RESET);
		String bookAuthor = scanner.nextLine();
		System.out.println(ANSI_PURPLE+"Enter a Book Genre Name  "+ANSI_RESET);
		String bookGenre = scanner.nextLine();
		
		long bookPrice , bookStock;
		while(true) {
			System.out.println(ANSI_PURPLE+"Enter a Book Price  "+ANSI_RESET);
			if(scanner.hasNextLong()) {
				bookPrice = scanner.nextLong();
				scanner.nextLine();
				break;
			}
			else {
				scanner.nextLine();
				showError(ANSI_PURPLE+"Enter a Valid Input "+ANSI_RESET);
				continue;
			}
			
		}
		while(true) {
			System.out.println(ANSI_PURPLE+"Enter a BookCount  "+ANSI_RESET);
			if(scanner.hasNextLong()) {
				bookStock = scanner.nextLong();
				scanner.nextLine();
				break;
			}
			else {
				scanner.nextLine();
				showError("Enter a Valid Input ");
				continue;
			}
			
		}
		
		System.out.println(ANSI_RESET+"\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		List adminInput = new ArrayList();
		adminInput.add(bookAuthor);
		adminInput.add(bookPrice);
		adminInput.add(bookGenre);
		adminInput.add(bookTitle);
		adminInput.add(bookStock);
		return adminInput;
	}

	public String getInputBookNameForDelete() {
		String bookTitle;
		System.out.println("\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		while(true) {
			System.out.println(ANSI_PURPLE+"Enter a Book Title Name  "+ANSI_RESET);
		       bookTitle = scanner.nextLine();
		       if(modifyBooksViewModel.isBookPresence(bookTitle)) {
		    	   break;
		       }
		       else {
		    	   showError("Book is Already Present");
		    	   continue;
		       }
		      
		}
		System.out.println(ANSI_RESET+"\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		return bookTitle;
	}

}
