package com.samprakash.railwayreservationsystem.view.jsonretreive;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.samprakash.railwayreservationsystem.colors.Colors;
import com.samprakash.railwayreservationsystem.view.booktickets.BookTicketsView;
import com.samprakash.railwayreservationsystem.viewmodel.jsonretreive.JSONRetreiverViewModel;

public class JSONRetreiverView extends Colors {
	private static JSONRetreiverViewModel jsonRetreiveViewModel;
	private static BookTicketsView bookTicketsview;
	private static Scanner sc;

	static {
		sc = new Scanner(System.in);
		bookTicketsview = new BookTicketsView();
		jsonRetreiveViewModel = new JSONRetreiverViewModel();
	}

	public void startTheApplication() {

		System.out.println(ANSI_BLUE + "WelCome to Sam Prakash's Railways Reservation System" + ANSI_RESET);
		jsonRetreiveViewModel.retreiveJSONObject();
		System.out.println("Loading Datas from data base ...\nPlease wait");
		this.showJsonDatas();

	}

	public void showJsonDatas() {
		System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);

		System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);

		Sam: do {

			switch (JSONRetreiverViewModel.stage) {
			case 1: {
				Set Alltrains = jsonRetreiveViewModel.showAllTrains();
				System.out.println("There are 10 Trains Running Currently!!!" + "\nChoose Accordingly");
				System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);
				int count = 0;
				for (Object train : Alltrains) {
					Map.Entry eachTrain = (Map.Entry) train;
					System.out.println(count++ + ") " + ANSI_BOLD + eachTrain.getKey() + ANSI_RESET);
				}

				System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);

				while (true) {
					try {
						int trainNo = sc.nextInt();
						jsonRetreiveViewModel.addChoice(trainNo);
						break;
					} catch (InputMismatchException ime) {
						System.out.println("Enter Valid Input");
						sc.next();
						continue;

					}
				}

				break;
			}
			case 2: {
				Set trainDetails = jsonRetreiveViewModel.showSelectedTrainDetails();
				for (Object key : trainDetails) {
					Map.Entry specs = (Map.Entry) key;
					System.out.println(ANSI_BG_WHITE + ANSI_BLACK + specs.getKey() + " --> " + specs.getValue() + "\n");
				}
				System.out.println(ANSI_RESET);
				System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);
				int choice = 0;
				Prakash: do {
					System.out.println(ANSI_GREEN + "Enter 1 for Ticket Booking\n"
							+ "Enter 2 for Cancel Ticket\nEnter 3 for Show Booked tickets\nEnter 4 for back <-- \nEnter 0 for Exit"
							+ ANSI_RESET);
					System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);
					while (true) {
						try {
							choice = sc.nextInt();
							if (choice == 4) {
								jsonRetreiveViewModel.removeChoice();
								break Prakash;
							}
							if (choice == 0) {
								break Sam;
							}

							break;
						} catch (InputMismatchException ime) {
							System.out.println("Enter Valid Input ");
							sc.next();
							continue;
						}

					}

					bookTicketsview.goToProcess(choice);
				} while (true);
				break;
			}

			}
		} while (true);

	}

	public void showError(String error) {

		System.err.println(error);
	}

}
