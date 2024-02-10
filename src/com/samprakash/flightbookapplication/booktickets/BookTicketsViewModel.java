package com.samprakash.flightbookapplication.booktickets;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Passengers;
import com.samprakash.flightbookapplication.repository.FlightRepository;
import com.samprakash.flightbookapplication.util.FileWriting;

public class BookTicketsViewModel {

	private BookTicketsView bookTicketsView;

	public BookTicketsViewModel(BookTicketsView bookTicketsView) {
		this.bookTicketsView = bookTicketsView;
	}

	public boolean stationsAvailability(String date, String fromStation, String toStation) {

		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		int minimumOneTime = 0;
		if (jsonObject.containsKey(date)) {
			JSONObject flightDetails = (JSONObject) jsonObject.get(date);
			Set flights = flightDetails.keySet();

			for (Object flight : flights) {

				JSONObject currFlight = (JSONObject) flightDetails.get(String.valueOf(flight));

				if (isCorrectStations(currFlight, fromStation, toStation)) {
					bookTicketsView.showFlightDetails(String.valueOf(flight),currFlight);
					minimumOneTime++;
				}
			}
		} else {
			bookTicketsView.showStatus("There is No Flights Available For the Given Date");
			return false;
		}
		if (minimumOneTime == 0) {
			bookTicketsView.showStatus("There is No Flights Available from " + fromStation + " to " + toStation);
			return false;
		}
		return true;

	}

	public boolean isCorrectStations(JSONObject currFlight, String fromStation, String toStation) {
		
		JSONArray flightRoutes = (JSONArray) currFlight.get("FlightRoutes");
		int fromPos = 1, toPos = 0;
		boolean from = false , to = false;
		for (int i = 0; i < flightRoutes.size(); i++) {

			if (((String) flightRoutes.get(i)).equals(fromStation)) {
				fromPos = i;
				from = true;
			}
			if (((String) flightRoutes.get(i)).equals(toStation)) {
				toPos = i;
				to = true;
			}
		}
		
		return from && to && fromPos < toPos;
	}

	public boolean isCorrectFlightNumber(String flightNo, String date) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(date);
		
		return allFlights.containsKey(flightNo);
	}

	public boolean isTicketsAvailable(String flightNo,int noPassengers,String date) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(date);
		JSONObject currFlight = (JSONObject) allFlights.get(flightNo);
		return  Integer.valueOf(String.valueOf(currFlight.get("TotalSeats"))) >= noPassengers;
	}

	public void addPassengerDetails(Passengers passengers) {
		
		FlightRepository.getInstance().passengers.add(passengers);
		
		
	}

	@SuppressWarnings("unchecked")
	public void changeAvailabilityInFile(String date, String flightNo, int noPassengers) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(date);
		JSONObject currFlight = (JSONObject) allFlights.get(flightNo);
		
		currFlight.put("TotalSeats", Integer.valueOf(String.valueOf(currFlight.get("TotalSeats"))) - noPassengers);
		FileWriting.writeFile(jsonObject);
		FileWriting.readFile(new JSONObject());
		
	}

}
