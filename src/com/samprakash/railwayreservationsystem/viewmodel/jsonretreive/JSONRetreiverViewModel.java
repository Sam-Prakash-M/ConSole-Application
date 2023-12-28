package com.samprakash.railwayreservationsystem.viewmodel.jsonretreive;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.model.jsonretreive.JSONRetreiverModel;
import com.samprakash.railwayreservationsystem.repository.RailwayRepository;
import com.samprakash.railwayreservationsystem.view.jsonretreive.JSONRetreiverView;

public class JSONRetreiverViewModel {
	private JSONRetreiverModel jsonRetreiverModel;
	private JSONRetreiverView jsonRetreiveView = new JSONRetreiverView();
	public static int stage = 1;
	public void retreiveJSONObject() {
		jsonRetreiverModel = new JSONRetreiverModel();
		JSONObject jsonObject = jsonRetreiverModel.getJSONObject();
		JSONParser jsonparser = new JSONParser();
		String path = "";
		try {
			path = new File(".").getCanonicalPath() 
			+"\\src\\com\\samprakash\\railwayreservationsystem"
			+ "\\Sam's Railways\\RailWay Reservation System.json";
		}
		catch(IOException ioe ) {
			ioe.printStackTrace();
			System.out.println("The file is not available : ");
			return;
		}
		try {
			jsonObject = (JSONObject) jsonparser.parse(new FileReader(path));
		} catch (Exception expection) {
			 expection.printStackTrace();
			return;
			
		}
		jsonRetreiverModel.setJSONObject(jsonObject);
		
	}

	public Set showAllTrains() {
		JSONObject jsonObject = jsonRetreiverModel.getJSONObject();
		 System.out.println("Welcome to : "+jsonObject.get("railway_system"));
		 
		 Map  trains = (Map) jsonObject.get("trains");
	
		return new TreeMap (trains).entrySet();
		
	}

	

	public void addChoice(int trainNo) {
		if(trainNo >= 0 && trainNo <= 9) {
			Passengers passenger = jsonRetreiverModel.addChoice(trainNo);
			RailwayRepository.getInstance().getTraverse().push(passenger);
			stage++;
		}
		else {
			jsonRetreiveView.showError("Please Given number between 0 to 9 !!");
		}
		
	}

	public Set showSelectedTrainDetails() {
		String choice = jsonRetreiverModel.getCurrentTrainDetails();
		
		JSONObject jsonObject = jsonRetreiverModel.getJSONObject();
		Map  trains = (Map) jsonObject.get("trains");
		Map currTrain  = (Map) trains.get(choice);
		TreeMap ordered = new TreeMap(currTrain);
		
		return ordered.entrySet();
		
		
	}

	public void removeChoice() {
		if(!RailwayRepository.getInstance().getTraverse().isEmpty()) {
			RailwayRepository.getInstance().getTraverse().pop();
			System.out.println("Curr stage : "+stage);
			stage--;
			System.out.println("Curr stage : "+stage);
		}
		
		
	}


	

}
