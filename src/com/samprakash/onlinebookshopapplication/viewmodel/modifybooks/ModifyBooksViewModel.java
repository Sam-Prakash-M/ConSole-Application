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

	public void modifyBooksInShop(String userName) {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToModifyStockCount(allBooks,userName);
		
	}

	public void modifyTheCurrentBookStock(JSONObject currBook , String userName) {
		
		modifyBooksView.showStocks(currBook);
		int newCount = modifyBooksView.getnewBookCount();
		if(String.valueOf(currBook.get("stock")).equals(newCount+"")) {
			modifyBooksView.showError("Current Stock count and new Stock Count is Same");
			return;
		}
		addAdminStatsOfStocksInFile(currBook , userName , newCount);
		currBook.put("stock", newCount);
		writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
	   modifyBooksView.showSuccess("Stock Count Changed SuccessFully to "+newCount);
		
	}
	
	public void modifyBooksPrice(String userName) {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToChangePrice(allBooks , userName);
		
	}

	public void modifyTheCurrentBookPrice(JSONObject currBook , String userName) {
		modifyBooksView.showPrice(currBook);
		int newPrice = modifyBooksView.getNewPrice();
		if(String.valueOf(currBook.get("price")).equals(newPrice+"")) {
			modifyBooksView.showError("Current Price and new Price is Same");
			return;
		}
		
		addAdminStatsOfPriceInFile(currBook,userName,newPrice);
		currBook.put("price", newPrice);
		writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
		modifyBooksView.showSuccess("Price Changed SuccessFully to "+newPrice);
		
	}

	public void showHistory(String userName) {
		JSONArray adminArray = (JSONArray) OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver().get("Admins");
		JSONObject adminStats = (JSONObject) OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver().get("Admins");
		findFullHistory(adminArray,adminStats,userName);
		
		
	}

	private void findFullHistory(JSONArray adminArray, JSONObject adminStats , String userName) {
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
				
				modifyBooksView.prinTheCurrentUserHistory((JSONObject)currUserHistory.get(i));
				
			}
			
		}
		else {
			modifyBooksView.showError("There is no history For the Current User !!!");
		}
		
		
	}

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
					break;
				}
			}
			
		writeAdminStatsOfDeleteBookInFile(userName , deletedBook);
		writeMainFile(jsonObject);
	}

	

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
