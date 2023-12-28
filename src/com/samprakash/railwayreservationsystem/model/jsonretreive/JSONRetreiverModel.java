package com.samprakash.railwayreservationsystem.model.jsonretreive;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.repository.RailwayRepository;

public class JSONRetreiverModel {
	
	public JSONObject getJSONObject() {
		
		return RailwayRepository.getJsonRetreiver();
		
	}

	public void setJSONObject(JSONObject jsonObject) {
		
		RailwayRepository.setJsonRetreiver(jsonObject);
	}

	public Passengers addChoice(int trainNo) {
		  
		String choice;
		if(trainNo == 9) {
			choice =  "TN10";
		}
		else {
			 choice = "TN0" + (trainNo+1);
		}
	 return new Passengers(choice);
	}

	public String getCurrentTrainDetails() {
		   
		 return RailwayRepository.getInstance().getTraverse().peek().getChoice();
		   
	}

	public void ticketBooking() {
		
		
	}

}
