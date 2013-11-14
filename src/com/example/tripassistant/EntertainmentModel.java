package com.example.tripassistant;

public class EntertainmentModel {

	private String entmName;
	private int entmStartTime;
	private double entmTravelTime;
	private double entmDuration;
	private LocationModel loc;
	
	public EntertainmentModel(String entmName,int entmStartTime,double entmTravelTime,double entmDuration,LocationModel loc){
		this.entmName = entmName;
		this.entmStartTime = entmStartTime;
		this.entmTravelTime = entmTravelTime;
		this.entmDuration = entmDuration;
		this.loc = loc;
	}

	public String getEntmName() {
		return entmName;
	}

	public void setEntmName(String entmName) {
		this.entmName = entmName;
	}

	public LocationModel getEntmLoc() {
		return loc;
	}

	public void setEntmLoc(LocationModel loc) {
		this.loc = loc;
	}
	
	public void setEntmStartTime(int entmStartTime){
		this.entmStartTime = entmStartTime;
	}
	
	public int getEntmStartTime(){
		return entmStartTime;
	}

	public double getEntmTravelTime() {
		return entmTravelTime;
	}

	public void setEntmTravelTime(int entmTravelTime) {
		this.entmTravelTime = entmTravelTime;
	}

	public double getEntmDuration() {
		return entmDuration;
	}

	public void setEntmDuration(int entmDuration) {
		this.entmDuration = entmDuration;
	}

}
