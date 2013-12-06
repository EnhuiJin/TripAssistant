package com.example.tripassistant;

public class UserInfoModel {
	
	public static final String PREF_TOKEN = "token"; 
	public static final String PREF_PERIOD = "period";
	private String uname;
	private String email;
	private String pw;
	private String city;
	
	public UserInfoModel(){
		
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
