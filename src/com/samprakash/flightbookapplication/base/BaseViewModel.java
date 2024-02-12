package com.samprakash.flightbookapplication.base;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.json.simple.JSONObject;

import com.samprakash.flightbookapplication.dto.Passengers;
import com.samprakash.flightbookapplication.repository.FlightRepository;
import com.samprakash.flightbookapplication.util.FileWriting;

public class BaseViewModel {

	private BaseView baseView;

	public BaseViewModel(BaseView baseView) {

		this.baseView = baseView;
	}

	public void fileLoader() {
		FileWriter fw=null;
		try {
			if (Files.size(Path.of("src/com/samprakash/flightbookapplication/FlightDetails.json")) == 0) {
				
				fw = new FileWriter("src/com/samprakash/flightbookapplication/FlightDetails.json");

	
				fw.write(new JSONObject().toJSONString());
				FileWriting.readFile(new JSONObject());
				

			} else {
				
				FileWriting.readFile(new JSONObject());
				
				
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(fw!=null)
				{
					fw.flush();
					fw.close();
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	public void searchByPassengerID() {
		
		int id = baseView.getPassengerID();
		List<Passengers> passengers = FlightRepository.getInstance().passengers;
		baseView.showPassengersDetails(id,passengers);
		
	}

}
