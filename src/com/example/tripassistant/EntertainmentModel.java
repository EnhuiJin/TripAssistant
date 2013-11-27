package com.example.tripassistant;

public class EntertainmentModel {

	private String entmName;
	private String entmStartTime;
	private String entmTravelTime;
	private String entmDuration;
	private LocationModel loc;
	
	public EntertainmentModel(String entmName,String entmStartTime,String entmTravelTime,String entmDuration,LocationModel loc){
		this.entmName = entmName;
		this.entmStartTime = entmStartTime;
		this.entmTravelTime = entmTravelTime;
		this.entmDuration = entmDuration;
		this.loc = loc;
	}
	
	public EntertainmentModel(){
		
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
	
	public void setEntmStartTime(String entmStartTime){
		this.entmStartTime = entmStartTime;
	}
	
	public String getEntmStartTime(){
		return entmStartTime;
	}

	public String getEntmTravelTime() {
		return entmTravelTime;
	}

	public void setEntmTravelTime(String entmTravelTime) {
		this.entmTravelTime = entmTravelTime;
	}

	public String getEntmDuration() {
		return entmDuration;
	}

	public void setEntmDuration(String entmDuration) {
		this.entmDuration = entmDuration;
	}

}
