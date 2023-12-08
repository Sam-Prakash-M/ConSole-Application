package com.samprakash.stackoperation.dto;

public class LaptopList {
	public static String[] ourLaptops = { "Apple", "Samsung", "Asus" };
	private String option, name;
	private int stage;

	public LaptopList(int stage, String option) {
		this.stage = stage;
		this.option = option;
	}

	public static String[] getOurLaptops() {
		return ourLaptops;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

}
