package com.samprakash.stackoperation.repository;

import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.samprakash.stackoperation.dto.LaptopList;

public class LaptopRepository {

	private static final LaptopRepository LAPTOP_OBJECT = new LaptopRepository();
	public static JSONObject jsonRetreiver;
	public static JSONArray brandCollection, processorsVarient, ramVarient, laptopVarient;
	private int stage = 1;
	private Stack<LaptopList> traverse = new Stack<>();

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public Stack<LaptopList> getTraverse() {
		return traverse;
	}

	private LaptopRepository() {
		// Private constructor to prevent instantiation from outside the class

	}

	public static LaptopRepository getInstance() {
		return LAPTOP_OBJECT;
	}

	public void upLevel(String option) {

		if (traverse.isEmpty()) {
			traverse.push(new LaptopList(stage, option));
		} else {
			LaptopList temp = traverse.peek();
			traverse.push(new LaptopList(stage, temp.getOption() + " " + option));
		}
		stage++;

	}

	public void downLevel() {
		if (traverse.isEmpty()) {
			stage = -1;
		} else {
			traverse.pop();
			stage--;
		}

	}

	public void upLevel(int option, String string) {
		LaptopList temp = traverse.peek();
		LaptopList myname = new LaptopList(stage, temp.getOption() + " " + string);
		myname.setName(string);
		traverse.push(myname);
		stage++;

	}

	public void upLevel2(int option, String string) {
		LaptopList myname = new LaptopList(stage, string);
		myname.setName(string);
		traverse.push(myname);
		stage++;

	}

}
