package com.samprakash.onlinebookshopapplication.repository;

import org.json.simple.JSONObject;

public class OnlineBookPurchaseRepository {

	private static final OnlineBookPurchaseRepository REPOSITORY = new OnlineBookPurchaseRepository();
	private  JSONObject jsonMainFileRetreiver , jsonPersonalDetailsRetreiver , jsonUserStatsRetreiver;
	
	private OnlineBookPurchaseRepository() {
		
	}
	
	public JSONObject getJsonPersonalDetailsRetreiver() {
		return jsonPersonalDetailsRetreiver;
	}

	public void setJsonPersonalDetailsRetreiver(JSONObject jsonPersonalDetailsRetreiver) {
		this.jsonPersonalDetailsRetreiver = jsonPersonalDetailsRetreiver;
	}

	public JSONObject getJsonUserStatsRetreiver() {
		return jsonUserStatsRetreiver;
	}

	public void setJsonUserStatsRetreiver(JSONObject jsonUserStatsRetreiver) {
		this.jsonUserStatsRetreiver = jsonUserStatsRetreiver;
	}

	public  JSONObject getJsonRetreiver() {
		return jsonMainFileRetreiver;
	}


	public void setJsonRetreiver(JSONObject jsonMainFileRetreiver) {
		this.jsonMainFileRetreiver = jsonMainFileRetreiver;
	}


	public static OnlineBookPurchaseRepository getInstance() {
		return REPOSITORY;
	}
	
}
