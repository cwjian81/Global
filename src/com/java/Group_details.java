package com.java;

import java.time.LocalDateTime;

public class Group_details {
	private String vehicle_no;
	private LocalDateTime datetime;
	private boolean isLead;
	private String vehicle_type;
	
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public boolean isLead() {
		return isLead;
	}
	public void setLead(boolean isLead) {
		this.isLead = isLead;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
}
