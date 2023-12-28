package com.samprakash.railwayreservationsystem.dto;

public class Passengers {
    private String choice,name;
    private int age;
    private char BirthPreference;
    private static int id = 1;
    private int passengerId;
	
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getBirthPreference() {
		return BirthPreference;
	}
	public void setBirthPreference(char birthPreference) {
		BirthPreference = birthPreference;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public Passengers(String choice) {
		this.choice = choice;
	}
	public Passengers(String name, int age, char birthPreference,String choice) {
		this.name = name;
		this.age = age;
		this.BirthPreference = birthPreference;
		this.choice = choice;
		this.passengerId = id++;
	}
		

	
	
}
