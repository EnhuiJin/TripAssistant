package com.example.tripassistant;

import java.util.List;

public class ScheduleModel {

	private String schStartTime;
	private String schEndTime;
	private List<RestaurantModel> restList;
	private List<EntertainmentModel> entmList;
	private List<AttractionModel> attList;

	public String getSchStartTime() {
		return schStartTime;
	}

	public void setSchStartTime(String schStartTime) {
		this.schStartTime = schStartTime;
	}

	public String getSchEndTime() {
		return schEndTime;
	}

	public void setSchEndTime(String schEndTime) {
		this.schEndTime = schEndTime;
	}

	public List<RestaurantModel> getRestList() {
		return restList;
	}

	public void setRestList(List<RestaurantModel> restList) {
		this.restList = restList;
	}

	public List<EntertainmentModel> getEntmList() {
		return entmList;
	}

	public void setEntmList(List<EntertainmentModel> entmList) {
		this.entmList = entmList;
	}

	public List<AttractionModel> getAttList() {
		return attList;
	}

	public void setAttList(List<AttractionModel> attList) {
		this.attList = attList;
	}

}
