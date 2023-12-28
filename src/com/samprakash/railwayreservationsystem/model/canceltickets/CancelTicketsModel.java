package com.samprakash.railwayreservationsystem.model.canceltickets;

import java.util.Set;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.repository.RailwayRepository;

public class CancelTicketsModel {

	public JSONObject gettingJSONObject() {
		
		return RailwayRepository.getJsonRetreiver();
	}

	public Set getPassengerStorage() {
		
		return RailwayRepository.getPassengerDetails();
	}
 
}
