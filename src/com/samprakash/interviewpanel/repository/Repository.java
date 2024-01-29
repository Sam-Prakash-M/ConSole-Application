package com.samprakash.interviewpanel.repository;

public class Repository {
	
	private static final Repository SINGLE_TON = new Repository();
       public String[] listOfQuestions = { "why main method is static in java?", "what is RunTime polymorphism?",
			"what is Compile Time Polymorphism?", "what is the Usage of this Keyword?",
			"static keyword usage in java?" };
	public String[] solutions = { "jvm", "method overriding", "method overloading", "current object", "common" };

	
	private Repository() {
		
	}
	public static Repository getInstance() {
		
		return SINGLE_TON;
	}

}
