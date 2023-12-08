package com.samprakash.stackoperation.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.stackoperation.repository.LaptopRepository;
import com.samprakash.stackoperation.viewmodel.LaptopViewModel;

public class LaptopModel {

	public static void brandNames() {
		int i, option;

		JSONObject obj = (JSONObject) LaptopRepository.jsonRetreiver.get("Laptop's Name");
		LaptopRepository.brandCollection = (JSONArray) obj.get("Brand Name");
		System.out.println("BRAND NAME\n");
		for (i = 0; i < LaptopRepository.brandCollection.size(); i++) {
			String str = (String) LaptopRepository.brandCollection.get(i);
			System.out.println((i + 1) + ") " + str);
		}
		System.out.print((i + 1) + ") Exit\nChoose the brand name : \n"
				+ "=========================================================================");
		option = LaptopViewModel.getInputFromUser(1, LaptopRepository.brandCollection.size() + 1);
		if (option <= LaptopRepository.brandCollection.size()) {
			LaptopRepository.getInstance().upLevel((String) LaptopRepository.brandCollection.get(option - 1));
		} else {
			LaptopRepository.getInstance().setStage(-1);
		}

	}

	public static void processorType() {
		int i, option;
		JSONObject obj = (JSONObject) LaptopRepository.jsonRetreiver.get("Laptop's Processors");

		LaptopRepository.processorsVarient = (JSONArray) obj
				.get(LaptopRepository.getInstance().getTraverse().peek().getOption());
		System.out.println("LAPTOP'S PROCESSOR NAME\n");
		for (i = 0; i < LaptopRepository.processorsVarient.size(); i++) {
			String str = (String) LaptopRepository.processorsVarient.get(i);
			System.out.println((i + 1) + ") " + str);
		}
		System.out.print((i + 1) + ") back to Previous Menu \n" + (i + 2) + ") Exit\nChoose the processor : \n"
				+ "=========================================================================");
		option = LaptopViewModel.getInputFromUser(1, LaptopRepository.processorsVarient.size() + 2);
		if (option <= LaptopRepository.processorsVarient.size()) {
			LaptopRepository.getInstance().upLevel((String) LaptopRepository.processorsVarient.get(option - 1));
		} else if (option == LaptopRepository.processorsVarient.size() + 1) {
			LaptopRepository.getInstance().downLevel();
		} else {
			LaptopRepository.getInstance().setStage(-1);
		}
	}

	public static void ramType() {
		int i, option;
		String temp = LaptopRepository.getInstance().getTraverse().peek().getOption();
		JSONObject obj = (JSONObject) LaptopRepository.jsonRetreiver.get("Laptop's RamVarient");
		LaptopRepository.ramVarient = (JSONArray) obj.get(temp);
		System.out.println("Ram Type\n");
		for (i = 0; i < LaptopRepository.ramVarient.size(); i++) {
			String str = (String) LaptopRepository.ramVarient.get(i);
			System.out.println((i + 1) + ") " + str);
		}
		System.out.print((i + 1) + ") back to Previous Menu\n" + (i + 2) + ") Exit\nChoose the Ram varient : \n"
				+ "=========================================================================");
		option = LaptopViewModel.getInputFromUser(1, LaptopRepository.ramVarient.size() + 2);
		if (option <= LaptopRepository.ramVarient.size()) {
			LaptopRepository.getInstance().upLevel(option, (String) LaptopRepository.ramVarient.get(option - 1));
		} else if (option == LaptopRepository.ramVarient.size() + 1) {
			LaptopRepository.getInstance().downLevel();
		} else {
			LaptopRepository.getInstance().setStage(-1);
		}
	}

	public static void listOfLaptops() {
		int i, option;
		String temp = LaptopRepository.getInstance().getTraverse().peek().getOption();
		JSONObject obj = (JSONObject) LaptopRepository.jsonRetreiver.get("List of Laptops");
		LaptopRepository.laptopVarient = (JSONArray) obj.get(temp);
		System.out.println("laptop Name\n");
		for (i = 0; i < LaptopRepository.laptopVarient.size(); i++) {
			String str = (String) LaptopRepository.laptopVarient.get(i);
			System.out.println((i + 1) + ") " + str);
		}
		System.out.print((i + 1) + ") back to Previous Menu\n" + (i + 2) + ") Exit\nChoose the Laptop : \n"
				+ "=========================================================================");
		option = LaptopViewModel.getInputFromUser(1, LaptopRepository.laptopVarient.size() + 2);
		if (option <= LaptopRepository.laptopVarient.size()) {
			LaptopRepository.getInstance().upLevel2(option, (String) LaptopRepository.laptopVarient.get(option - 1));
		} else if (option == LaptopRepository.laptopVarient.size() + 1) {
			LaptopRepository.getInstance().downLevel();
		} else {
			LaptopRepository.getInstance().setStage(-1);
		}

	}

	public static void laptopPrice() {
		int option;
		String temp = LaptopRepository.getInstance().getTraverse().peek().getName();
		JSONObject obj = (JSONObject) LaptopRepository.jsonRetreiver.get("Price of Laptops");
		System.out.println("You have Selected\n" + temp.substring(temp.indexOf('.') + 1).toUpperCase()
				+ " Which Price is " + obj.get(temp.substring(temp.indexOf('.') + 1)) + "\n");
		System.out.print("\n" + "1) back to Previous Menu\n2) Exit\nChoose the option : \n"
				+ "=========================================================================");
		option = LaptopViewModel.getInputFromUser(1, 2);
		if (option == 1) {
			LaptopRepository.getInstance().downLevel();
		} else {
			LaptopRepository.getInstance().setStage(-1);
		}
	}

}
