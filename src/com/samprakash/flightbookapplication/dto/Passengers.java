package com.samprakash.flightbookapplication.dto;

import java.util.Random;

public class Passengers {
	
	private int age,id;
	private String name , gender;
	private Tickets tickets;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Passengers(int age, String name, String gender, Tickets tickets) {
		
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.setTickets(tickets);
		this.setId(new Random().nextInt(50000,100000));
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tickets getTickets() {
		return tickets;
	}
	public void setTickets(Tickets tickets) {
		this.tickets = tickets;
	}
	
	

}
