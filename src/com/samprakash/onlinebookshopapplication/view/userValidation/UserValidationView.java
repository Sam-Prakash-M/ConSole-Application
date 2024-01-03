package com.samprakash.onlinebookshopapplication.view.userValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.colors.Colors;
import com.samprakash.onlinebookshopapplication.dto.Persons;
import com.samprakash.onlinebookshopapplication.viewmodel.uservalidation.UserValidationViewModel;

public class UserValidationView extends Colors {

	private UserValidationViewModel userValidationViewModel;
	private Scanner scanner = new Scanner(System.in);

	public UserValidationView() {
		userValidationViewModel = new UserValidationViewModel(this);
	}

	public String showUserUI() {
		int choice = 0;
		String userName = null;
		outer: do {
			System.out.println(ANSI_GREEN + "+---------------------------------------------------+" + "\n|   "
					+ ANSI_ITALIC + ANSI_YELLOW + "  1) Sign up" + ANSI_RESET + ANSI_GREEN
					+ "                                    |\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC
					+ ANSI_YELLOW + "     2) Sign in" + ANSI_RESET + ANSI_GREEN + "                                   "
					+ " |\n" + "|" + ANSI_ITALIC + ANSI_YELLOW + "     3) delete Account" + ANSI_RESET + ANSI_GREEN
					+ "                             " + "|\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC
					+ ANSI_YELLOW + "     4) Recover Deleted Account" + ANSI_RESET + ANSI_GREEN
					+ "                    |\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC + ANSI_YELLOW
					+ "     5) Exit                                       " + ANSI_RESET + ANSI_GREEN + "|\n"
					+ "+---------------------------------------------------+" + ANSI_RESET);
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1: {
					userName = userValidationViewModel.createUserNameAndPasswordForUser(choice);
					break outer;
				}
				case 2: {
					userName = userValidationViewModel.userVerify(choice);
					break outer;
				}
				case 3: {
					userValidationViewModel.deleteUserAccount(choice);
					break outer;
				}
				case 4: {
					userValidationViewModel.recoverDeletedUserAccount(choice);
					break outer;
				}
				case 5: {
					break;
				}
				default: {
					showError("Enter number between 1 to 3 !!!");
				}
				}
			} catch (InputMismatchException ime) {
				showError("Enter a Valid Input !!!");
				scanner.nextLine();
				continue;
			}
		} while (choice != 5);

		return userName;

	}

	public void showError(String error) {

		System.out.println(ANSI_BG_RED+ANSI_WHITE+ANSI_BOLD+error+ANSI_RESET);
	}
  
	/* This Method gets userName and Password of Persons
	 * Also this Method Validate the UserName And password Which Matches Regex
	 * pattern Only When the userName and Password is Matches the
	 * userName and password is assigned in Persons Object
	 * */
	public Persons getUserNameAndPassWord() {
		String userName, passWord;
		boolean isCorrect = false;
		do {
			showError("\n"
					+ "Rules For UserName  : -->\n"
					+ "Starts with either a lowercase or uppercase alphabet. Can contain any of the\r\n"
					+ " characters '@', '#', '-', or '_'. Can include digits. The length must be\r\n"
					+ " between 3 and 15 characters.\n\n"
					+ "Rules For PassWord : -->\n"
					+ "Starts with the specified conditions (at least one digit, one lowercase\r\n"
					+ "letter, one uppercase letter, and one of the specified special characters).\r\n"
					+ "Does not contain any whitespace characters. Has a total length between 8 and\r\n"
					+ "15 characters.");
			System.out.println(ANSI_CYAN + "Enter a UserName --> " + ANSI_RESET);
			userName = scanner.nextLine();
			System.out.println(ANSI_CYAN + "Enter a PassWord --> " + ANSI_RESET);
			passWord = scanner.nextLine();
			isCorrect = userValidationViewModel.UserNameAndPasswordValidation(userName, passWord);
			
		} while (!isCorrect);
		//showSuccess("PassWord Verifies Successfully ");
		return new Persons (userName,passWord);
	}

	public void showSuccess(String success) {
		System.out.println(ANSI_GREEN + "+---------------------------------------------------+" + "\n   " + ANSI_ITALIC
				+ ANSI_BG_PURPLE + success + ANSI_RESET + ANSI_GREEN
				+ "\n+---------------------------------------------------+\n\n" + ANSI_RESET);
	}
     
	//Its Shows The Admin View 
	public String showAdminUI() {
		int choice = 0;
		String userName = null;
		Outer: do {
			System.out.println(ANSI_GREEN + "+---------------------------------------------------+" + "\n|   "
					+ ANSI_ITALIC + ANSI_YELLOW + "  1) Sign up" + ANSI_RESET + ANSI_GREEN
					+ "                                    |\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC
					+ ANSI_YELLOW + "     2) Sign in" + ANSI_RESET + ANSI_GREEN + "                                   "
					+ " |\n" + "|" + ANSI_ITALIC + ANSI_YELLOW + "     3) delete Account" + ANSI_RESET + ANSI_GREEN
					+ "                            " + " |\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC
					+ ANSI_YELLOW + "     4) Recover Deleted Account" + ANSI_RESET + ANSI_GREEN
					+ "                    |\n" + ANSI_RESET + ANSI_GREEN + "|" + ANSI_ITALIC + ANSI_YELLOW
					+ "     5) LogOut                                     " + ANSI_RESET + ANSI_GREEN + "|\n"
					+ "+---------------------------------------------------+" + ANSI_RESET);
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1: {
					userName = userValidationViewModel.createUserNameAndPasswordForAdmin(choice);
					break Outer;
				}
				case 2: {
					userName = userValidationViewModel.adminVerify(choice);
					break Outer;
				}
				case 3: {
					userValidationViewModel.deteteAdminAccount(choice);
					break Outer;
				}
				case 4: {
					userValidationViewModel.recoverDeletedAdminAccount(choice);
					break Outer;
				}
				case 5: {
					break Outer;
				}
				default: {
					showError("Enter number between 1 to 5 !!!");
				}
				}
			} catch (InputMismatchException ime) {
				showError("Enter a Valid Input !!!");
				scanner.nextLine();
				continue;
			}
		} while (choice != 5);
		return userName;

	}

	public void showHistoryOfUser(String userName) {
		JSONArray userJSONArray = userValidationViewModel.getUsersPersonalJsonArray();
		JSONObject userAndAdminPeronsalDetails = userValidationViewModel.getUserStatsJsonObject();
		userValidationViewModel.findAllHistoriesOfCurrentUser(userJSONArray,userAndAdminPeronsalDetails,userName);

		

	}

	public void prinTheCurrentUserHistory(JSONObject currDateHistory) {
		System.out.println(ANSI_GREEN+"-----------------------"
				+ "--------------------------------------+"+ANSI_RESET);
	
			System.out.println(ANSI_PURPLE);
			System.out.printf("%3s  -> Purchase Time : %s"," "
					,(String) currDateHistory.get("PurchasedTime") + "\n\n");
			System.out.printf("%3s  -> Book Title : %s"," "
					,(String) currDateHistory.get("BooksName") + "\n\n");
			System.out.printf("%3s  -> Author Name : %s"," "
					,(String) currDateHistory.get("BooksAuthor") + "\n\n");
			System.out.printf("%3s  -> Genre Name : %s"," "
					,(String) currDateHistory.get("BooksGenre")+ "\n\n");
			System.out.printf("%3s  -> Book Price : %s"," "
					,(String.valueOf(currDateHistory.get("BooksPrice")+"")) + "\n\n");
			System.out.printf("%3s  -> Amount Payed : %s"," "
					,(String.valueOf((Double) currDateHistory.get("TotalPayment")+"")) + "\n\n");
			System.out.printf("%3s  -> No of Books Bought : %s"," "
					,(String.valueOf(currDateHistory.get("NoOfBooksBought")+"")) + "\n\n"+ANSI_RESET);
		
		System.out.println("\n"+ANSI_GREEN+"+--------------------------"
				+ "----------------------------------+"+ANSI_RESET);
		
	}

}
