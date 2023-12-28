package com.samprakash.onlinebookshopapplication.repository;

import org.json.simple.JSONObject;

public class OnlineBookPurchaseRepository {

	private static final OnlineBookPurchaseRepository REPOSITORY = new OnlineBookPurchaseRepository();
	private  JSONObject jsonRetreiver ;
	
	private OnlineBookPurchaseRepository() {
		
	}
	
	public  JSONObject getJsonRetreiver() {
		return jsonRetreiver;
	}


	public void setJsonRetreiver(JSONObject jsonRetreiver) {
		this.jsonRetreiver = jsonRetreiver;
	}


	public static OnlineBookPurchaseRepository getInstance() {
		return REPOSITORY;
	}
	
}
