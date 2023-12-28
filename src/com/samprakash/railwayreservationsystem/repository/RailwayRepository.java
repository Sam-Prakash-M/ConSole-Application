package com.samprakash.railwayreservationsystem.repository;

import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.dto.Passengers;

public class RailwayRepository {
       
	private static final RailwayRepository SINGLE_INSTANCE = new RailwayRepository();
	private static JSONObject jsonRetreiver ;
	private Stack <Passengers> traverse = new Stack <>();
	private static Set <Passengers> passengerDetails;
	

	public static Set<Passengers> getPassengerDetails() {
		return passengerDetails;
	}

	public static void setPassengerDetails(Set<Passengers> passengerDetails) {
		RailwayRepository.passengerDetails = passengerDetails;
	}
	public Stack<Passengers> getTraverse() {
		return traverse;
	}

	public static JSONObject getJsonRetreiver() {
		return jsonRetreiver;
	}
	
	public static void setJsonRetreiver(JSONObject jsonRetreiver) {
		RailwayRepository.jsonRetreiver = jsonRetreiver;
	}

	private RailwayRepository() {
		//avoids the creation of object;
	}
	
	public static  RailwayRepository getInstance() {
		
		return SINGLE_INSTANCE;
	}

	
}
