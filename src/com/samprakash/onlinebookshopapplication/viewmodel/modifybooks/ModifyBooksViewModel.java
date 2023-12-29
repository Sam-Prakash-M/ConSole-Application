package com.samprakash.onlinebookshopapplication.viewmodel.modifybooks;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.view.modifybooks.ModifyBooksView;

public class ModifyBooksViewModel {
	JSONObject jsonObject;
	ModifyBooksView modifyBooksView;
	JSONArray allBooks;
	//private JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
	
	//private JSONObject allBooks = (JSONObject) jsonObject.get("books");
	public ModifyBooksViewModel(ModifyBooksView modifyBooksView) {
		this.modifyBooksView = modifyBooksView;
	}

	public void modifyBooksInShop() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToAddStockCount(allBooks);
		
	}

	public void modifyTheCurrentBookStock(JSONObject currBook) {
		
		modifyBooksView.showStocks(currBook);
		int newCount = modifyBooksView.getnewBookCount();
		FileWriter fileWriter;
		if((long)currBook.get("stock") == newCount) {
			modifyBooksView.showError("Current Stock count and new Stock Count is Same");
			return;
		}
		currBook.put("stock", newCount);
			try {
				fileWriter = new FileWriter("src/com/samprakash/onlinebookshopapplication/Online Book Store/App's Data.json");
				fileWriter.write(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver().toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			modifyBooksView.showSuccess("Stock Count Changed SuccessFully to "+newCount);
		
	}

	public void modifyBooksPrice() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		modifyBooksView.showAllBooksToChangePrice(allBooks);
		
	}

	public void modifyTheCurrentBookPrice(JSONObject currBook) {
		modifyBooksView.showPrice(currBook);
		long newPrice = modifyBooksView.getNewPrice();
		if((long)currBook.get("price") == newPrice) {
			modifyBooksView.showError("Current Price and new Price is Same");
			return;
		}
		FileWriter fileWriter;
		currBook.put("price", newPrice);
			try {
				fileWriter = new FileWriter("src/com/samprakash/onlinebookshopapplication/Online Book Store/App's Data.json");
				fileWriter.write(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver().toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			modifyBooksView.showSuccess("Price Changed SuccessFully to "+newPrice);
		
	}

	
 
}
