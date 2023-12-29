package com.samprakash.onlinebookshopapplication.view.availablebook;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONArray;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.viewmodel.availablebooks.AvailableBooksViewModel;

public class AvailableBooksView extends Colors {

	private AvailableBooksViewModel availableBooksViewModel;
	private Scanner scanner = new Scanner(System.in);
	 public AvailableBooksView() {
		 availableBooksViewModel = new AvailableBooksViewModel(this);
	 }
	public void searchBook() {
	   
		int choice = 0;
		do {
			System.out.println(ANSI_GREEN+"+---------------------------------------------------+"
					+"\n|   "+"  "+ANSI_ITALIC+ANSI_BG_BLUE+ANSI_YELLOW+"1) Search by Title "+ANSI_RESET+ANSI_GREEN+"                           |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+"     "+ANSI_ITALIC+ANSI_BG_BLUE+ANSI_YELLOW+"2) Search by Author"+ANSI_RESET+ANSI_GREEN+"                           |\n"
					+ANSI_RESET+ANSI_GREEN+"|"+"     "+ANSI_ITALIC+ANSI_BG_BLUE+ANSI_YELLOW+"3) Search by Genre"+ANSI_RESET+ANSI_GREEN+"                            |\n"
					+"|     "+ANSI_ITALIC+ANSI_BG_BLUE+ANSI_YELLOW+"4) Exit"+ANSI_RESET+ANSI_GREEN+"                                       |\n"
					+"+---------------------------------------------------+"+ANSI_RESET);
			try {
				choice = scanner.nextInt();
				switch(choice) {
				case 1 : {
					
					searchByTitle();
					break;
				}
				case 2 : {
					searchByAuthor();
					break;
				}
				case 3 : {
					searchByGenre();
					break;
				}
				case 4 : {
					break;
				}
				default : {
					showError("Enter a number Between 1 to 4  : ");
				}
				}
			}
			catch(InputMismatchException ime) {
				showError("Enter a Valid Input : ");
				scanner.next();
				continue;
			}
			
		}
		while(choice != 4);
		
	}
	private void searchByGenre() {
		scanner.nextLine();
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Enter a Genre Name : ");
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByGenre(key);
		
		
	}
	private void searchByAuthor() {
		scanner.nextLine();
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Enter a Author Name : ");
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByAuthor(key);
		
		
		
	}
	private void searchByTitle() {
		scanner.nextLine();
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Enter a title Name : ");
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByTitle(key);
		
		
		
	}
	public void showError(String error) {
		
		System.err.println(error);
		
	}
	public void showTheMatchingTitle(String title) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Book Title is : "+title+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
	}
	public void showTheMatchingAuthor(String author) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Book Author is : "+author+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
		
	}
	public void showTheMatchingGenre(String genre) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+"Book Genre is : "+genre+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
		
		
	}
}
