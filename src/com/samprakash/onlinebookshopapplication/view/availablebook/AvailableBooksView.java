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
				scanner.nextLine();
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
				scanner.nextLine();
				continue;
			}
			
		}
		while(choice != 4);
		
	}
	private void searchByGenre() {
		
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
		+ANSI_RESET+ANSI_PURPLE
				+"Enter a Genre Name : "+ANSI_RESET);
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByGenre(key);
		
		
	}
	private void searchByAuthor() {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+ANSI_RESET+ANSI_PURPLE
						+"Enter a Author Name : "+ANSI_RESET);
		
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByAuthor(key);
		
		
		
	}
	private void searchByTitle() {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"
				+ANSI_RESET+ANSI_PURPLE
						+"Enter a title Name : "+ANSI_RESET);
		
		String key = scanner.nextLine();
		availableBooksViewModel.findRelatedBookByTitle(key);
		
		
		
	}
	public void showError(String error) {
		
		System.out.println(ANSI_BG_RED+ANSI_WHITE+ANSI_BOLD+error+ANSI_RESET);
		
	}
	public void showTheMatchingTitle(String title) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET+ANSI_PURPLE
				+"Book Title is : "+title+ANSI_RESET+ANSI_GREEN+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
	}
	public void showTheMatchingAuthor(String author) {
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET+ANSI_PURPLE
				+"Book Author is : "+author+ANSI_RESET+ANSI_GREEN+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
	}
	public void showTheMatchingGenre(String genre) {
		
		System.out.println(ANSI_GREEN+"+---------------------------------------------------+\n"+ANSI_RESET+ANSI_PURPLE
				+"Book Genre is : "+genre+ANSI_RESET+ANSI_GREEN+"\n"+"+---------------------------------------------------+"+ANSI_RESET);
		
		
		
	}
}
