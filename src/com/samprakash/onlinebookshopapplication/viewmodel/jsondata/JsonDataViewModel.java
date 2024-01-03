package com.samprakash.onlinebookshopapplication.viewmodel.jsondata;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.util.FileHandling;
import com.samprakash.onlinebookshopapplication.view.jsondata.JsonDataview;

public class JsonDataViewModel extends FileHandling {
	private  JsonDataview jsonDataView ;
	public JsonDataViewModel(JsonDataview jsonDataview) {
		this.jsonDataView = jsonDataview;
	}
	
	/*
	 * this method just given access to three files 
	 * App's Data.json
	 * User and Admin's Personal Details.json 
	 * User and Admin Usage stats.json
	 */
	public void assignJsonobject() {
	     
		JSONObject jsonObjectMain = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONObject jsonPersonalDetails = OnlineBookPurchaseRepository.getInstance().getJsonPersonalDetailsRetreiver();
		JSONObject jsonUserUsageDetails = OnlineBookPurchaseRepository.getInstance().getJsonUserStatsRetreiver();
		
		JSONParser jsonParser = new JSONParser();
		
		try {
			jsonObjectMain = (JSONObject) jsonParser.parse(new FileReader(PATH_MAIN));
			jsonPersonalDetails = (JSONObject) jsonParser.parse(new FileReader(PATH_PERSONAL_DETAILS));
			jsonUserUsageDetails = (JSONObject) jsonParser.parse(new FileReader(PATH_USER_STATS_DETAILS));
		}
		catch(Exception e) {
			jsonDataView.showError("The File is not readable");
			e.printStackTrace();
			return;
		}
		
		OnlineBookPurchaseRepository.getInstance().setJsonRetreiver(jsonObjectMain);
		OnlineBookPurchaseRepository.getInstance().setJsonPersonalDetailsRetreiver(jsonPersonalDetails);
		OnlineBookPurchaseRepository.getInstance().setJsonUserStatsRetreiver(jsonUserUsageDetails);
		//System.out.println(jsonObjectMain+"\n"+jsonPersonalDetails+"\n"+jsonUserUsageDetails);
		
	}
	
	
	
	

}
