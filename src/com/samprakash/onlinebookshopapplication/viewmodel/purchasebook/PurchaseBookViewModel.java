package com.samprakash.onlinebookshopapplication.viewmodel.purchasebook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.util.FileHandling;
import com.samprakash.onlinebookshopapplication.view.purchasebook.PurchaseBookView;

public class PurchaseBookViewModel extends FileHandling{
		
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
	public void purchaseCurrentBooks(int choice, int noOfBooks , String userName) {
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
		  
		    addUserStatsInFile(currBook,noOfBooks,receivedMoney ,userName);
		    
			currBook.put("stock", (long)currBook.get("stock") - noOfBooks );
			
			writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
				
	}
	

}
