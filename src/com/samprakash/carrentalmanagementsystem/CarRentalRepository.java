package com.samprakash.carrentalmanagementsystem;

public class CarRentalRepository {
     
	private static final CarRentalRepository SINGLE_OBJECT = new CarRentalRepository();
	
	private CarRentalRepository() {}//avoid object creation from outSide
	
	
	public static CarRentalRepository getInstance() {
		return SINGLE_OBJECT;
	}
}
