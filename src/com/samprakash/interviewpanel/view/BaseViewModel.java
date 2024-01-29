package com.samprakash.interviewpanel.view;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.samprakash.interviewpanel.dto.Candidates;
import com.samprakash.interviewpanel.repository.Repository;


public class BaseViewModel {
  
	
	private BaseView baseView;
      
	public BaseViewModel(BaseView baseView) {
		this.baseView = baseView;
	}

	static String[] hr = { "Sam Prakash", "Mydheen", "Subramanian", "veeriyaPerumal" };
	
	static Queue<Candidates> listOfCandidates = new ArrayDeque<>();
	static List<Candidates> selectedCandidates = new ArrayList<>();

	private static Scanner sc = new Scanner(System.in);
	
	

	public  void interviewProcess() {
		
		if (listOfCandidates.isEmpty()) {
			baseView.showError("No Candidates Available for interview : ");
			
			return;
		}
				while (!listOfCandidates.isEmpty()) {
					
					Candidates currCandidate = listOfCandidates.poll();
					
					baseView.interViewCurrentCandidate(currCandidate,hr,0);
		
			boolean result = isClearedtheRound(currCandidate);
					if (result) {
						baseView.candidateStatus("congradulation you have been shortlisted for further Round");
						
						selectedCandidates.add(currCandidate);
					} else {
						baseView.candidateStatus("Thank you for participating zoho interview Process\n"
								+ "we regret to inform that we have NOT BEEN able to select you further Consideration");
					
					}
					
				}

	}

	private  boolean isClearedtheRound(Candidates currCandidate) {
		int score = 0;
		baseView.candidateStatus("lets Start the Interview!!!\n");
	      String [] listOfQuestions = Repository.getInstance().listOfQuestions;
	      String [] solutions = Repository.getInstance().solutions;
		for (int i = 0; i < listOfQuestions.length; i++) {
			System.out.println(listOfQuestions[i]);
			String userInput = sc.nextLine();
			if (userInput.toLowerCase().contains(solutions[i])) {
				score++;
			}

		}
		
		return score >= 3 ? true : false;
	}

	public  void resultAnnounceMent() {
		
		
		baseView.candidateStatus("\n========================================================="
				+ "\n\nHi all thank you for coperating and patience"
				+ "\nnow we are going to announce the candidates who all are clear the rounds\n");
		
		baseView.resultAnnounceMent(selectedCandidates);
		
		
	}



	public  void showAllCandidates() {
		baseView.shoAllCandidates(listOfCandidates);
		
	}

	public  void showAllSelectedCandidates() {
		
		baseView.shoAllSelectedCandidates(selectedCandidates);
		

	}

	public void addCandidate(Candidates candidates) {
		listOfCandidates.add(candidates);
		
	}
}
