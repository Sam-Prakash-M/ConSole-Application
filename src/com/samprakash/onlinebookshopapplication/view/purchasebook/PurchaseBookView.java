package com.samprakash.onlinebookshopapplication.view.purchasebook;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.viewmodel.purchasebook.PurchaseBookViewModel;

public class PurchaseBookView extends Colors {
    
	private Scanner scanner = new Scanner(System.in);
	private PurchaseBookViewModel purchaseBookViewModel;

	public PurchaseBookView() {
		purchaseBookViewModel = new PurchaseBookViewModel(this);
	}

	public void purchaseBook(String userName) {
		JSONArray allBooks = purchaseBookViewModel.getJsonArray();
		showAllBooksFullDetails(allBooks , userName);
	}
	

	public void showAllBooksFullDetails(JSONArray allBooks , String userName) {

		int choice;
		do {
			System.out.println(ANSI_GREEN+"-----------------------"
					+ "--------------------------------------+"+ANSI_RESET);
			int i;
			for (i = 0; i < allBooks.size(); i++) {
				
				System.out.println(ANSI_PURPLE);
				System.out.printf("%3d  Book Name : %s", (i + 1),
						((JSONObject) allBooks.get(i)).get("title") + "\n");
				System.out.println(ANSI_RESET+ANSI_YELLOW);
				System.out.printf("%3s  -> Author Name : %s"," "
						,((JSONObject) allBooks.get(i)).get("author") + "\n");
				System.out.printf("%3s  -> Genre Name : %s"," "
						,((JSONObject) allBooks.get(i)).get("genre") + "\n");
				System.out.printf("%3s  -> Current Price : %s"," "
						,((JSONObject) allBooks.get(i)).get("price") + "\n");
				System.out.printf("%3s  -> Current Stock : %s"," "
						,((JSONObject) allBooks.get(i)).get("stock") + "\n"+ANSI_RESET);
				

			}
			System.out.printf(ANSI_BG_RED+ANSI_BOLD+"\n%3d Exit <-- ",0);
			System.out.println("\n"+ANSI_RESET+ANSI_GREEN+"+--------------------------"
					+ "----------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				if (choice >= 1 && choice <= i) {
					   long noOfBooks = getNumberOfBooks();
					   if(purchaseBookViewModel.bookHasNoOfBooks(choice-1,noOfBooks)) {
						   purchaseBookViewModel.purchaseCurrentBooks(choice-1,noOfBooks , userName);
					   }
					  
					   else {
						   showError("Stocks are Low you can't by "+noOfBooks+" books\n");
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
				
			} catch (InputMismatchException ime) {
				showError("Enter a Valid Input");
				scanner.nextLine();
				continue;
			}

		} while (true);

	}

	private int getNumberOfBooks() {
		int noOfBooks;
		 while(true) {
			 System.out.println(ANSI_GREEN+"-----------------------"
						+ "--------------------------------------+\n"+ANSI_RESET
			 +ANSI_CYAN+"Enter Number of Books you Want : \n"+ANSI_RESET+
			 ANSI_GREEN+"-----------------------"
						+ "--------------------------------------+"+ANSI_RESET);
			 try {
				 noOfBooks = scanner.nextInt();
				 scanner.nextLine();
				 if(noOfBooks <= 0) {
					 showError("Enter a Number Greater Than Input : ");
					 continue;
				 }
				 return noOfBooks;
			 }
			 catch(InputMismatchException ime) {
				    showError("Enter a Valid Input : ");
				    scanner.nextLine();
				    continue;
			 }
		 }
		
	}

	private void showError(String error) {
		
		System.out.println(ANSI_BG_RED+ANSI_WHITE+ANSI_BOLD+error+ANSI_RESET);

	}

	public double initiatePayment(double money) {
		
		double choice;
		
		do {
			System.out.println(ANSI_GREEN+"+--------------------------"
					+ "----------------------------------+"+ANSI_RESET+"\n\n"+
					ANSI_PURPLE+"you need to pay : "+money+" Rs\n\n"
									+ "Enter Amount to Pay"+ANSI_RESET);
			System.out.println(ANSI_GREEN+"+--------------------------"
					+ "----------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextDouble();
				scanner.nextLine();
				if(choice < money) {
					showError("The amount you given is not enough to buy these books !!!");
				}
				else {
					return choice;
				}
				
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.nextLine();
				continue;
			}
			
		}
		while(true);
		
	}

	public void SuccessStatus(String success) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
				+"\n   "+ANSI_ITALIC+ANSI_BG_PURPLE+success+
				ANSI_RESET+ANSI_GREEN
				+"\n+---------------------------------------------------+\n\n"+ANSI_RESET);
		
	}
}
