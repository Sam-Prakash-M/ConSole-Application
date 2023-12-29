package com.samprakash.onlinebookshopapplication.viewmodel.purchasebook;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.view.purchasebook.PurchaseBookView;

public class PurchaseBookViewModel {
		
	private PurchaseBookView purchaseBookView;
	public PurchaseBookViewModel(PurchaseBookView purchaseBookView) {
		this.purchaseBookView = purchaseBookView;
	}
	public boolean bookHasNoOfBooks(int choice ,int noOfBooks) {
		JSONArray allBooks = getJsonArray();
		       
		return (long)((JSONObject)allBooks.get(choice)).get("stock") >= noOfBooks ;
		
		 
	}
	public JSONArray getJsonArray() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		return allBooks;
		
	}
	public void purchaseCurrentBooks(int choice, int noOfBooks) {
		JSONArray allBooks = getJsonArray();
		double price = (long) ((JSONObject)allBooks.get(choice)).get("price");
		double receivedMoney = purchaseBookView.initiatePayment(price*noOfBooks);
		purchaseBookView.SuccessStatus("Payment Successfully Completed");
		purchaseBookView.SuccessStatus("SuccessFully all the Books are Purchased");
	    double 	balance  = receivedMoney - (price*noOfBooks);
		  if(balance > 0.0) {
			  purchaseBookView.SuccessStatus("Take your Balance : "+balance);
		  }
		  JSONObject currBook = (JSONObject) allBooks.get(choice);
		  FileWriter fileWriter;
			currBook.put("stock", (long)currBook.get("stock") - noOfBooks );
				try {
					fileWriter = new FileWriter("src/com/samprakash/onlinebookshopapplication/Online Book Store/App's Data.json");
					fileWriter.write(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver().toJSONString());
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
		 
		
	}

}
