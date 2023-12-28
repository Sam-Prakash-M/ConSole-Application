package com.samprakash.railwayreservationsystem.viewmodel.booktickets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONObject;

import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.model.booktickets.BookTicketsModel;
import com.samprakash.railwayreservationsystem.repository.RailwayRepository;
import com.samprakash.railwayreservationsystem.view.booktickets.BookTicketsView;

public class BookTicketsViewModel implements Comparator <Passengers>{
	private static BookTicketsView bookTicketsView;
	private static BookTicketsModel bookTicketsModel;

	static {
		bookTicketsView = new BookTicketsView();
		bookTicketsModel = new BookTicketsModel();
		RailwayRepository.setPassengerDetails(new TreeSet <> (new BookTicketsViewModel()));
	}

	public void bookTickets()  {

		Passengers currPassenger = bookTicketsView.getInputFromUser();
		bookTicketsModel.addPassengers(currPassenger ,currPassenger.getBirthPreference());

	}

	public boolean isAvailableBirthPreference(Passengers passenger ,char birthPreference) {
		String choice = bookTicketsModel.getChoice(birthPreference);
		JSONObject jsonObject = bookTicketsModel.getJSONObject();
		JSONObject entry = (JSONObject) jsonObject.get("trains");
		JSONObject train = (JSONObject) entry.get(choice);
		long ticket;
		if (birthPreference == 'L') {
			if ( (ticket = (long) train.get("5. available_LowerBirths")) > 0) {
					train.put("5. available_LowerBirths", ticket-1);
					Writer pw;
					try {
						pw = new FileWriter(
								"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
						pw.write(jsonObject.toJSONString());
						pw.flush();
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				bookTicketsView.showBookStatus("LowerBirth Given SuccessFully");
				return true;
			} 
			else if( (ticket = (long) train.get("7. available_MiddleBirths")) > 0) {
				train.put("7. available_MiddleBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("MiddleBirth Given SuccessFully");
				passenger.setBirthPreference('M');
				return true;
				
					
			}
			else if( (ticket = (long) train.get("6. available_UpperBirths")) > 0) {
				train.put("6. available_UpperBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("UpperBirth Given SuccessFully");
				passenger.setBirthPreference('U');
				return true;
			}
			
			else {
				 
				return this.bookInRAC(passenger ,jsonObject,train);

			}

		} else if (birthPreference == 'U') {
			
			if ( (ticket = (long) train.get("6. available_UpperBirths") ) > 0) {
				train.put("6. available_UpperBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("UpperBirth Given SuccessFully");
				return true;
			}
			else if( (ticket = (long) train.get("7. available_MiddleBirths")) > 0) {
				train.put("7. available_MiddleBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("MiddleBirth Given SuccessFully");
				passenger.setBirthPreference('M');
				return true;
				
					
			}
			else if( (ticket = (long) train.get("5. available_LowerBirths")) > 0) {
				train.put("5. available_LowerBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			bookTicketsView.showBookStatus("LowerBirth Given SuccessFully");
			passenger.setBirthPreference('L');
			return true;
			}
			else {
				return this.bookInRAC(passenger,jsonObject,train);
			

			}

		} else if (birthPreference == 'M') {
			
			if ( (ticket = (long) train.get("7. available_MiddleBirths") )> 0) {
				train.put("7. available_MiddleBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("MiddleBirth Given SuccessFully");
				return true;
			} 
			else if( (ticket = (long) train.get("5. available_LowerBirths") ) > 0) {
				train.put("5. available_LowerBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			bookTicketsView.showBookStatus("LowerBirth Given SuccessFully");
			passenger.setBirthPreference('L');
			return true;
			}
			else if( (ticket = (long) train.get("6. available_UpperBirths")) > 0) {
				train.put("6. available_UpperBirths", ticket-1);
				Writer pw;
				try {
					pw = new FileWriter(
							"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
					pw.write(jsonObject.toJSONString());
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				bookTicketsView.showBookStatus("UpperBirth Given SuccessFully");
				passenger.setBirthPreference('U');
				return true;
			}
			
			else {
				return this.bookInRAC(passenger,jsonObject,train);
			}

		} else {
			bookTicketsView.inputMismatch("Enter Only  L , M or U");
			return false;
		}

	}

	private boolean bookInRAC(Passengers passenger ,JSONObject jsonObject ,JSONObject train) {
		long ticket = (Long) train.get("8. available_RAC");
		if (ticket > 0) {
			train.put("8. available_RAC", ticket-1);
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(jsonObject.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bookTicketsView.showBookStatus("Rac Given SuccessFully");
			passenger.setBirthPreference('R');
			return true;
		} else {
			return this.bookInWaitingList(passenger,jsonObject,train);
		}

	}

	private boolean bookInWaitingList(Passengers passenger ,JSONObject jsonObject ,JSONObject train) {
		long ticket = (Long) train.get("9. available_WaitingList");
		if (ticket > 0) {
			train.put("9. available_WaitingList", ticket-1);
			Writer pw;
			try {
				pw = new FileWriter(
						"src/com/samprakash/railwayreservationsystem/Sam's Railways/RailWay Reservation System.json");
				pw.write(jsonObject.toJSONString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bookTicketsView.showBookStatus("WaitingList Given SuccessFully");
			passenger.setBirthPreference('W');
			return true;
		} else {
			bookTicketsView.ShowNoTickets("No Tickets available now");
			return false;
		}	

	}

	@Override
	public int compare(Passengers passenger1, Passengers passenger2) {
		
		return passenger1.getName().compareTo(passenger2.getName());
	}

	public void showStatus(String sucessMessage) {
		bookTicketsView.showBookStatus(sucessMessage);
	}

	public String getUserTrainChoice() {
		
		return bookTicketsModel.getUserTrainChoice();
	}

	public Set<Passengers> listOfBookedTickets() {
		
		return RailwayRepository.getPassengerDetails();
	}

	

}
