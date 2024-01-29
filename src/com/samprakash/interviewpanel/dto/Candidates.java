package com.samprakash.interviewpanel.dto;

public class Candidates {
	  private int age;
      private String name,qualification;
		public Candidates(String name, int age, String qualification) {
			this.name = name;
			this.age = age;
			this.qualification = qualification;
			
		}
		
		public int getAge() {
			return age;
		}
		
		public String getName() {
			return name;
		}
		
		public String getQualification() {
			return qualification;
		}
		
}
