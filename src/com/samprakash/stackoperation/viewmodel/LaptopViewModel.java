package com.samprakash.stackoperation.viewmodel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.samprakash.stackoperation.model.LaptopModel;
import com.samprakash.stackoperation.repository.LaptopRepository;

public class LaptopViewModel  {

	public void gettingJSONObject() {
		String path;
		JSONParser jp = new JSONParser();
		try {
			path = new File(".").getCanonicalPath()
					+ "\\src\\com\\samprakash\\stackoperation\\zohoSchoolForGraduateStudies\\MyLaptop.json";
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Data not available : ");
			return;
		}
		try {
			LaptopRepository.jsonRetreiver = (JSONObject) jp.parse(new FileReader(path));
		} catch (Exception expection) {
			expection.printStackTrace();
			return;
		}

	}

	public void listOutUserChoices() {
		switch (LaptopRepository.getInstance().getStage()) {
		case 1: {
			LaptopModel.brandNames();
			break;
		}
		case 2: {
			LaptopModel.processorType();
			break;
		}
		case 3: {
			LaptopModel.ramType();
			break;
		}
		case 4: {
			LaptopModel.listOfLaptops();
			break;
		}
		case 5: {
			LaptopModel.laptopPrice();
			break;
		}
		}

	}

	public static int getInputFromUser(int minimum, int maximum) {
		Scanner input = new Scanner(System.in);
		int option = 0;
		do {
			try {
				option = input.nextInt();
				if (option < minimum || option > maximum) {
					System.out.print("Please given a valid input : ");
					continue;
				}
				// input.close();
				return option;
			} catch (InputMismatchException e) {
				System.out.print("Please given a valid input : ");
				input.next();
				continue;
			}
		} while (true);

	}
}
