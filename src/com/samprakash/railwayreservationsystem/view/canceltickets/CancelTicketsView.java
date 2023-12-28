package com.samprakash.railwayreservationsystem.view.canceltickets;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.samprakash.railwayreservationsystem.colors.Colors;
import com.samprakash.railwayreservationsystem.viewmodel.canceltickets.CancelTicketsViewModel;

public class CancelTicketsView extends Colors {
    
	private static Scanner sc; 
	private static CancelTicketsViewModel cancelTicketsViewModel;
	
	static  {
		sc = new Scanner(System.in);
		cancelTicketsViewModel = new CancelTicketsViewModel();
	}
	public void startTheProcess() {
		
		int passengerID = 0;
		while(true) {
			try {
				System.out.println(ANSI_BG_RED+"Enter a Passenger Id : "+ANSI_RESET);
				passengerID = sc.nextInt();
				break;
			}
			catch(InputMismatchException ime) {
				System.err.println("Enter a Valid Input : ");
				sc.next();
				continue;
			}
		}
		
		
		cancelTicketsViewModel.CancelTicket(passengerID);
		
	}
	public void showStatus(String status) {
		System.out.println(ANSI_PURPLE+status+ANSI_RESET);
		
	}

}
