package com.samprakash.onlinebookshopapplication.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.dto.Persons;
import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;

public class FileHandling {
	protected static final String PATH_MAIN = "src/com/samprakash/onlinebookshopapplication/Online Book Store/App's Data.json";
	protected static final String PATH_PERSONAL_DETAILS = "src/com/samprakash/onlinebookshopapplication/Online Book Store/User and Admin's Personal Details.json";
	protected static final String PATH_USER_STATS_DETAILS = "src/com/samprakash/onlinebookshopapplication/Online Book Store/User and Admin Usage stats.json";
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm:ss a");
     
	/*
	 * this Method writes the the APP's Data.json File
	 * 
	 */	
	protected void writeMainFile(JSONObject jsonObject) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(PATH_MAIN);
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
   
	/*
	 * this method Writes the User and Admin's Personal Details.json File
	 */	
	protected void writeinUserAndAdminPersonalFile(JSONObject jsonUserAndAdmin) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(PATH_PERSONAL_DETAILS);
			fileWriter.write(jsonUserAndAdmin.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
 
	/* this method Writes the User and Admin Usage stats.json File */
	
	private void writeinUserAndAdminStatsFile(JSONObject jsonUserandAdminStats) {

		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(PATH_USER_STATS_DETAILS);
			fileWriter.write(jsonUserandAdminStats.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
     
	/*
	 * this method returns the User and Admin's Personal Details.json File
	 * JSONObject
	 */
	private JSONObject getJSONObjectOfPersonalDetails() {
		return OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver();
	}
	/*
	 * this method returns the User and Admin Usage stats.json  File
	 * JSONObject
	 */
	private JSONObject getJSONObjectOfUsageStatsDetails() {
		return OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver();
	}
     
	/*
  	 * this method is added the what are all the changes made admin in the books
  	 * Stocks and and the stats in User and Admin Usage stats.json file
  	 */
	protected void addAdminStatsOfStocksInFile(JSONObject currBook, String userName, int newCount) {

		long currCount = Long.valueOf(currBook.get("stock")+"");
		String Bookname = (String) currBook.get("title");
		JSONObject jsonMainPersonalDetails = getJSONObjectOfPersonalDetails();

		JSONArray eachUser = (JSONArray) jsonMainPersonalDetails.get("Admins");

		JSONObject jsonUserandAdminStats = getJSONObjectOfUsageStatsDetails();

		JSONObject adminStats = (JSONObject) jsonUserandAdminStats.get("Admins");
		long currUserId = 0;
		for (int i = 0; i < eachUser.size(); i++) {
			JSONObject currUser = (JSONObject) eachUser.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				currUserId = (long) currUser.get("ID");
				break;
			}
		}
		JSONObject details = new JSONObject();

		details.put("newCount", newCount);
		details.put("oldCount", currCount);
		details.put("modifiedTime", dateTimeFormatter.format(LocalDateTime.now()));
		details.put("BookName", Bookname);

		if (adminStats.containsKey(String.valueOf(currUserId))) {
			JSONArray currAdmin = (JSONArray) adminStats.get(String.valueOf(currUserId));
			currAdmin.add(details);

		} else {
			JSONArray currAdmin = new JSONArray();
			currAdmin.add(details);
			JSONObject newAdmin = new JSONObject();
			newAdmin.put(String.valueOf(currUserId), currAdmin);
			adminStats.putAll(newAdmin);

		}

		writeinUserAndAdminStatsFile(jsonUserandAdminStats);

	}  
	/*
  	 * this method is added the what are all the changes made admin in the books
  	 * Count and and the stats in User and Admin Usage stats.json file
  	 */
     protected void writeAdminStatsOfNewBookInFile(String userName , JSONObject newBookDetails) {
    	 JSONObject jsonMainPersonalDetails = getJSONObjectOfPersonalDetails();

 		JSONArray eachUser = (JSONArray) jsonMainPersonalDetails.get("Admins");

 		JSONObject jsonUserandAdminStats = getJSONObjectOfUsageStatsDetails();

 		JSONObject adminStats = (JSONObject) jsonUserandAdminStats.get("Admins");
 		long currUserId = 0;
 		// get the current Persons userId
 		for (int i = 0; i < eachUser.size(); i++) {
 			JSONObject currUser = (JSONObject) eachUser.get(i);
 			if (((String) currUser.get("UserName")).equals(userName)) {
 				currUserId = (long) currUser.get("ID");
 				break;
 			}
 		}
 		newBookDetails.put("BookAddedDate",dateTimeFormatter.format(LocalDateTime.now()));
 		if (adminStats.containsKey(String.valueOf(currUserId))) {
			JSONArray currAdmin = (JSONArray) adminStats.get(String.valueOf(currUserId));
			currAdmin.add(newBookDetails);

		} else {
			JSONArray currAdmin = new JSONArray();
			currAdmin.add(newBookDetails);
			JSONObject newAdmin = new JSONObject();
			newAdmin.put(String.valueOf(currUserId), currAdmin);
			adminStats.putAll(newAdmin);

		}
 		writeinUserAndAdminStatsFile(jsonUserandAdminStats);
		
	}
     /*
   	 * this method is added the what are all the changes made admin in the books
   	 * Count and and the stats in User and Admin Usage stats.json file
   	 */
     protected void writeAdminStatsOfDeleteBookInFile(String userName, JSONObject deletedBook) {
    	 JSONObject jsonMainPersonalDetails = getJSONObjectOfPersonalDetails();

  		JSONArray eachUser = (JSONArray) jsonMainPersonalDetails.get("Admins");

  		JSONObject jsonUserandAdminStats = getJSONObjectOfUsageStatsDetails();

  		JSONObject adminStats = (JSONObject) jsonUserandAdminStats.get("Admins");
  		
  		JSONObject newBookDetails = new JSONObject();
  	// get the current Persons userId
  		long currUserId = 0;
  		for (int i = 0; i < eachUser.size(); i++) {
  			JSONObject currUser = (JSONObject) eachUser.get(i);
  			if (((String) currUser.get("UserName")).equals(userName)) {
  				currUserId = (long) currUser.get("ID");
  				break;
  			}
  		}
  		newBookDetails.put("RemovedBookDate",dateTimeFormatter.format(LocalDateTime.now()));
  		newBookDetails.put("RemovedBookTitle",deletedBook.get("title"));
  		newBookDetails.put("RemovedBookAuthor",deletedBook.get("author"));
  		newBookDetails.put("RemovedBookGenre",deletedBook.get("genre"));
  		newBookDetails.put("RemovedBookPrice",deletedBook.get("price"));
  		newBookDetails.put("RemovedBookStocksCount",deletedBook.get("stock"));
  		
  		if (adminStats.containsKey(String.valueOf(currUserId))) {
 			JSONArray currAdmin = (JSONArray) adminStats.get(String.valueOf(currUserId));
 			currAdmin.add(newBookDetails);

 		} else {
 			JSONArray currAdmin = new JSONArray();
 			currAdmin.add(newBookDetails);
 			JSONObject newAdmin = new JSONObject();
 			newAdmin.put(String.valueOf(currUserId), currAdmin);
 			adminStats.putAll(newAdmin);

 		}
  		writeinUserAndAdminStatsFile(jsonUserandAdminStats);
 		
 	} 
     /*
  	 * this method is added the what are all the changes made admin in the books
  	 * price and and the stats in User and Admin Usage stats.json file
  	 */
     
	protected void addAdminStatsOfPriceInFile(JSONObject currBook, String userName, long newPrice) {

		long currPrice = Long.valueOf(currBook.get("price")+"");   
		String bookname = (String) currBook.get("title");
		JSONObject jsonMainPersonalDetails = getJSONObjectOfPersonalDetails();

		JSONArray eachUser = (JSONArray) jsonMainPersonalDetails.get("Admins");

		JSONObject jsonUserandAdminStats = getJSONObjectOfUsageStatsDetails();

		JSONObject adminStats = (JSONObject) jsonUserandAdminStats.get("Admins");
		long currUserId = 0;
		for (int i = 0; i < eachUser.size(); i++) {
			JSONObject currUser = (JSONObject) eachUser.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				currUserId = (long) currUser.get("ID");
				break;
			}
		}
		JSONObject details = new JSONObject();

		details.put("newPrice", newPrice);
		details.put("oldPrice", currPrice);
		details.put("modifiedTime", dateTimeFormatter.format(LocalDateTime.now()));
		details.put("BookName", bookname);

		if (adminStats.containsKey(String.valueOf(currUserId))) {
			JSONArray currAdmin = (JSONArray) adminStats.get(String.valueOf(currUserId));
			currAdmin.add(details);

		} else {
			JSONArray currAdmin = new JSONArray();
			currAdmin.add(details);
			JSONObject newAdmin = new JSONObject();
			newAdmin.put(String.valueOf(currUserId), currAdmin);
			adminStats.putAll(newAdmin);

		}

		writeinUserAndAdminStatsFile(jsonUserandAdminStats);

	}
	/*
  	 * this method is added  what are all the books buy by Current User
  	 * and add  the stats in User and Admin Usage stats.json file
  	 */
	protected void addUserStatsInFile(JSONObject currBook, long noOfBooks, double receivedMoney, String userName) {
		long bookPrice = Long.valueOf(currBook.get("price")+"");
		String bookName = (String) currBook.get("title");

		String bookAuthor = (String) currBook.get("author");
		String bookGenre = (String) currBook.get("genre");

		JSONObject jsonMainPersonalDetails = getJSONObjectOfPersonalDetails();

		JSONArray eachUser = (JSONArray) jsonMainPersonalDetails.get("Users");

		JSONObject jsonUserandAdminStats = getJSONObjectOfUsageStatsDetails();

		JSONObject userStats = (JSONObject) jsonUserandAdminStats.get("Users");
		long currUserId = 0;
		for (int i = 0; i < eachUser.size(); i++) {
			JSONObject currUser = (JSONObject) eachUser.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				currUserId = (long) currUser.get("ID");
				break;
			}
		}
		JSONObject details = new JSONObject();

		details.put("PurchasedTime", dateTimeFormatter.format(LocalDateTime.now()));
		details.put("TotalPayment", receivedMoney);
		details.put("NoOfBooksBought", noOfBooks);
		details.put("BooksPrice", bookPrice);
		details.put("BooksAuthor", bookAuthor);
		details.put("BooksGenre", bookGenre);
		details.put("BooksName", bookName);

		if (userStats.containsKey(String.valueOf(currUserId))) {
			JSONArray currAdmin = (JSONArray) userStats.get(String.valueOf(currUserId));
			currAdmin.add(details);

		} else {
			JSONArray currAdmin = new JSONArray();
			currAdmin.add(details);
			JSONObject newAdmin = new JSONObject();
			newAdmin.put(String.valueOf(currUserId), currAdmin);
			userStats.putAll(newAdmin);

		}

		writeinUserAndAdminStatsFile(jsonUserandAdminStats);

	}
    
	/* this method checks whether the user present in the database */
	protected boolean isUserAlreadyPresent(String userName, int choice) {
		JSONObject mainJsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachUser = (JSONArray) mainJsonObject.get("Users");

		if (eachUser.size() == 0) {
			
			return false;
		}
		for (int i = 0; i < eachUser.size(); i++) {
			JSONObject currUser = (JSONObject) eachUser.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				
				/*
				 * if active status is 0 User accout is deleted . but whenEver the user tries
				 * to recover account or sign up then this method return true or given false
				 * whenEver user tries  to delete  already deleted account or tries to sign in
				 * already deleted Account
				 */
				if (String.valueOf(currUser.get("ActiveStatus")).equals("0")) {
					if (choice == 4 || choice == 1) {
						return true;
					}
					return false;
				} else {
					/* wheEver User tries to Recover active account it returns False */
					if (choice == 4) {
						return false;
					}
					return true;
				}
			}
		}
		return false;

	}
        
	/* This Method checks whether admin is the Already presented or not */
	
	protected boolean isAdminAlreadyPresent(String userName, int choice) {
		JSONObject mainJsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachUser = (JSONArray) mainJsonObject.get("Admins");

		if (eachUser.size() == 0) {
			System.out.println("returns False : ");
			return false;
		}
		for (int i = 0; i < eachUser.size(); i++) {
			JSONObject currUser = (JSONObject) eachUser.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				
				/*
				 * if active status is 0 admin accout is deleted . but whenEver the admin tries
				 * to recover account or sign up then this method return true or given false
				 * whenEver user tries  to delete  already deleted account or tries to sign in
				 * already deleted Account
				 */
				if (String.valueOf(currUser.get("ActiveStatus")).equals("0")) {
					if (choice == 4 || choice == 1) {
						
						return true;
					}
					return false;
				} else {
					/* wheEver Admin tries to Recover active account it returns False */
					if (choice == 4) {
						return false;
					}
					return true;
				}

			}
		}
		return false;

	}

	/*
	 * write new user in User and Admin's Personal Details.json file
	 */
	protected void writeNewUserInFile(Persons userNameAndPassword) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachUser = (JSONArray) mainjsonObject.get("Users");
		JSONObject newUser = new JSONObject();
		long userIDCount = (long) mainjsonObject.get("UserID");
		newUser.put("ID", userIDCount + 1);
		newUser.put("UserName", userNameAndPassword.getUserName());
		newUser.put("PassWord", userNameAndPassword.getPassWord());
		newUser.put("JoinedDate", dateTimeFormatter.format(LocalDateTime.now()));
		newUser.put("ActiveStatus", 1);
		eachUser.add(newUser);
		mainjsonObject.put("UserID", userIDCount + 1);
		writeinUserAndAdminPersonalFile(mainjsonObject);
	}
     
	/*
	 * write new Admin in User and Admin's Personal Details.json file
	 */
	protected void writeNewAdminInFile(Persons userNameAndPassword) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachAdmin = (JSONArray) mainjsonObject.get("Admins");
		JSONObject newAdmin = new JSONObject();
		long adminIDCount = (long) mainjsonObject.get("AdminID");
		newAdmin.put("ID", adminIDCount + 1);
		newAdmin.put("UserName", userNameAndPassword.getUserName());
		newAdmin.put("PassWord", userNameAndPassword.getPassWord());
		newAdmin.put("JoinedDate", dateTimeFormatter.format(LocalDateTime.now()));
		newAdmin.put("ActiveStatus", 1);
		eachAdmin.add(newAdmin);
		mainjsonObject.put("AdminID", adminIDCount + 1);
		writeinUserAndAdminPersonalFile(mainjsonObject);
	}
	/*
	 * delete Admin in User and Admin's Personal Details.json file
	 */
	protected void deleteAdminInFile(String userName) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachAdmin = (JSONArray) mainjsonObject.get("Admins");

		for (int i = 0; i < eachAdmin.size(); i++) {
			JSONObject currAdmin = (JSONObject) eachAdmin.get(i);
			if (((String) currAdmin.get("UserName")).equals(userName)) {
				currAdmin.put("ActiveStatus", 0);
				currAdmin.put("LeftDate", dateTimeFormatter.format(LocalDateTime.now()));
				Set<String> everyKey = (Set) currAdmin.keySet();

				for (String object : everyKey) {
					// removes if rejoined date
					
					if (object.equals("reJoinedDate")) {
						System.out.println("Contains");
						currAdmin.remove(object);
						break;
					}
				}
				break;
			}
		}

		writeinUserAndAdminPersonalFile(mainjsonObject);
	}
	/*
	 * delete user in User and Admin's Personal Details.json file
	 */
	protected void deleteUserInFile(String userName) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachAdmin = (JSONArray) mainjsonObject.get("Users");

		for (int i = 0; i < eachAdmin.size(); i++) {
			JSONObject currAdmin = (JSONObject) eachAdmin.get(i);
			if (((String) currAdmin.get("UserName")).equals(userName)) {
				currAdmin.put("ActiveStatus", 0);
				currAdmin.put("LeftDate", dateTimeFormatter.format(LocalDateTime.now()));
				Set<String> everyKey = (Set) currAdmin.keySet();

				for (String object : everyKey) {
					// removes if rejoined date presents
					
					if (object.equals("reJoinedDate")) {
						System.out.println("Contains");
						currAdmin.remove(object);
						break;
					}
				}
				break;
			}
		}

		writeinUserAndAdminPersonalFile(mainjsonObject);
	}
	/*
	 * Recover user in User and Admin's Personal Details.json file
	 */
	protected void recoverUserInFile(String userName) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachAdmin = (JSONArray) mainjsonObject.get("Users");

		for (int i = 0; i < eachAdmin.size(); i++) {
			JSONObject currAdmin = (JSONObject) eachAdmin.get(i);
			if (((String) currAdmin.get("UserName")).equals(userName)) {
				currAdmin.put("ActiveStatus", 1);
				Set<String> everyKey = (Set) currAdmin.keySet();

				for (String object : everyKey) {
					// removes left date
					if (object.equals("LeftDate")) {
						System.out.println("Contains");
						currAdmin.remove(object);
						break;
					}
				}
				currAdmin.put("reJoinedDate", dateTimeFormatter.format(LocalDateTime.now()));
				break;
			}
		}

		writeinUserAndAdminPersonalFile(mainjsonObject);
	}
	/*
	 * Recover Admin in User and Admin's Personal Details.json file
	 */
	protected void recoverAdminInFile(String userName) {
		JSONObject mainjsonObject = getJSONObjectOfPersonalDetails();
		JSONArray eachAdmin = (JSONArray) mainjsonObject.get("Admins");

		for (int i = 0; i < eachAdmin.size(); i++) {
			JSONObject currAdmin = (JSONObject) eachAdmin.get(i);
			if (((String) currAdmin.get("UserName")).equals(userName)) {

				currAdmin.put("ActiveStatus", 1);
				Set<String> everyKey = (Set) currAdmin.keySet();

				for (String object : everyKey) {
					// removes left date
					if (object.equals("LeftDate")) {
						currAdmin.remove(object);
						break;
					}
				}
				currAdmin.put("reJoinedDate", dateTimeFormatter.format(LocalDateTime.now()));
				break;
			}
		}

		writeinUserAndAdminPersonalFile(mainjsonObject);
	}

}
