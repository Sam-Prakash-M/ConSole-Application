package com.samprakash.railwayreservationsystem.model.booktickets;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.repository.RailwayRepository;
import com.samprakash.railwayreservationsystem.viewmodel.booktickets.BookTicketsViewModel;



public class BookTicketsModel {
	private static BookTicketsViewModel bookviewModel;
	static {
		bookviewModel = new BookTicketsViewModel();
		
	}
	public void addPassengers(Passengers currPassenger , char choice)  {
		boolean isAvailable = bookviewModel.isAvailableBirthPreference(currPassenger ,choice);
			if(isAvailable) {
				RailwayRepository.getPassengerDetails().add(currPassenger);
				bookviewModel.showStatus("Passenger Detailes Added Succefully");
				
			}
	}
	public String getChoice(char birthPreference) {
		
		return RailwayRepository.getInstance().getTraverse().peek().getChoice();
		
	}
	public JSONObject getJSONObject() {
		
		return  RailwayRepository.getJsonRetreiver();
	}
	public String getUserTrainChoice() {
		
		return RailwayRepository.getInstance().getTraverse().peek().getChoice();
	}
	
	

}
