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
	public JSONArray getJsonArray() {
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONArray allBooks = (JSONArray) jsonObject.get("books");
		return allBooks;
		
	}
	public void findRelatedBookByTitle(String key) {
		JSONArray allBooks  = getJsonArray();
		
		for(int i = 0 ; i < allBooks.size() ; i++) {
			JSONObject eachBook = (JSONObject) allBooks.get(i);
			boolean isHas = ((String)eachBook.get("title")).toLowerCase().contains(key.toLowerCase());
			if(isHas) {
				availableBooksView.showTheMatchingTitle((String)eachBook.get("title"));
			}
		}
		
	}
	public void findRelatedBookByAuthor(String key) {
		
           JSONArray allBooks  = getJsonArray();
		
		for(int i = 0 ; i < allBooks.size() ; i++) {
			JSONObject eachBook = (JSONObject) allBooks.get(i);
			boolean isHas = ((String)eachBook.get("author")).toLowerCase().contains(key.toLowerCase());
			if(isHas) {
				availableBooksView.showTheMatchingAuthor((String)eachBook.get("author"));
			}
		}
		
	}
	public void findRelatedBookByGenre(String key) {
		 JSONArray allBooks  = getJsonArray();
			
			for(int i = 0 ; i < allBooks.size() ; i++) {
				JSONObject eachBook = (JSONObject) allBooks.get(i);
				boolean isHas = ((String)eachBook.get("genre")).toLowerCase().contains(key.toLowerCase());
				if(isHas) {
					availableBooksView.showTheMatchingGenre((String)eachBook.get("genre"));
				}
			}
		
	}
	

}
