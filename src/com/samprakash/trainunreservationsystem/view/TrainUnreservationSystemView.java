package com.samprakash.trainunreservationsystem.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainUnreservationSystemView {
  
	private Scanner scanner = new Scanner(System.in);
	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * writecsvFile("UnReservationTrain Routes.csv"); }
	 * 
	 * private static void writecsvFile(String string) throws IOException {
	 * 
	 * //String heading [] = {"Routes" , "Distance"};
	 * 
	 * BufferedWriter bw = new BufferedWriter(new FileWriter(string));
	 * bw.write(String.join(",", heading)); bw.write("\n"); bw.flush();
	 * 
	 * Scanner sc = new Scanner(System.in); for(int i = 0 ; i < 10 ; i++) { String
	 * [] eachRow = {sc.next() , sc.next()};
	 * 
	 * bw.write(String.join(",", eachRow)); bw.write("\n"); bw.flush();
	 * 
	 * }
	 * 
	 * bw.close();
	 * 
	 * BufferedReader br = new BufferedReader(new FileReader(string)); Map<String ,
	 * Integer> map = new LinkedHashMap <> ();
	 * 
	 * String line ; br.readLine(); while( (line = br.readLine()) != null) { String
	 * [] eachRow = line.split(",");
	 * map.put(eachRow[0],Integer.valueOf(eachRow[1])); } System.out.println(map);
	 * 
	 * }
	 */
	public  void init() {
		System.out.println("+---------------------------------------------------+\n"
				+"1) Admin  \n2) User\n3) Exit\n+---------------------------------------------------+");
		int choice;
		do {
			try {
				choice = scanner.nextInt();
				break;
			}
			
		
		catch(InputMismatchException ime) {
			showError("Enter a Valid Input : ");
			scanner.nextInt();
			
		}
		}while(true);
	}
	private static void showError(String string) {
		// TODO Auto-generated method stub
		
	}

}
