package com.samprakash.flightbookapplication.base;

import java.util.List;
import java.util.Scanner;

import com.samprakash.flightbookapplication.booktickets.BookTicketsView;
import com.samprakash.flightbookapplication.cancelticket.CancelTicketsView;
import com.samprakash.flightbookapplication.dto.Passengers;
import com.samprakash.flightbookapplication.flightroutesview.FlightRoutesView;

public class BaseView {

	private BaseViewModel baseViewModel;
	private FlightRoutesView flightRoutesView;
	private Scanner scanner;
	private BookTicketsView bookTicketsView;
	private CancelTicketsView cancelTicketsView;

	public BaseView() {
		baseViewModel = new BaseViewModel(this);
		flightRoutesView = new FlightRoutesView();
		scanner = new Scanner(System.in);
		bookTicketsView = new BookTicketsView();
		cancelTicketsView = new CancelTicketsView();
	}

	public void init() {

		baseViewModel.fileLoader();//loadFiles
		int choice;
		do {

			System.out.println("+----------------------------------------------+");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 1, ")Create Flight Routes", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 2, ")List Flight Routes", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 3, ")Search Flights", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 4, ")Book Tickets", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 5, ")List Out Number Of Tickets Booked", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 6, ")Get PNR Status", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 7, ")Cancel Tickets", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 8, ")Search Passenger By ID", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 9, ")Change Ticket Status Of Passenger", "|");
			System.out.printf("%-2s %2d%-40s %2s\n", "|", 10, ")Exit", "|");
			System.out.println("+----------------------------------------------+");

			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) { 

			case 1 -> {
				flightRoutesView.createFlightRoutes();

			}
			case 2 -> {
				flightRoutesView.showFlightRoutes();

			}
			case 3 -> {
				System.out.println("Enter a Date(YYYY-MM-DD) To Search : ");
				searchFlights(scanner.nextLine());

			}
			case 4 -> {
				bookTicketsView.bookTickets();
			}
			case 5 -> {
				bookTicketsView.showBookedTickets();
			}
			case 6 -> {
				bookTicketsView.getPNRStatus();
			}
			case 7 -> {
				cancelTicketsView.cancelTicket();
			}
			case 8 -> {
				baseViewModel.searchByPassengerID();
			}
			case 9 -> {
				cancelTicketsView.changeStatus();
			}

			}

		} while (choice != 10);

	}

	private void searchFlights(String date) {

		int choice;
		do {
			System.out.println("+--------------------------------------------+");
			System.out.printf("%-2s %-40s %2s\n", "|", "1)Search By From Station", "|");
			System.out.printf("%-2s %-40s %2s\n", "|", "2)Search By To Station", "|");
			System.out.printf("%-2s %-40s %2s\n", "|", "3)Search By Date", "|");
			System.out.printf("%-2s %-40s %2s\n", "|", "4)Exit", "|");
			System.out.println("+--------------------------------------------+");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {

			case 1 -> {
				flightRoutesView.searchByFromStation(date);

			}
			case 2 -> {
				flightRoutesView.searchByToStation(date);

			}
			case 3 -> {
				flightRoutesView.searchByDate(date);

			}
			case 4 -> {

			}
			default -> {
				System.out.println("Enter a Valid Input from 1 to 4");
			}

			}
		} while (choice != 4);

	}

	public int getPassengerID() {
		System.out.println("Enter a Passenger ID : ");
		int passengerID = scanner.nextInt();
		scanner.nextLine();
		return passengerID;
	}

	public void showPassengersDetails(int id, List<Passengers> passengers) {

		for (Passengers passenger : passengers) {
			
			if(passenger.getId() == id) {
				System.out.println("+--------------------------------------------------------------------------------+");
				System.out.printf("| %30sPassenger Details%-30s %s|\n", "", "", "");
				System.out.println("+--------------------------------------------------------------------------------+");
				System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger's Name ", passenger.getName(), "|");
				System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger's Age ",passenger.getAge() , "|");
				System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Passenger's Gender ",passenger.getGender(), "|");
				System.out.printf("%-2s %-25s: %-50d %s\n", "|", "Passenger's ID ",id, "|");
				System.out.printf("%-2s %-25s: %-50s %s\n", "|", "Flight Number ",passenger.getTickets().getFlightNo(), "|");
				
				System.out.println("+--------------------------------------------------------------------------------+");
			}
			

		}

	}

}
