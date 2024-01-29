package com.samprakash.interviewpanel.view;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.samprakash.interviewpanel.dto.Candidates;

public class BaseView {
     
	private BaseViewModel baseViewModel;
	private Scanner scanner = new Scanner(System.in);
	public BaseView() {
		baseViewModel = new BaseViewModel(this);
	}
	
	
	public void startTheApplication() {
		System.out.println("=======================================================\n\n"
				+ "Hi all!! Welcome to Zoho Corporation Private Limited , Tenkasi..\n\n"
				+ "=======================================================\n");
		getInputFromUser();
		
	}
	
	private void getInputFromUser() {
		
		
		int choice;
		do {
			System.out.println("Enter 1 for add Candidates : ");
			System.out.println("Enter 2 for start the Interview : ");
			System.out.println("Enter 3 for show the list of Candidates : ");
			System.out.println("Enter 4 for list of Selected Candidates : ");
			System.out.println("Enter 5 for Exit : ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1 : {
				addCandidates();
				
				break;
			}
			case 2 : {
				baseViewModel.interviewProcess();
				break;
			}
			case 3 : {
				baseViewModel.showAllCandidates();
				break;
			}
			case 4 : {
				baseViewModel.showAllSelectedCandidates();
				break;
			}
			case 5 : {
				   System.out.println("Thank you ...........bye ");
				   break;
			}
			}
		}
		while(choice != 5);
		
		
		
	}
        
	public  void addCandidates() {

		int choice;
		do {
			System.out.println("what is your name :");
			scanner.nextLine();
			String name = scanner.nextLine();
			System.out.println("What is your age : ");
			int age = scanner.nextInt();
			System.out.println("What is your Qualification : ");
			scanner.nextLine();
			String qualification = scanner.nextLine();
			baseViewModel.addCandidate(new Candidates(name, age, qualification));
			
			
			System.out.println("Enter 1 for add Candidates : ");
			System.out.println("Enter 2 for exit from this : ");
			choice = scanner.nextInt();
		} while (choice != 2);
		

	}

	public void showError(String error) {
		
		System.out.println(error);
	}


	public void interViewCurrentCandidate(Candidates currCandidate, String[] hr, int i) {
		System.out.println(currCandidate.getName()
				+ " Please Go the Interview hall to attend the Interview..... All the best\n\n");
			  System.out.println("Hi!!  " + currCandidate.getName() + " take your Seat");
				System.out.println("My name is " + hr[i++] 
						+ " and my team members are " + hr[i++] + ", " + hr[i++]
							+ ", " + hr[i++] + ", ");
		
	}


	public void candidateStatus(String status) {
		
		System.out.println(status);
	}


	public void shoAllCandidates(Queue<Candidates> listOfCandidates) {
		System.out.println("Total Number of Candidates : " + listOfCandidates.size());

		for (Candidates c : listOfCandidates) {
			System.out.println(
					"===========================================\n" + "| Candidate Name          : " + c.getName()
							+ "\n" + "| Candidate Age           : " + c.getAge() + "\n" + "| Candidate Qualification : "
							+ c.getQualification() + "\n" + "===========================================");
		}

		
	}


	public void shoAllSelectedCandidates(List<Candidates> selectedCandidates) {
		System.out.println("Total Number of Candidates : " + selectedCandidates.size());

		for (Candidates c : selectedCandidates) {
			System.out.println(
					"===========================================\n" + "| Candidate Name          : " + c.getName()
							+ "\n" + "| Candidate Age           : " + c.getAge() + "\n" + "| Candidate Qualification : "
							+ c.getQualification() + "\n" + "===========================================");
		}
		
	}


	public void resultAnnounceMent(List<Candidates> selectedCandidates) {
		
		if(!selectedCandidates.isEmpty()) {
			for (Candidates person : selectedCandidates) {
				candidateStatus(
		person.getName() + " you have been Selcted for " 
				+ "further round stand in your own  Place");
			}

		}
		else {
		     candidateStatus("There is no Candidate selected : ");
		}
		
	}

}
