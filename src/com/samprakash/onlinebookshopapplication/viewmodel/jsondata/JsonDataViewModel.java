package com.samprakash.onlinebookshopapplication.viewmodel.jsondata;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.samprakash.onlinebookshopapplication.dto.Admin;
import com.samprakash.onlinebookshopapplication.repository.OnlineBookPurchaseRepository;
import com.samprakash.onlinebookshopapplication.view.jsondata.JsonDataview;

public class JsonDataViewModel {
	private  JsonDataview jsonDataView ;
	public JsonDataViewModel(JsonDataview jsonDataview) {
		this.jsonDataView = jsonDataview;
	}
	public void assignJsonobject() {
	     
		JSONObject jsonObject = OnlineBookPurchaseRepository.getInstance().getJsonRetreiver();
		JSONParser jsonParser = new JSONParser();
		String path;
		try {
			path = new File(".").getCanonicalPath()
					+"/src/com/samprakash/onlinebookshopapplication/Online Book Store/App's Data.json";
		}
		catch(IOException ioe) {
			jsonDataView.showError("File can't read");
			ioe.printStackTrace();
			return;
		}
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
		}
		catch(Exception e) {
			jsonDataView.showError("The File is not readable");
			e.printStackTrace();
			return;
		}
		
		OnlineBookPurchaseRepository.getInstance().setJsonRetreiver(jsonObject);
		
	}
	public void adminVerify() {
		jsonDataView.getAdminCredential();
		
		
	}
	public boolean UserNameAndPaswordValidation(String userName, String passWord) {
		 
		boolean isCorrect = Admin.getUserName().equals(userName) && Admin.getPassword().equals(passWord);
		if(!isCorrect) {
			jsonDataView.showError("UserName or Password is Wrong");
			
		}
	
		return  isCorrect;
	}
	

}
