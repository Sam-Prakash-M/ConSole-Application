package com.samprakash.flightbookapplication.flightroutesview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Flights;
import com.samprakash.flightbookapplication.repository.FlightRepository;
import com.samprakash.flightbookapplication.util.FileWriting;

public class FlightRoutesViewModel {

	private FlightRoutesView flightRoutesView;

	public FlightRoutesViewModel(FlightRoutesView flightRoutesView) {

		this.flightRoutesView = flightRoutesView;
	}

	@SuppressWarnings("unchecked")
	public void addFlights(Flights flight) {

		FlightRepository.getInstance().flights.add(flight);
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;

		String currDate = flight.getDate();

		JSONObject newFlights = new JSONObject();
		newFlights.put("FlightName", flight.getFlightName());
		newFlights.put("FlightDepartureTime", flight.getDepartureTime());
		newFlights.put("FlightArrivalTime", flight.getArrivalTime());

		List<String> flightRoutes = flight.getFlightRoutes();
		JSONArray allRoutes = new JSONArray();
		for (String routes : flightRoutes) {
			allRoutes.add(routes);
		}
		newFlights.put("FlightRoutes", allRoutes);
		newFlights.put("TotalSeats", flight.getSeats());
		newFlights.put("Fare", flight.getFare());
		if (jsonObject.containsKey(currDate)) {
			System.out.println("Already there");
			JSONObject todayRoutes = (JSONObject) jsonObject.get(currDate);
			todayRoutes.put(flight.getFlightNo(), newFlights);
		} else {
			System.out.println("Not Already there");
			JSONObject todayRoutes = new JSONObject();
			todayRoutes.put(flight.getFlightNo(), newFlights);
			jsonObject.put(currDate, todayRoutes);
		}
		FileWriting.writeFile(jsonObject);
		FileWriting.readFile(new JSONObject());

	}

	public void showFlightRoutes() {

		try {
			if (Files.size(Path.of("src/com/samprakash/flightbookapplication/FlightDetails.json")) == 0) {

				flightRoutesView.showStatus("Currently There are No Flights");
				return;

			} else {
				if (Optional.ofNullable(FlightRepository.getInstance().jsonRetreiver).get().size() == 0) {
					flightRoutesView.showStatus("There is No Flight Routes there");
				} else {

					showRoutes(FlightRepository.getInstance().jsonRetreiver);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void showRoutes(JSONObject jsonRetreiver) {
		Set dateKeys = jsonRetreiver.keySet();
		for (Object date : dateKeys) {
			JSONObject jsonObject = (JSONObject) jsonRetreiver.get((String) date);
			Set flightKeys = jsonObject.keySet();
			for (Object flight : flightKeys) {
				JSONObject flightDetails = (JSONObject) jsonObject.get(flight.toString());
				if(!flightDetails.containsKey("FlightRoutes"))
				{
					continue;
				}
				JSONArray flightRoutes = (JSONArray) flightDetails.get("FlightRoutes");
				flightRoutesView.showFlightRoutes(flightRoutes);

			}
		}
	}

	public void searchByFromStation(String fromStation, String date) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		int minimimOneTime = 0;
		if (jsonObject.containsKey(date)) {

			JSONObject flightDates = (JSONObject) jsonObject.get(date);
			Set flights = flightDates.keySet();
			for (Object flight : flights) {

				JSONObject currFlight = (JSONObject) flightDates.get(String.valueOf(flight));
				if (flightRoutesView.showFromStationDetails(fromStation, String.valueOf(flight), currFlight)) {
					minimimOneTime++;
				}

			}
		} else {
			flightRoutesView.showStatus("In The Date There is No Flights Available : ");

		}
		if (minimimOneTime == 0) {
			flightRoutesView.showStatus("No Flights Available For The Stations : ");
		}
	}

	public void searchByToStation(String toStation, String date) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		int minimumOneTime = 0;
		if (jsonObject.containsKey(date)) {
			JSONObject flightDates = (JSONObject) jsonObject.get(date);
			Set flights = flightDates.keySet();
			for (Object flight : flights) {
				JSONObject currFlight = (JSONObject) flightDates.get(String.valueOf(flight));
				if(!currFlight.containsKey("FlightRoutes"))
				{
					continue;
				}
				JSONArray flightRoutes = (JSONArray) currFlight.get("FlightRoutes");
				if( flightRoutes.size()>0 && flightRoutes.contains(toStation) && !flightRoutes.get(0).toString().equals(toStation));
				{
					flightRoutesView.showStatus(String.valueOf(flight),currFlight);
					minimumOneTime++;
				}
				

			}
		} else {
			flightRoutesView.showStatus("In The Date There is No Flights Available : ");

		}
		if (minimumOneTime == 0) {
			flightRoutesView.showStatus("No Flights Available For The Stations : ");
		}

	}

	public void searchByDate(String date) {
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;

		if (jsonObject.containsKey(date)) {

			JSONObject flightDates = (JSONObject) jsonObject.get(date);
			Set flights = flightDates.keySet();
			for (Object flight : flights) {

				JSONObject currFlight = (JSONObject) flightDates.get(String.valueOf(flight));
				flightRoutesView.showStatus(String.valueOf(flight), currFlight);

			}
		} else {
			flightRoutesView.showStatus("In The Date There is No Flights Available : ");

		}

	}

	public boolean isCorrectToStation(String toStation, String flightNo, JSONObject currFlight) {

		JSONArray flightRoutes = (JSONArray) currFlight.get("FlightRoutes");
		return flightRoutes.contains(toStation) && flightRoutes.get(0).toString().equals(toStation);
		
	}

	public boolean isCorrectFromStation(String fromStation, String flightNo, JSONObject currFlight) {
		JSONArray flightRoutes = (JSONArray) currFlight.get("FlightRoutes");

		for (int i = 0; i < flightRoutes.size() - 1; i++) {

			if (((String) (flightRoutes.get(i))).equals(fromStation)) {
				return true;
			}
		}
		return false;
	}

}
