package com.samprakash.flightbookapplication.flightroutesview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Flights;
import com.samprakash.flightbookapplication.repository.FlightRepository;

public class FlightRoutesView {

	private FlightRoutesViewModel flightRoutesViewModel;
	private Scanner scanner;

	public FlightRoutesView() {
		flightRoutesViewModel = new FlightRoutesViewModel(this);
		scanner =new Scanner(System.in);
	}

	public void createFlightRoutes() {

		System.out.println("Welcome to Indian RailWays.....");
		System.out.println("+--------------------------------------------+");
		System.out.println("Enter a Number of Schedules : ");

		int noOfSchedules = scanner.nextInt();
	
		for (int i = 1; i <= noOfSchedules; i++) {
			
			System.out.println("Enter a Date : ");
			String date = scanner.next();
			System.out.println("Enter a Flight Number : "+i);
			int flightNumber = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter a Flight Name : "+i);

			String flightName = scanner.nextLine();
			System.out.println("Enter a Departure Time of Flight  : "+i);

			String departureTime = scanner.nextLine();
			System.out.println("Enter a Arrival Time of Flight : "+i);

			String arrivalTime = scanner.nextLine();
			
			System.out.println("Enter a no of Flight Routes : "+i);
			
			int noOfFlightRoutes = scanner.nextInt();
			scanner.nextLine();
			 List <String> list = new ArrayList<>();
			for(int j = 1 ; j <= noOfFlightRoutes ;j++) {
				
				System.out.println("Enter a Route : "+j);
				list.add(scanner.nextLine());
			}
			
			System.out.println("Enter a Total No Of Seats of Flight : "+i);
			int totalNoOfSeats = scanner.nextInt();
			
			System.out.println("Fare of Flight Ticket of Flight : "+i);
			int fare = scanner.nextInt();
			flightRoutesViewModel.addFlights(new Flights(date,flightNumber, totalNoOfSeats, 
					fare, flightName, departureTime, arrivalTime, list));
			
			
		}
		
	}

	public void showFlightRoutes() {
		
		flightRoutesViewModel.showFlightRoutes();
		
	}

	public void showStatus(String result) {
		
		System.out.println(result);
		
	}

	public void searchByFromStation(String date) {
		
		System.out.println("Enter a From Station : ");
		flightRoutesViewModel.searchByFromStation(scanner.nextLine(),date);
		
		
	}

	public void searchByToStation(String date) {
		System.out.println("Enter a To Station : ");
		flightRoutesViewModel.searchByToStation(scanner.nextLine(),date);
	}

	public void searchByDate(String date) {
		flightRoutesViewModel.searchByDate(date);
		
	}

	public void showFlightRoutes(JSONArray routes) {
	         
		for(int i = 0 ; i < routes.size() ;i++) {
			System.out.print(routes.get(i)+" ");
		}
		System.out.println();
		
	}

	public boolean showFromStationDetails(String fromStation,String flightNo,JSONObject currFlight) {
		
		if(flightRoutesViewModel.isCorrectFromStation(fromStation,flightNo,currFlight)) {
			showStatus(flightNo,currFlight);
			
			return true;
		}
		
		return false;
		
	}

	public void showStatus(String flightNo, JSONObject currFlight) {
		System.out.println("+--------------------------------------------------------------------------------+");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","FlightNumber ",flightNo,"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Flight Name ",currFlight.get("FlightName"),"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Flight Arrival Time ",currFlight.get("FlightArrivalTime"),"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Flight Departure Time ",currFlight.get("FlightDepartureTime"),"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Flight Routes ",String.valueOf(currFlight.get("FlightRoutes")),"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Available Seats ",currFlight.get("TotalSeats"),"|");
		System.out.printf("%-2s %-25s: %-50s %s\n","|","Fare ",currFlight.get("Fare"),"|");
		System.out.println("+--------------------------------------------------------------------------------+");
		
	}

	public boolean showToStationDetails(String toStation, String flightNo, JSONObject currFlight) {
		
		if(flightRoutesViewModel.isCorrectToStation(toStation,flightNo,currFlight)) {
			showStatus(flightNo,currFlight);
			return true;
		}
		
		return false;
	}

	

}
