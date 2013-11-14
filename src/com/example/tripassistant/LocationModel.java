package com.example.tripassistant;

public class LocationModel {

	private long longitude;
	private long latitude;
	private String address;
	
	public LocationModel(long longitude,long latitude,String address){
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}

}
