package com.samprakash.flightbookapplication.dto;

public class Tickets {

	private long pnrNo;
	private int flightNo , noOfPassengers;
	private String ticketStatus,date,fromStation,toStation;

	public Tickets(String date,long pnrNo, String ticketStatus,int flightNo,
			String fromStation,String toStation,int noOfPassengers) {
		
		this.setNoOfPassengers(noOfPassengers);
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.date = date;
		this.pnrNo = pnrNo;
		this.ticketStatus = ticketStatus;
		this.flightNo = flightNo;
	}

	public long getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int trainNo) {
		this.flightNo = trainNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	
}
