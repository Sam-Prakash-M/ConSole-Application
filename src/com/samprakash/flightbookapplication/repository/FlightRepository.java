package com.samprakash.flightbookapplication.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Flights;
import com.samprakash.flightbookapplication.dto.Passengers;

public class FlightRepository {
		
	private static final FlightRepository SINGLE_OBJECT = new FlightRepository();
	
	public JSONObject jsonRetreiver;
	public List <Flights> flights = new ArrayList<>();
	public List <Passengers> passengers = new ArrayList<>();
	
	private FlightRepository() {
		
	}
	
	public static FlightRepository getInstance() {
		
		return SINGLE_OBJECT;
	}
	
}
