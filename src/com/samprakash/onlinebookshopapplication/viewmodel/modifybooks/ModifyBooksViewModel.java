package com.samprakash.onlinebookshopapplication.viewmodel.modifybooks;

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
		modifyBooksView.showAllBooksToAddStockCount(allBooks,userName);
		
	}

	public void modifyTheCurrentBookStock(JSONObject currBook , String userName) {
		
		modifyBooksView.showStocks(currBook);
		int newCount = modifyBooksView.getnewBookCount();
		if((long)currBook.get("stock") == newCount) {
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
		long newPrice = modifyBooksView.getNewPrice();
		if((long)currBook.get("price") == newPrice) {
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
 
}
