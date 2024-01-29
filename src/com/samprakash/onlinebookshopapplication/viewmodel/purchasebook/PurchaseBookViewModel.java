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
	 
	/*
	 * this method just check whether the user given books count is available or not
	 */
	public boolean bookHasNoOfBooks(int choice ,long noOfBooks) {
		
		JSONArray allBooks = getJsonArray();
		return Long.valueOf(((JSONObject)allBooks.get(choice)).get("stock")+"") >= noOfBooks ;
		
	}  

	/*
	 * this method just return the jsonArray object of books in App's Data.json File
	 */	
	public JSONArray getJsonArray() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		return allBooks;
		
	}

	/*
	 * this method used to purchase books and write the details in both User and
	 * Admin Usage stats.json App's Data.json
	 */
	public void purchaseCurrentBooks(int choice, long noOfBooks , String userName) {
		JSONArray allBooks = getJsonArray();
		double price = Long.valueOf(((JSONObject)allBooks.get(choice)).get("price")+"");
		double receivedMoney = purchaseBookView.initiatePayment(price*noOfBooks);
		purchaseBookView.SuccessStatus("Payment Successfully Completed");
		purchaseBookView.SuccessStatus("SuccessFully all the Books are Purchased");
	    double 	balance  = receivedMoney - (price*noOfBooks);
		  if(balance > 0.0) {
			  purchaseBookView.SuccessStatus("Take your Balance : "+balance);
		  }
		  JSONObject currBook = (JSONObject) allBooks.get(choice);
		  
		    addUserStatsInFile(currBook,noOfBooks,receivedMoney ,userName);
		    
			currBook.put("stock", Long.valueOf(currBook.get("stock")+"") - noOfBooks );
			
			writeMainFile(OnlineBookPurchaseRepository.getInstance().getJsonRetreiver());
				
	}
	

}
