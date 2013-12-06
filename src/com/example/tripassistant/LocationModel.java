package com.example.tripassistant;

public class LocationModel {

	private double longitude;
	private double latitude;
	private String address;
	
	public LocationModel(double longitude,double latitude,String address){
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		
	}
	
	public LocationModel(){
		
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}

}
