package com.samprakash.onlinebookshopapplication.dto;

public class Persons {
	public static final String OWNER = "Sam Prakash";
	private String userName , passWord;
	
	
	public Persons(String userName, String passWord) {
		
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	

}
