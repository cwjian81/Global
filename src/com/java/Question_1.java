package com.java;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Question_1 {
	static double charges_per_minute = 0.02;
	Map vehicleList = new HashMap<String, LocalDateTime>();
	
	public static void main(String[] args)
    {
		Question_1 test1 = new Question_1();
		
		// car entry to car park
		LocalDateTime now = LocalDateTime.now();  
		Double charges = test1.car_entry(now, "CCE 8263");
		System.out.println("charges : " + charges);
		
		//car exit car park after  5 minutes
		now = LocalDateTime.now().plusMinutes(5);  
		charges = test1.car_entry(now, "CCE 8263");
		System.out.println("charges : " + charges);
		
    }

	private Double car_entry(LocalDateTime datetime, String vehicle_no) {
		Double chargres = null;
		
		// add vechicle no to list if list is empty (car first entry to car park)
		if(vehicleList.get(vehicle_no) == null) {
			vehicleList.put(vehicle_no, datetime);
		} else {
			// vechicle no found in list
			LocalDateTime exitTime = datetime;
			LocalDateTime entryTime = (LocalDateTime) vehicleList.get(vehicle_no);
			
			int minutes_different = exitTime.getMinute() - entryTime.getMinute();
			
			chargres = minutes_different * charges_per_minute;
		}
		 
		return chargres;
	}
}
