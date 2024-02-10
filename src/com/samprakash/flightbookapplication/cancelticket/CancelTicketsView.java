package com.samprakash.flightbookapplication.cancelticket;

import java.util.Scanner;

public class CancelTicketsView {

	private CancelTicketsViewModel cancelTicketsViewModel;
	private Scanner scanner;
	
	
	
	public CancelTicketsView() {
		cancelTicketsViewModel = new CancelTicketsViewModel(this);
		scanner = new Scanner(System.in);
	}

	public void cancelTicket() {
		
		System.out.println("Enter a PNR Number : ");
		long pnrNo = scanner.nextLong();
		scanner.nextLine();
		
		cancelTicketsViewModel.cancelCurrentTicket(pnrNo);

	}

	public boolean isGoingtoDelete() {
		System.out.println("+----------------------------------------------+");
		System.out.println("Do you Still Going to Cancel Delete : \n1)Yes\n2)NO");
		System.out.println("+----------------------------------------------+");
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice == 1;
	}

	public void showStatus(String status) {
		System.out.println(status);
		
	}

	public void changeStatus() {
		System.out.println("+----------------------------------------------+");
		System.out.println("Enter a PNR Number : ");
		long pnrNo = scanner.nextLong();
		scanner.nextLine();
		cancelTicketsViewModel.changeTicketStatus(pnrNo);
		
	}

	public String gettingNewStatus() {
		System.out.println("+----------------------------------------------+");
		System.out.println("Change Ticket Status : \n1)CNF\n2)Cancel");
		System.out.println("+----------------------------------------------+");
		int choice = scanner.nextInt();
		return choice == 1 ? "CNF" : "Cancel";
	}

}
