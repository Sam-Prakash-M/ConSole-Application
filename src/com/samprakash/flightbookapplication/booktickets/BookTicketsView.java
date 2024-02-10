package com.samprakash.flightbookapplication.booktickets;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Passengers;
import com.samprakash.flightbookapplication.dto.Tickets;
import com.samprakash.flightbookapplication.repository.FlightRepository;

public class BookTicketsView {

	private BookTicketsViewModel bookTicketsViewModel;
	private Scanner scanner;

	public BookTicketsView() {

		this.bookTicketsViewModel = new BookTicketsViewModel(this);
		scanner = new Scanner(System.in);
	}

	public void bookTickets() {

		System.out.println("+----------------------------------------------+");
		System.out.println("Enter a date to Book Tickets : ");
		String date = scanner.nextLine();
		System.out.println("Enter a From Station : ");
		String fromStation = scanner.nextLine();
		System.out.println("Enter a To Station : ");
		String toStation = scanner.nextLine();
		if (bookTicketsViewModel.stationsAvailability(date, fromStation, toStation)) {
			System.out.println("Enter a Train Number : ");
			int flightNo = scanner.nextInt();
			scanner.nextLine();
			if (bookTicketsViewModel.isCorrectFlightNumber(String.valueOf(flightNo), date)) {

				System.out.println("Enter a Number Of Passengers : ");
				int noPassengers = scanner.nextInt();
				scanner.nextLine();
				if (bookTicketsViewModel.isTicketsAvailable(String.valueOf(flightNo), noPassengers, date)) {

					long pnrNo = new Random().nextLong(50000000, 100000000);
					for (int i = 1; i <= noPassengers; i++) {
						System.out.println("+----------------------------------------------+");
						System.out.println("Enter a Passenger Name : " + i);
						String name = scanner.nextLine();
						System.out.println("Enter a Passenger Age : " + i);
						int age = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Enter a Passenger Gender : " + i);
						String gender = scanner.nextLine();
						bookTicketsViewModel.addPassengerDetails(new Passengers(age, name, gender,
								new Tickets(date, pnrNo, "CNF", flightNo, fromStation, toStation, noPassengers)));

					}
					bookTicketsViewModel.changeAvailabilityInFile(date, String.valueOf(flightNo), noPassengers);
					showStatus("Ticket Booked SuccessFully");
				} else {
					showStatus("Tickets Counts Are low You can't Book " + noPassengers + " Tickets");
				}
			} else {
				showStatus("The Flight number You Entered is Wrong");
			}
		}

	}

	public void showStatus(String status) {

		System.out.println(status);

	}

	public void showFlightDetails(String flightNo, JSONObject currFlight) {

		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("| %30sFlight Details%-34s %s|\n","","","");
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "FlightNumber ", flightNo, "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Name ", currFlight.get("FlightName"), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Arrival Time ", currFlight.get("FlightArrivalTime"),
				"|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Departure Time ",
				currFlight.get("FlightDepartureTime"), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Routes ",
				String.valueOf(currFlight.get("FlightRoutes")), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Available Seats ", currFlight.get("TotalSeats"), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Fare ", currFlight.get("Fare"), "|");
		System.out.println("+--------------------------------------------------------------------------------+");

	}

	public void showBookedTickets() {
		List<Passengers> passengersDetails = FlightRepository.getInstance().passengers;
		
		if(passengersDetails.isEmpty()) {
			showStatus("Currently Tickets Not Booked : ");
			return;
		}
		for (Passengers passenger : passengersDetails) {
				
			if(passenger.getTickets().getTicketStatus().equals("CNF")) {
				showFlightDetails(passenger.getTickets());
				showPassengerDetails(passenger);
			}
		
		}

	}

	private void showPassengerDetails(Passengers passenger) {
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("| %27sPassenger Details%-34s %s|\n","","","");
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger Name ", passenger.getName(), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger Age ", passenger.getAge(), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger Gender ", passenger.getGender(), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger ID ", passenger.getId(), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Ticket Status ", passenger.getTickets().getTicketStatus(),"|");
		System.out.println("+--------------------------------------------------------------------------------+");

	}

	private void showFlightDetails(Tickets ticket) {
		int flightNo = ticket.getFlightNo(), noOfPassengers = ticket.getNoOfPassengers();
		long pnrNo = ticket.getPnrNo();
		String date = ticket.getDate(), fromStation = ticket.getFromStation(), toStation = ticket.getToStation();
		JSONObject jsonObject = FlightRepository.getInstance().jsonRetreiver;
		JSONObject allFlights = (JSONObject) jsonObject.get(date);
		JSONObject currFlight = (JSONObject) allFlights.get(String.valueOf(flightNo));
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("| %30sFlight Details%-34s %s|\n","","","");
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Number ", flightNo, "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Name ", currFlight.get("FlightName"), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Arrival Time ", currFlight.get("FlightArrivalTime"),
				"|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Departure Time ",
				currFlight.get("FlightDepartureTime"), "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight From Station ", fromStation, "|");
		System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight To Station ", toStation, "|");
		System.out.printf("%-2s %-25s: %-50d %s\n", "|", "PNR No ", pnrNo, "|");
		System.out.printf("%-2s %-25s: %-50d %s\n", "|", "Total Fare ",
				Long.valueOf(String.valueOf(currFlight.get("Fare"))) * noOfPassengers, "|");
		System.out.println("+--------------------------------------------------------------------------------+");

	}

	public void getPNRStatus() {

		System.out.println("Enter Your PNR Number : ");
		long pnrNumber = scanner.nextLong();
		scanner.nextLine();
		List<Passengers> passengersDetails = FlightRepository.getInstance().passengers;

		for (Passengers passenger : passengersDetails) {

			if (passenger.getTickets().getPnrNo() == pnrNumber) {
				showFlightDetails(passenger.getTickets());
				showPassengerDetails(passenger);
			}
		}

	}

}
