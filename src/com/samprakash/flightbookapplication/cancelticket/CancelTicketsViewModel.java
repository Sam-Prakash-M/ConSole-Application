package com.samprakash.flightbookapplication.cancelticket;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Passengers;
import com.samprakash.flightbookapplication.dto.Tickets;
import com.samprakash.flightbookapplication.repository.FlightRepository;
import com.samprakash.flightbookapplication.util.FileWriting;

public class CancelTicketsViewModel {

	private CancelTicketsView cancelTicketsView;

	public CancelTicketsViewModel(CancelTicketsView cancelTicketsView) {

		this.cancelTicketsView = cancelTicketsView;
	}

	public void cancelCurrentTicket(long pnrNo) {
		List<Passengers> passengerDetails = FlightRepository.getInstance().passengers;

		if (passengerDetails.isEmpty()) {
			cancelTicketsView.showStatus("Currently there is No Passengers Available : ");
			return;
		}

		int minOneTime = 0;
		List<Passengers> cancellingPassengerDetails = new ArrayList<>();
		for (int i = 0; i < passengerDetails.size(); i++) {

			if (passengerDetails.get(i).getTickets().getPnrNo() == pnrNo) {
				minOneTime++;
				if (cancelTicketsView.isGoingtoDelete()) {
					cancellingPassengerDetails.add(passengerDetails.get(i));
					passengerDetails.get(i).getTickets().setTicketStatus("Cancel");
				}

			}
		}
		if (minOneTime == 0) {
			cancelTicketsView.showStatus("There is No Passengers matches with given PNR No : " + pnrNo);
			return;
		}
		for (Passengers passenger : cancellingPassengerDetails) {
			reAddtheTicketsInFile(passenger);

		}

	}

	@SuppressWarnings("unchecked")
	private void reAddtheTicketsInFile(Passengers passengers) {
		Tickets ticket = passengers.getTickets();
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(ticket.getDate());
		JSONObject currFlight = (JSONObject) allFlights.get(String.valueOf(ticket.getFlightNo()));

		currFlight.put("TotalSeats", Integer.valueOf(String.valueOf(currFlight.get("TotalSeats"))) + 1);
		FileWriting.writeFile(jsonObject);
		FileWriting.readFile(new JSONObject());

	}

	

	public void changeTicketStatus(long pnrNo) {
		List<Passengers> passengerDetails = FlightRepository.getInstance().passengers;

		if (passengerDetails.isEmpty()) {
			cancelTicketsView.showStatus("Currently there is No Passengers Available : ");
			return;
		}
		int minOneTime = 0;
		List<Passengers> PassengerAddedStatus = new ArrayList<>();
		List<Passengers> PassengerCancelledStatus = new ArrayList<>();
		for (int i = 0; i < passengerDetails.size(); i++) {

			if (passengerDetails.get(i).getTickets().getPnrNo() == pnrNo) {
				minOneTime++;
				String status = cancelTicketsView.gettingNewStatus();
				if (!status.equals(passengerDetails.get(i).getTickets().getTicketStatus())) {
					if(status.equals("CNF")) {
						
						if(isPossibleToAddPassengers(passengerDetails.get(i))) {
							PassengerAddedStatus.add(passengerDetails.get(i));
						}
						else {
							cancelTicketsView.showStatus("Can't Make CNF Because there is  No Available Tickets");
							return;
						}
						
					}
					else {
						PassengerCancelledStatus.add(passengerDetails.get(i));
					}
					passengerDetails.get(i).getTickets().setTicketStatus(status);
				}
				else {
					cancelTicketsView.showStatus("Current Status and New Status is Same : ");
					return;
				}

			}
		}
		if (minOneTime == 0) {
			cancelTicketsView.showStatus("There is No Passengers matches with given PNR No : " + pnrNo);
			return;
		}
		for (Passengers passenger : PassengerAddedStatus) {
			reAddtheTicketsInFile(passenger);

		}
		for (Passengers passenger : PassengerCancelledStatus) {
			removeTicketsInFile(passenger);

		}

	}

	private boolean isPossibleToAddPassengers(Passengers passengers) {
		Tickets ticket = passengers.getTickets();
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(ticket.getDate());
		JSONObject currFlight = (JSONObject) allFlights.get(String.valueOf(ticket.getFlightNo()));

		return !String.valueOf(currFlight.get("TotalSeats")).equals("0");
	}

	@SuppressWarnings("unchecked")
	private void removeTicketsInFile(Passengers passenger) {
		Tickets ticket = passenger.getTickets();
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(ticket.getDate());
		JSONObject currFlight = (JSONObject) allFlights.get(String.valueOf(ticket.getFlightNo()));

		currFlight.put("TotalSeats", Integer.valueOf(String.valueOf(currFlight.get("TotalSeats"))) - 1);
		FileWriting.writeFile(jsonObject);
		FileWriting.readFile(new JSONObject());
		
	}

}
