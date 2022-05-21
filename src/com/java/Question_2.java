package com.java;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question_2 {
	DecimalFormat df = new DecimalFormat("####0.00");
	static double car_charges_per_minute_normal = 0.02;
	static double motorcycles_charges_per_minute_normal = 0.005;
	static double car_charges_per_minute_12pmto2pm = 0.08;
	static double motorcycles_charges_per_minute_12pmto2pm = 0.02;
	
	Map<String, List<Group_details>> vehicleList = new HashMap<String, List<Group_details>>();
	
	public static void main(String[] args)
    {
		Question_2 test2 = new Question_2();
		
		// scenario A
		LocalDateTime entryTime = LocalDate.now().atTime(8, 0); 
		LocalDateTime exitTime = LocalDate.now().atTime(10, 0);		
		test2.car_entry(entryTime, "Group1", "VAH 831", "car", true);
		test2.car_entry(entryTime, "Group1", "VAH 888", "car", false);
		test2.car_entry(entryTime, "Group1", "VAH 111", "car", false);
		test2.car_entry(entryTime, "Group1", "VAH 111", "motorcycle", false);
		test2.car_entry(entryTime, "Group1", "VAH 111", "motorcycle", false);
		test2.car_exit(exitTime, "Group1");
		
		// scenario B
		entryTime = LocalDate.now().atTime(8, 0); 
		exitTime = LocalDate.now().atTime(15, 0);		
		test2.car_entry(entryTime, "Group2", "VAH 831", "car", true);
		test2.car_entry(entryTime, "Group2", "VAH 888", "car", false);
		test2.car_entry(entryTime, "Group2", "VAH 111", "car", false);
		test2.car_entry(entryTime, "Group2", "VAH 111", "motorcycle", false);
		test2.car_entry(entryTime, "Group2", "VAH 111", "motorcycle", false);
		test2.car_exit(exitTime, "Group2");
		
		// scenario C
		entryTime = LocalDate.now().atTime(12, 30); 
		exitTime = LocalDate.now().atTime(15, 0);		
		test2.car_entry(entryTime, "Group3", "VAH 831", "car", true);
		test2.car_entry(entryTime, "Group3", "VAH 888", "car", false);
		test2.car_entry(entryTime, "Group3", "VAH 111", "car", false);
		test2.car_entry(entryTime, "Group3", "VAH 111", "motorcycle", false);
		test2.car_entry(entryTime, "Group3", "VAH 111", "motorcycle", false);
		test2.car_exit(exitTime, "Group3");
		
		// scenario D
		entryTime = LocalDate.now().atTime(10, 30); 
		exitTime = LocalDate.now().atTime(12, 30);		
		test2.car_entry(entryTime, "Group4", "VAH 831", "car", true);
		test2.car_entry(entryTime, "Group4", "VAH 888", "car", false);
		test2.car_entry(entryTime, "Group4", "VAH 111", "car", false);
		test2.car_entry(entryTime, "Group4", "VAH 111", "motorcycle", false);
		test2.car_entry(entryTime, "Group4", "VAH 111", "motorcycle", false);
		test2.car_exit(exitTime, "Group4");
    }
	
	private void car_entry(LocalDateTime datetime, String group_id, String vehicle_no, String vehicle_type, boolean isLead) {
		List<Group_details> list_group_details = new ArrayList<Group_details>();
		
		//check is group id in map
		if(vehicleList.get(group_id) != null) {
		
			//assign previous value to arraylist if found from map
			list_group_details = vehicleList.get(group_id);
			
		}
		
		//initial new object
		Group_details details = new Group_details();
		details.setDatetime(datetime);
		details.setLead(isLead);
		details.setVehicle_no(vehicle_no);
		details.setVehicle_type(vehicle_type);
		
		//add new object to arraylist
		list_group_details.add(details);
		
		//update map with new list
		vehicleList.put(group_id, list_group_details);
		
	}
	
	private void car_exit(LocalDateTime exitTime, String group_id)  {
		
		List<Group_details> list_group_details = vehicleList.get(group_id);
		
		double charges = calculate_total_cost(group_id, exitTime);
		
		int memberExcludeLead = list_group_details.size() - 1;
		double leadToPay = charges/2;
		double divisionAccess = (charges/2) % memberExcludeLead;
	
		System.out.println("division Access : " + df.format(divisionAccess));
		System.out.println("lead To Pay : " + df.format(leadToPay + divisionAccess));
		System.out.println("");
		
	}
	
	private double calculate_total_cost(String group_id, LocalDateTime exitTime) {
		double total_cost = 0.0;
				
		LocalDateTime timeAt12 = LocalDate.now().atTime(12, 0); // time 12pm
		LocalDateTime timeAt14 = LocalDate.now().atTime(14, 0); //time 2pm
		
		long minutesBefore12 = 0;
		long minutesBetween12And2 = 0;
		long minutesAfter2 = 0;
		
		List<Group_details> list_group_details = vehicleList.get(group_id);
		
		//assume vehicle for a group enter in same time
		LocalDateTime entryTime = list_group_details.get(0).getDatetime();
		
		System.out.println("entryTime : " + entryTime);
		System.out.println("exitTime : " + exitTime);
		
		// entry time before 12pm
		if (entryTime.isBefore(timeAt12)) {
			// exit time before 12pm
			if (exitTime.isBefore(timeAt12) || exitTime.isEqual(timeAt12)) {
				
				 minutesBefore12 = ChronoUnit.MINUTES.between(entryTime, exitTime);
				System.out.println("minutesBefore12 : " + minutesBefore12);
				
			} 
			// exit time between 12pm and 2pm
			else if(exitTime.isAfter(timeAt12) && (exitTime.isBefore(timeAt14) || exitTime.isEqual(timeAt14))) {
				
				minutesBefore12 = ChronoUnit.MINUTES.between(entryTime, timeAt12);		
				System.out.println("minutesBefore12 : " + minutesBefore12);
				
				minutesBetween12And2 = ChronoUnit.MINUTES.between(timeAt12, exitTime);
				System.out.println("minutesBetween12And2 : " + minutesBetween12And2);
			} 
			// exit time after 2pm
			else {
				minutesBefore12 = ChronoUnit.MINUTES.between(entryTime, timeAt12);		
				System.out.println("minutesBefore12 : " + minutesBefore12);
				
				minutesBetween12And2 = 120;
				System.out.println("minutesBetween12And2 : " + minutesBetween12And2);
				
				minutesAfter2 = ChronoUnit.MINUTES.between(timeAt14, exitTime);		
				System.out.println("minutesAfter2 : " + minutesAfter2);
			}
		}
		// entry time between 12pm and 2pm
		else if ((entryTime.isEqual(timeAt12) || entryTime.isAfter(timeAt12)) && (entryTime.isBefore(timeAt14) || entryTime.isEqual(timeAt14))) {

			// exit time before 2pm
			if (exitTime.isBefore(timeAt14) || exitTime.isEqual(timeAt14)) {
				
				minutesBetween12And2 = ChronoUnit.MINUTES.between(entryTime, exitTime);
				System.out.println("minutesBetween12And2 : " + minutesBetween12And2);
				
			}
			// exit time after 2pm
			else {
				
				minutesBetween12And2 = ChronoUnit.MINUTES.between(entryTime, timeAt14);
				System.out.println("minutesBetween12And2 : " + minutesBetween12And2);
				
				minutesAfter2 = ChronoUnit.MINUTES.between(timeAt14, exitTime);		
				System.out.println("minutesAfter2 : " + minutesAfter2);
				
			}
		}
		// entry time after 2pm
		else {
			//exit time also must br after 2pm			
			minutesAfter2 = ChronoUnit.MINUTES.between(entryTime, exitTime);		
			System.out.println("minutesAfter2 : " + minutesAfter2);
		}
		
		//start calculate total cost
		Group_details details = null;
		for(int i = 0; i < list_group_details.size(); i++) {
			details = list_group_details.get(i);
			
			total_cost += minutesBefore12 * ((details.getVehicle_type().equals("car")?car_charges_per_minute_normal:motorcycles_charges_per_minute_normal));
			total_cost += minutesBetween12And2 * ((details.getVehicle_type().equals("car")?car_charges_per_minute_12pmto2pm:motorcycles_charges_per_minute_12pmto2pm));
			total_cost += minutesAfter2 * ((details.getVehicle_type().equals("car")?car_charges_per_minute_normal:motorcycles_charges_per_minute_normal));
		}
		
		System.out.println("total_cost : " + df.format(total_cost));

		return total_cost;
	}
}
