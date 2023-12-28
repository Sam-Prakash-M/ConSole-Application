package com.samprakash.railwayreservationsystem.view.booktickets;

import java.io.IOException;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.samprakash.railwayreservationsystem.colors.Colors;
import com.samprakash.railwayreservationsystem.dto.Passengers;
import com.samprakash.railwayreservationsystem.view.canceltickets.CancelTicketsView;
import com.samprakash.railwayreservationsystem.viewmodel.booktickets.BookTicketsViewModel;

public class BookTicketsView extends Colors {
		private BookTicketsViewModel bookTicketsViewModel = new BookTicketsViewModel();
		private static CancelTicketsView cancelTicket;
		private static Scanner sc;
		static {
			cancelTicket = new CancelTicketsView();
			sc = new Scanner(System.in);
		}
	public void goToProcess(int choice)  {
	   
			 if(choice == 1) {
	        	  bookTicketsViewModel.bookTickets();
	        	  sc.nextLine();
	          }
	          else if(choice == 2) {
	        	  cancelTicket.startTheProcess();
	          }
	          else if(choice == 3) {
	        	  Set <Passengers> bookedPassengersDetails = bookTicketsViewModel.listOfBookedTickets();
	        	  this.showOfAllPassengers(bookedPassengersDetails);
	          }
	          else {
	        	  this.showError("The input must be given betWeen  1 to 3 : ");
	          }
		
	}

	private void showOfAllPassengers(Set<Passengers> bookedPassengersDetails) {
		if(bookedPassengersDetails.isEmpty()) {
			System.err.println("Currently Passengers are not booked any tickets !!!");
			return;
		}
		
		for(Passengers passenger : bookedPassengersDetails) {
			
		System.out.println(ANSI_ITALIC+ANSI_BG_YELLOW+"Passenger's Name : "+passenger.getName()+"\nPassenger's ID is : "
				+ ""+passenger.getPassengerId()
				+"\nPassenger's Train No : "
				+passenger.getChoice()+"\nPassenger's Seat : "+passenger.getBirthPreference()
				+ANSI_RESET);
			
		}
		
	}

	private void showError(String err) {
		
		System.err.println(err);
	}

	public Passengers getInputFromUser() {
		  System.out.println(ANSI_YELLOW+"Enter a Name : ");
		  String name = sc.nextLine();
		  int age = 0;
		  while(true) {
			  try {
				  System.out.println("Enter a age : ");
				  age = sc.nextInt();
				  break;
			  }
			  catch(InputMismatchException ime) {
				  System.out.println("Enter a valid input : ");
				  sc.next();
				  continue;
			  }
			  
		  }
		 
		  System.out.println("Enter birth preference : \nL for lower birth\n"
		  		+ "U for upper birth\nM for middle birth"+ANSI_RESET);
		char  birthPreference = sc.next().charAt(0);
		String choice = bookTicketsViewModel.getUserTrainChoice();
		return new Passengers(name , age , birthPreference, choice);
	}

	public void inputMismatch(String err) {
		
		System.err.println(err);
	}

	public void ShowNoTickets(String noTickets) {
		System.out.println(ANSI_BG_YELLOW+noTickets+ANSI_RESET);
		
	}

	public void showBookStatus(String status) {
		
		System.out.println(ANSI_BG_YELLOW+status+ANSI_RESET);
	}

	public void writeInFile(Writer file, List choice) throws IOException {
	  
		file.write("Passenger Details "
				+"\nName : "+choice.get(0)+"\n");
		file.write("age : "+choice.get(1)+"\n");
		file.write("BirthPreference : " +choice.get(2)+"\n");
		file.flush();
		//file.close();
		
		
	}

}
