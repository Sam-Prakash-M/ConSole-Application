package com.samprakash.stackoperation;

import com.samprakash.stackoperation.repository.LaptopRepository;
import com.samprakash.stackoperation.viewmodel.LaptopViewModel;

class StackImplementation {

	public static void main(String[] args) {

		StackImplementation stackImplemtation = new StackImplementation();
		stackImplemtation.userManual();

	}

	private void userManual() {

		LaptopViewModel laptopviewmodel = new LaptopViewModel();
		System.out.println("=======================================\n\nHi !! welcome to here" + "\n\nwe have "
				+ "3 Different Brands laptops which is : \n\n" + "\n\n=======================================");
		laptopviewmodel.gettingJSONObject();
		do {
			laptopviewmodel.listOutUserChoices();
			System.out.println("\n\n=========================================================================\n");
		} while (LaptopRepository.getInstance().getStage() != -1);
		System.out.println("Thank you for Coming !!!");
	}

}
