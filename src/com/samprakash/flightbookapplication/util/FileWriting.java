package com.samprakash.flightbookapplication.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.samprakash.flightbookapplication.repository.FlightRepository;

public class FileWriting {
		
	private static final String PATH = "src/com/samprakash/flightbookapplication/FlightDetails.json";
	public static void writeFile(JSONObject jsonObject) {
		
		try {
			FileWriter fw = new FileWriter(PATH);
			fw.write(jsonObject.toJSONString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	public static void readFile(JSONObject jsonObject) {
		
			try {
				jsonObject = (JSONObject) new JSONParser()
						.parse(new FileReader("src/com/samprakash/flightbookapplication/FlightDetails.json"));
			} catch (IOException | ParseException e) {
				
				e.printStackTrace();
			}
			FlightRepository.getInstance().jsonRetreiver = jsonObject;
		
		
		
	}

}
