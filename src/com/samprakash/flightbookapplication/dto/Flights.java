package com.samprakash.flightbookapplication.dto;

import java.util.List;

public class Flights {
	
	private int flightNo , seats,fare;
	private String flightName , departureTime , ArrivalTime,date;
	private List<String> flightRoutes;
	
	public Flights(String date,int flightNo, int seats, int fare, String flightName, String departureTime, String arrivalTime,
			List<String> flightRoutes) {
		this.date = date;
		this.flightNo = flightNo;
		this.seats = seats;
		this.fare = fare;
		this.flightName = flightName;
		this.departureTime = departureTime;
		ArrivalTime = arrivalTime;
		this.flightRoutes = flightRoutes;
	}
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public List<String> getFlightRoutes() {
		return flightRoutes;
	}
	public void setFlightRoutes(List<String> flightRoutes) {
		this.flightRoutes = flightRoutes;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
