package com.samprakash.railwayreservationsystem.viewmodel.canceltickets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.model.canceltickets.CancelTicketsModel;
import com.samprakash.railwayreservationsystem.view.canceltickets.CancelTicketsView;

public class CancelTicketsViewModel {
	
	private static CancelTicketsModel cancelTicketsModel;
	private static CancelTicketsView CancelTicketsView;
	private static Passengers PassengerObject;
	static {
		cancelTicketsModel = new CancelTicketsModel();
		CancelTicketsView = new CancelTicketsView();
	}

	public void CancelTicket(int passengerID) {
		JSONObject jsonObject = cancelTicketsModel.gettingJSONObject();
		JSONObject  listOfTrains = (JSONObject) jsonObject.get("trains");
		this.removeCurrentPassenger(listOfTrains,passengerID);
	}

	private void removeCurrentPassenger(JSONObject  listOfTrains,int passengerID) {
		      if(this.isRemovePassenger(passengerID)) {
		    	  JSONObject trainDetails =  (JSONObject) listOfTrains.get(PassengerObject.getChoice());
		    	    this.addAvailableTicket(trainDetails,PassengerObject);
		    	  CancelTicketsView.showStatus("Passengers Details Deleted SuccessFully");
		      }
		      else {
		    	  CancelTicketsView.showStatus("Passengers ID is not Correct ");
		      }
		   
	}

	private void addAvailableTicket(JSONObject trainDetails, Passengers passengerObject) {
	          
		char preference = passengerObject.getBirthPreference();
		JSONObject original = cancelTicketsModel.gettingJSONObject();
		switch(preference) {
		  
		case 'L' : {
			trainDetails.put("5. available_LowerBirths" , (long)trainDetails.get("5. available_LowerBirths") + 1 );
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(original.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 'U' : {
			trainDetails.put("6. available_UpperBirths" , (long)trainDetails.get("6. available_UpperBirths") + 1 );
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(original.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 'M' : {
			trainDetails.put("7. available_MiddleBirths" , (long)trainDetails.get("7. available_MiddleBirths") + 1 );
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(original.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 'R' : {
			trainDetails.put("8. available_RAC" , (long)trainDetails.get("8. available_RAC") + 1 );
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(original.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 'W' : {
			trainDetails.put("9. available_WaitingList" , (long)trainDetails.get("9. available_WaitingList") + 1 );
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(original.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
		
	}

	private boolean isRemovePassenger(int passengerID) {
		Set <Passengers> passenger = cancelTicketsModel.getPassengerStorage();
		boolean hasPassenger = false;
		for(Passengers p : passenger) {
			if(p.getPassengerId() == passengerID) {
				PassengerObject = p;
				passenger.remove(p);
				hasPassenger = true;
				break;
			}
		}
		return hasPassenger;
	}

}
