package com.samprakash.onlinebookshopapplication.viewmodel.modifybooks;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.util.FileHandling;
import com.samprakash.onlinebookshopapplication.view.modifybooks.ModifyBooksView;

public class ModifyBooksViewModel extends FileHandling {
	
	private ModifyBooksView modifyBooksView;

	public ModifyBooksViewModel(ModifyBooksView modifyBooksView) {
		this.modifyBooksView = modifyBooksView;
	}
         
	/*
	 * this method get JSONArray Object of books in App's Data.json and Current
	 * UserName to another method
	 */
	public void modifyBooksStocks(String userName) {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToModifyStockCount(allBooks,userName);
		
	}
        
	/* its OverRide the current Book stock Count to User given Stock Counts 
	 * if its Same count it won't change 
	 * */
	
	public boolean modifyTheCurrentBookStock(JSONObject currBook , String userName) {
		
		modifyBooksView.showStocks(currBook);
		int newCount = modifyBooksView.getnewBookCount();
		if(String.valueOf(currBook.get("stock")).equals(newCount+"")) {
			modifyBooksView.showError("Current Stock count and new Stock Count is Same");
			return false;
		}
		addAdminStatsOfStocksInFile(currBook , userName , newCount);
		currBook.put("stock", newCount);
		writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
	   modifyBooksView.showSuccess("Stock Count Changed SuccessFully to "+newCount);
		return true;
	}
    
	/*
	 * this method get JSONArray Object of books in App's Data.json and Current
	 * UserName to another method
	 */
	public void modifyBooksPrice(String userName) {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToChangePrice(allBooks , userName);
		
	}
       
	/* its OverRide the current Book Price  to User given Price 
	 * If it same price it won't change 
	 * */
	public boolean modifyTheCurrentBookPrice(JSONObject currBook , String userName) {
		modifyBooksView.showPrice(currBook);
		int newPrice = modifyBooksView.getNewPrice();
		if(String.valueOf(currBook.get("price")).equals(newPrice+"")) {
			modifyBooksView.showError("Current Price and new Price is Same");
			return false;
		}
		
		addAdminStatsOfPriceInFile(currBook,userName,newPrice);
		currBook.put("price", newPrice);
		writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
		modifyBooksView.showSuccess("Price Changed SuccessFully to "+newPrice);
		return true;
	}   
	
	/*
	 * this method get jsonArray object of admins in User and Admin's Personal
	 * Details.json and get jsonObject of admins in User and Admin Usage stats.json
	 * file and given to findFullHistory method.
	 */

	public void showHistoryOfAdmin(String userName) {
		JSONArray adminArray = (JSONArray) OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver().get("Admins");
		JSONObject adminStats = (JSONObject) OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver().get("Admins");
		findFullHistoryOfAdmin(adminArray,adminStats,userName);
		
		
	}   
	/*
	 * if adminArray is empty then its shows an error or its finds the Current
	 * userId for mapping into User and Admin Usage stats.json file then it find the
	 * current user has any history then it given to view or return error message
	 */

	private void findFullHistoryOfAdmin(JSONArray adminArray, JSONObject adminStats , String userName) {
		if (adminArray.isEmpty()) {
			modifyBooksView.showError("There is no history Was Saved as of Now!!!");
			return;
		}
		long userID = 0;
		for (int i = 0; i < adminArray.size(); i++) {
			JSONObject currUser = (JSONObject) adminArray.get(i);
			if (((String) currUser.get("UserName")).equals(userName)) {
				userID = (long) currUser.get("ID");
				break;
			}
		}
		String id = String.valueOf(userID);
		if(adminStats.containsKey(id)) {
			
			JSONArray currUserHistory = (JSONArray) adminStats.get(id);
			
			for(int i = 0 ; i < currUserHistory.size() ; i++) {
				
				modifyBooksView.prinTheCurrentHistoryOfAdmin((JSONObject)currUserHistory.get(i));
				
			}
			
		}
		else {
			modifyBooksView.showError("There is no history For the Current User !!!");
		}
		
		
	}

	/*
	 * this method get input of new books from view class and added in the App's
	 * Data.json file and writes the stats in User and Admin Usage stats.json
	 */	
	public void addNewBook(String userName) {
		List newBookDetails = modifyBooksView.getInputFromAdmin();
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONObject newBook = new JSONObject();
		if(jsonObject.containsKey("books")) {
			JSONArray allBooks = (JSONArray) jsonObject.get("books");
			
			newBook.put("author", newBookDetails.get(0));
			newBook.put("price", newBookDetails.get(1));
			newBook.put("genre", newBookDetails.get(2));
			newBook.put("title", newBookDetails.get(3));
			newBook.put("stock", newBookDetails.get(4));
			allBooks.add(newBook);
			
		}
		else {
			JSONArray allBooks = new JSONArray();
			newBook.put("author", newBookDetails.get(0));
			newBook.put("price", newBookDetails.get(1));
			newBook.put("genre", newBookDetails.get(2));
			newBook.put("title", newBookDetails.get(3));
			newBook.put("stock", newBookDetails.get(4));
			allBooks.add(newBook);
			jsonObject.put("books", allBooks);
		}
		writeAdminStatsOfNewBookInFile(userName , newBook);
		writeMainFile(jsonObject);
		
	}

	
	/*
	 * this method get bookname to delete and writes the stats in User and Admin
	 * Usage stats.json 
	 */

	public void deleteABook(String userName) {
		String bookName = modifyBooksView.getInputBookNameForDelete();
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONObject deletedBook = new JSONObject();
		
			JSONArray allBooks = (JSONArray) jsonObject.get("books");
			  
			for(int i = 0 ; i < allBooks.size() ; i++)
			{
				JSONObject currBook = (JSONObject) allBooks.get(i);
				if(((String)currBook.get("title")).equals(bookName)) {
					deletedBook = currBook;
					allBooks.remove(currBook);
					modifyBooksView.showSuccess("Succefully Book Was Deleted");
					break;
				}
			}
			
		writeAdminStatsOfDeleteBookInFile(userName , deletedBook);
		writeMainFile(jsonObject);
	}

	
	/*
	 * this method find the book is already present or not this is prevent to add
	 * the duplicate book
	 */

	public boolean isBookPresence(String bookTitle) {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		if(allBooks.isEmpty()) {
			return false;
		}
		for(int i = 0 ; i < allBooks.size() ; i++) {
			JSONObject currBook = (JSONObject) allBooks.get(i);
			if( ((String)currBook.get("title")).equals(bookTitle)) {
				return true;
			}
			
		}
		return false;
	}
 
}
