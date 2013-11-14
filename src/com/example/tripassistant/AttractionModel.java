package com.example.tripassistant;

public class AttractionModel {

	private String attName;
	private int attTravelTime;
	private int attDuration;
	private LocationModel loc;

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	

	public int getAttTravelTime() {
		return attTravelTime;
	}

	public void setAttTravelTime(int attTravelTime) {
		this.attTravelTime = attTravelTime;
	}

	public int getAttDuration() {
		return attDuration;
	}

	public void setAttDuration(int attDuration) {
		this.attDuration = attDuration;
	}

}
