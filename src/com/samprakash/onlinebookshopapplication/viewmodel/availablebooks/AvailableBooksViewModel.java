package com.samprakash.onlinebookshopapplication.viewmodel.availablebooks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.view.availablebook.AvailableBooksView;

public class AvailableBooksViewModel {
	private AvailableBooksView availableBooksView;
	public AvailableBooksViewModel(AvailableBooksView availableBooksView) {
		this.availableBooksView = availableBooksView;
	}
	   
	/*
	 * this method returns the jsonArray Object of books in App's Data.json File
	 */	public JSONArray getJsonArray() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		return allBooks;
		
	}  

	/*
	 * this method finds the books presented in App's Data.json File by given user
	 * keyword as Title and shows the books to view if not Present any book then shows 
	 * the Error no Books Available
	 */
	public void findRelatedBookByTitle(String key) {
		JSONArray allBooks  = getJsonArray();
		boolean isAtleastoneBookPresent = false;
		for(int i = 0 ; i < allBooks.size() ; i++) {
			JSONObject eachBook = (JSONObject) allBooks.get(i);
			boolean isHas = ((String)eachBook.get("title")).toLowerCase().contains(key.toLowerCase());
			if(isHas) {
				isAtleastoneBookPresent = true;
				availableBooksView.showTheMatchingTitle((String)eachBook.get("title"));
			}
		}
		if(!isAtleastoneBookPresent) {
			availableBooksView.showError("No Books Available");
		}
		
	}

	/*
	 * this method finds the books presented in App's Data.json File by given user
	 * keyword as Author and shows the books to view if not Present any book then shows 
	 * the Error no Books Available
	 */
	public void findRelatedBookByAuthor(String key) {
		
           JSONArray allBooks  = getJsonArray();
           boolean isAtleastoneBookPresent = false;
		for(int i = 0 ; i < allBooks.size() ; i++) {
			JSONObject eachBook = (JSONObject) allBooks.get(i);
			boolean isHas = ((String)eachBook.get("author")).toLowerCase().contains(key.toLowerCase());
			if(isHas) {
				isAtleastoneBookPresent = true;
				availableBooksView.showTheMatchingAuthor((String)eachBook.get("author"));
			}
		}
		if(!isAtleastoneBookPresent) {
			availableBooksView.showError("No Books Available");
		}
		
	}
	/*
	 * this method finds the books presented in App's Data.json File by given user
	 * keyword as Genre and shows the books to view if not Present any book then shows 
	 * the Error no Books Available
	 */
	public void findRelatedBookByGenre(String key) {
		 JSONArray allBooks  = getJsonArray();
		 boolean isAtleastoneBookPresent = false;
			for(int i = 0 ; i < allBooks.size() ; i++) {
				JSONObject eachBook = (JSONObject) allBooks.get(i);
				boolean isHas = ((String)eachBook.get("genre")).toLowerCase().contains(key.toLowerCase());
				if(isHas) {
					isAtleastoneBookPresent = true;
					availableBooksView.showTheMatchingGenre((String)eachBook.get("genre"));
				}
			}
			if(!isAtleastoneBookPresent) {
				availableBooksView.showError("No Books Available");
			}
		
	}
	

}
